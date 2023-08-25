/**
 * This package provides the implementation of various URL blocking options.
 */
package com.hac.Blocker;

import java.net.HttpURLConnection;
import static com.hac.Exception.ErrorMessages.DENIED;

/**
 * The CookieBlocker class extends BlockerDecorator and implements the
 * BlockerCommand interface, providing a URL blocking mechanism by
 * checking for the presence of cookies in the response header.
 */
public class CookieBlocker extends BlockerDecorator implements BlockerCommand {
    static final String COOKIE_CONTENT = "Set-Cookie";

    /**
     * Constructor for the CookieBlocker class.
     *
     * @param blockerCommand The BlockerCommand to be decorated with the CookieBlocker.
     */
    public CookieBlocker(BlockerCommand blockerCommand) {
        super(blockerCommand);
    }

    /**
     * Executes the decorated blocking command first, then checks for cookies
     * in the response header. If cookies are not found, the connection is returned.
     *
     * @param urlStr The string representation of the URL to be checked.
     * @return HttpURLConnection An open connection to the reachable and non-blocked URL.
     * @throws Exception If cookies are found in the response header or any other exception occurs.
     */
    @Override
    public HttpURLConnection execute(String urlStr) throws Exception {
        // Connecting to the URL
        HttpURLConnection connection = super.execute(urlStr);
        checkUrlReachability(connection);
        String cookieHeader = connection.getHeaderField(COOKIE_CONTENT);
        if (cookieHeader != null) {
            throw new Exception(DENIED);
        }
        return connection;
    }
}
