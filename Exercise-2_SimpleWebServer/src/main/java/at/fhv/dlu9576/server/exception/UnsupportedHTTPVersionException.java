/*
 * Dominic Luidold
 *
 * @license https://github.com/DominicLuidold/FHV-WebApplicationEngineering/blob/master/LICENSE (MIT License)
 */
package at.fhv.dlu9576.server.exception;

/**
 * @author Dominic Luidold
 * @version 1.0
 */
public class UnsupportedHTTPVersionException extends Exception {
    private static final long serialVersionUID = 6406475482709404082L;

    /**
     * Constructs an {@code UnsupportedHTTPVersionException} with {@code null}
     * as its error detail message.
     */
    public UnsupportedHTTPVersionException() {
        super();
    }

    /**
     * Constructs an {@code UnsupportedHTTPVersionException} with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public UnsupportedHTTPVersionException(String message) {
        super(message);
    }
}