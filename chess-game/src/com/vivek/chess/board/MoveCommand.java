package com.vivek.chess.board;

public class MoveCommand {

    private final Position src;
    private final Position dst;

    public MoveCommand(Position src, Position dst) {
        this.src = src;
        this.dst = dst;
    }

    public Position getSrc() {
        return src;
    }

    public Position getDst() {
        return dst;
    }

    public static MoveCommand fromString(String str) {
        var tokens = str.split(" ");
        if (tokens.length != 2)
            return null;
        var src = Position.fromString(tokens[0]);
        var dst = Position.fromString(tokens[1]);
        if (src == null || dst == null)
            return null;
        return new MoveCommand(src, dst);
    }

}
