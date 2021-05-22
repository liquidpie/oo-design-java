package com.vivek.chess.pieces;

import com.vivek.chess.board.Board;
import com.vivek.chess.board.Position;

import java.util.List;

public interface PieceMovement {

    List<Position> getThreatenedPositions(Board board);

    List<Position> getMoveablePositions(Board board);

}
