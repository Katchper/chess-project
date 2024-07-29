/**
 * The chess game's board class.
 * @author Archie Malvern [arm36], Kacper Dziedzic [ktd1]
 * @version 1.2
 * @version 1.3 Added javadocs for all methods.
 */

import java.util.ArrayList;

public class Board {

    private final Tile[][] gameBoard;
    private boolean flipped;

    /**
     * The constructor for the chess board class. Creates a new board and fills it with empty tiles.
     */
    public Board(){
        flipped = false;
        gameBoard = new Tile[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = new Tile(i, j);
            }
        }
    }

    /**
     * Add individual pieces to the board for testing
     * @param x the x-coordinate of the piece
     * @param y the y-coordinate of the piece
     * @param piece the type of piece
     */
    public void addPiece(int x, int y, Piece piece){
        gameBoard[x][y] =   new Tile(x,y, piece);
    }


    /**
     * Fills the board's tiles with pieces in the starting position for a game of chess.
     * Primary use is for testing.
     */
    public void initializeBoard(){

        for (int i = 0; i < 8; i++) {
            gameBoard[i][6] = new Tile(i,6, new Pawn(true));
            gameBoard[i][1] = new Tile(i , 1, new Pawn(false));
        }

        gameBoard[0][0] = new Tile(0,0, new Rook(false));
        gameBoard[7][0] = new Tile(7,0, new Rook(false));
        gameBoard[1][0] = new Tile(1,0, new Knight(false));
        gameBoard[6][0] = new Tile(6,0, new Knight(false));
        gameBoard[2][0] = new Tile(2,0, new Bishop(false));
        gameBoard[5][0] = new Tile(5,0, new Bishop(false));
        gameBoard[3][0] = new Tile(3,0, new Queen(false));
        gameBoard[4][0] = new Tile(4,0, new King(false));

        gameBoard[1][7] = new Tile(1,7, new Knight(true));
        gameBoard[6][7] = new Tile(6,7, new Knight(true));
        gameBoard[2][7] = new Tile(2,7, new Bishop(true));
        gameBoard[5][7] = new Tile(5,7, new Bishop(true));
        gameBoard[3][7] = new Tile(3,7, new Queen(true));
        gameBoard[4][7] = new Tile(4,7, new King(true));
        gameBoard[0][7] = new Tile(0,7, new Rook(true));
        gameBoard[7][7] = new Tile(7,7, new Rook(true));

    }

    /**
     * Returns a tile from the 2d array of tiles, based on its index.
     * @param x X index of array.
     * @param y Y index of array.
     * @return The tile at the specified index.
     */
    public Tile getTile(int x, int y){
        if (x < 0 || x > 7){
            return null;
        } else if (y < 0 || y > 7) {
            return null;
        }
        return gameBoard[x][y];
    }

    /**
     * Sets a tile to a specified place in the 2d array of tiles
     * @param x X index of array.
     * @param y Y index of array.
     * @param newTile The tile to be set in the array.
     */
    public void setTile(int y, int x, Tile newTile){
        if (x < 0 || x > 7){
            return;
        } else if (y < 0 || y > 7) {
            return;
        }
        gameBoard[x][y] = newTile;
    }

    /**
     * Returns a tile from the 2d array of tiles, based on its stored coordinates.
     * @param x X coordinates.
     * @param y Y coordinates.
     * @return The tile with the specified coordinates.
     */
    public Tile getActualTile(int x, int y){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (gameBoard[i][j].getX() == x && gameBoard[i][j].getY() == y){
                    return gameBoard[i][j];
                }
            }
        }
        return null;
    }

    /**
     * Outputs a textual representation of the board. (Only used for debugging / testing)
     */
    public void displayBoard(){
        String[][] storeNames  = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int n = getActualTile(i,j).getX();
                int m = getActualTile(i,j).getY();
                storeNames[n][m] = getActualTile(i,j).getPiece().getPieceName();
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("[" + storeNames[i][j] +"]");
            }
            System.out.println();
        }
    }

    /**
     * This method creates an exact copy of the board so that we can perform checks for various methods.
     */
    public Board cloneBoard(){
        Board clone = new Board();
        clone.setFlipped(flipped);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int pieceKind = getActualTile(i,j).getPiece().checkWhichPiece(getActualTile(i,j));
                boolean pieceColor = getActualTile(i,j).getPiece().getColor();
                int x = getActualTile(i, j).getX();
                int y = getActualTile(i, j).getY();
                clone.getActualTile(i,j).setEmpty(false);
                // checks the piece name and creates a new Piece in the tile based on the one in the original board
                switch (pieceKind) {
                    case 1 -> {
                        Pawn temporaryPawn = (Pawn) getActualTile(i, j).getPiece();
                        clone.getActualTile(i, j).setPiece(new Pawn(pieceColor, temporaryPawn.getMoveStatus()));
                    }
                    case 2 -> {
                        King temporaryKing = (King) getActualTile(i, j).getPiece();
                        clone.getActualTile(i, j).setPiece(new King(pieceColor, temporaryKing.hasMoved()));
                    }
                    case 3 -> clone.getActualTile(i, j).setPiece(new Queen(pieceColor));
                    case 4 -> {
                        Rook temporaryRook = (Rook) getActualTile(i, j).getPiece();
                        clone.getActualTile(i, j).setPiece(new Rook(pieceColor, temporaryRook.hasMoved()));
                    }
                    case 5 -> clone.getActualTile(i, j).setPiece(new Bishop(pieceColor));
                    case 6 -> clone.getActualTile(i, j).setPiece(new Knight(pieceColor));
                    default -> {
                        clone.getActualTile(i, j).setPiece(new EmptyPiece());
                        clone.getActualTile(i, j).setEmpty(true);
                    }
                }
                clone.getTile(i,j).setXY(x,y);
            }
        }

        return clone;
    }

    /**
     * This method iterates over the entire board to flip all coordinates,
     * then sets the flipped variable.
     */
    public void flipBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j].flipCoords();
            }
        }
        flipped = !flipped;
    }

    /**
     * CHANGE THIS COMMENT
     * my brain died bug-fixing, so I probably made the most complicated way
     * of checking if when you're castling you're moving through squares
     */

    public void generateMostMoves(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Tile tempTile;
                tempTile = getActualTile(i, j);
                if (tempTile.getPiece().checkWhichPiece(tempTile) == 2) {
                    tempTile.setMoveType(5);
                }
                tempTile.getPiece().clearMoves();
                tempTile.getPiece().generateMoves(this, tempTile);
            }
        }
    }

    /** CHANGE THIS COMMENT
     * my brain died bug-fixing, so I probably made the most complicated way
     * of checking if when you're castling you're moving through squares
     * @param firstX initial x-coordinate of the piece
     * @param firstY initial y-coordinate of the piece
     * @param destX final x-coordinate of the piece
     * @param destY final y-coordinate of the piece
     */
    public void simpleMove(int firstX, int firstY, int destX, int destY){
        Tile tileOne = getActualTile(firstX, firstY);
        Tile tileTwo = getActualTile(destX, destY);
        int storeX = tileOne.getX();
        int storeY = tileOne.getY();
        tileOne.setXY(tileTwo.getX(), tileTwo.getY());
        tileTwo.setXY(storeX, storeY);
        tileTwo.setPiece(new EmptyPiece());
        tileTwo.setEmpty(true);
        specificPieceUpdate(tileOne, 0);
    }
    /**
     * This method moves a piece from its original tile to another.
     * @param firstX initial x-coordinate of the piece
     * @param firstY initial y-coordinate of the piece
     * @param destX final x-coordinate of the piece
     * @param destY final y-coordinate of the piece
     */
    public void movePiece(int firstX, int firstY, int destX, int destY){
        //get the tile of the piece and it's destination
        Tile tileOne = getActualTile(firstX, firstY);
        Tile tileTwo = getActualTile(destX, destY);
        int storeX = tileOne.getX();
        int storeY = tileOne.getY();
        //check the move type of the move in question by checking through the moves of the piece
        int moveType = tileOne.getPiece().getMove(tileTwo.getX(), tileTwo.getY()).getMoveType();
        // move type 3 = en passant
        if (moveType == 3){
            executeEnPassant(tileTwo.getX(), tileTwo.getY());
            tileOne.setXY(tileTwo.getX(), tileTwo.getY());
            tileTwo.setXY(storeX, storeY);
            tileTwo.setEmpty(true);
            tileTwo.setPiece(new EmptyPiece());
        // move type 2 = castling
        } else if (moveType == 2) {
            executeCastling(tileOne, tileTwo);
        } else {
            // any other standard move
            // flips the coordinates of the piece tile and destination tile
            tileOne.setXY(tileTwo.getX(), tileTwo.getY());
            tileTwo.setXY(storeX, storeY);
            tileTwo.setPiece(new EmptyPiece());
            tileTwo.setEmpty(true);
            specificPieceUpdate(tileOne, moveType);
        }

    }

    /**
     * This method updates the current status of certain pieces:
     *  1. Pawn: changes its eligibility to move two tiles ahead.
     *  2. King: changes its eligibility to perform castling.
     *  3. Rook: changes its eligibility to perform castling.
     * @param currentPiece the current piece.
     * @param move the integer of the piece's move condition
     */
    public void specificPieceUpdate(Tile currentPiece, int move){
        switch (currentPiece.getPiece().getPieceName()) {
            case "white_pawn", "black_pawn" -> {
                Pawn temp1 = (Pawn) currentPiece.getPiece();
                if (move == 1) {
                    temp1.setMoveStatus(1);
                } else if (move == 0) {
                    temp1.setMoveStatus(0);
                }
            }
            case "white_king", "black_king" -> {
                King temp2 = (King) currentPiece.getPiece();
                temp2.setHasMoved(true);
            }
            case "white_rook", "black_rook" -> {
                Rook temp3 = (Rook) currentPiece.getPiece();
                temp3.setHasMoved(true);
            }
        }

    }

    /**
     * This method lets the king execute a castling move with a selected rook.
     * @param king      The player's king
     * @param rook      The target rook
     */
    public void executeCastling(Tile king, Tile rook){
        int kingX = king.getX();
        int kingY = king.getY();
        int rookX = rook.getX();
        int rookY = rook.getY();
        Tile rookDest;
        Tile kingDest;

        if (rookX-kingX < 0){
            kingDest = getActualTile(kingX-2, kingY);
            rookDest = getActualTile(kingX-1, kingY);
        } else {
            kingDest = getActualTile(kingX+2, kingY);
            rookDest = getActualTile(kingX+1, kingY);
        }

        int kingDestX = kingDest.getX();
        int kingDestY = kingDest.getY();

        rook.setXY(rookDest.getX(), rookDest.getY());
        rookDest.setXY(rookX, rookY);
        rookDest.setEmpty(true);
        rookDest.setPiece(new EmptyPiece());

        //sets the rook and king that they have moved now
        Rook rookPiece = (Rook) rook.getPiece();
        King kingPiece = (King) king.getPiece();
        kingPiece.setHasMoved(true);
        rookPiece.setHasMoved(true);

        king.setXY(kingDestX, kingDestY);
        kingDest.setXY(kingX, kingY);
        kingDest.setEmpty(true);
        kingDest.setPiece(new EmptyPiece());
    }

    /**
     * This method allows the pawn to perform en passant.
     * Note that this method itself does not check if the pawn is eligible to perform an en passant.
     * @param x the x-coordinate of the tile
     * @param y the y-coordinate of the tile
     */
    public void executeEnPassant(int x, int y){
        Tile tileOne = getActualTile(x, y+1);
        tileOne.setEmpty(true);
        tileOne.setPiece(new EmptyPiece());
    }

    /**
     * This method generates all the possible moves a piece can have at player's current turn.
     */
    public void generateAllMoves(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                Tile tempTile;
                tempTile = getActualTile(i, j);
                tempTile.getPiece().clearMoves();
                tempTile.getPiece().generateMoves(this, tempTile);
            }
        }
    }

    /**
     * This method filters all the moves of the pieces across the board based
     * on whether a move puts your own king into check.
     */
    public void filterAllMoves(){
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                getActualTile(i, j).getPiece().filterPieceMoves(this, getActualTile(i, j));
            }
        }
    }

    /**
     * This method gives all the legal moves from every pieces a player can play.
     * @param color the color chosen by the player
     * @return all the tiles where the legal moves are legal
     */
    public ArrayList<Tile> getAllColorMoves(boolean color){
        ArrayList<Tile> squares = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (getActualTile(i,j).getPiece().getColor() == color){
                    squares.addAll(getActualTile(i,j).getPiece().getAllMoves());
                }
            }
        }
        return squares;
    }

    /**
     * This method goes through the chess board to find the king of the color desired.
     * @param color the color (black or white)
     * @return the king object
     */
    public Tile findKing(boolean color){
        Tile king = null;
        boolean found = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece temp = getActualTile(i,j).getPiece();
                if (color == temp.getColor() && temp.checkWhichPiece(getActualTile(i,j)) == 2){
                    king = getActualTile(i,j);
                    found = true;
                    break;
                }
            }
            if (found){
                break;
            }
        }
        return king;
    }

    /**
     * This method changes the condition of the flipped boolean variable.
     * @param flipped the flipped status of the board.
     */
    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }

    /**
     * This method check whether the board is flipped or not.
     * @return the boolean value of flipped
     */
    public boolean isFlipped() {
        return flipped;
    }

}
