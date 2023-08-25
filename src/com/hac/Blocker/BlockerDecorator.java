/**
 * This package provides the implementation of various URL blocking options.
 */
package com.hac.Blocker;

import com.hac.Exception.InvalidURLException;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * The BlockerDecorator abstract class implements the BlockerCommand interface
 * and serves as a base class for various URL blocking decorators. It allows
 * for the dynamic combination of different blocking behaviors, utilizing the
 * decorator design pattern.
 */
public abstract class BlockerDecorator implements BlockerCommand {
    /**
     * The blockerCommand field is a reference to the underlying BlockerCommand
     * that this decorator is wrapping. It allows the decorator to delegate
     * the execution of the command to the wrapped command and apply additional
     * blocking behaviors.
     */
    protected BlockerCommand blockerCommand;

    /**
     * Constructor for the BlockerDecorator class.
     *
     * @param blockerCommand The BlockerCommand instance to be decorated by this class.
     */
    public BlockerDecorator(BlockerCommand blockerCommand) {
        this.blockerCommand = blockerCommand;
    }

    /**
     * Executes the decorated blocking command on the given URL.
     *
     * @param url The string representation of the URL to be checked.
     * @return HttpURLConnection An open connection to the reachable and non-blocked URL.
     * @throws Exception If any exception occurs during the execution of the blocking command.
     */
    public HttpURLConnection execute(String url) throws Exception {
        return blockerCommand.execute(url);
    }
    /**
     * Checks if the given URL is reachable by examining the response code.
     *
     * @param connection HttpURLConnection An open connection to the URL.
     * @throws Exception If the URL is not reachable or any other exception occurs.
     */
    void checkUrlReachability(HttpURLConnection connection) throws Exception {
        int statusCode;
        try {
            statusCode = connection.getResponseCode();
        } catch (Exception e) {
            throw new InvalidURLException();
        }

        if (statusCode < 200 || statusCode >= 300) {
            throw new IOException("" + statusCode);
        }
    }
}
