package com.vivek.mqs.service;

import com.vivek.mqs.pubsub.Subscriber;
import com.vivek.mqs.pubsub.impl.SimpleSubscriber;
import com.vivek.mqs.config.SubscriberConfig;
import com.vivek.mqs.db.InMemoryDataStore;
import com.vivek.mqs.db.schema.Subscription;
import com.vivek.mqs.exception.ErrorType;
import com.vivek.mqs.exception.SystemException;

import java.util.HashMap;
import java.util.Map;

import static com.vivek.mqs.util.Utilities.newUUID;

public class SubscriberService {

    private final Map<String, Subscriber> subscribers;
    private final InMemoryDataStore dataStore;

    public SubscriberService() {
        this.subscribers = new HashMap<>();
        dataStore = InMemoryDataStore.getInstance();
    }

    public Subscriber createSubscriber(SubscriberConfig config) {
        String subscriberId = newUUID();
        Subscriber newSubscriber = new SimpleSubscriber(subscriberId, config.getCallback(), config.getFetchSize(), this);
        subscribers.put(subscriberId, newSubscriber);

        var dbSubscriber = new com.vivek.mqs.db.schema.Subscriber(subscriberId);
        dbSubscriber.setCreatedAt(System.currentTimeMillis());
        dataStore.subscriberCollection.insert(subscriberId, dbSubscriber);

        return newSubscriber;
    }

    public Subscriber getSubscriber(String subscriberId) {
        return subscribers.get(subscriberId);
    }

    public void registerSubscriber(String queue, String subscriberId) {
        var subscriber = dataStore.subscriberCollection.get(subscriberId);
        if (subscriber == null || subscriber.isDeleted()) {
            throw new SystemException(ErrorType.SUBSCRIBER_NOT_FOUND);
        }

        if (subscriber.getQueueName() == null) {

            Subscription subscription = dataStore.subscriptionCollection.get(queue);
            if (subscription == null) {
                throw new SystemException(ErrorType.QUEUE_NOT_FOUND);
            }

            subscription.addSubscriber(subscriberId);
            dataStore.subscriptionCollection.update(queue, subscription);

            subscriber.setQueueName(queue);
            dataStore.subscriberCollection.update(subscriberId, subscriber);

        } else if (!subscriber.getQueueName().equals(queue)) {
            throw new SystemException(ErrorType.EXISTING_SUBSCRIPTION_FOUND);
        }

    }

    public void deregisterSubscriber(String queue, String subscriberId) {
        var subscriber = dataStore.subscriberCollection.get(subscriberId);
        if (subscriber == null || subscriber.isDeleted()) {
            return;
        }

        Subscription subscription = dataStore.subscriptionCollection.get(queue);
        subscription.removeSubscriber(subscriberId);
        dataStore.subscriptionCollection.update(queue, subscription);

        subscriber.setQueueName(null);
        dataStore.subscriberCollection.update(subscriberId, subscriber);
    }

    public void removeSubscriber(String subscriberId) {
        var subscriber = dataStore.subscriberCollection.get(subscriberId);
        if (subscriber == null || subscriber.isDeleted()) {
            return;
        }

        var queue = subscriber.getQueueName();
        subscriber.setQueueName(null);
        subscriber.setDeleted(true);
        dataStore.subscriberCollection.update(subscriberId, subscriber);

        if (queue != null) {
            Subscription subscription = dataStore.subscriptionCollection.get(queue);
            if (subscription != null) {
                subscription.removeSubscriber(subscriberId);
                dataStore.subscriptionCollection.update(queue, subscription);
            }
        }
    }

}
