/**
 * The chess game's bishop class, inherits from "Piece".
 * @author Kacper Dziedzic [ktd1]
 * @version 1.3
 * @
 */

import java.util.ArrayList;

public class Bishop extends Piece {

    /**
     * The constructor for the bishop class.
     * @param color Is used to determine whether this piece belongs to player 1 or player 2.
     * This is reflected in "pieceName".
     */
    public Bishop(boolean color) {
        super(color);
        if (color) {
            this.pieceName = "white_bishop";
        } else {
            this.pieceName = "black_bishop";
        }
    }

    /**
     * Generates all the bishop moves using the lineMovement method, it then adds them to this bishop's moves list.
     * @param board The chessboard that is being played on.
     * @param piece The tile that is storing the bishop and its coordinates.
     */
    public void generateMoves(Board board, Tile piece) {
        ArrayList<Tile> squares = new ArrayList<>();
        squares.addAll(lineMovement(piece, board, 1, 1));
        squares.addAll(lineMovement(piece, board, -1, 1));
        squares.addAll(lineMovement(piece, board, 1, -1));
        squares.addAll(lineMovement(piece, board, -1, -1));
        moves = squares;
    }
}
