package com.vivek.chess.pieces;

import com.vivek.chess.board.Board;
import com.vivek.chess.types.PieceColor;
import com.vivek.chess.types.PieceType;
import com.vivek.chess.board.Position;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece {

    private static final int[][] BEAM_INCREMENTS = {
            {0, -1}, {0, 1}, {-1, 0}, {1, 0}
    };

    public Rook(PieceColor color, Position position) {
        super(PieceType.ROOK, color, position);
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
