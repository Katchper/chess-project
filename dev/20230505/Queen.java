package org.group18;

import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(boolean colour){
        super(colour);
        if (colour){
            this.pieceChar = 'q';
        } else {
            this.pieceChar = 'Q';
        }
    }

    public void generateMoves(ChessBoard board, Tile piece){
        ArrayList<Tile> completeList = generateStraightMoves(board, piece);
        completeList.addAll(generateDiagonalMoves(board, piece));
        this.moves = completeList;
    }
    public void move(GridPane board, Tile start, Tile end) {
        // Implement the movement logic for a Queen here
    }
}
