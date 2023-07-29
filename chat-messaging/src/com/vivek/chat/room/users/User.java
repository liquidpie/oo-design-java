package com.vivek.chat.room.users;

import com.vivek.chat.room.messages.ContentType;
import com.vivek.chat.room.messages.Message;
import com.vivek.chat.room.messages.MessageContent;
import com.vivek.chat.room.messages.MessageReceipt;
import com.vivek.chat.room.service.ChatManager;

import java.util.*;

public class User {

    private final String userId;

    private final List<Message> recMessages;
    private final List<Message> sendMessages;
    private final ChatManager chatManager;
    private final Set<String> friends;

    public User(String displayName) {
        this.userId = displayName;
        this.recMessages = new ArrayList<>();
        this.sendMessages = new ArrayList<>();
        this.chatManager = new ChatManager();
        this.friends = new HashSet<>();
    }

    public String getUserId() {
        return userId;
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
        var message = new Message(userId, receiver, new MessageContent(ContentType.TEXT, content));
        chatManager.sendMessage(userId, receiver, message);
        this.sendMessages.add(message);
    }

    public void receiveMessage(Message message) {
        message.setState(MessageReceipt.DELIVERED);
        this.recMessages.add(message);
    }

    public void seenMessage(Message message) {
        var msg = sendMessages.stream().filter(message1 -> message1.getMessageId().equals(message.getMessageId())).findFirst();
        msg.ifPresent(m -> m.setState(MessageReceipt.SEEN));
    }

}
