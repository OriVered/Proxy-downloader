package com.hac.Command;

import com.hac.IO.IOManager;
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
 * The UnblockCommand class represents the "unblock url" command in the commands.
 * This command removes a URL from the list of blocked URLs.
 */
public class UnblockCommand implements Command {
    /**
     * The singleton instance of the UnblockCommand class.
     */
    private static final UnblockCommand instance = new UnblockCommand();

    /**
     * Private constructor to enforce singleton pattern.
     */
    private UnblockCommand() {}

    /**
     * @return the singleton instance of the UnblockCommand class
     */
    public static UnblockCommand getInstance() {
        return instance;
    }

    /**
     * Executes the "unblock" command by unblocking the URL specified in the command line.
     * The URL is removed from the list of blocked URLs stored in a file.
     *
     * @param CommandLine a Map of key-value pairs representing the command-line arguments
     * @throws Exception if an error occurs while unblocking the URL, such as being unable to read or write to the file
     */
    public void execute(Map<String, String> CommandLine) throws Exception {
        // get url from pasred command line
        String urlToUnblock = CommandLine.get(URL_KEY);

        // Read the contents of the blocked.txt file into a list
        List<String> blockedUrls;
        IOManager ioManager = IOManager.getInstance();
        try {
            String fileContent = ioManager.readFromFile(BLOCKED_URLS_FILE);
            blockedUrls = Stream.of(fileContent.split("\\n")).collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException(CANNOT_READ_BLOCKED_TXT);
        }

        // Check if the URL to unblock is in the list
        if (!blockedUrls.contains(urlToUnblock)) {
            // URL is not blocked, exit silently
            return;
        }

        // Remove the URL from the list and update the file
        blockedUrls.remove(urlToUnblock);
        try {
            String outputContent = String.join("\n", blockedUrls);
            ioManager.writeToFile(BLOCKED_URLS_FILE, outputContent);
        } catch (IOException e) {
            throw new IOException(CANNOT_WRITE_BLOCKED_TXT);
        }
    }
}
