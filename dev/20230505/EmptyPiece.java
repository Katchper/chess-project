package org.group18;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class EmptyPiece extends Piece{

    /**
     * empty piece needed so i don't have to check for nulls
     */
    public EmptyPiece(){
        super();
        this.pieceChar = ' ';
    }

    @Override
    public void generateMoves(ChessBoard board, Tile piece) {
        super.generateMoves(board, piece);
    }

    @Override
    public void move(GridPane board, Tile oldTile, Tile newTile) {
        System.out.println("Empty piece cannot move");
       // throw new UnsupportedOperationException("Empty piece cannot move");
    }
}
