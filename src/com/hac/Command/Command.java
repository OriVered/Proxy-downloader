package com.hac.Command;

import java.util.Map;

/**
 * The Command interface represents a command that can be executed by the command-line tool.
 */
public interface Command {
    /**
     * Executes the command with the specified command-line arguments.
     *
     * @param CommandLine a Map of key-value pairs representing the command-line arguments
     * @throws Exception if an error occurs while executing the command
     */
    void execute(Map<String, String> CommandLine) throws Exception;
}
