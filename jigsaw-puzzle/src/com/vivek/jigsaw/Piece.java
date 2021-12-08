package com.vivek.jigsaw;

public class Piece {

    private Side side1, side2, side3, side4;

    public void rotate() {
        Side extraSide = side1;
        side1 = side2;
        side2 = side3;
        side3 = side4;
        side4 = extraSide;
    }

    public boolean isCornerPiece() {
        if ((side1.isEdge() && side2.isEdge() || (side2.isEdge() && side3.isEdge() || ... ) //etc - check all adjacent sides) {
        return true;
    }
    return false;
}

    public boolean isEdgePiece() {
        if (isCornerPiece()) {
            return false;
        }
        if (side1.isEdge() || side2.isEdge() || .. //etc - check all sides) {
        return true;
    }

public class Side {
    private enum Edge {IN, OUT, FLAT}
    private Edge edge;
    private Curvature curve;

    public boolean isEdge() {
        return edge == Edge.FLAT;
    }
}

}
