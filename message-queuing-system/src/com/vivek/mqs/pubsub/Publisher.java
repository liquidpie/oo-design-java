package com.vivek.mqs.pubsub;

import com.vivek.mqs.domain.Message;

public interface Publisher {

    void publish(Message message);

    String getId();

}
