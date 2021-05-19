package com.vivek.mqs.orchestration;

import com.vivek.mqs.db.InMemoryDataStore;
import com.vivek.mqs.db.schema.Subscription;
import com.vivek.mqs.pubsub.MessageQueue;
import com.vivek.mqs.pubsub.Subscriber;
import com.vivek.mqs.service.QueueService;
import com.vivek.mqs.service.SubscriberService;

import java.util.LinkedList;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class MessagePoller {

    private static final long DELAY = 0;
    private static final long PERIOD = 1000;

    private final QueueService queueService;
    private final SubscriberService subscriberService;
    private final MessageReceiver messageReceiver;
    private final InMemoryDataStore dataStore;

    public MessagePoller(QueueService queueService, SubscriberService subscriberService) {
        this.queueService = queueService;
        this.subscriberService = subscriberService;
        this.messageReceiver = new MessageReceiver();
        this.dataStore = InMemoryDataStore.getInstance();
    }

    public void schedule() {
        Timer time = new Timer();
        ScheduledTask st = new ScheduledTask();
        time.schedule(st, DELAY, PERIOD);
    }

    private class ScheduledTask extends TimerTask {
        @Override
        public void run() {
            var subscriptions = dataStore.subscriptionCollection.getAll();
            for (Map.Entry<String, Subscription> entry : subscriptions.entrySet()) {
                String queueName = entry.getKey();
                MessageQueue queue = queueService.getQueue(queueName);

                LinkedList<String> subscribers = new LinkedList<>((entry.getValue().getSubscribers()));

                while (!queue.isEmpty() && !subscribers.isEmpty()) {
                    String subscriberId = subscribers.removeFirst();
                    Subscriber subscriber = subscriberService.getSubscriber(subscriberId);
                    int fetchSize = subscriber.getFetchSize();

                    AtomicInteger counter = new AtomicInteger();
                    while (!queue.isEmpty() && counter.getAndIncrement() < fetchSize) {
                        messageReceiver.receive(queue, subscriber);
                    }

                    subscribers.addLast(subscriberId);
                }
            }
        }
    }

}
