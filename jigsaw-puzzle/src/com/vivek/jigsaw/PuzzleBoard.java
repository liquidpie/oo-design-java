package com.vivek.jigsaw;

import java.util.Queue;

public class PuzzleBoard {
    private Piece[][] board;
    private Queue<Piece> unusedPieces;

    public void placePiece(Piece p, int row, int column) {
        board[row][column] = p;
    }

    public Piece getNextPiece() {
        return unusedPieces.poll();
    }

    public void returnPiece(Piece p) {
        unusedPieces.offer(p);
    }
}

