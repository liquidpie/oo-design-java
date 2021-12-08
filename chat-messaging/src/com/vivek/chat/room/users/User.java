package com.vivek.chat.room.users;

import com.vivek.chat.room.messages.ContentType;
import com.vivek.chat.room.messages.Message;
import com.vivek.chat.room.messages.MessageContent;
import com.vivek.chat.room.messages.MessageReceipt;
import com.vivek.chat.room.service.ChatManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private List<Message> recMessages;
    private List<Message> sendMessages;
    private ChatManager chatManager;

    public User(String displayName) {
        this.recMessages = new ArrayList<>();
        this.sendMessages = new ArrayList<>();
        this.chatManager = new ChatManager();
    }


    public List<Message> getRecMessages() {
        return recMessages;
    }

    public List<Message> getSendMessages() {
        return sendMessages;
    }

    public void sendFriendRequest(String userId) {
        chatManager.addFriend(this.userId, userId);
    }

    public void acceptFriendRequest(String userId) {
        friends.add(userId);
    }

    public void removeFriend(String userId) {
        friends.remove(userId);
    }

    public void sendMessage(String receiver, String content) {
        var message = new Message(userId, receiver, content, new MessageContent(ContentType.TEXT, content));
        chatManager.sendMessage(userId, receiver, message);
        this.sendMessages.add(message);
    }

    public void receiveMessage(Message message) {
        message.setState(MessageReceipt.DELIVERED);
        this.recMessages.add(message);
    }

    public void seenMessage(Message message) {
        var msg = sendMessages.stream().filter(message1 -> message1.getMessageId().equals(message.getMessageId()));
        msg.setState(MessageReceipt.SEEN);
    }

}
