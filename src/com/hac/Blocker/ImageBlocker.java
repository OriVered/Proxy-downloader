/**
 * This package provides the implementation of various URL blocking options.
 */
package com.hac.Blocker;

import java.net.HttpURLConnection;
import static com.hac.Exception.ErrorMessages.DENIED;

/**
 * The ImageBlocker class extends BlockerDecorator and implements the
 * BlockerCommand interface, providing a URL blocking mechanism by
 * checking for the presence of image content in the response header.
 */
public class ImageBlocker extends BlockerDecorator implements BlockerCommand {
    static final String IMAGE_CONTENT = "image/";

    /**
     * Constructor for the ImageBlocker class.
     *
     * @param blockerCommand The BlockerCommand to be decorated with the ImageBlocker.
     */
    public ImageBlocker(BlockerCommand blockerCommand) {
        super(blockerCommand);
    }

    /**
     * Executes the decorated blocking command first, then checks for image content
     * in the response header. If image content is not found, the connection is returned.
     *
     * @param urlStr The string representation of the URL to be checked.
     * @return HttpURLConnection An open connection to the reachable and non-blocked URL.
     * @throws Exception If image content is found in the response header or any other exception occurs.
     */
    @Override
    public HttpURLConnection execute(String urlStr) throws Exception {
        // Connecting to the URL
        HttpURLConnection connection = super.execute(urlStr);
        checkUrlReachability(connection);
        // Blocking image content
        String contentType = connection.getContentType();
        if (contentType != null && contentType.contains(IMAGE_CONTENT)) {
            throw new Exception(DENIED);
        }
        return connection;
    }
}
