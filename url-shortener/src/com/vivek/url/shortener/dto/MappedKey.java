package com.vivek.url.shortener.dto;

import java.util.Objects;

public class MappedKey {

    private final String userId;
    private final String originalUrl;

    public MappedKey(String userId, String originalUrl) {
        this.userId = userId;
        this.originalUrl = originalUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MappedKey mappedKey = (MappedKey) o;
        return Objects.equals(userId, mappedKey.userId) && Objects.equals(originalUrl, mappedKey.originalUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, originalUrl);
    }

    @Override
    public String toString() {
        return "MappedKey{" +
                "userId='" + userId + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                '}';
    }
}
