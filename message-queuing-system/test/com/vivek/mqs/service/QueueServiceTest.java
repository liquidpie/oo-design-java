package com.vivek.mqs.service;

import com.vivek.mqs.config.QueueConfig;
import com.vivek.mqs.db.InMemoryDataStore;
import com.vivek.mqs.exception.SystemException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueueServiceTest {

    private QueueService queueService;
    private InMemoryDataStore dataStore;

    @BeforeEach
    void setup() {
        queueService = new QueueService();
        dataStore = InMemoryDataStore.getInstance();
    }

    @Test
    void shouldCreateQueueSuccessfully() {
        var config = QueueConfig.builder()
                .withName("myQueue")
                .build();

        queueService.createQueue(config);

        Assertions.assertEquals(1, dataStore.queueCollection.getAll().size());
    }

    @Test
    void shouldThrowExceptionWhenQueueExists() {
        Assertions.assertThrows(SystemException.class,  () -> {
            var config = QueueConfig.builder()
                    .withName("myQueue")
                    .build();

            queueService.createQueue(config);
            queueService.createQueue(config);
        });
    }


}
