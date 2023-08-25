package com.hac.Command;

import com.hac.Exception.QuitException;

import java.util.Map;

/**
 * The QuitCommand class represents the "quit" command in the commands.
 * This command quits the program.
 */
public class QuitCommand implements Command {
    /**
     * The singleton instance of the QuitCommand class.
     */
    private static QuitCommand instance = new QuitCommand();

    /**
     * Private constructor to enforce singleton pattern.
     */
    private QuitCommand() {
    }

    /**
     * @return the singleton instance of the QuitCommand class
     */
    public static QuitCommand getInstance() {
        return instance;
    }

    /**
     * Executes the "quit" command with the specified command-line arguments.
     * This method is overloaded to implement the Command interface.
     *
     * @param CommandLine the command-line arguments
     * @throws QuitException if the command is executed successfully, this exception is thrown to exit the tool
     */
    @Override
    public void execute(Map<String, String> CommandLine) throws QuitException {
        throw new QuitException();
    }
}
