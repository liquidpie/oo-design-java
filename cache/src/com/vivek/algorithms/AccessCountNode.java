package com.vivek.algorithms;

import java.util.Objects;

public class AccessCountNode<E> implements Comparable<AccessCountNode<E>> {

    private final E value;
    private Integer frequency;

    public AccessCountNode(E value) {
        this.value = value;
        this.frequency = 1;
    }

    public E getValue() {
        return value;
    }

    public int getFrequency() {
        return frequency;
    }

    public void accessed() {
        frequency++;
    }

    @Override
    public int compareTo(AccessCountNode other) {
        return frequency.compareTo(other.frequency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessCountNode<?> that = (AccessCountNode<?>) o;
        return frequency.equals(that.frequency) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, frequency);
    }
}
