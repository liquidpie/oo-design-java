package com.vivek.chess.board;

import com.vivek.chess.pieces.Piece;

import java.util.List;

public class GameState {

    private final int boardSize;
    private final List<Piece> pieces;

    public GameState(int boardSize, List<Piece> pieces) {
        this.boardSize = boardSize;
        this.pieces = pieces;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
