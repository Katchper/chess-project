import java.util.ArrayList;

public class Rook extends Piece{
    private boolean hasMoved;

    public Rook(char cr, boolean colour){
        super(cr, colour);
        hasMoved = false;
        if (colour){
            this.pieceChar = 'r';
        } else {
            this.pieceChar = 'R';
        }
    }
    public ArrayList<Tile> generateMoves(Board board, Tile piece){
        ArrayList<Tile> squares = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();
        Rook rook = (Rook) piece.getPiece();

        for (int i = 1; i < 8; i++) {
            switch (checkForPiece(board.getTile(x+i, 0))){
                case 0:
                    break;
                case 1:
                    break;
                case 2:
                    break;
            }
        }
        return squares;
    }
}
