import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(char cr, boolean colour){
        super(cr, colour);
        if (colour){
            this.pieceChar = 'q';
        } else {
            this.pieceChar = 'Q';
        }
    }

    public ArrayList<Tile> generateMoves(Board board, Tile piece){
        return null;
    }
}
