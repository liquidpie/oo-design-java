package com.vivek.mqs.domain;

import com.vivek.mqs.util.Utilities;

public class Message {

    private final String messageId;
    private final Object body;
    private final Long createdAt;
    private int redeliveryAttempts;

    public Message(Object body) {
        this.messageId = Utilities.newUUID();
        this.body = body;
        createdAt = System.currentTimeMillis();
    }

    public String getMessageId() {
        return messageId;
    }

    public Object getBody() {
        return body;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public int getRedeliveryAttempts() {
        return redeliveryAttempts;
    }

    public void setRedeliveryAttempts(int redeliveryAttempts) {
        this.redeliveryAttempts = redeliveryAttempts;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId='" + messageId + '\'' +
                ", body=" + body +
                ", createdAt=" + createdAt +
                ", redeliveryAttempts=" + redeliveryAttempts +
                '}';
    }
}
