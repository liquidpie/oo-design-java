package com.vivek.fantasy.cricket.dao;

import com.vivek.fantasy.cricket.database.Database;
import com.vivek.fantasy.cricket.domain.User;

public class UserDAO {

    public void addUser(User user) {
        Database.USERS.put(user.getId(), user);
    }

    public void updateUser(String userId, String teamId, int credits) {
        User user = Database.USERS.get(userId);
        User updatedUser = new User(userId, user.getName(), teamId, credits);
        Database.USERS.put(userId, updatedUser);
    }

    public User getUser(String id) {
        return Database.USERS.get(id);
    }

    public void deleteUser(String id) {
        Database.USERS.remove(id);
    }

}
