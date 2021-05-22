package com.vivek.chess.pieces;

import com.vivek.chess.board.Board;
import com.vivek.chess.types.PieceColor;
import com.vivek.chess.types.PieceType;
import com.vivek.chess.board.Position;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {

    private static final int[][] BEAM_INCREMENTS = {
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
    };

    public Bishop(PieceColor color, Position position) {
        super(PieceType.BISHOP, color, position);
    }

    @Override
    public List<Position> getThreatenedPositions(Board board) {
        List<Position> positions = new ArrayList<>();
        for (int[] increment : BEAM_INCREMENTS) {
            positions.addAll(board.beamSearchThreat(position, color, increment[0], increment[1]));
        }
        return positions;
    }

    @Override
    public List<Position> getMoveablePositions(Board board) {
        return getThreatenedPositions(board);
    }
}
