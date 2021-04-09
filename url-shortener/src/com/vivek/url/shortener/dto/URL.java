package com.vivek.url.shortener.dto;

import java.time.LocalDate;
import java.util.Objects;

public class URL {

    private final String hash;
    private final String originalUrl;
    private final LocalDate creationDate;
    private final LocalDate expirationDate;
    private final String userId;

    public URL(String hash, String originalUrl, LocalDate creationDate, LocalDate expirationDate, String userId) {
        this.hash = hash;
        this.originalUrl = originalUrl;
        this.creationDate = creationDate;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }

    public String getHash() {
        return hash;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        URL url = (URL) o;
        return Objects.equals(hash, url.hash) && Objects.equals(originalUrl, url.originalUrl) && Objects.equals(creationDate, url.creationDate) && Objects.equals(expirationDate, url.expirationDate) && Objects.equals(userId, url.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, originalUrl, creationDate, expirationDate, userId);
    }

    @Override
    public String toString() {
        return "URL{" +
                "hash='" + hash + '\'' +
                ", originalUrl='" + originalUrl + '\'' +
                ", creationDate=" + creationDate +
                ", expirationDate=" + expirationDate +
                ", userId='" + userId + '\'' +
                '}';
    }
}
