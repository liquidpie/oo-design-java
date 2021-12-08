package com.vivek.messaging.entities;

import java.net.URI;
import java.util.Objects;

public class User {

    private final String userId;
    private final String displayName;
    private final URI profilePictureUrl;

    public User(String userId, String displayName, URI profilePictureUrl) {
        this.userId = userId;
        this.displayName = displayName;
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public URI getProfilePictureUrl() {
        return profilePictureUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
