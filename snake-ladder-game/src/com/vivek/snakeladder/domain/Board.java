package com.vivek.snakeladder.domain;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Board {

    private final int size;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private Map<String, Integer> playerPieces;

    public Board(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public Map<String, Integer> getPlayerPieces() {
        return playerPieces;
    }

    public void setSnakes(List<Snake> snakes) {
        this.snakes = snakes;
    }

    public void setLadders(List<Ladder> ladders) {
        this.ladders = ladders;
    }

    public void setPlayerPieces(Map<String, Integer> playerPieces) {
        this.playerPieces = playerPieces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return size == board.size && Objects.equals(snakes, board.snakes) && Objects.equals(ladders, board.ladders) && Objects.equals(playerPieces, board.playerPieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size, snakes, ladders, playerPieces);
    }

    @Override
    public String toString() {
        return "Board{" +
                "size=" + size +
                ", snakes=" + snakes +
                ", ladders=" + ladders +
                ", playerPieces=" + playerPieces +
                '}';
    }
}
