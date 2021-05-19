package com.vivek.mqs.queue;

public interface Queue<T> {

    void enqueue(T element);

    T dequeue();

    T first();

    int size();

    boolean isEmpty();

}
