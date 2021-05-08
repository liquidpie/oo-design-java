package com.vivek.snakeladder.service;

import com.vivek.snakeladder.domain.Board;
import com.vivek.snakeladder.domain.Ladder;
import com.vivek.snakeladder.domain.Player;
import com.vivek.snakeladder.domain.Snake;
import com.vivek.snakeladder.enums.PlayerCount;

import java.util.*;

public class GameService {

    private static final int BOARD_SIZE = 100;
    private static final int NUM_SNAKES = 8;
    private static final int NUM_LADDERS = 8;

    private final Board board;
    private final PlayerCount playerCount;
    private final SnakeLadderMappingService mappingService;
    private final DiceService diceService;

    private Queue<Player> players;

    public GameService(PlayerCount numberOfPlayers) {
        this(numberOfPlayers, new SnakeLadderMappingService(NUM_SNAKES, NUM_LADDERS));
    }

    public GameService(PlayerCount numberOfPlayers, SnakeLadderMappingService mappingService) {
        this.mappingService = mappingService;
        this.diceService = new DiceService();
        this.playerCount = numberOfPlayers;
        this.board = new Board(BOARD_SIZE);
        init();
    }

    private void init() {
        mappingService.init();
        this.board.setSnakes(mappingService.getSnakes());
        this.board.setLadders(mappingService.getLadders());
    }

    public void setPlayers(List<Player> playerList) {
        if (playerList.size() != playerCount.getCount()) {
            throw new IllegalArgumentException(String.format("Number of players should be %s", playerCount.getCount()));
        }
        this.players = new LinkedList<>();
        Map<String, Integer> playerPositions = new HashMap<>();
        for (Player player : playerList) {
            this.players.add(player);
            playerPositions.put(player.getId(), player.getInitPosition());
        }

        this.board.setPlayerPieces(playerPositions);
    }

    public void startGame() {
        while (!isGameCompleted()) {

            int diceValue = diceService.rollDice();
            Player currentPlayer = players.poll();
            move(currentPlayer, diceValue);

            if (hasPlayerWon(currentPlayer)) {
                System.out.println("Player " + currentPlayer.getName() + " has won the game");
                board.getPlayerPieces().remove(currentPlayer.getId());
            } else {
                players.add(currentPlayer);
            }
        }
    }

    private void move(Player player, int position) {
        int oldPosition = board.getPlayerPieces().get(player.getId());
        int newPosition = oldPosition + position;
        if (newPosition > BOARD_SIZE) {
            newPosition = oldPosition;
        } else {
            newPosition = getPositionAfterSnakesAndLadders(newPosition);
        }

        board.getPlayerPieces().put(player.getId(), newPosition);

        System.out.println(player.getName() + " rolled a " + position + ", and moved from " + oldPosition + " to " + newPosition);
    }

    private int getPositionAfterSnakesAndLadders(int position) {
        int prevPosition;
        do {
            prevPosition = position;

            for (Snake snake : board.getSnakes()) {
                if (position == snake.getHead()) {
                    position = snake.getTail();
                }
            }

            for (Ladder ladder : board.getLadders()) {
                if (position == ladder.getBottom()) {
                    position = ladder.getTop();
                }
            }
        } while (position != prevPosition);

        return position;
    }

    private boolean hasPlayerWon(Player player) {
        int position = board.getPlayerPieces().get(player.getId());
        return position == BOARD_SIZE;
    }

    private boolean isGameCompleted() {
        int currentNumberOfPlayers = players.size();
        return currentNumberOfPlayers < playerCount.getCount();
    }

}
