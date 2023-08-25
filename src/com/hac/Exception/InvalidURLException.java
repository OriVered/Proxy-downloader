package com.hac.Exception;

import static com.hac.Exception.ErrorMessages.INVALID_URL;
/**
 * The InvalidURLException class is a custom exception class that extends the Exception class.
 * It is used to signal that the user has entered wrong url input.
 */
public class InvalidURLException extends Exception {
    /**
     * Constructs a InvalidURLException with the specified detail message.
     */
    public InvalidURLException() {
        super(INVALID_URL);
    }
}
