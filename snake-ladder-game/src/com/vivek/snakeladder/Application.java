package com.vivek.snakeladder;

import com.vivek.snakeladder.domain.Player;
import com.vivek.snakeladder.enums.PlayerCount;
import com.vivek.snakeladder.service.GameService;

import java.util.List;

public class Application {

    public static void main(String[] args) {

        GameService game = new GameService(PlayerCount.TWO);
        game.setPlayers(List.of(new Player("1", "John"), new Player("2", "James")));
        game.startGame();

    }

}
