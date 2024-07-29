import java.util.ArrayList;

/**
 * This class handles everything about the knight piece in a chess board, and it extends the class Piece.
 * @author Kacper Dziedzic [ktd1]
 * @version 1.0 first iteration of javadoc
 */
public class Knight extends Piece{

    /**
     * The constructor of the knight piece
     * @param color the color of the knight piece
     */
    public Knight(boolean color){
        super(color);
        if (color){
            this.pieceName = "white_knight";
        } else {
            this.pieceName = "black_knight";
        }
    }

    /**
     * generates all the knight's moves by having the knight's 8 moves hard coded
     * and iterating over them and checking if there's an enemy piece, friendly piece, or if it's an empty tile
     * @param board the chess board
     * @param piece
     */
    public void generateMoves(Board board, Tile piece){
        ArrayList<Tile> squares = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();
        //all possible knight movements
        int[][] coordinates = {{2, 1},{-2, 1},{-2, -1},{2, -1},{1,-2},{-1,-2},{1,2},{-1,2}};
        for (int i = 0; i < 8; i++) {
            if (checkForPiece(board.getActualTile((x+coordinates[i][0]), (y+coordinates[i][1]))) < 2){
                squares.add(board.getActualTile((x+coordinates[i][0]), (y+coordinates[i][1])));
            }
        }
        this.moves = squares;
    }

}
