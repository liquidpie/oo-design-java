package com.vivek.mqs.pubsub.impl;

import com.vivek.mqs.pubsub.Broker;
import com.vivek.mqs.pubsub.Publisher;
import com.vivek.mqs.pubsub.MessageQueue;
import com.vivek.mqs.pubsub.Subscriber;
import com.vivek.mqs.config.QueueConfig;
import com.vivek.mqs.config.SubscriberConfig;
import com.vivek.mqs.orchestration.MessagePoller;
import com.vivek.mqs.service.PublisherService;
import com.vivek.mqs.service.QueueService;
import com.vivek.mqs.service.SubscriberService;

public class MessageBroker implements Broker {

    private final QueueService queueService;
    private final SubscriberService subscriberService;
    private final PublisherService publisherService;
    private final MessagePoller messagePoller;

    public MessageBroker() {
        this.queueService = new QueueService();
        this.subscriberService = new SubscriberService();
        this.publisherService = new PublisherService(queueService);
        this.messagePoller = new MessagePoller(queueService, subscriberService);
        start();
    }

    public MessageBroker(QueueService queueService, SubscriberService subscriberService, PublisherService publisherService) {
        this.queueService = queueService;
        this.subscriberService = subscriberService;
        this.publisherService = publisherService;
        this.messagePoller = new MessagePoller(queueService, subscriberService);
        start();
    }

    private void start() {
        messagePoller.schedule();
    }

    @Override
    public MessageQueue createQueue(QueueConfig queueConfig) {
        return queueService.createQueue(queueConfig);
    }

    @Override
    public void deleteQueue(String qName) {
        queueService.deleteQueue(qName);
    }

    @Override
    public Publisher createPublisher(String qName) {
        return publisherService.createPublisher(qName);
    }

    @Override
    public Subscriber createSubscriber(SubscriberConfig config) {
        return subscriberService.createSubscriber(config);
    }

    @Override
    public void removePublisher(String publisherId) {
        publisherService.removePublisher(publisherId);
    }

    @Override
    public void removeSubscriber(String subscriberId) {
        subscriberService.removeSubscriber(subscriberId);
    }
}
