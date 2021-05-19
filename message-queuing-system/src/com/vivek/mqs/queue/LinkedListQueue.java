package com.vivek.mqs.queue;


import java.util.Objects;

public class LinkedListQueue<T> implements Queue<T> {

    private Node<T> front = null;
    private Node<T> rear = null;
    private int size = 0;

    private final int maxSize;

    public LinkedListQueue() {
        this(null);
    }

    public LinkedListQueue(Integer maxSize) {
        this.maxSize = Objects.nonNull(maxSize) ? maxSize : -1;
    }

    @Override
    public void enqueue(T element) {
        insertLast(element);
    }

    @Override
    public T dequeue() {
        return removeFirst();
    }

    @Override
    public T first() {
        if (isEmpty())
            return null;
        return front.data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void insertLast(T elt) {
        if (size == maxSize) {
            throw new IllegalStateException("Queue Full");
        }
        Node<T> newNode = new Node<>(elt);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    private T removeFirst() {
        if (isEmpty())
            return null;
        T temp = front.data;
        if (front.next == null) {
            rear = null;
        }
        front = front.next;
        size--;
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Node<T> current = front;
        String delimiter = "";
        while (current != null) {
            builder.append(delimiter).append(current);
            current = current.next;
            delimiter = ", ";
        }
        builder.append("]");

        return builder.toString();
    }

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

}
