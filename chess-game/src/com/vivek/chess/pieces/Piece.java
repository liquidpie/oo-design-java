package com.vivek.chess.pieces;

import com.vivek.chess.board.Board;
import com.vivek.chess.board.Position;
import com.vivek.chess.types.PieceColor;
import com.vivek.chess.types.PieceType;

public abstract class Piece implements PieceMovement {

    protected final PieceType type;
    protected final PieceColor color;
    protected Position position;

    public Piece(PieceType type, PieceColor color, Position position) {
        this.type = type;
        this.color = color;
        this.position = position;
    }

    public PieceType getType() {
        return type;
    }

    public PieceColor getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public String getSymbol() {
        return color.getSymbol() + type.getSymbol();
    }

    public void move(Position newPosition) {
        this.position = newPosition;
    }

    public void setBoardHandle(Board board) {
        throw new UnsupportedOperationException();
    }

}
