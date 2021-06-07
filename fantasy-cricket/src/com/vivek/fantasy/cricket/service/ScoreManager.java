package com.vivek.fantasy.cricket.service;

import com.vivek.fantasy.cricket.dao.PlayerScoreDAO;
import com.vivek.fantasy.cricket.dao.UserPointsDAO;

public class ScoreManager {

    private final PlayerScoreDAO playerScoreDAO;
    private final UserPointsDAO userPointsDAO;

    public ScoreManager(PlayerScoreDAO playerScoreDAO, UserPointsDAO userPointsDAO) {
        this.playerScoreDAO = playerScoreDAO;
        this.userPointsDAO = userPointsDAO;
    }

    public void addScore(String userId, String playerId, int score) {
        playerScoreDAO.addScore(userId, playerId, score);
        userPointsDAO.updatePoint(userId, score);
    }

    public int getUserScore(String userId) {
        return userPointsDAO.getPoint(userId);
    }

}
