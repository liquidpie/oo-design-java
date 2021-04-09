package com.vivek.url.shortener.dao;

import com.vivek.url.shortener.dto.User;
import com.vivek.url.shortener.persistence.Database;

public class UsersDao {

    public User getUser(String userId) {
        return Database.USERS.get(userId);
    }

}
