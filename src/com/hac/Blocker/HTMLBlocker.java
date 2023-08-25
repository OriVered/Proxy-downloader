/**
 * This package provides the implementation of various URL blocking options.
 */
package com.hac.Blocker;

import java.net.HttpURLConnection;
import static com.hac.Exception.ErrorMessages.DENIED;

/**
 * The HTMLBlocker class extends BlockerDecorator and implements the
 * BlockerCommand interface, providing a URL blocking mechanism by
 * checking for the presence of HTML content in the response header.
 */
public class HTMLBlocker extends BlockerDecorator implements BlockerCommand {
    static final String HTML_CONTENT = "text/html";

    /**
     * Constructor for the HTMLBlocker class.
     *
     * @param blockerCommand The BlockerCommand to be decorated with the HTMLBlocker.
     */
    public HTMLBlocker(BlockerCommand blockerCommand) {
        super(blockerCommand);
    }

    /**
     * Executes the decorated blocking command first, then checks for HTML content
     * in the response header. If HTML content is not found, the connection is returned.
     *
     * @param urlStr The string representation of the URL to be checked.
     * @return HttpURLConnection An open connection to the reachable and non-blocked URL.
     * @throws Exception If HTML content is found in the response header or any other exception occurs.
     */
    @Override
    public HttpURLConnection execute(String urlStr) throws Exception {
        // Connecting to the URL
        HttpURLConnection connection = super.execute(urlStr);
        checkUrlReachability(connection);
        // Blocking HTML content
        String contentType = connection.getContentType();
        if (contentType != null && contentType.contains(HTML_CONTENT)) {
            throw new Exception(DENIED);
        }
        return connection;
    }
}
