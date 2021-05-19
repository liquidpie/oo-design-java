package com.vivek.mqs.service;

import com.vivek.mqs.config.SubscriberConfig;
import com.vivek.mqs.db.InMemoryDataStore;
import com.vivek.mqs.db.schema.Queue;
import com.vivek.mqs.db.schema.Subscription;
import com.vivek.mqs.exception.SystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubscriberServiceTest {

    private SubscriberService subscriberService;
    private InMemoryDataStore dataStore;

    @BeforeEach
    void setup() {
        subscriberService = new SubscriberService();
        dataStore = InMemoryDataStore.getInstance();
    }

    @Test
    void shouldCreateSubscriberSuccessfully() {
        var config = SubscriberConfig.builder()
                                        .withCallback(System.out::println)
                                        .build();
        subscriberService.createSubscriber(config);

        Assertions.assertEquals(1, dataStore.subscriberCollection.getAll().size());
    }

    @Test
    void shouldRegisterSubscriberSuccessfully() {
        var config = SubscriberConfig.builder()
                .withCallback(System.out::println)
                .build();
        var subscriber = subscriberService.createSubscriber(config);
        dataStore.queueCollection.insert("myQueue", new Queue("myQueue", null));
        dataStore.subscriptionCollection.insert("myQueue", new Subscription("queueName"));

        // when
        subscriberService.registerSubscriber("myQueue", subscriber.getId());

        // then
        var subscribers = dataStore.subscriptionCollection.get("myQueue").getSubscribers();
        Assertions.assertTrue(subscribers.contains(subscriber.getId()));
    }

    @Test
    void shouldThrowExceptionOnSubscriberRegisterWhenSubscriberNotFound() {
        Assertions.assertThrows(SystemException.class,  () -> subscriberService.registerSubscriber("myQueue", "some-id"));
    }

    @Test
    void shouldThrowExceptionOnSubscriberRegisterWhenQueueNotFound() {
        Assertions.assertThrows(SystemException.class,  () -> {
            var config = SubscriberConfig.builder()
                    .withCallback(System.out::println)
                    .build();
            var subscriber = subscriberService.createSubscriber(config);
            subscriberService.registerSubscriber("myQueue", subscriber.getId());
        });
    }

}
