import java.util.ArrayList;

public class Rook extends Piece{
    private boolean hasMoved;

    public Rook(boolean colour) {
        super(colour);
        hasMoved = false;
        if (colour) {
            this.pieceChar = 'r';
        } else {
            this.pieceChar = 'R';
        }
    }

    public void generateMoves(Board board, Tile piece){
        this.moves = generateStraightMoves(board, piece);
    }

    public boolean hasMoved() {
        return hasMoved;
    }

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }
}
