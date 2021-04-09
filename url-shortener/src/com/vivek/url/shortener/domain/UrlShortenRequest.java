package com.vivek.url.shortener.domain;

import java.time.LocalDate;

public class UrlShortenRequest {

    private String longUrl;
    private LocalDate expiresDate = LocalDate.MAX;

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public LocalDate getExpiresDate() {
        return expiresDate;
    }

    public void setExpiresDate(LocalDate expiresDate) {
        this.expiresDate = expiresDate;
    }

    @Override
    public String toString() {
        return "UrlShortenRequest{" +
                "longUrl='" + longUrl + '\'' +
                ", expiresDate=" + expiresDate +
                '}';
    }
}
