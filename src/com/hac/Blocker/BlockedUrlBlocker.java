/**
 * This package provides the implementation of various URL blocking options.
 */
package com.hac.Blocker;

import com.hac.IO.IOManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.hac.Exception.ErrorMessages.CANNOT_READ_BLOCKED_TXT;
import static com.hac.Exception.ErrorMessages.DENIED;
import static com.hac.Main.BLOCKED_URLS_FILE;

/**
 * The BlockedUrlBlocker class extends BlockerDecorator and implements
 * the BlockerCommand interface, providing a URL blocking mechanism by
 * checking if the URL is in the blocked URLs list.
 */
public class BlockedUrlBlocker extends BlockerDecorator implements BlockerCommand {

    /**
     * Constructor for the BlockedUrlBlocker class.
     *
     * @param blockerCommand The BlockerCommand to be decorated with the BlockedUrlBlocker.
     */
    public BlockedUrlBlocker(BlockerCommand blockerCommand) {
        super(blockerCommand);
    }

    /**
     * Executes the blocking command by checking if the provided URL is in the blocked URLs list.
     * If the URL is not blocked, it calls the decorated BlockerCommand's execute method.
     *
     * @param urlStr The string representation of the URL to be checked.
     * @return HttpURLConnection An open connection to the reachable and non-blocked URL.
     * @throws Exception If the URL is blocked or any other exception occurs.
     */
    @Override
    public HttpURLConnection execute(String urlStr) throws Exception {
        HttpURLConnection connection = super.execute(urlStr);
        // Read the contents of the blocked.txt file into a list
        List<String> blockedUrls;
        try {
            IOManager ioManager = IOManager.getInstance();
            String fileContent = ioManager.readFromFile(BLOCKED_URLS_FILE);
            blockedUrls = Stream.of(fileContent.split("\\n")).collect(Collectors.toList());

        } catch (IOException e) {
            throw new Exception(CANNOT_READ_BLOCKED_TXT);
        }

        // Check if the URL is in the block.txt file
        boolean isBlocked = blockedUrls.stream().anyMatch(urlStr::startsWith);
        if (isBlocked) {
            throw new Exception(DENIED);
        }

        // Check if the URL is reachable by the basic blocker
        return connection;
    }
}
