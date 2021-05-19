package com.vivek.mqs.service;

import com.vivek.mqs.db.InMemoryDataStore;
import com.vivek.mqs.db.schema.Subscription;
import com.vivek.mqs.exception.SystemException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PublisherServiceTest {

    private PublisherService publisherService;
    private InMemoryDataStore dataStore;

    @BeforeEach
    void setup() {
        publisherService = new PublisherService(new QueueService());
        dataStore = InMemoryDataStore.getInstance();
    }

    @AfterEach
    void teardown() {
        for (String id : dataStore.publisherCollection.getAll().keySet()) {
            dataStore.publisherCollection.delete(id);
        }
    }

    @Test
    void shouldCreatePublisherSuccessfully() {
        dataStore.subscriptionCollection.insert("myQueue", new Subscription("queueName"));
        var publisher = publisherService.createPublisher("myQueue");
        Assertions.assertEquals(1, dataStore.publisherCollection.getAll().size());
        Assertions.assertEquals(dataStore.subscriptionCollection.get("myQueue").getPublisherId(), publisher.getId());
    }

    @Test
    void shouldThrowExceptionWhenPublisherExists() {
        Assertions.assertThrows(SystemException.class,  () -> {
            publisherService.createPublisher("myQueue");
            publisherService.createPublisher("myQueue");
        });
    }

}
