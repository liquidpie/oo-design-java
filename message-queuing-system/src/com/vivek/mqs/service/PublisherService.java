package com.vivek.mqs.service;

import com.vivek.mqs.pubsub.Publisher;
import com.vivek.mqs.pubsub.impl.SimplePublisher;
import com.vivek.mqs.db.InMemoryDataStore;
import com.vivek.mqs.db.schema.Subscription;
import com.vivek.mqs.exception.ErrorType;
import com.vivek.mqs.exception.SystemException;
import com.vivek.mqs.orchestration.MessageDispatcher;

import java.util.Objects;

import static com.vivek.mqs.util.Utilities.newUUID;

public class PublisherService {

    private final MessageDispatcher dispatcher;
    private final InMemoryDataStore dataStore;

    public PublisherService(QueueService queueService) {
        this.dispatcher = new MessageDispatcher(queueService);
        dataStore = InMemoryDataStore.getInstance();
    }

    public Publisher createPublisher(String queueName) {
        var allPublishers = dataStore.publisherCollection.getAll();
        boolean publisherExists = allPublishers.values().stream()
                                    .anyMatch(publisher -> publisher.getQueueName().equals(queueName) && !publisher.isDeleted());

        if (publisherExists) {
            throw new SystemException(ErrorType.PUBLISHER_ALREADY_EXISTS_FOR_QUEUE);
        }

        String publisherId = newUUID();
        Publisher newPublisher = new SimplePublisher(publisherId, queueName, dispatcher, this);

        var dbPublisher = new com.vivek.mqs.db.schema.Publisher(publisherId);
        dbPublisher.setQueueName(queueName);
        dbPublisher.setCreatedAt(System.currentTimeMillis());
        dataStore.publisherCollection.insert(publisherId, dbPublisher);

        Subscription subscription = dataStore.subscriptionCollection.get(queueName);
        if (subscription == null) {
            throw new SystemException(ErrorType.QUEUE_NOT_FOUND);
        }
        subscription.setPublisherId(publisherId);
        dataStore.subscriptionCollection.update(queueName, subscription);

        return newPublisher;
    }

    public void removePublisher(String publisherId) {
        var publisher = dataStore.publisherCollection.get(publisherId);
        if (publisher == null || publisher.isDeleted()) {
            return;
        }

        var queue = publisher.getQueueName();
        publisher.setQueueName(null);
        publisher.setDeleted(true);
        dataStore.publisherCollection.update(publisherId, publisher);

        // remove subscription when publisher is deleted
        dataStore.subscriptionCollection.delete(queue);
    }

    public boolean publisherExists(String publisherId) {
        var publisher = dataStore.publisherCollection.get(publisherId);
        return Objects.nonNull(publisher) && !publisher.isDeleted();
    }

}
