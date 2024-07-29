import java.util.ArrayList;

public class Piece {

    private boolean colour;
    protected char pieceChar;
    public Piece(char character, boolean colour){
        this.colour = colour;
        this.pieceChar = character;
    }
    public Piece(){
        this.colour = false;
        this.pieceChar = ' ';
    }

    public ArrayList<Tile> generateMoves(Board board, Tile piece){
        return null;
    }

    /**
     * 0 = empty 1 = enemy 2 = friendly
     */

    public int checkForPiece(Tile tile){
        if (tile.isEmpty()){
            return 0;
        }
        if (tile.getPiece().getColour() != colour){
            return 1;
        }
        return 2;
    }

    /**
     * gets the piece character of a specific tile
     * 0 = empty 1 = pawn 2 = king 3 = queen 4 = rook 5 = bishop 6 = knight
     * @param tile
     * @return
     */
    public int checkWhichPiece(Tile tile){
        switch (tile.getPiece().pieceChar){
            case 'p':
            case 'P':
                return 1;

        }
        return 0;
    }

    public boolean getColour() {
        return colour;
    }

    public void setColour(boolean colour) {
        this.colour = colour;
    }

    public char getPieceChar() {
        return pieceChar;
    }
}
