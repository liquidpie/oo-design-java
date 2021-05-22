package com.vivek.chess.pieces;

import com.vivek.chess.board.Board;
import com.vivek.chess.types.PieceColor;
import com.vivek.chess.types.PieceType;
import com.vivek.chess.board.Position;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final int[][] SPOT_INCREMENTS = {
            {2, 1}, {-2, 1}, {1, 2}, {1, -2}, {2, -1}, {-2, -1}, {-1, -2}, {-1, 2}
    };

    public Knight(PieceColor color, Position position) {
        super(PieceType.KNIGHT, color, position);
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
}
