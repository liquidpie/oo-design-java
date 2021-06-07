package com.vivek.fantasy.cricket.domain;

import com.vivek.fantasy.cricket.domain.enums.PlayerType;

import java.util.Objects;

public class Player {

    private final String id;
    private final String name;
    private final PlayerType type;
    private final int credits;

    public Player(String id, String name, PlayerType type, int credits) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.credits = credits;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public PlayerType getType() {
        return type;
    }

    public int getCredits() {
        return credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return credits == player.credits && Objects.equals(id, player.id) && Objects.equals(name, player.name) && type == player.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, credits);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", credits=" + credits +
                '}';
    }
}
