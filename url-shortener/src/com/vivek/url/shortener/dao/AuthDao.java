package com.vivek.url.shortener.dao;

import com.vivek.url.shortener.persistence.Database;

import java.sql.Timestamp;

public class AuthDao {

    public void createSession(String userId) {
        Database.SESSIONS.put(userId, new Timestamp(System.currentTimeMillis()));
    }

    public boolean isSessionActive(String userId) {
        return Database.SESSIONS.get(userId) != null;
    }

    public void deleteSession(String userId) {
        Database.SESSIONS.remove(userId);
    }

}
