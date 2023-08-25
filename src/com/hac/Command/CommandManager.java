package com.hac.Command;

import java.util.HashMap;
import java.util.Map;

/**
 * The CommandManager class is responsible for managing the available commands in the command-line tool.
 */
public class CommandManager {
    /**
     * The singleton instance of the CommandManager class.
     */
    private static final CommandManager instance = new CommandManager();
    /**
     * A Map of command names to Command objects.
     */
    private final Map<String, Command> commands;

    /**
     * The key string for the block command.
     */
    public static final String BLOCK_COMMAND_KEY = "b";
    /**
     * The key string for the unblock command.
     */
    public static final String UNBLOCK_COMMAND_KEY = "u";
    /**
     * The key string for the unblock command.
     */
    public static final String PRINT_COMMAND_KEY = "p";
    /**
     * The key string for the print command.
     */
    public static final String DOWNLOAD_COMMAND_KEY = "d";
    /**
     * The key string for the quit command.
     */
    public static final String QUIT_COMMAND_KEY = "q";

    /**
     * Private constructor to enforce singleton pattern.
     * Initializes the commands Map with the available commands.
     */

    private CommandManager() {
        commands = new HashMap<String, Command>();
        commands.put(BLOCK_COMMAND_KEY, BlockCommand.getInstance());
        commands.put(UNBLOCK_COMMAND_KEY, UnblockCommand.getInstance());
        commands.put(PRINT_COMMAND_KEY, PrintCommand.getInstance());
        commands.put(DOWNLOAD_COMMAND_KEY, DownloadCommand.getInstance());
        commands.put(QUIT_COMMAND_KEY, QuitCommand.getInstance());
    }

    /**
     * @return the singleton instance of the CommandManager class
     */
    public static CommandManager getInstance() {
        return instance;
    }

    /**
     * @param commandName the name of the command to retrieve
     * @return the Command object corresponding to the specified command name
     * @throws Exception if the specified command name is invalid and does not correspond to a Command object
     */
    public Command getCommand(String commandName) throws Exception {
        var command = commands.get(commandName);
        if (command != null)
            return command;
        else
            throw new Exception("Invalid command");
    }
}
