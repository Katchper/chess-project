import java.util.ArrayList;

public class King extends Piece{
    private boolean hasMoved;

    public King(boolean colour){
        super(colour);
        hasMoved = false;
        if (colour){
            this.pieceChar = 'k';
        } else {
            this.pieceChar = 'K';
        }
    }

    public void generateMoves(Board board, Tile piece){
        ArrayList<Tile> moves = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();

        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                Tile temp = board.getTile(x+i, y+j);
                    //System.out.print(i);
                    //System.out.println(j);
                    if (checkForPiece(temp) < 2){
                        //System.out.print(x+i);
                        //System.out.println(x+j);
                        moves.add(temp);
                    }
            }

        }
        // moves.addAll(castlingMoves(board, piece));

        this.moves = moves;
    }

    // first check to see if the king has moved, then check if the rook(s) have moved
    // check if the squares are empty.
    // check if when it moves it puts the king into check
    // return valid castling moves

    public ArrayList<Tile> castlingMoves(Board board, Tile piece){
        ArrayList<Tile> moves = new ArrayList<>();
        King king = (King) piece.getPiece();
        int x = piece.getX();
        int y = piece.getY();
        Rook leftRook = new Rook(!king.getColour());
        Rook rightRook = new Rook(!king.getColour());

        if (board.getTile(0, y).getPiece().getPieceChar() == 'r' || board.getTile(0, y).getPiece().getPieceChar() == 'R' ){
            leftRook = (Rook) board.getTile(0, y).getPiece();
        }
        if (board.getTile(7, y).getPiece().getPieceChar() == 'r' || board.getTile(7, y).getPiece().getPieceChar() == 'R' ){
            rightRook = (Rook) board.getTile(7, y).getPiece();
        }

        char leftSquareOne = board.getTile(x+1, y).getPiece().getPieceChar();
        char leftSquareTwo = board.getTile(x+2, y).getPiece().getPieceChar();

        char rightSquareOne = board.getTile(x-1, y).getPiece().getPieceChar();
        char rightSquareTwo = board.getTile(x-2, y).getPiece().getPieceChar();
        char rightSquareThree = board.getTile(x-3, y).getPiece().getPieceChar();

        if (!king.hasMoved){
            if (!leftRook.hasMoved() && leftRook.getColour() == king.getColour()){
                if (leftSquareOne == ' ' && leftSquareTwo == ' '){
                    moves.add(board.getTile(x+2, y));
                }
            }
            if (!rightRook.hasMoved() && rightRook.getColour() == king.getColour()){
                if (rightSquareOne == ' ' && rightSquareTwo == ' ' && rightSquareThree == ' '){
                    moves.add(board.getTile(x-2, y));
                }
            }
        }
        return moves;
    }

}
