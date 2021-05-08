package com.vivek.snakeladder.domain;

import java.util.Objects;

public class CellPair {

    private final int top;
    private final int bottom;

    public CellPair(int top, int bottom) {
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
        CellPair cellPair = (CellPair) o;
        return top == cellPair.top && bottom == cellPair.bottom;
    }

    @Override
    public int hashCode() {
        return Objects.hash(top, bottom);
    }

    @Override
    public String toString() {
        return "CellPair{" +
                "top=" + top +
                ", bottom=" + bottom +
                '}';
    }
}
