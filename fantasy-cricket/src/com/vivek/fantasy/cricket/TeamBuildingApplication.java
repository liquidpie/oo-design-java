package com.vivek.fantasy.cricket;

import com.vivek.fantasy.cricket.dao.*;
import com.vivek.fantasy.cricket.domain.Player;
import com.vivek.fantasy.cricket.domain.User;
import com.vivek.fantasy.cricket.domain.enums.PlayerType;
import com.vivek.fantasy.cricket.service.ScoreManager;
import com.vivek.fantasy.cricket.service.TeamBuilder;

public class TeamBuildingApplication {

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        PlayerDAO playerDAO = new PlayerDAO();
        PlayerScoreDAO playerScoreDAO = new PlayerScoreDAO();
        TeamDAO teamDAO = new TeamDAO();
        UserPointsDAO userPointsDAO = new UserPointsDAO();
        AdminDAO adminDAO = new AdminDAO();

        TeamBuilder teamBuilder = new TeamBuilder(userDAO, playerDAO, teamDAO);
        ScoreManager scoreManager = new ScoreManager(playerScoreDAO, userPointsDAO);

        // Add user
        User user1 = new User("1", "A", null, 100);
        userDAO.addUser(user1);
        // Add Player
        Player p1 = new Player("1", "P1", PlayerType.BATSMAN, 10);
        Player p2 = new Player("2", "P2", PlayerType.BATSMAN, 8);
        Player p3 = new Player("3", "P3", PlayerType.BATSMAN, 11);
        Player p4 = new Player("4", "P4", PlayerType.BATSMAN, 9);
        Player p5 = new Player("5", "P5", PlayerType.BOWLER, 6);
        Player p6 = new Player("6", "P6", PlayerType.BOWLER, 15);
        Player p7 = new Player("7", "P7", PlayerType.BATSMAN, 12);
        Player p8 = new Player("8", "P8", PlayerType.BATSMAN, 7);
        Player p9 = new Player("9", "P9", PlayerType.BATSMAN, 4);
        Player p10 = new Player("10", "P10", PlayerType.WICKET_KEEPER, 13);
        Player p11 = new Player("11", "P11", PlayerType.BOWLER, 12);
        Player p12 = new Player("12", "P12", PlayerType.BOWLER, 8);
        Player p13 = new Player("13", "P13", PlayerType.BOWLER, 7);
        Player p14 = new Player("14", "P14", PlayerType.WICKET_KEEPER, 6);
        Player p15 = new Player("15", "P15", PlayerType.BATSMAN, 16);
        playerDAO.addPlayer(p1);
        playerDAO.addPlayer(p2);
        playerDAO.addPlayer(p3);
        playerDAO.addPlayer(p4);
        playerDAO.addPlayer(p5);
        playerDAO.addPlayer(p6);
        playerDAO.addPlayer(p7);
        playerDAO.addPlayer(p8);
        playerDAO.addPlayer(p9);
        playerDAO.addPlayer(p10);
        playerDAO.addPlayer(p11);
        playerDAO.addPlayer(p12);
        playerDAO.addPlayer(p13);
        playerDAO.addPlayer(p14);
        playerDAO.addPlayer(p15);

        // build team
        teamBuilder.startTeam(user1.getId());
        teamBuilder.addPlayer(p1.getId())
                .addPlayer(p2.getId())
                .addPlayer(p4.getId())
                .addPlayer(p5.getId())
                .addPlayer(p6.getId())
                .addPlayer(p14.getId())
                .addPlayer(p8.getId())
                .addPlayer(p7.getId())
                .addPlayer(p12.getId())
                .addPlayer(p13.getId())
                .addPlayer(p9.getId());

        var team = teamBuilder.buildTeam(user1.getId());
        System.out.println(team);

        // Scores
        scoreManager.addScore(user1.getId(), p1.getId(), 10);
        scoreManager.addScore(user1.getId(), p2.getId(), 8);
        scoreManager.addScore(user1.getId(), p4.getId(), 12);
        scoreManager.addScore(user1.getId(), p5.getId(), 15);

        System.out.println(scoreManager.getUserScore(user1.getId()));

        System.out.println(userDAO.getUser(user1.getId()));

    }

}
