package com.vivek.mqs.orchestration;

import com.vivek.mqs.config.RetryConfig;
import com.vivek.mqs.domain.Message;
import com.vivek.mqs.exception.SystemException;
import com.vivek.mqs.json.JsonHandler;
import com.vivek.mqs.pubsub.MessageQueue;
import com.vivek.mqs.pubsub.Subscriber;
import com.vivek.mqs.pubsub.impl.SimpleQueue;
import com.vivek.mqs.pubsub.impl.SimpleSubscriber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class MessageReceiverTest {

    private MessageReceiver messageReceiver;

    @BeforeEach
    void setup() {
        messageReceiver = new MessageReceiver();

    }

    @Test
    void shouldReceiveMessageSuccessfully() {
        MessageQueue queue = new SimpleQueue("myQueue");
        Message message = new Message("Hello World !!");
        queue.enqueue(JsonHandler.serialize(message));
        Set<String> set = new HashSet<>();

        Subscriber subscriber = new SimpleSubscriber("id", x -> set.add(x.getMessageId()), null, null);

        messageReceiver.receive(queue, subscriber);

        Assertions.assertEquals(1, set.size());
    }

    @Test
    void shouldRetryMessageByEnqueueMessagePolicy() {
        RetryConfig retryConfig = RetryConfig.builder().withMaxAttempts(1).withRetryPolicy(RetryConfig.RetryPolicy.ENQUEUE_MESSAGE).build();
        MessageQueue queue = new SimpleQueue("myQueue", null, retryConfig);
        Message message = new Message("Hello World !!");
        queue.enqueue(JsonHandler.serialize(message));

        Subscriber subscriber = new SimpleSubscriber("id", x -> { throw new SystemException("exception"); }, null, null);

        Assertions.assertEquals(0, message.getRedeliveryAttempts());

        messageReceiver.receive(queue, subscriber);

        Assertions.assertEquals(1, queue.size());
        message = JsonHandler.deserialize(queue.first(), Message.class);
        Assertions.assertEquals(1, message.getRedeliveryAttempts());
    }

    @Test
    void shouldRetryMessageByDelayedRetryPolicy() {
        RetryConfig retryConfig = RetryConfig.builder().withMaxAttempts(2).withRetryPolicy(RetryConfig.RetryPolicy.DELAYED_RETRY).build();
        MessageQueue queue = new SimpleQueue("myQueue", null, retryConfig);
        Message message = new Message("Hello World !!");
        queue.enqueue(JsonHandler.serialize(message));
        Set<String> set = new HashSet<>();
        AtomicInteger counter = new AtomicInteger();

        Consumer<Message> callback = x -> {
            if (counter.incrementAndGet() < 2) {
                throw new SystemException("exception");
            } else {
                set.add(x.getMessageId());
            }
        };
        Subscriber subscriber = new SimpleSubscriber("id", callback, null, null);

        Assertions.assertEquals(0, message.getRedeliveryAttempts());

        messageReceiver.receive(queue, subscriber);

        Assertions.assertEquals(0, queue.size());
        Assertions.assertEquals(1, set.size());
    }

}
