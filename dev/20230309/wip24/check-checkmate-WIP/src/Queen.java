import java.util.ArrayList;

public class Queen extends Piece{

    public Queen(boolean colour){
        super(colour);
        if (colour){
            this.pieceChar = 'q';
        } else {
            this.pieceChar = 'Q';
        }
    }

    public void generateMoves(Board board, Tile piece){
        ArrayList<Tile> completeList = generateStraightMoves(board, piece);
        completeList.addAll(generateDiagonalMoves(board, piece));
        this.moves = completeList;
    }
}
