package com.hac.Command;

import com.hac.IO.IOManager;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hac.Exception.ErrorMessages.CANNOT_READ_BLOCKED_TXT;
import static com.hac.Main.BLOCKED_URLS_FILE;

/**
 * The PrintCommand class represents the "print" command in the commands .
 * This command prints the list of blocked URLs to the console.
 */
public class PrintCommand implements Command {
    /**
     * The singleton instance of the PrintCommand class.
     */
    private static final PrintCommand instance = new PrintCommand();
    /**
     * Private constructor to enforce singleton pattern.
     */
    private PrintCommand() {}

    /**
     * @return the singleton instance of the PrintCommand class
     */
    public static PrintCommand getInstance() {
        return instance;
    }

    /**
     * Executes the "print" command with the specified command-line arguments.
     * This method is overloaded to implement the Command interface.
     *
     * @param CommandLine the command-line arguments
     * @throws IOException if an error occurs while reading the blocked URLs file
     */
    @Override
    public void execute(Map<String, String> CommandLine) throws IOException {
        // Read the contents of the blocked.txt file into a list
        List<String> blockedUrls;
        try {
            IOManager ioManager = IOManager.getInstance();
            String fileContent = ioManager.readFromFile(BLOCKED_URLS_FILE);
            blockedUrls = Stream.of(fileContent.split("\\n")).collect(Collectors.toList());

        } catch (IOException e) {
            throw new IOException(CANNOT_READ_BLOCKED_TXT);
        }


        // Print each blocked site in the sorted list, if is file empty - silence
        if (!blockedUrls.isEmpty() && !blockedUrls.get(0).trim().isEmpty()) {
            for (String blockedUrl : blockedUrls) {
                System.out.println(blockedUrl);
            }
        }
        System.out.println();
    }
}
