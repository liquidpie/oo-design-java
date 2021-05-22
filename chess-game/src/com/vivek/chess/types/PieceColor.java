package com.vivek.chess.types;

public enum PieceColor {

    WHITE("W"), BLACK("B");

    private final String symbol;

    PieceColor(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
