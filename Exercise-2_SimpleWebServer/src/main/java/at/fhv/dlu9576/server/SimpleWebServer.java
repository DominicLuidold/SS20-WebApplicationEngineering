/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The {@code SimpleWebServer} class contains methods for getting
 * user input and is used to start the web server.
 *
 * @author Dominic Luidold
 * @version 1.0
 */
public class SimpleWebServer {
    /**
     * Start SimpleWebServer.
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        /* Get web root directory from user */
        File webRootDirectory = null;
        try {
            webRootDirectory = getDirectoryInput();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the input: " + e.getMessage());
            System.exit(1);
        }

        /* Print info message */
        System.out.println("Starting SimpleWebServer in directory '" + webRootDirectory + "' using port '8080'");

        /* Start web server on port 8080 */
        new Server(webRootDirectory).run(8080);
    }

    /**
     * Get an existing directory from {@code System.in}, provided by user.
     *
     * @return the directory from {@code System.in}
     * @throws IOException if anything goes wrong
     */
    public static File getDirectoryInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean existingDirectoryProvided = false;
        File webRootDirectory = null;

        /* Wait until existing directory is provided */
        System.out.println("Please enter a directory:");
        while (!existingDirectoryProvided) {
            webRootDirectory = new File(reader.readLine());
            if (webRootDirectory.exists() && webRootDirectory.isDirectory()) {
                existingDirectoryProvided = true;
            } else {
                System.out.println("Please enter a valid directory:");
            }
        }
        return webRootDirectory;
    }
}
