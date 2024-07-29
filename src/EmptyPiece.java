/**
 * The chess game's empty piece class, inherits from "Piece".
 * @author Kacper Dziedzic [ktd1]
 * @version 1.3
 * @
 */
public class EmptyPiece extends Piece{

    /**
     * Empty piece constructor, the purpose of this class is to remove the need to check for null pieces.
     */
    public EmptyPiece(){
        super();
        this.pieceName = "";
    }


    /**
     * Generate moves placeholder which generates an empty list
     * @param board original board
     * @param piece the piece location
     */
    @Override
    public void generateMoves(Board board, Tile piece) {
        super.generateMoves(board, piece);
    }
}

