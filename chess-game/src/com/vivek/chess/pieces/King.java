package com.vivek.chess.pieces;

import com.vivek.chess.board.Board;
import com.vivek.chess.types.PieceColor;
import com.vivek.chess.types.PieceType;
import com.vivek.chess.board.Position;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    private static final int[][] SPOT_INCREMENTS = {
            {-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}
    };

    private Board boardHandle;

    public King(PieceColor color, Position position) {
        super(PieceType.KING, color, position);
    }

    @Override
    public List<Position> getThreatenedPositions(Board board) {
        List<Position> positions = new ArrayList<>();
        for (int[] increment : SPOT_INCREMENTS) {
            var newPosition = board.spotSearchThreat(position, color, increment[0], increment[1], false, false);
            if (newPosition != null)
                positions.add(newPosition);
        }
        return positions;
    }

    @Override
    public List<Position> getMoveablePositions(Board board) {
        return getThreatenedPositions(board);
    }

    public void setBoardHandle(Board board) {
        this.boardHandle = board;
        this.boardHandle.registerKingPosition(this);
    }

    @Override
    public void move(Position newPosition) {
        super.move(newPosition);
        boardHandle.registerKingPosition(this);
    }
}
