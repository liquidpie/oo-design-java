package com.vivek.fantasy.cricket.dao;

import com.vivek.fantasy.cricket.database.Database;
import com.vivek.fantasy.cricket.database.domain.ScoreKey;

public class PlayerScoreDAO {

    public void addScore(String userId, String playerId, Integer score) {
        Database.PLAYER_SCORE.put(new ScoreKey(userId, playerId), score);
    }

    public void updateScore(String userId, String playerId, Integer inc) {
        var key = new ScoreKey(userId, playerId);
        var score = Database.PLAYER_SCORE.getOrDefault(key, 0);
        Database.PLAYER_SCORE.put(key, score + inc);
    }

    public Integer getScore(String userId, String playerId) {
        var key = new ScoreKey(userId, playerId);
        return Database.PLAYER_SCORE.get(key);
    }

}
