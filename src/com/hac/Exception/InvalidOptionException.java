package com.hac.Exception;

import static com.hac.Exception.ErrorMessages.INVALID_OPTION;
/**
 * The InvalidOptionException class is a custom exception class that extends the Exception class.
 * It is used to signal that the user has entered wrong denying option input.
 */
public class InvalidOptionException extends Exception {
    /**
     * Constructs a InvalidOptionException with the specified detail message.
     */
    public InvalidOptionException() {
        super(INVALID_OPTION);
    }
}
