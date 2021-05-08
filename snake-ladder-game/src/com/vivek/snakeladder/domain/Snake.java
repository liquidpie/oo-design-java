package com.vivek.snakeladder.domain;

import java.util.Objects;

public class Snake {

    private final int head;
    private final int tail;

    public Snake(int head, int tail) {
        this.head = head;
        this.tail = tail;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snake snake = (Snake) o;
        return head == snake.head && tail == snake.tail;
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail);
    }

    @Override
    public String toString() {
        return "Snake{" +
                "head=" + head +
                ", tail=" + tail +
                '}';
    }
}
