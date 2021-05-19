package com.vivek.mqs.orchestration;

import com.vivek.mqs.pubsub.MessageQueue;
import com.vivek.mqs.domain.Message;
import com.vivek.mqs.json.JsonHandler;
import com.vivek.mqs.service.QueueService;

public class MessageDispatcher {

    private final QueueService queueService;

    public MessageDispatcher(QueueService queueService) {
        this.queueService = queueService;
    }

    public void dispatch(Message message, String qName) {
        String payload = JsonHandler.serialize(message);
        MessageQueue queue = queueService.getQueue(qName);
        queue.enqueue(payload);
    }

}
