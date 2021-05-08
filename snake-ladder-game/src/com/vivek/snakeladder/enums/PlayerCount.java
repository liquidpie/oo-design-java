package com.vivek.snakeladder.enums;

public enum PlayerCount {

    TWO(2),
    FOUR(4);

    private final int count;

    PlayerCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }
}
