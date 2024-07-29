import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(boolean colour){
        super(colour);
        if (colour){
            this.pieceChar = 'b';
        } else {
            this.pieceChar = 'B';
        }
    }

    public void generateMoves(Board board, Tile piece){
        this.moves = generateDiagonalMoves(board,piece);
    }


}
