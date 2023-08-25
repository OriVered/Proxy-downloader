/**
 * This package provides the implementation of option validation.
 */
package com.hac.Validator;

import com.hac.Exception.InvalidCommandException;

/**
 * The OptionValidator class provides a method for validating command line input options.
 */
public class OptionValidator {

    /**
     * Validates the options string based on the provided input.
     *
     * @param options The options as a string.
     * @return The valid options string without the leading hyphen.
     * @throws Exception If the options string is invalid or does not start with a hyphen.
     */
    public static String getValidOption(String options) throws Exception {
        // Validate option start
        if (options == null || (options.length() >= 1  && options.charAt(0) != '-')) {
            throw new InvalidCommandException();
        }

        return options.substring(1); // Remove the hyphen
    }
}
