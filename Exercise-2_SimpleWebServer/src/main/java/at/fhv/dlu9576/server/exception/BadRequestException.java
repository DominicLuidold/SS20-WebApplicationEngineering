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
public class BadRequestException extends Exception {
    private static final long serialVersionUID = 7047779152584394921L;

    /**
     * Constructs an {@code BadRequestException} with {@code null}
     * as its error detail message.
     */
    public BadRequestException() {
        super();
    }

    /**
     * Constructs an {@code BadRequestException} with the specified detail message.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public BadRequestException(String message) {
        super(message);
    }
}
