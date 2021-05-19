package com.vivek.mqs.pubsub;

import com.vivek.mqs.domain.Message;

import java.util.function.Consumer;

public interface Subscriber {

    void subscribe(String queueName);

    void unsubscribe();

    String getId();

    Consumer<Message> getCallback();

    int getFetchSize();

}
