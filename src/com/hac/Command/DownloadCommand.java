package com.hac.Command;

import com.hac.Blocker.BlockerCommand;
import com.hac.IO.IOManager;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import static com.hac.Blocker.BlockedOption.buildCommands;
import static com.hac.Exception.ErrorMessages.CANNOT_WRITE_OUTPUT_FILE;
import static com.hac.Validator.CommandLineParser.*;

/**
 * The DownloadCommand class represents the "download" command in the commands.
 * This command downloads the content of a URL and saves it to a file.
 */
public class DownloadCommand implements Command {
    /**
     * The singleton instance of the DownloadCommand class.
     */
    private final static DownloadCommand instance = new DownloadCommand();

    /**
     * Private constructor to enforce singleton pattern.
     */
    private DownloadCommand() {
    }

    /**
     * @return the singleton instance of the DownloadCommand class
     */
    public static DownloadCommand getInstance() {
        return instance;
    }

    /**
     * Executes the "download" command with the specified command-line arguments.
     * This method is overloaded to implement the Command interface.
     *
     * @param CommandLine the command-line arguments
     * @throws Exception if an error occurs while executing the command
     */
    @Override
    public void execute(Map<String, String> CommandLine) throws Exception {
        String options = CommandLine.get(OPTIONS_KEY);
        String urlStr = CommandLine.get(URL_KEY);
        String outputFileName = CommandLine.get(OUTPUT_FILE_NAME_KEY);

        //Build and run block options
        BlockerCommand command = buildCommands(options);
        HttpURLConnection connection = command.execute(urlStr);

        // Download content
        try {
            IOManager ioManager = IOManager.getInstance();
            ioManager.streamToFile(connection, outputFileName);
        } catch (IOException e) {
            throw new IOException(CANNOT_WRITE_OUTPUT_FILE);
        }
    }
}
