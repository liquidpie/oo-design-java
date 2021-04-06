package com.vivek.tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    private final Board board = new Board();
    private Turn turn = Turn.X;

    void play() {
        Scanner in = new Scanner(System.in);
        String winner = null;
        System.out.println("Welcome to 3x3 Tic Tac Toe.");
        board.printBoard();

        System.out.printf("%s will play first. ", turn.name());

        while (winner == null) {
            System.out.printf("%s's turn; Enter a slot number to place %s in:%n", turn.name(), turn.name());
            int input;
            try {
                input = in.nextInt();
                validateInput(input);
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input; re-enter slot number:");
                continue;
            }

            if (board.get(input).equals(String.valueOf(input))) {
                board.set(input, turn);

                board.printBoard();
                winner = checkWinner();

                turn = turn == Turn.X ? Turn.O : Turn.X;
            } else {
                System.out.println("Slot already taken; re-enter slot number:");
            }
        }

        if (winner.equalsIgnoreCase("draw")) {
            System.out.println("It's a draw! Thanks for playing.");
        } else {
            System.out.println("Congratulations! " + winner + "'s have won! Thanks for playing.");
        }

    }

    private void validateInput(int input) {
        if (!(input > 0 && input <= 9)) {
            throw new InputMismatchException();
        }
    }

    private String checkWinner() {
        for (int i = 0; i < 8; i++) {
            String streak = null;
            switch (i) {
                case 0:
                    streak = board.get(1) + board.get(2) + board.get(3);
                    break;
                case 1:
                    streak = board.get(4) + board.get(5) + board.get(6);
                    break;
                case 2:
                    streak = board.get(7) + board.get(8) + board.get(9);
                    break;
                case 3:
                    streak = board.get(1) + board.get(4) + board.get(7);
                    break;
                case 4:
                    streak = board.get(2) + board.get(5) + board.get(8);
                    break;
                case 5:
                    streak = board.get(3) + board.get(6) + board.get(9);
                    break;
                case 6:
                    streak = board.get(1) + board.get(5) + board.get(9);
                    break;
                case 7:
                    streak = board.get(3) + board.get(5) + board.get(7);
                    break;
            }

            if (streak.equals("XXX")) return Turn.X.name();
            else if (streak.equals("OOO")) return Turn.O.name();
        }

        // check if any cell is not filled
        for (int i = 1; i <= 9; i++) {
            if (board.get(i).equals(String.valueOf(i)))
                return null;
        }

        return "draw";
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }

}
