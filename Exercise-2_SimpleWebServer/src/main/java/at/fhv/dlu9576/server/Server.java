/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.server;

import at.fhv.dlu9576.server.exception.BadRequestException;
import at.fhv.dlu9576.server.exception.MethodNotAllowedException;
import at.fhv.dlu9576.server.exception.UnsupportedHTTPVersionException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code Server} class contains methods for performing operations
 * such as listening to TCP connections or writing HTTP header.
 *
 * @author Dominic Luidold
 * @version 1.1
 */
public class Server {
    /**
     * All currently supported HTTP status codes.
     */
    public enum HTTPStatus {
        OK("HTTP/1.1 200 OK"),
        BAD_REQUEST("HTTP/1.1 400 Bad Request"),
        NOT_FOUND("HTTP/1.1 404 Not Found"),
        METHOD_NOT_ALLOWED("HTTP/1.1 405 Method Not Allowed"),
        INTERNAL_SERVER_ERROR("HTTP/1.1 500 Internal Server Error"),
        UNSUPPORTED_HTTP_VERSION("HTTP/1.1 505 HTTP Version not supported");

        private final String _description;

        HTTPStatus(String description) {
            _description = description;
        }

        /**
         * Get HTTP status description.
         *
         * @return The description
         */
        public String getDescription() {
            return _description;
        }
    }

    /**
     * Directory storing files and resources.
     */
    private File _webRootDirectory;

    /**
     * Create a Server instance.
     *
     * @param webRootDirectory The directory storing files/resources
     */
    public Server(File webRootDirectory) {
        _webRootDirectory = webRootDirectory;
    }

    /**
     * Listen to incoming TCP connections on specified port.
     * <p>
     * Client requests get handled in separate threads.
     *
     * @param port The port to listen to
     */
    public void run(int port) {
        /*  Listen to TCP connections on specified port */
        try (ServerSocket socket = new ServerSocket(port)) {
            while (true) {
                Socket connection = socket.accept();
                System.out.println("Incoming connection from: " + connection.getInetAddress().getHostAddress());
                /* Handle client request in a separate thread */
                new Thread(
                        new ClientHandler(
                                connection,
                                new BufferedReader(new InputStreamReader(connection.getInputStream())),
                                new PrintWriter(connection.getOutputStream()),
                                _webRootDirectory
                        )
                ).start();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while starting the server: " + e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Analyze a HTTP request regarding its validity and requirements based on HTTP version.
     *
     * @param httpRequest the HTTP request to analyze
     *
     * @throws BadRequestException             if request is invalid or does not match all requirements
     * @throws MethodNotAllowedException       if HTTP method is not allowed
     * @throws UnsupportedHTTPVersionException if HTTP version is not supported
     */
    public static void analyzeHTTPRequest(String httpRequest)
            throws BadRequestException, MethodNotAllowedException, UnsupportedHTTPVersionException {
        String[] httpRequestParts = httpRequest.split("\r?\n", 2);

        /* Require valid HTTP request line */
        String[] requestLine = httpRequestParts[0].split(" ");
        if (requestLine.length != 3) {
            throw new BadRequestException();
        }

        /* Require HTTP "GET" as method */
        String httpMethod = requestLine[0];
        if (httpMethod.isEmpty()) {
            throw new BadRequestException();
        } else if (!httpMethod.equals("GET")) {
            throw new MethodNotAllowedException("Method not allowed");
        }

        /* Check for given HTTP/1.0 or HTTP/1.1 requirements */
        if (!requestLine[2].equals("HTTP/1.0") && !requestLine[2].equals("HTTP/1.1")) {
            throw new UnsupportedHTTPVersionException();
        } else if (requestLine[2].equals("HTTP/1.1") && httpRequestParts.length < 2) {
            throw new BadRequestException("Required header fields missing");
        } else if (requestLine[2].equals("HTTP/1.0")) {
            return;
        }

        /* Analyze HTTP "Host" header field, if HTTP/1.1 */
        String httpHeader = httpRequestParts[1];
        Matcher m = Pattern.compile("(?i)(Host:).+\r?\n").matcher(httpHeader);
        String hostField = "";
        if (m.find()) {
            hostField = m.group();
        }
        hostField = hostField.replaceAll("Host: ", "").replaceAll("\r?\n", "");
        if (hostField.isEmpty() || (!hostField.equals("localhost") && !hostField.equals("localhost:8080"))) {
            throw new BadRequestException("Unknown host");
        }
    }

    /**
     * Extract the resource path (document reference) from an HTTP request.
     *
     * @param httpRequest The HTTP request
     *
     * @return the requested resource path
     */
    public static String getResourcePathFromRequest(String httpRequest) {
        String requestLine = httpRequest.split("\r?\n", 2)[0];
        String resource = requestLine.replaceFirst("^GET ((https?://)?[^/ ]+)?", "").replaceAll("(\\.\\./)+", "").split(" ")[0];
        if (requestLine.isEmpty() || resource.isEmpty() || resource.equals("/")) {
            return "/index.html";
        }
        return resource;
    }

    /**
     * Write HTTP header fields based on HTTP status (see {@link HTTPStatus}).
     *
     * @param status The HTTP status to use
     * @param out    The Writer to use
     * @param file   The file to get information
     */
    public static void writeHTTPHeader(HTTPStatus status, Writer out, File file) {
        DateFormat dateFormat = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss z");
        try {
            if (status == HTTPStatus.OK) {
                out.write(status.getDescription() + "\r\n");
                out.write("Last-Modified: " + dateFormat.format(file.lastModified()) + "\r\n");
                out.write("Content-Length: " + file.length() + "\r\n");
            } else {
                out.write(status.getDescription() + "\r\n");
            }
            out.write("Date: " + dateFormat.format(new Date()) + "\r\n");
            out.write("Server: SimpleWebServer/1.1\r\n");
            out.write("Content-Type: text/html\r\n\r\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing HTTP header: " + e.getMessage());
            System.out.println("Exiting..");
            System.exit(1);
        }
    }
}
