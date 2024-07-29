import java.util.ArrayList;

/**
 * This class handles everything about a queen piece, and it extends the abstract class Piece.
 * @author (INSERT NAME HERE)
 * @version 1.0 the first iteration of javadoc.
 */
public class Queen extends Piece{

    /**
     * The constructor of the queen piece
     * @param color the color of the queen piece.
     */
    public Queen(boolean color){
        super(color);
        if (color){
            this.pieceName = "white_queen";
        } else {
            this.pieceName = "black_queen";
        }
    }

    /**
     * generates the moves for the queen in all 8 directions, then adds them to this
     * queen's move list
     * @param board
     * @param piece
     */
    public void generateMoves(Board board, Tile piece) {
        ArrayList<Tile> squares;
        squares = new ArrayList<>();
        squares.addAll(lineMovement(piece, board, 1, 1));
        squares.addAll(lineMovement(piece, board, -1, 1));
        squares.addAll(lineMovement(piece, board, 1, -1));
        squares.addAll(lineMovement(piece, board, -1, -1));
        squares.addAll(lineMovement(piece, board, 0, 1));
        squares.addAll(lineMovement(piece, board, 1, 0));
        squares.addAll(lineMovement(piece, board, -1, 0));
        squares.addAll(lineMovement(piece, board, 0, -1));
        moves = squares;
    }
}
