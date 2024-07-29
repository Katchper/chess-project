package org.group18;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Rook extends Piece{
    private boolean hasMoved;

    public Rook(boolean colour) {
        super(colour);
        hasMoved = false;
        if (colour) {
            this.pieceChar = 'r';
        } else {
            this.pieceChar = 'R';
        }
    }

    public void generateMoves(ChessBoard board, Tile piece){
        this.moves = generateStraightMoves(board, piece);
    }

    @Override

    public void move(GridPane board, Tile oldTile, Tile newTile) {
        // update the board and the tiles
        newTile.setPiece(this);
        oldTile.setEmpty(true);
        oldTile.setPiece(new EmptyPiece());

        // update the piece's position
        setHasMoved(true);

        // update the GUI
        board.getChildren().remove(oldTile.getImageView());
        board.add(getImageView(), newTile.getX(), newTile.getY());
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
