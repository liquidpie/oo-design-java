package com.vivek.mqs.pubsub.impl;

import com.vivek.mqs.config.RetryConfig;
import com.vivek.mqs.pubsub.MessageQueue;
import com.vivek.mqs.queue.LinkedListQueue;
import com.vivek.mqs.queue.Queue;

public class SimpleQueue implements MessageQueue {

    private final String name;
    private final Integer maxSize;
    private final RetryConfig retryConfig;
    private final Queue<String> queue;

    public SimpleQueue(String name) {
        this(name, null);
    }

    public SimpleQueue(String name, Integer maxSize) {
        this(name, maxSize, new LinkedListQueue<>(maxSize));
    }

    public SimpleQueue(String name, Integer maxSize, Queue<String> queue) {
        this(name, maxSize, null, queue);
    }

    public SimpleQueue(String name, Integer maxSize, RetryConfig retryConfig) {
        this(name, maxSize, retryConfig, new LinkedListQueue<>(maxSize));
    }

    public SimpleQueue(String name, Integer maxSize, RetryConfig retryConfig, Queue<String> queue) {
        this.name = name;
        this.maxSize = maxSize;
        this.retryConfig = retryConfig;
        this.queue = queue;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getMaxSize() {
        return maxSize;
    }

    @Override
    public RetryConfig getRetryConfig() {
        return retryConfig;
    }

    @Override
    public void enqueue(String message) {
        queue.enqueue(message);
    }

    @Override
    public String dequeue() {
        return queue.dequeue();
    }

    @Override
    public String first() {
        return queue.first();
    }

    @Override
    public int size() {
        return queue.size();
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public String toString() {
        return "SimpleQueue{" +
                "name='" + name + '\'' +
                ", maxSize=" + maxSize +
                ", queue=" + queue +
                '}';
    }
}
