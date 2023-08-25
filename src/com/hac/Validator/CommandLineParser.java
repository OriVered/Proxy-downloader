/**
 * This package provides the implementation of command line parsing and validation.
 */
package com.hac.Validator;

import com.hac.Exception.InvalidCommandException;
import java.util.HashMap;
import java.util.Map;

import static com.hac.Validator.CommandValidator.validateCommand;

/**
 * The CommandLineParser class provides methods for parsing and validating command line input.
 */
public class CommandLineParser {
    /**
     * The command key for the parse Command Line in the Map.
     */
    public static final String COMMAND_KEY = "command";
    /**
     * The options key for the parse Command Line in the Map.
     */
    public static final String OPTIONS_KEY = "options";
    /**
     * The url key for the parse Command Line in the Map.
     */
    public static final String URL_KEY = "url";
    /**
     * The output File Name key for the parse Command Line in the Map.
     */
    public static final String OUTPUT_FILE_NAME_KEY = "outputFileName";

    /**
     * Parses the command line input and returns a map of the parsed components.
     *
     * @param commandLine The command line input as a string.
     * @return A map containing the parsed command, options, URL, and output file name.
     * @throws Exception If an invalid command or input is encountered.
     */
    public static Map<String, String> parseCommandLine(String commandLine) throws Exception {
        Map<String, String> result = new HashMap<>();

        String[] parts = commandLine.split("\\s+");
        String command = validateCommand(parts[0], parts.length);
        switch (parts.length) {
            case 4 -> {
                result.put(COMMAND_KEY, command);
                result.put(OPTIONS_KEY, OptionValidator.getValidOption(parts[1]));
                result.put(URL_KEY, URLValidator.getValidURL(parts[2]));
                result.put(OUTPUT_FILE_NAME_KEY, parts[3]);
            }
            case 3 -> {
                result.put(COMMAND_KEY, command);
                result.put(OPTIONS_KEY, null);
                result.put(URL_KEY, URLValidator.getValidURL(parts[1]));
                result.put(OUTPUT_FILE_NAME_KEY, parts[2]);
            }
            case 2 -> {
                result.put(COMMAND_KEY, command);
                result.put(OPTIONS_KEY, null);
                result.put(URL_KEY, parts[1]);
                result.put(OUTPUT_FILE_NAME_KEY, null);
            }
            case 1 -> {
                result.put
                        (COMMAND_KEY, command);
                result.put(OPTIONS_KEY, null);
                result.put(URL_KEY, null);
                result.put(OUTPUT_FILE_NAME_KEY, null);
            }
            default -> throw new InvalidCommandException();
        }
        return result;
    }
}




