package com.vivek.url.shortener.domain;

import java.util.Objects;

public class URL {

    private final String originalUrl;
    private final String hash;
    private final String userId;

    public URL(String originalUrl, String hash, String userId) {
        this.originalUrl = originalUrl;
        this.hash = hash;
        this.userId = userId;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getHash() {
        return hash;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URL url = (URL) o;
        return Objects.equals(originalUrl, url.originalUrl) && Objects.equals(hash, url.hash) && Objects.equals(userId, url.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalUrl, hash, userId);
    }

    @Override
    public String toString() {
        return "URL{" +
                "originalUrl='" + originalUrl + '\'' +
                ", hash='" + hash + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
