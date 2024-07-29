import java.util.ArrayList;

public class Bishop extends Piece{

    public Bishop(char cr,boolean colour){
        super(cr, colour);
        if (colour){
            this.pieceChar = 'b';
        } else {
            this.pieceChar = 'B';
        }
    }

    public ArrayList<Tile> generateMoves(Board board, Tile piece){
        return null;
    }
}
