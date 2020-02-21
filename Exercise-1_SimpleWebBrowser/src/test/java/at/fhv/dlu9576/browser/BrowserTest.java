/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.browser;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The {@code BrowserTest} class contains methods for testing the code
 * of the {@link Browser} class using JUnit 5.4.
 *
 * @author Dominic Luidold
 * @version 1.0
 */
class BrowserTest {

    @Test
    void isValidURL_ShouldReturnTrue_IfUrlIsValid() {
        /* Domain as host */
        assertTrue(Browser.isValidURL("http://example.com"));
        assertTrue(Browser.isValidURL("http://example.com/XYZ"));
        assertTrue(Browser.isValidURL("http://example.com/XYZ.html"));
        assertTrue(Browser.isValidURL("http://www.example.com"));
        assertTrue(Browser.isValidURL("http://www.example.com/XYZ"));
        assertTrue(Browser.isValidURL("http://www.example.com/XYZ.html"));

        assertTrue(Browser.isValidURL("https://example.com"));
        assertTrue(Browser.isValidURL("https://example.com/XYZ"));
        assertTrue(Browser.isValidURL("https://example.com/XYZ.html"));
        assertTrue(Browser.isValidURL("https://www.example.com"));
        assertTrue(Browser.isValidURL("https://www.example.com/XYZ"));
        assertTrue(Browser.isValidURL("https://www.example.com/XYZ.html"));

        /* IP address as host */
        assertTrue(Browser.isValidURL("http://192.168.0.1"));
        assertTrue(Browser.isValidURL("http://192.168.0.1/XYZ"));
        assertTrue(Browser.isValidURL("http://192.168.0.1/XYZ.html"));

        assertTrue(Browser.isValidURL("https://192.168.0.1"));
        assertTrue(Browser.isValidURL("https://192.168.0.1/XYZ"));
        assertTrue(Browser.isValidURL("https://192.168.0.1/XYZ.html"));

        /* Other */
        assertTrue(Browser.isValidURL("http://localhost"));
        assertTrue(Browser.isValidURL("http://localhost/XYZ"));
        assertTrue(Browser.isValidURL("http://localhost/XYZ.html"));
        assertTrue(Browser.isValidURL("https://localhost"));
        assertTrue(Browser.isValidURL("https://localhost/XYZ"));
        assertTrue(Browser.isValidURL("https://localhost/XYZ.html"));
    }

    @Test
    void isValidURL_ShouldReturnFalse_IfUrlIsInvalid() {
        /* Domain as host */
        assertFalse(Browser.isValidURL("http:/example.com"));
        assertFalse(Browser.isValidURL("http//example.com"));
        assertFalse(Browser.isValidURL("htp://example.com"));
        assertFalse(Browser.isValidURL("http://example.com!"));

        assertFalse(Browser.isValidURL("https:/example.com"));
        assertFalse(Browser.isValidURL("https//example.com"));
        assertFalse(Browser.isValidURL("htps://example.com"));
        assertFalse(Browser.isValidURL("https://example.com!"));

        /* IP address as host*/
        assertFalse(Browser.isValidURL("http:/192.168.0.1"));
        assertFalse(Browser.isValidURL("http//192.168.0.1"));
        assertFalse(Browser.isValidURL("htp://192.168.0.1"));
        assertFalse(Browser.isValidURL("http://192.168.0.1!"));

        assertFalse(Browser.isValidURL("https:/192.168.0.1"));
        assertFalse(Browser.isValidURL("https//192.168.0.1"));
        assertFalse(Browser.isValidURL("htps://192.168.0.1"));
        assertFalse(Browser.isValidURL("https://192.168.0.1!"));

        /* Other */
        assertFalse(Browser.isValidURL("http://!!"));
    }

    @ParameterizedTest
    @CsvSource({
            "http://www.example.com, www.example.com",
            "https://www.example.com, www.example.com",
            "http://example.com/XYZ.html, example.com",
            "http://192.168.0.1, 192.168.0.1",
            "https://192.168.0.1/XYZ, 192.168.0.1",
            "http://localhost/XYZ.html, localhost"
    })
    void getHostFromURL_ShouldReturnHost(String url, String expectedResult) {
        assertEquals(expectedResult, Browser.getHostFromURL(url));
    }
}
