package com.vivek.url.shortener.service;

import com.vivek.url.shortener.dao.AuthDao;

public class AuthService {

    private final AuthDao authDao;

    public AuthService(AuthDao authDao) {
        this.authDao = authDao;
    }

    public boolean checkIfUserLoggedIn(String userId) {
        return authDao.isSessionActive(userId);
    }

    public void loginUser(String userId) {
        authDao.createSession(userId);
    }

    public void logoutUser(String userId) {
        authDao.deleteSession(userId);
    }

}
