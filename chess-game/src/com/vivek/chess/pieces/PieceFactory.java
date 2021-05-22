package com.vivek.chess.pieces;

import com.vivek.chess.board.Position;
import com.vivek.chess.types.PieceColor;
import com.vivek.chess.types.PieceType;

public class PieceFactory {

    public static Piece create(PieceType type, PieceColor color, Position position) {
        switch (type) {
            case KING:
                return new King(color, position);
            case QUEEN:
                return new Queen(color, position);
            case BISHOP:
                return new Bishop(color, position);
            case KNIGHT:
                return new Knight(color, position);
            case ROOK:
                return new Rook(color, position);
            case PAWN:
                return new Pawn(color, position);
        }
        return null;
    }

}
