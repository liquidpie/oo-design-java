package com.vivek.fantasy.cricket.domain;

import java.util.Objects;

public class User {

    private final String id;
    private final String name;
    private final String teamId;
    private final int creditsBudget;

    public User(String id, String name, String teamId, int creditsBudget) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;
        this.creditsBudget = creditsBudget;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeamId() {
        return teamId;
    }

    public int getCreditsBudget() {
        return creditsBudget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return creditsBudget == user.creditsBudget && Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(teamId, user.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, teamId, creditsBudget);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", teamId='" + teamId + '\'' +
                ", creditsBudget=" + creditsBudget +
                '}';
    }
}
