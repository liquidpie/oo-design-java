package com.vivek.chess.board;

import java.util.Objects;

public class Position {

    private final int x;
    private final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position of(int x, int y) {
        return new Position(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Position fromString(String pos) {
        if (pos.length() != 2)
            return null;
        return new Position(pos.charAt(0) - 'a', Integer.parseInt(pos.substring(1)) - 1);
    }

    public Position copy() {
        return of(this.x, this.y);
    }

    @Override
    public String toString() {
        return (char) ('a' + x) + "" + (y + 1) ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
