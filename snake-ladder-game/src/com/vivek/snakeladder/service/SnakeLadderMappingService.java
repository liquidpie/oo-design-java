package com.vivek.snakeladder.service;

import com.vivek.snakeladder.domain.CellPair;
import com.vivek.snakeladder.domain.Ladder;
import com.vivek.snakeladder.domain.Snake;

import java.util.*;

public class SnakeLadderMappingService {

    private static final int START = 2;
    private static final int END = 100;

    private final int numberOfSnakes;
    private final int numberOfLadders;

    private final List<Snake> snakes;
    private final List<Ladder> ladders;

    private final Random random;

    public SnakeLadderMappingService(int numberOfSnakes, int numberOfLadders) {
        this.numberOfSnakes = numberOfSnakes;
        this.numberOfLadders = numberOfLadders;
        this.snakes = new ArrayList<>();
        this.ladders = new ArrayList<>();
        this.random = new Random();
    }

    public SnakeLadderMappingService(List<Snake> snakes, List<Ladder> ladders) {
        this.numberOfSnakes = snakes.size();
        this.numberOfLadders = ladders.size();
        this.snakes = snakes;
        this.ladders = ladders;
        this.random = new Random();
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public void init() {
        Set<CellPair> generatedPairs = new HashSet<>();
        CellPair cellPair = getCellPair();

        for (int i = 0; i < numberOfSnakes; i++) {
            while (generatedPairs.contains(cellPair))
                cellPair = getCellPair();
            generatedPairs.add(cellPair);
            snakes.add(new Snake(cellPair.getTop(), cellPair.getBottom()));
        }

        for (int i = 0; i < numberOfLadders; i++) {
            while (generatedPairs.contains(cellPair))
                cellPair = getCellPair();
            generatedPairs.add(cellPair);
            ladders.add(new Ladder(cellPair.getTop(), cellPair.getBottom()));
        }
    }

    private CellPair getCellPair() {
        int top = nextCell();
        int bottom = nextCell();

        while (bottom >= top) {
            bottom = nextCell();
        }

        return new CellPair(top, bottom);
    }

    private int nextCell() {
        return random.nextInt(END - START) + START;
    }

}
