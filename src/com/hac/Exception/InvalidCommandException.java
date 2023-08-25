package com.hac.Exception;

import static com.hac.Exception.ErrorMessages.INVALID_COMMAND;

/**
 * The InvalidCommandException class is a custom exception class that extends the Exception class.
 * It is used to signal that the user has entered wrong command input.
 */
public class InvalidCommandException extends Exception {
    /**
     * Constructs a InvalidCommandException with the specified detail message.
     */
    public InvalidCommandException() {
        super(INVALID_COMMAND);
    }
}
