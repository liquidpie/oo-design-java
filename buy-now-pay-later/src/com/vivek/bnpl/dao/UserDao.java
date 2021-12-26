package com.vivek.bnpl.dao;

import com.vivek.bnpl.database.Database;
import com.vivek.bnpl.domain.User;

import java.math.BigInteger;

public class UserDao {

    public User getUser(String userId) {
        return Database.USERS.get(userId);
    }

    public boolean isBlackListed(String userId) {
        return Database.BLACKLISTED_USERS.contains(userId);
    }

    public void blacklistUser(String userId) {
        Database.BLACKLISTED_USERS.add(userId);
    }

    public void updateCreditLimit(String userId, BigInteger creditLimit) {
        var user = Database.USERS.get(userId);
        user.setCreditLimit(creditLimit);
    }

    public BigInteger getCreditLimit(String userId) {
        return Database.USERS.get(userId).getCreditLimit();
    }

}
