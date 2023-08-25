/**
 * This package provides the implementation of various URL blocking options.
 */
package com.hac.Blocker;

import com.hac.Exception.InvalidOptionException;

/**
 * The BlockedOption class is responsible for building the appropriate
 * BlockerCommand instances based on the provided options string.
 */
public class BlockedOption {

    private static final String IMAGE_BLOCKER_KEY = "i";
    private static final String COOKIE_BLOCKER_KEY = "c";
    private static final String HTML_BLOCKER_KEY = "h";
    private static final String BLOCKER_URL_BLOCKER_KEY = "b";

    /**
     * Builds a BlockerCommand chain based on the provided options string.
     *
     * @param options A string containing the options for the BlockerCommand chain.
     *                Each character in the string represents a specific blocker type.
     * @return BlockerCommand A chain of BlockerCommands based on the options string.
     * @throws Exception If an invalid option is provided or any other exception occurs.
     */
    public static BlockerCommand buildCommands(String options) throws Exception {
        BlockerCommand blockerCommand = new BasicBlocker();

        if (options != null && !options.equals("")) {
            for (String option : options.split("")) {
                blockerCommand = switch (option) {
                    case BLOCKER_URL_BLOCKER_KEY -> new BlockedUrlBlocker(blockerCommand);
                    case IMAGE_BLOCKER_KEY -> new ImageBlocker(blockerCommand);
                    case COOKIE_BLOCKER_KEY -> new CookieBlocker(blockerCommand);
                    case HTML_BLOCKER_KEY -> new HTMLBlocker(blockerCommand);
                    default -> throw new InvalidOptionException();
                };
            }

        }
        return blockerCommand;
    }
}
