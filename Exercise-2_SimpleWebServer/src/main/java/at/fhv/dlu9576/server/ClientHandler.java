/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.server;

import java.io.*;
import java.net.Socket;

/**
 * The {@code ClientHandler} class is responsible for handling
 * HTTP requests performed by a client.
 *
 * @author Dominic Luidold
 * @version 1.0
 */
public class ClientHandler implements Runnable {
    private final Socket _connection;
    private final BufferedReader _in;
    private final PrintWriter _out;
    private final File _webRootDirectory;

    /**
     * Create a ClientHandler instance.
     *
     * @param connection       The connection to use
     * @param in               The Reader to read from
     * @param out              The Writer to use
     * @param webRootDirectory The web root directory to use
     */
    public ClientHandler(Socket connection, BufferedReader in, PrintWriter out, File webRootDirectory) {
        _connection = connection;
        _in = in;
        _out = out;
        _webRootDirectory = webRootDirectory;
    }

    /**
     * Handle client request.
     */
    @Override
    public void run() {
        try {
            /* Read input */
            StringBuilder httpRequest = new StringBuilder();
            String line;
            while ((line = _in.readLine()) != null && !line.isEmpty()) {
                httpRequest.append(line).append("\r\n");
            }

            /* Get file from web root directory */
            File file = new File(_webRootDirectory, Server.getResourcePathFromRequest(httpRequest.toString()));
            BufferedReader fileReader = new BufferedReader(new FileReader(file));

            /* Send HTTP header */
            Server.writeHTTPHeader(Server.HTTPStatus.OK, _out, file);

            /* Send file content */
            while ((line = fileReader.readLine()) != null) {
                _out.write(line);
                _out.write("\r\n");
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            Server.writeHTTPHeader(Server.HTTPStatus.NOT_FOUND, _out, null);
            _out.write("<html><body><h1>404 - Not Found</h1></body></html>\r\n");
        } catch (IOException e) {
            Server.writeHTTPHeader(Server.HTTPStatus.INTERNAL_SERVER_ERROR, _out, null);
            _out.write("<html><body><h1>500 - Internal Server Error</h1></body></html>\r\n");
        } finally {
            _out.flush();
            try {
                if (_connection != null) {
                    _connection.close();
                }
            } catch (IOException e) {
                System.out.println("An error occurred while closing the connection of <" + _connection + ">");
                System.out.println("Exiting..");
                System.exit(1);
            }
        }
    }
}
