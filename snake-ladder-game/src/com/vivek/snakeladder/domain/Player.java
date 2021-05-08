package com.vivek.snakeladder.domain;

import java.util.Objects;

public class Player {

    private final String id;
    private final String name;
    private final int initPosition;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.initPosition = 0;
    }

    public Player(String id, String name, int initPosition) {
        this.id = id;
        this.name = name;
        this.initPosition = initPosition;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInitPosition() {
        return initPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return initPosition == player.initPosition && Objects.equals(id, player.id) && Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, initPosition);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", initPosition=" + initPosition +
                '}';
    }
}
