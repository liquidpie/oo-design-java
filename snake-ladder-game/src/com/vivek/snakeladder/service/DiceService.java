package com.vivek.snakeladder.service;

import java.util.Random;

public class DiceService {

    public int rollDice() {
        return new Random().nextInt(7);
    }

}
