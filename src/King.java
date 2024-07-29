import java.util.ArrayList;

/**
 * This class handles everything about a king piece, which extends the interface Piece.
 * @author Kacper Dziedzic [ktd1]
 * @version 1.0 First iteration of javadoc
 */
public class King extends Piece{

    private boolean hasMoved;

    /**
     * This is a constructor of a King piece.
     * @param color sets the color of the king piece
     */
    public King(boolean color){
        super(color);
        hasMoved = false;
        if (color){
            this.pieceName = "white_king";
        } else {
            this.pieceName = "black_king";
        }
    }

    public King(boolean color, boolean hasMoved){
        super(color);
        this.hasMoved = hasMoved;
        if (color){
            this.pieceName = "white_king";
        } else {
            this.pieceName = "black_king";
        }
    }

    /**
     * This method tells you if the king has already moved
     * @return a boolean value representing its move condition
     */
    public boolean hasMoved() {
        return hasMoved;
    }

    /**
     * This method flips the move condition of the king piece.
     * @param hasMoved the original boolean value of the move condition.
     */
    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    /**
     * generates all the king's moves by having the king's 8 moves hard coded
     * and iterating over them and checking if there's an enemy piece, friendly piece, or if it's an empty tile
     * @param board The chessboard that is being played on.
     * @param piece The tile that is storing the king and its coordinates.
     */
    public void generateMoves(Board board, Tile piece) {
        ArrayList<Tile> squares = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();
        int[][] coordinates = {{1,0},{1,1},{1,-1},{-1,0},{-1,-1},{-1,1},{0,1},{0,-1}};
        for (int i = 0; i < 8; i++) {
            if (checkForPiece(board.getActualTile((x+coordinates[i][0]), (y+coordinates[i][1]))) < 2){
                board.getActualTile((x+coordinates[i][0]), (y+coordinates[i][1])).setMoveType(0);
                squares.add(board.getActualTile((x+coordinates[i][0]), (y+coordinates[i][1])));
            }
        }
        if (piece.getMoveType() != 5){
            squares.addAll(castlingMoves(board, piece));
        }

        this.moves = squares;
    }

    /**
     * Generates the 2 possible castling moves and performs the prerequisite checks to see if the moves are valid.
     * @param board The chessboard that is being played on.
     * @param piece The tile that is storing the king and its coordinates.
     * @return a list of the valid castling moves
     */
    public ArrayList<Tile> castlingMoves(Board board, Tile piece){
        ArrayList<Tile> squares = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();
        Tile rightRook = board.getActualTile(0, y);
        Tile leftRook = board.getActualTile(7, y);
        Tile move1 = new Tile(-1,-1);
        Tile move2 = new Tile(-1,-1);
        boolean canCastleLeft = false;
        boolean canCastleRight = false;

        if (!hasMoved() && !checkCastlingValidity(board, piece, piece)){
            // has not moved
            if (rightRook.getPiece().checkWhichPiece(rightRook) == 4){
                // piece on the right side is a rook
                Rook rook = (Rook) rightRook.getPiece();
                if (!rook.hasMoved()){
                    // if rook has not moved
                    for (int i = 1; i < x; i++) {
                        //all tiles between the king and rook
                        //if there are any tiles in between you can't castle
                        if (!board.getActualTile(i, y).isEmpty()){
                            canCastleLeft = false;
                            break;
                        } else {
                            canCastleLeft = true;
                        }
                    }
                    // check if the king isn't in check, and that he doesn't castle through a check
                    for (int i = x-2; i < x; i++) {
                        if (checkCastlingValidity(board, new Tile(i, y), piece)){
                            canCastleLeft = false;
                        }
                    }
                }
            }
            if (leftRook.getPiece().checkWhichPiece(leftRook) == 4){
                Rook rook = (Rook) leftRook.getPiece();
                if (!rook.hasMoved()){
                    //need to check every tile in between the king and rook
                    for (int i = 6; i > x; i--) {
                        if (!board.getActualTile(i, y).isEmpty()){
                            canCastleRight = false;
                            break;
                        } else {
                            canCastleRight = true;
                        }
                    }
                    // check if the king isn't in check, and he doesn't castle through a check
                    // should check x-1, x-2
                    for (int i = x+2; i > x; i--) {
                        if (checkCastlingValidity(board, new Tile(i, y), piece)){
                            canCastleRight = false;
                        }
                    }
                }
            }
        }

        if (canCastleRight){
            if (board.getActualTile(7, y) != null){
                move1.setXY(7, y);
                move1.setMoveType(2);
                squares.add(move1);
            }
        }
        if (canCastleLeft){
            if (board.getActualTile(0, y) != null){
                move2.setXY(0, y);
                move2.setMoveType(2);
                squares.add(move2);
            }
        }
        return squares;
    }

    /**
     * Method to check a specific tile to see if it puts your king in check
     * if it does it return's true.
     * @param board the chessboard
     * @param move the tile to move to coordinates
     * @param king the tile with the king's coordinates
     * @return true or false
     */
    public boolean checkCastlingValidity(Board board, Tile move, Tile king){

        int kingX = king.getX();
        int kingY = king.getY();
        boolean kingColor = king.getPiece().getColor();
        Board cloneBoard;
        cloneBoard = board.cloneBoard();
        int moveX = move.getX();
        int moveY = move.getY();
        // if coordinates aren't the same then move the king
        if (kingX != moveX || kingY != moveY){
            cloneBoard.simpleMove(kingX,kingY, moveX, moveY);
        }
        // generate the moves for the opposing color
        cloneBoard.generateMostMoves();

        Tile cloneKing1 = cloneBoard.findKing(kingColor);

        // check if any of the moves go over the king coordinates
        for (Tile enemyMove : cloneBoard.getAllColorMoves(!kingColor)){
            int enemyX = enemyMove.getX();
            int enemyY = enemyMove.getY();
            if (enemyX == cloneKing1.getX() && enemyY == cloneKing1.getY()){
                return true;
            }
        }
        return false;
    }


}
