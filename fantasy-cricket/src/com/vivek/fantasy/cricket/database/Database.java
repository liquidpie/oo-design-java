package com.vivek.fantasy.cricket.database;

import com.vivek.fantasy.cricket.database.domain.ScoreKey;
import com.vivek.fantasy.cricket.domain.Admin;
import com.vivek.fantasy.cricket.domain.Player;
import com.vivek.fantasy.cricket.domain.Team;
import com.vivek.fantasy.cricket.domain.User;

import java.util.HashMap;
import java.util.Map;

public class Database {

    private Database() { }

    public static final Map<String, Player> PLAYERS = new HashMap<>();
    public static final Map<String, User> USERS = new HashMap<>();
    public static final Map<String, Admin> ADMINS = new HashMap<>();
    public static final Map<String, Team> TEAMS = new HashMap<>();

    public static final Map<ScoreKey, Integer> PLAYER_SCORE = new HashMap<>();
    public static final Map<String, Integer> USER_POINTS = new HashMap<>();

}
