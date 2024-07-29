package org.group18;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public abstract class Piece {

    protected ArrayList<Tile> moves;
    private boolean colour;
    protected char pieceChar;
    private ImageView imageView;

    public Piece(boolean colour){
        this.colour = colour;
        this.pieceChar = ' ';
        moves = new ArrayList<>();
    }
    public Piece(){

    }

    public ArrayList<Tile> getAllMoves(){
        return moves;
    }

    public void generateMoves(ChessBoard board, Tile piece){
    }

    /** checks the tile for the piece colour on it
     * 0 = empty 1 = enemy 2 = friendly 3 = out of bounds
     */

    public int checkForPiece(Tile tile){
        try {
            if (tile.isEmpty()){
                return 0;
            }
            if (tile.getPiece().getColour() != colour){
                return 1;
            }
        } catch (Exception ignore){
            return 3;
        }
        return 2;
    }
    /**
     * gets the piece character of a specific tile
     * 0 = empty 1 = pawn 2 = king 3 = queen 4 = rook 5 = bishop 6 = knight
     * @param tile
     * @return
     */
    public int checkWhichPiece(Tile tile){
        switch (tile.getPiece().pieceChar){
            case 'p':
            case 'P':
                return 1;
            case 'k':
            case 'K':
                return 2;
            case 'q':
            case 'Q':
                return 3;
            case 'r':
            case 'R':
                return 4;
            case 'b':
            case 'B':
                return 5;
            case 'n':
            case 'N':
                return 6;
        }
        return 0;
    }

    /**
     * get all of the straight moves in all directions until it reaches the
     * edge of the board or another piece
     * @param board
     * @param piece
     * @return
     */
    public ArrayList<Tile> generateStraightMoves(ChessBoard board, Tile piece){
        ArrayList<Tile> squares = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();
        for (int i = 1; i < 8; i++) {
            Tile temp = board.getTile(x+i, y);
            if (checkForPiece(temp) == 0){
                squares.add(temp);
            } else if (checkForPiece(temp) == 1){
                squares.add(temp);
                break;
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            Tile temp = board.getTile(x-i, y);
            if (checkForPiece(temp) == 0){
                squares.add(temp);
            } else if (checkForPiece(temp) == 1){
                squares.add(temp);
                break;
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            Tile temp = board.getTile(x, y+i);
            if (checkForPiece(temp) == 0){
                squares.add(temp);
            } else if (checkForPiece(temp) == 1){
                squares.add(temp);
                break;
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            Tile temp = board.getTile(x, y-i);
            if (checkForPiece(temp) == 0){
                squares.add(temp);
            } else if (checkForPiece(temp) == 1){
                squares.add(temp);
                break;
            } else {
                break;
            }
        }
        return squares;
    }

    /**
     * generate all of the diagonal moves from the chosen piece
     * until it reaches another piece or the outside of the board
     * @param board
     * @param piece
     * @return
     */
    public ArrayList<Tile> generateDiagonalMoves(ChessBoard board, Tile piece){
        ArrayList<Tile> squares = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();
        for (int i = 1; i < 8; i++) {
            Tile temp = board.getTile(x+i, y+i);
            if (checkForPiece(temp) == 0){
                squares.add(temp);
            } else if (checkForPiece(temp) == 1){
                squares.add(temp);
                break;
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            Tile temp = board.getTile(x-i, y-i);
            if (checkForPiece(temp) == 0){
                squares.add(temp);
            } else if (checkForPiece(temp) == 1){
                squares.add(temp);
                break;
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            Tile temp = board.getTile(x+i, y-i);
            if (checkForPiece(temp) == 0){
                squares.add(temp);
            } else if (checkForPiece(temp) == 1){
                squares.add(temp);
                break;
            } else {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            Tile temp = board.getTile(x-i, y+i);
            if (checkForPiece(temp) == 0){
                squares.add(temp);
            } else if (checkForPiece(temp) == 1){
                squares.add(temp);
                break;
            } else {
                break;
            }
        }
        return squares;
    }



    public abstract void move(GridPane board, Tile oldTile, Tile newTile);

    public boolean getColour() {
        return colour;
    }

    public void setColour(boolean colour) {
        this.colour = colour;
    }

    public char getPieceChar() {
        return pieceChar;
    }
    public ImageView getImageView() {
        return this.imageView;
    }


    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
