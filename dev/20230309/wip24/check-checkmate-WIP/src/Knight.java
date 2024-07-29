import java.util.ArrayList;

public class Knight extends Piece{

    public Knight(boolean colour){
        super(colour);
        if (colour){
            this.pieceChar = 'n';
        } else {
            this.pieceChar = 'N';
        }
    }

    public void generateMoves(Board board, Tile piece){
        ArrayList<Tile> squares = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();
        for (int i = -2; i < 3; i+=4) {
            for (int j = -1; j < 2; j+=2) {
                if (checkForPiece(board.getTile((x+i), (y+j))) < 2){
                    squares.add(board.getTile((x+i), (y+j)));
                }
                if (checkForPiece(board.getTile(x+j, y+i)) < 2){
                    squares.add(board.getTile(x+j, y+i));
                }
            }
        }

        this.moves = squares;
    }
}
