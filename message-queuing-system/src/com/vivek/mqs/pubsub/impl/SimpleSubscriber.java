package com.vivek.mqs.pubsub.impl;

import com.vivek.mqs.pubsub.Subscriber;
import com.vivek.mqs.domain.Message;
import com.vivek.mqs.service.SubscriberService;

import java.util.Objects;
import java.util.function.Consumer;

public class SimpleSubscriber implements Subscriber {

    private static final int DEFAULT_FETCH_SIZE = 1;

    private final String id;
    private final Consumer<Message> callback;
    private final int fetchSize;
    private final SubscriberService subscriberService;
    private String queueName;

    public SimpleSubscriber(String id, Consumer<Message> callback, Integer fetchSize, SubscriberService subscriberService) {
        this.id = id;
        this.callback = callback;
        this.fetchSize = Objects.nonNull(fetchSize) ? fetchSize : DEFAULT_FETCH_SIZE;
        this.subscriberService = subscriberService;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getQueueName() {
        return queueName;
    }

    @Override
    public Consumer<Message> getCallback() {
        return callback;
    }

    @Override
    public int getFetchSize() {
        return fetchSize;
    }

    @Override
    public void subscribe(String queueName) {
        subscriberService.registerSubscriber(queueName, id);
        this.queueName = queueName;
    }

    @Override
    public void unsubscribe() {
        subscriberService.deregisterSubscriber(queueName, id);
        queueName = null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleSubscriber that = (SimpleSubscriber) o;
        return Objects.equals(id, that.id) && Objects.equals(callback, that.callback) && Objects.equals(subscriberService, that.subscriberService) && Objects.equals(queueName, that.queueName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, callback, subscriberService, queueName);
    }
}
