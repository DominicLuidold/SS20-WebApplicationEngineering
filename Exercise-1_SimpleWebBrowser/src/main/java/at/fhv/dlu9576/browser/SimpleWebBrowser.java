/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.browser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The {@code SimpleWebBrowser} class contains methods for getting
 * user input and is used to start the web browsing experience.
 *
 * @author Dominic Luidold
 * @version 1.0
 */
public class SimpleWebBrowser {

    /**
     * Start SimpleWebBrowser.
     *
     * @param args Arguments
     */
    public static void main(String[] args) {
        /* Get URL from user */
        String url = null;
        try {
            url = getURLInput();
        } catch (IOException e) {
            System.out.println("An error occurred while reading the input: " + e.getMessage());
            System.exit(1);
        }

        /* Extract result from HTTP response */
        String result = Browser.removeHTTPHeader(
                Browser.performHttpRequest(Browser.AvailableMethods.GET, Browser.getHostFromURL(url), url)
        );

        /* Print result as plain text */
        System.out.println(result);
        System.out.println("Exiting..");
    }

    /**
     * Get a valid URL from {@code System.in}, provided by user.
     * <p>
     * A valid URL is required, see {@link Browser#isValidURL(String url)}.
     *
     * @return the URL from {@code System.in}
     * @throws IOException if anything goes wrong
     */
    public static String getURLInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean validURLProvided = false;
        String url = null;

        /* Wait until valid URL is provided */
        System.out.println("Please enter a URL:");
        while (!validURLProvided) {
            url = reader.readLine();
            if (Browser.isValidURL(url)) {
                validURLProvided = true;
            } else {
                System.out.println("Please enter a valid URL:");
            }
        }
        return url;
    }
}
