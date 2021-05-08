package com.vivek.snakeladder.domain;

import java.util.Objects;

public class Ladder {

    private final int top;
    private final int bottom;

    public Ladder(int top, int bottom) {
        this.top = top;
        this.bottom = bottom;
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ladder ladder = (Ladder) o;
        return top == ladder.top && bottom == ladder.bottom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(top, bottom);
    }

    @Override
    public String toString() {
        return "Ladder{" +
                "top=" + top +
                ", bottom=" + bottom +
                '}';
    }
}
