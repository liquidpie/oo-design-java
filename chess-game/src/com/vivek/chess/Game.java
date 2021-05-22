package com.vivek.chess;

import com.vivek.chess.board.Board;
import com.vivek.chess.board.GameState;
import com.vivek.chess.board.MoveCommand;
import com.vivek.chess.pieces.Piece;
import com.vivek.chess.render.BoardRenderer;
import com.vivek.chess.types.GameStatus;
import com.vivek.chess.types.PieceColor;

import java.util.Scanner;

public class Game {

    private final Board board;
    private final BoardRenderer renderer;
    private GameStatus status;
    private boolean finished;

    private final Scanner scanner;

    public Game(BoardRenderer renderer) {
        this.board = new Board();
        this.renderer = renderer;
        this.status = GameStatus.WHITE_MOVE;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        renderer.render(getState());

        while (!finished) {
            renderer.renderMessage(status);
            var command = getCommand();
            if (command == null) {
                renderer.renderMessage("Invalid command, please re-enter");
                continue;
            }
            if (!tryMove(command)) {
                renderer.renderMessage("Invalid move, please re-enter");
                continue;
            }

            board.executeMove(command);

            if (status == GameStatus.WHITE_MOVE)
                status = GameStatus.BLACK_MOVE;
            else if (status == GameStatus.BLACK_MOVE)
                status = GameStatus.WHITE_MOVE;

            renderer.render(getState());


        }

        // Game Over status message
        renderer.renderMessage(status);
    }

    private boolean tryMove(MoveCommand command) {
        var boardCopy = board.deepCopy();
        var srcPiece = boardCopy.getPiece(command.getSrc());

        if (srcPiece == null)
            return false;
        if (status == GameStatus.WHITE_MOVE && srcPiece.getColor() == PieceColor.BLACK ||
            status == GameStatus.BLACK_MOVE && srcPiece.getColor() == PieceColor.WHITE) {
            return false;
        }
        if (!srcPiece.getMoveablePositions(boardCopy).contains(command.getDst()) &&
            !srcPiece.getThreatenedPositions(boardCopy).contains(command.getDst())) {
            return false;
        }

        boardCopy.executeMove(command);

        for (Piece piece : boardCopy.getPieces()) {
            if (status == GameStatus.WHITE_MOVE &&
                    piece.getThreatenedPositions(boardCopy).contains(boardCopy.getWhiteKingPosition()))
                return false;
            if (status == GameStatus.BLACK_MOVE &&
                    piece.getThreatenedPositions(boardCopy).contains(boardCopy.getBlackKingPosition()))
                return false;
        }

        return true;
    }

    private MoveCommand getCommand() {
        return MoveCommand.fromString(scanner.nextLine());
    }

    public GameState getState() {
        return new GameState(board.getSize(), board.getPieces());
    }

}
