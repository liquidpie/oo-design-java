package com.vivek.fantasy.cricket.domain;

import java.util.Objects;
import java.util.Set;

public class Team {

    private final String id;
    private final String userId;
    private final Set<String> playerIds;

    public Team(String id, String userId, Set<String> playerIds) {
        this.id = id;
        this.userId = userId;
        this.playerIds = playerIds;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public Set<String> getPlayerIds() {
        return playerIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(userId, team.userId) && Objects.equals(playerIds, team.playerIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, playerIds);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", playerIds=" + playerIds +
                '}';
    }
}
