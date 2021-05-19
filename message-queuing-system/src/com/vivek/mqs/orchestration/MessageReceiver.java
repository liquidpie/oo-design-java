package com.vivek.mqs.orchestration;

import com.vivek.mqs.config.RetryConfig;
import com.vivek.mqs.domain.Message;
import com.vivek.mqs.json.JsonHandler;
import com.vivek.mqs.pubsub.MessageQueue;
import com.vivek.mqs.pubsub.Subscriber;

import java.util.concurrent.TimeUnit;

public class MessageReceiver {

    public void receive(MessageQueue queue, Subscriber subscriber) {
        Message message = JsonHandler.deserialize(queue.dequeue(), Message.class);
        try {
            subscriber.getCallback().accept(message);
        } catch (Exception ex) {
            System.err.println("Exception occurred while reading & processing message: " + ex.getMessage());
            tryReceive(message, queue, subscriber);
        }
    }

    private void tryReceive(Message message, MessageQueue queue, Subscriber subscriber) {
        RetryConfig retryConfig = queue.getRetryConfig();
        if (message.getRedeliveryAttempts() < retryConfig.getMaxAttempts()) {
            switch (retryConfig.getRetryPolicy()) {
                case DELAYED_RETRY:
                    delayedRetry(message, subscriber, retryConfig);
                    break;
                case ENQUEUE_MESSAGE:
                    enqueueRetry(message, queue);
                    break;
                case NONE:
                default:
            }
        }
    }

    private void delayedRetry(Message message, Subscriber subscriber, RetryConfig retryConfig) {
        while (message.getRedeliveryAttempts() < retryConfig.getMaxAttempts()) {
            message.setRedeliveryAttempts(message.getRedeliveryAttempts() + 1);
            try {
                TimeUnit.MILLISECONDS.sleep(retryConfig.getDelayMs());
                subscriber.getCallback().accept(message);
                break;
            } catch (Exception ignored) { }
        }
    }

    private void enqueueRetry(Message message, MessageQueue queue) {
        message.setRedeliveryAttempts(message.getRedeliveryAttempts() + 1);
        String packet = JsonHandler.serialize(message);
        queue.enqueue(packet);
    }

}
