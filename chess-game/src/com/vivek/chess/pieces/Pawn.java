package com.vivek.chess.pieces;

import com.vivek.chess.board.Board;
import com.vivek.chess.types.PieceColor;
import com.vivek.chess.types.PieceType;
import com.vivek.chess.board.Position;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final int[][] SPOT_INCREMENTS = { {0, 1} };
    private static final int[][] SPOT_INCREMENTS_FIRST_MOVE = { {0, 1} , {0, 2} };
    private static final int[][] SPOT_INCREMENTS_TAKE = { {-1, 1}, {1, 1} };

    private boolean moved;

    public Pawn(PieceColor color, Position position) {
        super(PieceType.PAWN, color, position);
        this.moved = false;
    }

    @Override
    public List<Position> getThreatenedPositions(Board board) {
        List<Position> positions = new ArrayList<>();
        for (int[] increment : SPOT_INCREMENTS_TAKE) {
            var newPosition = board.spotSearchThreat(position, color, increment[0], color == PieceColor.WHITE ? increment[1] : -1 * increment[1], false, false);
            if (newPosition != null)
                positions.add(newPosition);
        }
        return positions;
    }

    @Override
    public List<Position> getMoveablePositions(Board board) {
        List<Position> positions = new ArrayList<>();

        for (int[] increment : moved ? SPOT_INCREMENTS : SPOT_INCREMENTS_FIRST_MOVE) {
            var newPosition = board.spotSearchThreat(position, color, increment[0], color == PieceColor.WHITE ? increment[1] : -1 * increment[1], false, true);
            if (newPosition != null)
                positions.add(newPosition);
        }

        for (int[] increment : SPOT_INCREMENTS_TAKE) {
            var newPosition = board.spotSearchThreat(position, color, increment[0], color == PieceColor.WHITE ? increment[1] : -1 * increment[1], true, false);
            if (newPosition != null)
                positions.add(newPosition);
        }

        return positions;
    }

    @Override
    public void move(Position newPosition) {
        super.move(newPosition);
        moved = true;
    }
}
