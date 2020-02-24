/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.browser;

import java.io.*;
import java.net.IDN;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The {@code Browser} class contains methods for performing the most basic
 * operations such as making an HTTP request or validating a URL.
 *
 * @author Dominic Luidold
 * @version 1.0
 */
public class Browser {
    /**
     * All currently available HTTP methods.
     */
    public enum AvailableMethods {
        GET,
    }

    /**
     * Open a TCP connection to a host and send an {@code HTTP/1.0} request.
     *
     * @param method The method to use in the request
     * @param url    The URL to make the request
     * @param host   The host to connect
     *
     * @return a String containing the HTTP response of the request, empty when undefined errors occur
     */
    public static String performHttpRequest(AvailableMethods method, String host, String url) {
        /* Print info message containing domain name and host IP address */
        try {
            System.out.println("Connecting to Server <" + host + "> at <" + InetAddress.getByName(host).getHostAddress() + ">");
        } catch (UnknownHostException e) {
            System.out.println("An error occurred while resolving the host address (IP address) of <" + host + ">");
            System.out.println("Trying to send HTTP request anyway..");
        }

        StringBuilder httpResponse = new StringBuilder();
        /* Open TCP connection to host */
        try (Socket socket = new Socket(IDN.toASCII(host), 80)) {
            /* Generate HTTP/1.0 request */
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            out.write(method + " " + url + " HTTP/1.0");
            out.write("\r\n\r\n"); // Use write method instead of println to ensure cross-platform compatibility
            out.flush();

            /* Write HTTP response into StringBuilder */
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            int c;
            while ((c = in.read()) != -1) {
                httpResponse.append((char) c);
            }
            in.close();
        } catch (UnknownHostException e) {
            System.out.println("An error occurred while connecting to <" + host + ">");
            System.out.println(e.toString());
            System.exit(1);
        } catch (IOException e) {
            System.out.println("I/O error: " + e.getMessage());
            System.exit(1);
        }
        return httpResponse.toString();
    }

    /**
     * Remove the HTTP header from a response.
     *
     * @param httpResponse The HTTP response to edit
     *
     * @return HTTP response w/o header (e.g. plain HTML)
     */
    public static String removeHTTPHeader(String httpResponse) {
        /* Split HTTP response on first empty line */
        String[] result = httpResponse.split("(\r?\n){2}|\n{2}", 2);

        /* If response is empty or has nothing apart from header, exit programme */
        if (httpResponse.length() == 0 || result.length < 2 || result[1].isEmpty()) {
            System.out.println("An error occurred while loading the content");
            System.out.println("A possible redirect (HTTP 301, 302, 307, 308) may be the cause");
            System.exit(1);
        }

        return result[1];
    }

    /**
     * Determine whether a URL is valid.
     * <p>
     * Source of regex: https://stackoverflow.com/a/163398
     *
     * @param url The URL to validate
     *
     * @return true if URL is valid, false otherwise
     */
    public static boolean isValidURL(String url) {
        return url.matches("^https?://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");
    }

    /**
     * Extract the host from a URL.
     * <p>
     * Requirement for this method is a valid URL, see {@link #isValidURL(String url)}.
     *
     * @param url The URL, must be valid
     *
     * @return the host of a URL
     */
    public static String getHostFromURL(String url) {
        return url.replaceFirst("^https?://", "").split("/", 2)[0];
    }

    /**
     * Extract the document reference from a URL.
     * <p>
     * Requirement for this method is a valid URL, see {@link #isValidURL(String url)}.
     * Not used in the current version of SimpleWebBrowser but required by specifications.
     *
     * @param url The URL, must be valid
     *
     * @return the document reference of a URL, empty when no reference is given
     */
    public static String getDocumentReferenceFromURL(String url) {
        String[] urlParts = url.replaceFirst("^https?://", "").split("/", 2);
        if (urlParts.length > 1) {
            return urlParts[1];
        }
        return "";
    }
}
