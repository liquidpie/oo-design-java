package com.vivek.fantasy.cricket.dao;

import com.vivek.fantasy.cricket.database.Database;
import com.vivek.fantasy.cricket.domain.Team;

public class TeamDAO {

    public void createTeam(Team team) {
        Database.TEAMS.put(team.getId(), team);
    }

    public Team getTeam(String id) {
        return Database.TEAMS.get(id);
    }

    public void updateTeam(Team team) {
        Database.TEAMS.put(team.getId(), team);
    }

    public void removeTeam(String id) {
        Database.TEAMS.remove(id);
    }

}
