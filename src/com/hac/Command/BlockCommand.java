package com.hac.Command;

import com.hac.IO.IOManager;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hac.Exception.ErrorMessages.CANNOT_READ_BLOCKED_TXT;
import static com.hac.Exception.ErrorMessages.CANNOT_WRITE_BLOCKED_TXT;
import static com.hac.Main.BLOCKED_URLS_FILE;
import static com.hac.Validator.CommandLineParser.URL_KEY;

/**
 * The BlockCommand class represents the "block url" command in the commands.
 * This command add URL to a list of blocked URLs.
 */
public class BlockCommand implements Command {
    /**
     * The singleton instance of the BlockCommand class.
     */
    private static final BlockCommand instance = new BlockCommand();

    /**
     * Private constructor to enforce singleton pattern.
     */
    private BlockCommand(){}

    /**
     * @return the singleton instance of the BlockCommand class
     */
    public static BlockCommand getInstance() {
        return instance;
    }

    /**
     * Executes the "block" command by blocking the URL specified in the command line.
     * The URL is added to a list of blocked URLs stored in a file.
     *
     * @param CommandLine a Map of key-value pairs representing the command-line arguments
     * @throws Exception if an error occurs while blocking the URL, such as being unable to write to the file
     */
    @Override
    public void execute(Map<String, String>  CommandLine) throws Exception {
        String urlToBlock = CommandLine.get(URL_KEY);

        // Open the blocked.txt file for appending
        File blockedFile = new File(BLOCKED_URLS_FILE);
        if (!blockedFile.exists()) {
            // Create the file if it doesn't exist
            blockedFile.createNewFile();
        }

        // Read the contents of the blocked.txt file into a list
        List<String> blockedUrls;
        IOManager ioManager = IOManager.getInstance();
        try {
            String fileContent = ioManager.readFromFile(BLOCKED_URLS_FILE);
            blockedUrls = Stream.of(fileContent.split("\\n")).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException(CANNOT_READ_BLOCKED_TXT);
        }

        // Check if the URL to exist in the list
        if (blockedUrls.contains(urlToBlock)) {
            // URL is blocked, exit silently
            return;
        }



        // Write the URL to the blocked.txt file
        try {
            ioManager.appendToFile(BLOCKED_URLS_FILE, urlToBlock.toString());
        } catch (IOException e) {
            throw new IOException(CANNOT_WRITE_BLOCKED_TXT);
        }
    }
}
