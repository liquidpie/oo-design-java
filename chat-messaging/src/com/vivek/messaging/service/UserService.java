package com.vivek.messaging.service;

import com.vivek.messaging.entities.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {

    private static final Map<String, User> USERS = new HashMap<>();

    public User getUser(String userId) {
        return USERS.get(userId);
    }

    public String addUser(String name) {
        var userId = UUID.randomUUID().toString();
        USERS.put(userId, new User(userId, name, null));
        return userId;
    }

    public void removeUser(String userId) {
        USERS.remove(userId);
    }

}
