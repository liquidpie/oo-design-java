package com.vivek.fantasy.cricket.dao;

import com.vivek.fantasy.cricket.database.Database;

public class UserPointsDAO {

    public void addPoint(String userId, Integer point) {
        Database.USER_POINTS.put(userId, point);
    }

    public void updatePoint(String userId, Integer inc) {
        var point = Database.USER_POINTS.getOrDefault(userId, 0);
        Database.USER_POINTS.put(userId, point + inc);
    }

    public Integer getPoint(String userId) {
        return Database.USER_POINTS.get(userId);
    }

}
