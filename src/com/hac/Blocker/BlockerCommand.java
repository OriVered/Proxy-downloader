/**
 * This package provides the implementation of various URL blocking options.
 */
package com.hac.Blocker;

import com.hac.Exception.InvalidURLException;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * The BlockerCommand interface defines the structure for URL blocking commands.
 * Classes implementing this interface should provide a specific URL blocking mechanism.
 */
public interface BlockerCommand {

    /**
     * Executes the blocking command on the given URL.
     *
     * @param urlStr The string representation of the URL to be checked.
     * @return HttpURLConnection An open connection to the reachable and non-blocked URL.
     * @throws Exception If any exception occurs during the execution of the blocking command.
     */
    HttpURLConnection execute(String urlStr) throws Exception;

}
