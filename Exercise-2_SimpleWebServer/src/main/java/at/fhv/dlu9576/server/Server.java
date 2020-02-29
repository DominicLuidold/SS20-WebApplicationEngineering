/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The {@code Server} class contains methods for performing operations
 * such as listening to TCP connections or writing HTTP header.
 *
 * @author Dominic Luidold
 * @version 1.0
 */
public class Server {
    /**
     * All currently supported HTTP status codes.
     */
    public enum HTTPStatus {
        OK("HTTP/1.1 200 OK"),
        NOT_FOUND("HTTP/1.1 404 Not Found"),
        INTERNAL_SERVER_ERROR("HTTP/1.1 500 Internal Server Error");

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
     * Extract the resource path (document reference) from an HTTP request.
     * <p>
     * For simplicity, the server returns "index.html" even in case of a
     * bad request ({@code HTTP 400}) or a method that is not allowed ({@code HTTP 405}).
     *
     * @param httpRequest The HTTP request
     *
     * @return the requested resource path
     */
    public static String getResourcePathFromRequest(String httpRequest) {
        String requestLine = httpRequest.split("\r?\n", 2)[0];
        String resource = requestLine.replaceFirst("GET ", "").replaceAll("(\\.\\./)+", "").split(" ")[0];
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
            switch (status) {
                case OK:
                    out.write(HTTPStatus.OK.getDescription() + "\r\n");
                    out.write("Last-Modified: " + dateFormat.format(file.lastModified()) + "\r\n");
                    out.write("Content-Length: " + file.length() + "\r\n");
                    break;
                case NOT_FOUND:
                    out.write(HTTPStatus.NOT_FOUND.getDescription() + "\r\n");
                    break;
                case INTERNAL_SERVER_ERROR:
                    out.write(HTTPStatus.INTERNAL_SERVER_ERROR.getDescription() + "\r\n");
                    break;
            }
            out.write("Date: " + dateFormat.format(new Date()) + "\r\n");
            out.write("Server: SimpleWebServer/1.0\r\n");
            out.write("Content-Type: text/html\r\n\r\n");
        } catch (IOException e) {
            System.out.println("An error occurred while writing HTTP header: " + e.getMessage());
            System.out.println("Exiting..");
            System.exit(1);
        }
    }
}
