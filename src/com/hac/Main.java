package com.hac;
import com.hac.Exception.QuitException;
import com.hac.Command.Command;
import com.hac.Command.CommandManager;

import java.util.Map;
import java.util.Scanner;

import static com.hac.Validator.CommandLineParser.parseCommandLine;

/**
 * The Main class serves as the entry point for a command-line tool designed
 * to download content from a URL while allowing users to block specific content types.
 * The program reads input commands, parses them, and executes the corresponding actions.
 *
 * @author Ori Vered And Damian Tissembaum
 * @version 1.0
 * @since 20.3.2023
 */

public class Main {
    /**
     * Represents file name of Blocked url list.
     */
    public static final String BLOCKED_URLS_FILE = "blocked.txt";

    /**
     * The main method initializes the CommandManager singleton instance
     * and starts a loop that continues until the user chooses to quit.
     * Inside the loop, it performs the following tasks:
     * 1. Prompt the user to input a command and read it using a Scanner object, more info at README.md file
     * 2. Parse the command line using the parseCommandLine() method from the CommandLineParser class.
     *    This method returns a Map containing the parsed command-line arguments.
     * 3. Retrieve the corresponding Command object for the input command using the
     *    CommandManager.getCommand() method.
     * 4. Execute the command with the parsed command-line arguments using the Command.execute() method.
     * 5. Handle different types of exceptions:
     *    - QuitException: When the user enters the "q" command to quit the program, the loop terminates.
     *    - InvalidCommandException: When the user enters an unknown command, the program prints "invalid command".
     *    - InvalidURLException: When the user enters an invalid URL, the program prints "invalid URL".
     *    - InvalidOptionException: When the user enters an invalid option for the download command, the program prints "invalid option".
     *    - IOException: When there is a problem reading or writing files, the program prints "cannot read/write blocked.txt" or "cannot write output file".
     *
     * @param args default param
     */
    public static void main(String[] args) {

        CommandManager commandManager = CommandManager.getInstance();
            try (Scanner scanner = new Scanner(System.in)) {
                boolean shouldQuit = false;
                while (!shouldQuit) {
                    try {
                        //"input a command"
                        String input = scanner.nextLine();

                        //Parse command line for execute, throw Exception if template is wrong
                        Map<String, String>  CommandLine = parseCommandLine(input);

                        //Get command class by command input
                        Command command = commandManager.getCommand(CommandLine.get("command"));

                        //execute rest command line by command input
                        command.execute(CommandLine);

                    } catch (QuitException e) {
                        //Catch executing command q - exit program
                        shouldQuit = true;
                    } catch (Exception e) {
                        //Catch executing command line - client errors
                        System.out.println(e.getMessage());
                    }
                }
            } catch (Exception e) {
                //Catch read command line Exception(std stream)
                System.out.println(e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    //for closing all correct
                } catch (Exception ignored) {
                }
            }
        }
    }
