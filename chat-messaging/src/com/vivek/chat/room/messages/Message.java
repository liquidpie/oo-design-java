package com.vivek.chat.room.messages;

import java.util.UUID;

public class Message {

    private String messageId;
    private String sender;
    private String receiver;
    private MessageContent content;
    private MessageReceipt state;

    public Message(String sender, String receiver, MessageContent content) {
        this.messageId = UUID.randomUUID().toString();
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.state = MessageReceipt.SENT;
    }

    public String getMessageId() {
        return messageId;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public MessageContent getContent() {
        return content;
    }

    public MessageReceipt getState() {
        return state;
    }

    public void setState(MessageReceipt state) {
        this.state = state;
    }
}
