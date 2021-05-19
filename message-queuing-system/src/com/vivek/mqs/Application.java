package com.vivek.mqs;

import com.vivek.mqs.api.ApiClient;
import com.vivek.mqs.config.QueueConfig;
import com.vivek.mqs.config.RetryConfig;
import com.vivek.mqs.config.SubscriberConfig;
import com.vivek.mqs.domain.Message;
import com.vivek.mqs.pubsub.Broker;
import com.vivek.mqs.pubsub.Publisher;
import com.vivek.mqs.pubsub.Subscriber;
import com.vivek.mqs.pubsub.impl.MessageBroker;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        ApiClient apiClient = new ApiClient("https://mqueue.free.beeceptor.com/my/api/path");  // Mock endpoint, it accepts a random post body and responds with static response
        Broker broker = new MessageBroker();

        broker.createQueue(QueueConfig.builder()
                                        .withName("qName")
                                        .withRetryConfig(RetryConfig.builder()
                                                .withMaxAttempts(2)
                                                .withRetryPolicy(RetryConfig.RetryPolicy.ENQUEUE_MESSAGE)
                                                .withDelayMs(2000)
                                                .build())
                                        .build());
        Publisher publisher1 = broker.createPublisher("qName");
        Subscriber subscriber1 = broker.createSubscriber(SubscriberConfig.builder()
                                                            .withCallback(packet -> {
                                                                System.out.println("Subscriber 1 calling api with request=" + packet.getBody());
                                                                System.out.println(apiClient.submit(packet));
                                                            })
                                                            .build());

        Subscriber subscriber2 = broker.createSubscriber(SubscriberConfig.builder()
                                                            .withCallback(message -> System.out.println("Subscriber 2 - " + message.toString()))
                                                            .withFetchSize(3)
                                                            .build());

        subscriber1.subscribe("qName");

        Message message = new Message("{ \"name\": \"morpheus\", \"job\": \"leader\" }");
        publisher1.publish(message);

        subscriber2.subscribe("qName");

        message = new Message("Hello World 1 !!");
        publisher1.publish(message);

        message = new Message("Hello World 2 !!");
        publisher1.publish(message);

        message = new Message("Hello World 3 !!");
        publisher1.publish(message);

        message = new Message("Hello World 4 !!");
        publisher1.publish(message);

        Thread.sleep(5000);

        subscriber1.unsubscribe();

        message = new Message("Hello World 5 !!");
        publisher1.publish(message);

        Thread.sleep(3000);

        broker.removeSubscriber(subscriber1.getId());
        broker.removeSubscriber(subscriber2.getId());

        Thread.sleep(5000);

        message = new Message("Hello World 6 !!");
        publisher1.publish(message);

        Subscriber subscriber3 = broker.createSubscriber(SubscriberConfig.builder()
                .withCallback(packet -> System.out.println("Subscriber 3 - " + packet.toString()))
                .build());

        subscriber3.subscribe("qName");

        Thread.sleep(5000);

        broker.removePublisher(publisher1.getId());

    }

}
