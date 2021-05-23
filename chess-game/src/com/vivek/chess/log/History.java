package com.vivek.chess.log;

import com.vivek.chess.board.Position;
import com.vivek.chess.pieces.Piece;
import com.vivek.chess.pieces.PieceFactory;

import java.util.List;
import java.util.stream.Collectors;

public class History {

    private static final int MAX_LOGS = 50;

    private final int maxLogs;
    private int size;
    private Log front;
    private Log rear;

    public History() {
        this(-1);
    }

    public History(int maxLogs) {
        this.maxLogs = maxLogs > 0 ? Math.max(maxLogs, MAX_LOGS) : MAX_LOGS;
    }

    public void addLog(Log log) {
        if (size == maxLogs) {
            removeFront();
        }

        if (front == null) {
            front = log;
            rear = log;
        }
        rear.next = log;
        log.prev = rear;
        rear = log;
        size++;
    }

    public Log getRecentLog() {
        return rear;
    }

    public int getSize() {
        return size;
    }

    private void removeFront() {
        front = front.next;
        front.prev = null;
        size--;
    }

    public static final class Log {
        private final List<Piece> pieces;
        private final Position from;
        private final Position to;
        private Log prev;
        private Log next;

        private Log(List<Piece> pieces, Position from, Position to) {
            this.pieces = pieces;
            this.from = from;
            this.to = to;
        }

        public static Log of(List<Piece> pieces, Position from, Position to) {
            var piecesCopy = pieces.stream()
                    .map(piece -> PieceFactory.create(piece.getType(), piece.getColor(), piece.getPosition().copy()))
                    .collect(Collectors.toList());
            var fromCopy = from.copy();
            var toCopy = to.copy();
            return new Log(piecesCopy, fromCopy, toCopy);
        }

        public List<Piece> getPieces() {
            return pieces;
        }

        public Position getFrom() {
            return from;
        }

        public Position getTo() {
            return to;
        }
    }

}
