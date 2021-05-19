package com.vivek.mqs.pubsub;

import com.vivek.mqs.config.QueueConfig;
import com.vivek.mqs.config.SubscriberConfig;

public interface Broker {

    MessageQueue createQueue(QueueConfig queueConfig);

    void deleteQueue(String qName);

    Publisher createPublisher(String qName);

    Subscriber createSubscriber(SubscriberConfig subscriberConfig);

    void removePublisher(String publisherId);

    void removeSubscriber(String subscriberId);

}
