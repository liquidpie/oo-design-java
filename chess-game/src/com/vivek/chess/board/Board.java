package com.vivek.chess.board;

import com.vivek.chess.types.Constants;
import com.vivek.chess.pieces.Piece;
import com.vivek.chess.pieces.PieceFactory;
import com.vivek.chess.types.PieceColor;
import com.vivek.chess.types.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private final int size;
    private final List<Piece> pieces;
    private Position whiteKingPosition;
    private Position blackKingPosition;

    public Board() {
        this.size = Constants.CHESS_BOARD_SIZE;
        pieces = new ArrayList<>();
        initialize();
    }

    private void initialize() {
        for (PiecePosition piecePosition : Constants.INITIAL_PIECE_POSITION) {

            var whitePiece = PieceFactory.create(piecePosition.getType(), PieceColor.WHITE, piecePosition.getPosition());
            if (piecePosition.getType() == PieceType.KING) {
                assert whitePiece != null;
                whitePiece.setBoardHandle(this);
            }
            pieces.add(whitePiece);

            var pos = piecePosition.getPosition();
            var blackPiece = PieceFactory.create(piecePosition.getType(), PieceColor.BLACK, Position.of(size - pos.getX() - 1, size - pos.getY() - 1));
            if (piecePosition.getType() == PieceType.KING) {
                assert blackPiece != null;
                blackPiece.setBoardHandle(this);
            }
            pieces.add(blackPiece);
        }
    }

    public Piece getPiece(Position position) {
        return pieces.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .findFirst()
                .orElse(null);
    }

    public Position spotSearchThreat(Position startPosition, PieceColor ownColor, int incrementX, int incrementY, boolean threatOnly, boolean freeOnly) {
        int currentX = startPosition.getX() + incrementX;
        int currentY = startPosition.getY() + incrementY;

        if (!isSafe(currentX, currentY)) {
            return null;
        }

        var currentPosition = Position.of(currentX, currentY);
        var currentPiece = getPiece(currentPosition);

        if (currentPiece != null) {
            if (freeOnly) return null;
            return currentPiece.getColor() != ownColor ? currentPosition : null;
        }
        return !threatOnly ? currentPosition : null;
    }

    public List<Position> beamSearchThreat(Position startPosition, PieceColor ownColor, int incrementX, int incrementY) {
        int currentX = startPosition.getX() + incrementX;
        int currentY = startPosition.getY() + incrementY;

        List<Position> threatenedPositions = new ArrayList<>();
        while (isSafe(currentX, currentY)) {
            var currentPosition = Position.of(currentX, currentY);
            var currentPiece = getPiece(currentPosition);

            if (currentPiece != null) {
                if (currentPiece.getColor() != ownColor)
                    threatenedPositions.add(currentPosition);
                break;
            }
            threatenedPositions.add(currentPosition);

            currentX = currentX + incrementX;
            currentY = currentY + incrementY;
        }

        return threatenedPositions;
    }

    public void registerKingPosition(PieceColor color, Position position) {
        if (color == PieceColor.WHITE)
            whiteKingPosition = position;
        else if (color == PieceColor.BLACK)
            blackKingPosition = position;
    }

    public void executeMove(MoveCommand command) {
        var srcPiece = getPiece(command.getSrc());
        for (int i = 0; i < pieces.size(); i++) {
            var targetPiece = pieces.get(i);
            if (targetPiece.getPosition().equals(command.getDst())) {
                if (targetPiece.getType() == PieceType.KING) {
                    registerKingPosition(targetPiece.getColor(), null);
                }
                pieces.remove(i);
                break;
            }
        }
        srcPiece.move(command.getDst());
    }

    public Board deepCopy() {
        var board = new Board();
        board.pieces.clear();
        pieces.forEach(piece -> {
            var newPiece = PieceFactory.create(piece.getType(), piece.getColor(), piece.getPosition().copy());
            if (piece.getType() == PieceType.KING) {
                newPiece.setBoardHandle(board);
            }
            board.pieces.add(newPiece);
        });

        board.whiteKingPosition = this.whiteKingPosition.copy();
        board.blackKingPosition = this.blackKingPosition.copy();
        return board;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public Position getWhiteKingPosition() {
        return whiteKingPosition;
    }

    public Position getBlackKingPosition() {
        return blackKingPosition;
    }

    public int getSize() {
        return size;
    }

    private boolean isSafe(int coordinateX, int coordinateY) {
        return coordinateX < size && coordinateX >= 0 &&
                coordinateY < size && coordinateY >= 0;
    }
}
