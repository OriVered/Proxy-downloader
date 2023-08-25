package com.hac.Exception;

/**
 * The ErrorMessages class is a utility class that contains constant strings for
 * various error messages used throughout the application. These constants help to
 * maintain consistency in error reporting and make it easier to update or modify
 * error messages in the future.
 */
public final class ErrorMessages {

    /**
     * Represents an error message that occurs when an invalid command is entered in the program.
     */
    public static final String INVALID_COMMAND = "invalid command";

    /**
     * Represents an error message that occurs when an invalid URL is entered in the program.
     */
    public static final String INVALID_URL = "invalid URL";

    /**
     * Represents an error message that occurs when an invalid option is selected in the program.
     */
    public static final String INVALID_OPTION = "invalid option";

    /**
     * Represents an error message that occurs when a user is denied access to a resource or feature in the program.
     */
    public static final String DENIED = "denied";

    /**
     * Represents an error message that occurs when the program cannot write output to a file.
     */
    public static final String CANNOT_WRITE_OUTPUT_FILE = "cannot write output file";

    /**
     * Represents an error message that occurs when the program cannot read the 'blocked.txt' file.
     */
    public static final String CANNOT_READ_BLOCKED_TXT = "cannot read blocked.txt";

    /**
     * Represents an error message that occurs when the program cannot write to the 'blocked.txt' file.
     */
    public static final String CANNOT_WRITE_BLOCKED_TXT = "cannot write blocked.txt";


    /**
     * Private constructor to prevent the creation of objects of this class.
     */
    private ErrorMessages() {}
}
