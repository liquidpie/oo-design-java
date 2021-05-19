package com.vivek.mqs.db.schema;

import java.util.HashSet;
import java.util.Set;

public class Subscription {

    private final String queueName;
    private final Set<String> subscribers;
    private String publisherId;

    public Subscription(String queueName) {
        this.queueName = queueName;
        this.subscribers = new HashSet<>();
    }

    public String getQueueName() {
        return queueName;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public Set<String> getSubscribers() {
        return subscribers;
    }

    public void addSubscriber(String subscriberId) {
        subscribers.add(subscriberId);
    }

    public void removeSubscriber(String subscriberId) {
        subscribers.remove(subscriberId);
    }

}
