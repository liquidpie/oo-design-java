package com.vivek.url.shortener.validators;

public class UrlValidator {

    // This method should take care various issues with a valid url
    // e.g. www.google.com,www.google.com/, http://www.google.com, http://www.google.com/
    // all the above URL should point to same shortened URL
    // There could be several other cases like these.
    public static String sanitizeUrl(String url) {
        if (url.startsWith("http://"))
            url = url.substring(7);

        if (url.startsWith("https://"))
            url = url.substring(8);

        if (url.charAt(url.length() - 1) == '/')
            url = url.substring(0, url.length() - 1);
        return url;
    }

}
