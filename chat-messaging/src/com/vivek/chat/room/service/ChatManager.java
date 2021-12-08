package com.vivek.chat.room.service;

import com.vivek.chat.room.messages.Message;
import com.vivek.chat.room.users.User;

import java.util.ArrayList;
import java.util.List;

public class ChatManager {

    private List<User> users;

    public ChatManager() {
        users = new ArrayList<>();
    }

    public User getUser(String userId) {
        return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst().orElse(null);
    }

    public void addUser(String name) {
        users.add(new User(name));
    }

    public void removeUser(String userId) {
        users.remove(getUser(userId));
    }

    public void addFriend(String senderId, String friendId) {
        User sender = getUser(senderId);
        User friend = getUser(friendId);

        friend.acceptFriendRequest(senderId);
        sender.acceptFriendRequest(friendId);
    }

    public void removeFriend(String senderId, String friendId) {
        User sender = getUser(senderId);
        User friend = getUser(friendId);
        sender.removeFriend(friendId);
        friend.removeFriend(senderId);
    }

    public void sendMessage(String senderId, String receiverId, Message message) {
        User sender = getUser(senderId);
        User receiver = getUser(receiverId);
//        receiver.receiveMessage(message);
//        sender.seenMessage(message);
    }

}
