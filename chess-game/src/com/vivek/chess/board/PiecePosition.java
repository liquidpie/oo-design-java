package com.vivek.chess.board;

import com.vivek.chess.types.PieceType;

public class PiecePosition {

    private final PieceType type;
    private final Position position;

    private PiecePosition(PieceType type, Position position) {
        this.type = type;
        this.position = position;
    }

    public static PiecePosition from(PieceType type, Position position) {
        return new PiecePosition(type, position);
    }

    public PieceType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }
}
