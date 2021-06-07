package com.vivek.fantasy.cricket.dao;

import com.vivek.fantasy.cricket.database.Database;
import com.vivek.fantasy.cricket.domain.Player;

import java.util.Set;

public class PlayerDAO {

    public Set<Player> getAllPlayers() {
        return Set.copyOf(Database.PLAYERS.values());
    }

    public Player getPlayer(String id) {
        return Database.PLAYERS.get(id);
    }

    public void addPlayer(Player player) {
        Database.PLAYERS.put(player.getId(), player);
    }

    public void removePlayer(String id) {
        Database.PLAYERS.remove(id);
    }

}
