package com.vivek.mqs.service;

import com.vivek.mqs.pubsub.MessageQueue;
import com.vivek.mqs.pubsub.impl.SimpleQueue;
import com.vivek.mqs.config.QueueConfig;
import com.vivek.mqs.db.InMemoryDataStore;
import com.vivek.mqs.db.schema.Subscription;
import com.vivek.mqs.exception.ErrorType;
import com.vivek.mqs.exception.SystemException;

import java.util.HashMap;
import java.util.Map;

import static com.vivek.mqs.util.Utilities.isBlank;
import static com.vivek.mqs.util.Utilities.newUUID;

public class QueueService {

    private final Map<String, MessageQueue> queues;
    private final InMemoryDataStore dataStore;

    public QueueService() {
        this.queues = new HashMap<>();
        this.dataStore = InMemoryDataStore.getInstance();
    }

    public MessageQueue createQueue(QueueConfig queueConfig) {
        String name = queueConfig.getName();
        if (isBlank(name)) {
            name = "queue-" + newUUID();
        }

        if (queueExists(name)) {
            throw new SystemException(ErrorType.QUEUE_ALREADY_EXISTS);
        }

        MessageQueue newQueue = new SimpleQueue(name, queueConfig.getSizeLimit(), queueConfig.getRetryConfig());

        var dbQueue = new com.vivek.mqs.db.schema.Queue(newQueue.getName(), queueConfig.getSizeLimit());
        dbQueue.setCreatedAt(System.currentTimeMillis());

        dataStore.queueCollection.insert(dbQueue.getName(), dbQueue);
        dataStore.subscriptionCollection.insert(newQueue.getName(), new Subscription(newQueue.getName()));

        queues.put(name, newQueue);

        return newQueue;
    }

    public MessageQueue getQueue(String queueName) {
        return queues.get(queueName);
    }

    public void deleteQueue(String queueName) {
        if (isBlank(queueName))
            return;
        if (!queueExists(queueName)) {
            throw new SystemException(ErrorType.QUEUE_NOT_FOUND);
        }

        var queue = dataStore.queueCollection.get(queueName);
        queue.setActive(false);
        queue.setModifiedAt(System.currentTimeMillis());

        dataStore.queueCollection.update(queueName, queue);
        dataStore.subscriberCollection.delete(queueName);

        queues.remove(queueName);
    }

    private boolean queueExists(String queueName) {
        return dataStore.queueCollection.get(queueName) != null;
    }

}
