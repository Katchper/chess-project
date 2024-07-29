package org.group18;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Bishop extends Piece {

    public Bishop(boolean colour){
        super(colour);
        if (colour){
            this.pieceChar = 'b';
        } else {
            this.pieceChar = 'B';
        }
    }

    public void generateMoves(ChessBoard board, Tile piece){
        this.moves = generateDiagonalMoves(board,piece);
    }

    @Override
    public void move(GridPane board, Tile oldTile, Tile newTile) {

    }


}
