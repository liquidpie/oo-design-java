package com.vivek.chess.render;

import com.vivek.chess.board.GameState;
import com.vivek.chess.pieces.Piece;
import com.vivek.chess.types.GameStatus;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ConsoleRenderer implements BoardRenderer {

    @Override
    public void render(GameState state) {
        String[][] table = new String[state.getBoardSize() + 1][state.getBoardSize() + 1];

        IntStream.range(0, state.getBoardSize() + 1).forEach(i -> Arrays.fill(table[i], ""));

        for (int i = 0; i < state.getBoardSize(); i++) {
            table[i][0] = (char) ('a' + i) + "";
            table[state.getBoardSize()][i + 1] = (char) ('1' + i) + "";
        }

        for (Piece piece : state.getPieces()) {
            var pos = piece.getPosition();
            table[pos.getX()][pos.getY() + 1] = piece.getSymbol();
        }

        String border = "    +----+----+----+----+----+----+----+----+";

        StringBuilder builder = new StringBuilder();
        IntStream.range(0, state.getBoardSize() + 1).forEach(ignored -> builder.append(" %2s |"));
        builder.append("\n");

        System.out.println("Chess Board\n");
        Stream.iterate(0, (i -> i < table.length), (i -> ++i))
                .forEach(ele -> {
                    System.out.println(border);
                    String format = ele != table.length - 1 ? builder.toString() : builder.toString().replaceAll("\\|", " ");
                    System.out.printf(format, table[ele]);
                });

    }

    @Override
    public void renderMessage(GameStatus status) {
        switch (status) {
            case WHITE_MOVE:
                System.out.println("White should make the move (for ex: a1 e5)");
                break;
            case BLACK_MOVE:
                System.out.println("Black should make the move (for ex: a1 e5)");
                break;
            case WHITE_VICTORY:
                System.out.println("White has Won !!");
                break;
            case BLACK_VICTORY:
                System.out.println("Black has Won !!");
                break;
        }
    }

    @Override
    public void renderMessage(String message) {
        System.out.println(message);
    }
}
