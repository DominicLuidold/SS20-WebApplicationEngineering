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
public class MethodNotAllowedException extends Exception {
    private static final long serialVersionUID = -8656206107012779558L;

    /**
     * Constructs an {@code MethodNotAllowedException} with {@code null}
     * as its error detail message.
     */
    public MethodNotAllowedException() {
        super();
    }

    /**
     * Constructs an {@code MethodNotAllowedException} with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public MethodNotAllowedException(String message) {
        super(message);
    }
}
