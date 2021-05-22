package com.vivek.chess.types;

import com.vivek.chess.board.PiecePosition;
import com.vivek.chess.board.Position;
import com.vivek.chess.types.PieceType;

public class Constants {

    private Constants() { }

    public static final int CHESS_BOARD_SIZE = 8;

    public static final PiecePosition[] INITIAL_PIECE_POSITION = {
            PiecePosition.from(PieceType.ROOK,   Position.of(0, 0)),
            PiecePosition.from(PieceType.BISHOP, Position.of(1, 0)),
            PiecePosition.from(PieceType.KNIGHT, Position.of(2, 0)),
            PiecePosition.from(PieceType.QUEEN,  Position.of(3, 0)),
            PiecePosition.from(PieceType.KING,   Position.of(4, 0)),
            PiecePosition.from(PieceType.KNIGHT, Position.of(5, 0)),
            PiecePosition.from(PieceType.BISHOP, Position.of(6, 0)),
            PiecePosition.from(PieceType.ROOK,   Position.of(7, 0)),
            PiecePosition.from(PieceType.PAWN,   Position.of(0, 1)),
            PiecePosition.from(PieceType.PAWN,   Position.of(1, 1)),
            PiecePosition.from(PieceType.PAWN,   Position.of(2, 1)),
            PiecePosition.from(PieceType.PAWN,   Position.of(3, 1)),
            PiecePosition.from(PieceType.PAWN,   Position.of(4, 1)),
            PiecePosition.from(PieceType.PAWN,   Position.of(5, 1)),
            PiecePosition.from(PieceType.PAWN,   Position.of(6, 1)),
            PiecePosition.from(PieceType.PAWN,   Position.of(7, 1)),
    };

}
