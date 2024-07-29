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
    public void generateMoves(Board board, Tile piece) {
        super.generateMoves(board, piece);
    }
}
