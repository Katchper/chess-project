import java.util.ArrayList;

/**
 * This class handles everything about the rook piece on the chessboard, and it extends the abstract class Piece.
 * @author Kacper Dziedzic [ktd1]
 * @version 1.0 first iteration of javadoc
 */
public class Rook extends Piece{

    private boolean hasMoved;

    /**
     * Constructor of the rook piece.
     * @param color the color of the rook piece.
     */
    public Rook(boolean color) {
        super(color);
        hasMoved = false;
        if (color) {
            this.pieceName = "white_rook";
        } else {
            this.pieceName = "black_rook";
        }
    }

    public Rook(boolean color, boolean hasMoved) {
        super(color);
        this.hasMoved = hasMoved;
        if (color) {
            this.pieceName = "white_rook";
        } else {
            this.pieceName = "black_rook";
        }
    }

    /**
     * using the lineMovement method it generates all the standard rook moves and
     * adds them to this rooks move list
     * @param board
     * @param piece
     */
    public void generateMoves(Board board, Tile piece) {
        ArrayList<Tile> squares = new ArrayList<>();
        squares.addAll(lineMovement(piece, board, 0, 1));
        squares.addAll(lineMovement(piece, board, 1, 0));
        squares.addAll(lineMovement(piece, board, -1, 0));
        squares.addAll(lineMovement(piece, board, 0, -1));
        moves = squares;
    }

    /**
     * checks if the rook has moved
     * @return if it moved
     */
    public boolean hasMoved() {
        return hasMoved;
    }

    /**
     * sets whether the rook has moved
     * @param hasMoved
     */
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
