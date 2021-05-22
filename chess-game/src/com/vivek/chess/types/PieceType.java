package com.vivek.chess.types;

public enum PieceType {
    PAWN("P"), ROOK("R"), BISHOP("B"), KNIGHT("N"), QUEEN("Q"), KING("K");

    private final String symbol;

    PieceType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
