/**
 * This package provides the implementation of URL validation.
 */
package com.hac.Validator;

import com.hac.Exception.InvalidURLException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The URLValidator class is a utility class responsible for validating
 * URLs passed to the application.
 */
public class URLValidator {
    private static final String URL_REGEX =
            "^(([^:/?#]+):)(//([^/?#]*))([^?#]*)(\\?([^#]*))?(#(.*))?";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    /**
     * Validates the given URL string and returns the valid URL string.
     * If the URL string is invalid, throws an InvalidURLException.
     *
     * @param urlString The URL string to be validated.
     * @return The valid URL string.
     * @throws Exception If the URL string is invalid or malformed.
     */
    public static String getValidURL(String urlString) throws Exception {

        try {
            new URL(urlString).toURI();
        } catch (MalformedURLException | URISyntaxException e) {
            throw new InvalidURLException();
        }

        String trimmedUrl = urlString.trim();

        if (trimmedUrl.isEmpty()) {
            throw new InvalidURLException();
        }

        Matcher matcher = URL_PATTERN.matcher(trimmedUrl);
        if (matcher.matches())
            return trimmedUrl;
        else
            throw new InvalidURLException();
    }
}
