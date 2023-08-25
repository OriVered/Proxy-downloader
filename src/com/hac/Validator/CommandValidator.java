/**
 * This package provides the implementation of command validation.
 */
package com.hac.Validator;

import com.hac.Exception.InvalidCommandException;
import static com.hac.Command.CommandManager.*;

/**
 * The CommandValidator class provides a method for validating command line input commands.
 */
public class CommandValidator {
    /**
     * Validates the command based on the provided string and length of input.
     *
     * @param command The command as a string.
     * @param length The length of the command line input parts.
     * @return The valid command as a string.
     * @throws InvalidCommandException If an invalid command is encountered.
     */
    public static String validateCommand(String command, int length) throws InvalidCommandException {
        if (length == 3 || length == 4) {
            if (command.equals(DOWNLOAD_COMMAND_KEY)) {
                return command;
            }
        } else if (length == 1) {
            if (command.equals(PRINT_COMMAND_KEY) || command.equals(QUIT_COMMAND_KEY)) {
                return command;
            }
        } else if (length == 2) {
            if (command.equals(BLOCK_COMMAND_KEY) || command.equals(UNBLOCK_COMMAND_KEY)) {
                return command;
            }
        }
        throw new InvalidCommandException();
    }
}
