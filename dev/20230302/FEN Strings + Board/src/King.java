import java.util.ArrayList;

public class King extends Piece{
    private boolean hasMoved;

    public King(char cr, boolean colour){
        super(cr, colour);
        hasMoved = false;
        if (colour){
            this.pieceChar = 'k';
        } else {
            this.pieceChar = 'K';
        }
    }

    public ArrayList<Tile> generateMoves(Board board, Tile piece){
        ArrayList<Tile> moves = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();
        King thisPiece = (King) piece.getPiece();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 && j != 0){
                    if (checkForPiece(board.getTile(x+i, y+j)) < 2){
                        moves.add(new Tile(x+i,y+j, thisPiece));
                    }
                }
            }

        }
        return moves;
    }

    public ArrayList<Tile> castling(){
        return null;
    }


}
