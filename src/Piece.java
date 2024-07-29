import java.util.ArrayList;

/**
 * This abstract class sets up all the attributes of a regular chess piece, for example, its legal moves.
 * @author Kacper Dziedzic [ktd1]
 * @version 1.0 first iteration of javadoc
 */
public abstract class Piece {

    protected ArrayList<Tile> moves;
    private boolean color;
    protected String pieceName;

    /**
     * The constructor of a piece.
     * @param color the color of the piece.
     */
    public Piece(boolean color) {
        this.color = color;
        this.pieceName = "";
        moves = new ArrayList<>();
    }

    public Piece() {
        moves = new ArrayList<>();
    }

    /**
     * checks the tile for the piece color on it or if it's empty or out of bounds
     * 0 = empty, 1 = enemy, 2 = friendly, 3 = out of bounds
     */

    public int checkForPiece(Tile tile){
        try {
            if (tile.isEmpty()){
                return 0;
            }
            if (tile.getPiece().getColor() != color){
                return 1;
            }
        } catch (Exception ignore){
            return 3;
        }
        return 2;
    }
    /**
     * gets the piece character of a specific tile, this is primarily to
     * check if a piece is a rook or a pawn for the special moves
     * 0 = empty 1 = pawn 2 = king 3 = queen 4 = rook 5 = bishop 6 = knight
     * @param tile containing the piece to be checked
     * @return integer value representing the piece
     */
    public int checkWhichPiece(Tile tile){
        return switch (tile.getPiece().pieceName) {
            case "white_pawn", "black_pawn" -> 1;
            case "white_king", "black_king" -> 2;
            case "white_queen", "black_queen" -> 3;
            case "white_rook", "black_rook" -> 4;
            case "white_bishop", "black_bishop" -> 5;
            case "white_knight", "black_knight" -> 6;
            default -> 0;
        };
    }

    /**
     * gets the color
     * @return true or false
     */
    public boolean getColor() {
        return color;
    }

    /**
     * sets the color of this piece
     * @param color the color of the piece
     */
    public void setColor(boolean color) {
        this.color = color;
    }

    /**
     * gets the character of this piece
     * @return the piece name
     */
    public String getPieceName() {
        return pieceName;
    }

    /**
     * sets the character of this piece
     * @param pieceChar
     */
    public void setPieceName(String pieceChar) {
        this.pieceName = pieceChar;
    }


    /**
     * generates all the moves for this piece
     * @param board the Board in play
     * @param piece the Tile containing the piece
     */
    public void generateMoves(Board board, Tile piece){
    }

    /**
     * get all the straight moves in a chosen direction until it reaches the
     * edge of the board or another piece
     * @param piece the piece the moves are being generates for
     * @param board the current board layout
     * @param m if we're checking along the x value
     * @param n if we're checking along the y value
     * @return a list of all the moves the piece can make in the chosen line
     */
    public ArrayList<Tile> lineMovement(Tile piece, Board board, int m, int n) {
        ArrayList<Tile> squares = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();
        for (int index = 1; index < 8; index++) {
            Tile temp = board.getActualTile(x+(index*m),y+(index*n));
            if (checkForPiece(temp) == 0) {
                temp.setMoveType(0);
                squares.add(temp);
            } else if (checkForPiece(temp) == 1) {
                temp.setMoveType(0);
                squares.add(temp);
                break;
            } else {
                break;
            }
        }
        return squares;
    }

    /**
     * This method filters all the illegal moves from every possible moves of a piece.
     * It does this by getting the piece coordinates, then for every move the piece has,
     * it clones the board into "cloneBoard" and performs that move within the cloned board.
     * ---
     * then all the enemies moves are generated and iterating over them, we
     * check if any of them pass over the king and if they do then that move is
     * deemed invalid and removed from the piece.
     * @param board the chessboard
     * @param piece the selected chess piece
     */
    public void filterPieceMoves(Board board, Tile piece){
        ArrayList<Tile> movesToRemove = new ArrayList<>();

        int pieceX = piece.getX();
        int pieceY = piece.getY();

        for (Tile move : piece.getPiece().getAllMoves()) {
            Board cloneBoard;
            cloneBoard = board.cloneBoard();
            cloneBoard.generateAllMoves();
            int moveX = move.getX();
            int moveY = move.getY();
            if (move.getMoveType() != 2){

                cloneBoard.movePiece(pieceX,pieceY, moveX, moveY);
                cloneBoard.generateAllMoves();

                Tile king = cloneBoard.findKing(piece.getPiece().getColor());

                for (Tile enemyMove : cloneBoard.getAllColorMoves(!piece.getPiece().getColor())){
                    int enemyX = enemyMove.getX();
                    int enemyY = enemyMove.getY();
                    if (enemyX == king.getX() && enemyY == king.getY()){
                        movesToRemove.add(move);
                        break;
                    }
                }
            }

        }
        piece.getPiece().removeAllMoves(movesToRemove);
    }

    /**
     * gets all the moves from Piece
     * @return all the moves the piece has
     */
    public ArrayList<Tile> getAllMoves(){
        return moves;
    }

    /**
     * Clears all the moves within the piece
     */
    public void clearMoves(){
        moves.clear();
    }

    /**
     * sets the moves for this piece
     * @param moves the new list of moves for the piece
     */
    public void setMoves(ArrayList<Tile> moves) {
        this.moves = moves;
    }

    /**
     * This method returns a specific move it can do unless it doesn't
     * have it then null is returned
     * @param x x-coordinate of the piece
     * @param y y-coordinate of the piece
     * @return move or null
     */
    public Tile getMove(int x, int y){
        for (Tile move: moves) {
            if (move.getX() == x && move.getY() == y){
                return move;
            }
        }
        return null;
    }

    /**
     * This method removes all the moves of a piece by comparing the x and y of the move
     * against the moves sent through the parameter "moves"
     * @param moves the ArrayList of the moves of the piece
     */
    public void removeAllMoves(ArrayList<Tile> moves){
        ArrayList<Tile> movesToRemove = new ArrayList<>();
        for (Tile current: moves) {
            int moveX = current.getX();
            int moveY = current.getY();
            for (Tile moveToCheck:getAllMoves()) {
                if (moveX == moveToCheck.getX() && moveY == moveToCheck.getY()){
                    movesToRemove.add(moveToCheck);
                }
            }
        }
        this.moves.removeAll(movesToRemove);
    }
}
