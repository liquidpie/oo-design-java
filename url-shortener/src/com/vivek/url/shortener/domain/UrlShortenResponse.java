package com.vivek.url.shortener.domain;

public class UrlShortenResponse {

    private String shortUrl;
    private ErrorType errorType;

    public UrlShortenResponse(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public UrlShortenResponse(ErrorType errorType) {
        this.errorType = errorType;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    @Override
    public String toString() {
        return "UrlShortenResponse{" +
                "shortUrl='" + shortUrl + '\'' +
                ", errorType=" + errorType +
                '}';
    }
}
