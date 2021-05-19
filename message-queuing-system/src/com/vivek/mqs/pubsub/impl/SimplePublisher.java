package com.vivek.mqs.pubsub.impl;

import com.vivek.mqs.pubsub.Publisher;
import com.vivek.mqs.domain.Message;
import com.vivek.mqs.exception.ErrorType;
import com.vivek.mqs.exception.SystemException;
import com.vivek.mqs.orchestration.MessageDispatcher;
import com.vivek.mqs.service.PublisherService;

public class SimplePublisher implements Publisher {

    private final String id;
    private final String queueName;
    private final MessageDispatcher dispatcher;
    private final PublisherService publisherService;

    public SimplePublisher(String id, String queueName, MessageDispatcher dispatcher, PublisherService publisherService) {
        this.id = id;
        this.queueName = queueName;
        this.dispatcher = dispatcher;
        this.publisherService = publisherService;
    }

    public String getId() {
        return id;
    }

    public String getQueueName() {
        return queueName;
    }

    @Override
    public void publish(Message message) {
        if (!publisherService.publisherExists(id)) {
            throw new SystemException(ErrorType.PUBLISHER_NOT_FOUND);
        }
        dispatcher.dispatch(message, queueName);
    }
}
