package com.vivek.fantasy.cricket.service;

import com.vivek.fantasy.cricket.constraints.TeamCombination;
import com.vivek.fantasy.cricket.dao.PlayerDAO;
import com.vivek.fantasy.cricket.dao.TeamDAO;
import com.vivek.fantasy.cricket.dao.UserDAO;
import com.vivek.fantasy.cricket.domain.Player;
import com.vivek.fantasy.cricket.domain.Team;
import com.vivek.fantasy.cricket.domain.TempTeam;
import com.vivek.fantasy.cricket.domain.User;
import com.vivek.fantasy.cricket.exception.ErrorCode;
import com.vivek.fantasy.cricket.exception.ServiceException;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class TeamBuilder {

    private static final int MAX_TEAM_SIZE = 11;
    private static final TeamCombination combination = TeamCombination.COMBINATION1;

    private final UserDAO userDAO;
    private final PlayerDAO playerDAO;
    private final TeamDAO teamDAO;

    private TempTeam tempTeam = null;

    public TeamBuilder(UserDAO userDAO, PlayerDAO playerDAO, TeamDAO teamDAO) {
        this.userDAO = userDAO;
        this.playerDAO = playerDAO;
        this.teamDAO = teamDAO;
    }

    public void startTeam(String userId) {
        User user = userDAO.getUser(userId);
        tempTeam = new TempTeam(user.getCreditsBudget());
    }

    public TeamBuilder addPlayer(String playerId) {
        validateTeamSizeConstraint();
        Player player = playerDAO.getPlayer(playerId);
        validateCreditConstraint(player.getCredits());
        tempTeam.addPlayer(playerId, player.getCredits());
        return this;
    }

    public Team buildTeam(String userId) {
        var playerIds = tempTeam.getMembers().stream().map(TempTeam.Member::getPlayerId).collect(Collectors.toSet());
        if (validateTeamCombination(playerIds)) {
            String teamID = UUID.randomUUID().toString();
            Team team = new Team(teamID, userId, playerIds);
            teamDAO.createTeam(team);
            userDAO.updateUser(userId, teamID, tempTeam.getUserBudget());
            return team;
        } else {
            throw new ServiceException(ErrorCode.TEAM_COMBINATION_VIOLATED);
        }
    }

    private void validateTeamSizeConstraint() {
        if (tempTeam.getMembers().size() == MAX_TEAM_SIZE) {
            throw new ServiceException(ErrorCode.TEAM_SIZE_REACHED);
        }
    }

    private void validateCreditConstraint(int credits) {
        if (tempTeam.getUserBudget() < credits) {
            throw new ServiceException(ErrorCode.NOT_ENOUGH_BUDGET);
        }
    }

    private boolean validateTeamCombination(Set<String> playerIds) {

        int[] constraint = {combination.getBatsmanCount(), combination.getBowlerCount(), combination.getKeeperCount()};

        for (String playerId : playerIds) {
            Player player = playerDAO.getPlayer(playerId);
            switch (player.getType()) {
                case BATSMAN:
                    constraint[0]--;
                    break;
                case BOWLER:
                    constraint[1]--;
                    break;
                case WICKET_KEEPER:
                    constraint[2]--;
                    break;
            }
        }
        return constraint[0] <= 0 && constraint[1] <= 0 && constraint[2] <= 0;
    }

}
