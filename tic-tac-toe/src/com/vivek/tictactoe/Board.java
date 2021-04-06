package com.vivek.tictactoe;

public class Board {

    private final String[] boxes;

    public Board() {
        this.boxes = new String[9];
        for (int i = 0; i < 9; i++) {
            boxes[i] = String.valueOf(i + 1);
        }
    }

    public String get(int i) {
        return boxes[i - 1];
    }

    public void set(int i, Turn turn) {
        boxes[i - 1] = turn.name();
    }

    public void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + boxes[0] + " | " + boxes[1] + " | " + boxes[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + boxes[3] + " | " + boxes[4] + " | " + boxes[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + boxes[6] + " | " + boxes[7] + " | " + boxes[8] + " |");
        System.out.println("|---|---|---|");
    }

}
