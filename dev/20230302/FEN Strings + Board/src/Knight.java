import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(char cr, boolean colour){
        super(cr, colour);
        if (colour){
            this.pieceChar = 'n';
        } else {
            this.pieceChar = 'N';
        }
    }

    public ArrayList<Tile> generateMoves(Board board, Tile piece){
        ArrayList<Tile> squares = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();
        Knight thisPiece = (Knight) piece.getPiece();

        for (int i = -2; i < 3; i+=4) {
            for (int j = -1; j < 2; j+=2) {
                if (checkForPiece(board.getTile(i, j)) < 2){
                    squares.add(new Tile(x, y+1, thisPiece));
                }
                if (checkForPiece(board.getTile(j, i)) < 2){
                    squares.add(new Tile(x, y+1, thisPiece));
                }
            }
        }

        return squares;
    }
}
