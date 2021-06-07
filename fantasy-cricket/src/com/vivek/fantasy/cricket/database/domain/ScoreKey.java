package com.vivek.fantasy.cricket.database.domain;

import java.util.Objects;

public class ScoreKey {

    private final String userId;
    private final String playerId;

    public ScoreKey(String userId, String playerId) {
        this.userId = userId;
        this.playerId = playerId;
    }

    public String getUserId() {
        return userId;
    }

    public String getPlayerId() {
        return playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreKey scoreKey = (ScoreKey) o;
        return Objects.equals(userId, scoreKey.userId) && Objects.equals(playerId, scoreKey.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, playerId);
    }

    @Override
    public String toString() {
        return "ScoreKey{" +
                "userId='" + userId + '\'' +
                ", playerId='" + playerId + '\'' +
                '}';
    }
}

