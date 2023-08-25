/**
 * This package provides the basic implementation of a URL blocker.
 */
package com.hac.Blocker;

import com.hac.Exception.InvalidURLException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * BasicBlocker class provides a basic implementation of a URL blocker
 * by checking the reachability of a given URL.
 */
public class BasicBlocker implements BlockerCommand {

    /**
     * Executes the blocking command by checking the reachability of a given URL.
     *
     * @param urlStr The string representation of the URL to be checked.
     * @return HttpURLConnection An open connection to the reachable URL.
     * @throws Exception If the URL is not reachable or any other exception occurs.
     */
    @Override
    public HttpURLConnection execute(String urlStr) throws Exception {
        // Connecting to the URL
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        return connection;
    }
}
