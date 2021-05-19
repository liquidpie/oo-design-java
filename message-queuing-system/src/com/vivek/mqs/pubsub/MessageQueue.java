package com.vivek.mqs.pubsub;

import com.vivek.mqs.config.RetryConfig;

public interface MessageQueue {

    String getName();

    Integer getMaxSize();

    RetryConfig getRetryConfig();

    void enqueue(String message);

    String dequeue();

    String first();

    int size();

    boolean isEmpty();

}
