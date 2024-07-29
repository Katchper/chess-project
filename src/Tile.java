/**
 * The chess game's tile class also used for defining moves.
 * Referencing Software Engineering Group 18 Project Design Specification.
 * @author Jasper Crabb [jac127], Kacper Dziedzic [ktd1].
 * @version 1.3
 */

public class Tile {
    private int coordX;
    private int coordY;
    private Piece piece;
    private boolean isEmpty;
    private int moveType;

    /**
     * The constructor for the tile class. Initializes the tile with its coordinates and the piece in it.
     * @param coordX the x coordinate of the tile.
     * @param coordY the y coordinate of the tile.
     * @param piece the piece in this tile's position.
     */
    public Tile(int coordX, int coordY, Piece piece) {
        moveType = 0;
        this.coordX = coordX;
        this.coordY = coordY;
        this.piece = piece;
    }

    /**
     * The second constructor for the tile class. It initializes the tile with an empty piece, this is for squares on
     * the board with no pieces.
     * @param coordX the x coordinate of the tile.
     * @param coordY the y coordinate of the tile.
     */
    public Tile(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.piece = new EmptyPiece();
        this.isEmpty = true;
    }

    /**
     * Flips the coordinates of the tile.
     * This is needed so that generate moves method in the piece classes can work for both players.
     */
    public void flipCoords() {
        coordX = Math.abs(coordX-7);
        coordY = Math.abs(coordY-7);
    }

    /**
     * Getter for isEmpty.
     * @return boolean representing if the tile contains a piece.
     */
    public boolean isEmpty() {
        return isEmpty;
    }

    /**
     * Setter for isEmpty, representing if the tile contains a piece.
     */
    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    /**
     * Getter for getCoordX.
     * @return x coordinate of the tile.
     */

    public void setXY(int x, int y){
        this.coordX = x;
        this.coordY = y;
    }
    public int getX() {
        return coordX;
    }

    /**
     * Setter for the tile's x coordinate.
     * @param coordX the x coordinate.
     */
    public void setX(int coordX) {
        this.coordX = coordX;
    }

    /**
     * Getter for getCoordX.
     * @return y coordinate of the tile.
     */
    public int getY() {
        return coordY;
    }

    /**
     * Setter for the tile's y coordinate.
     * @param coordY the y coordinate.
     */
    public void setY(int coordY) {
        this.coordY = coordY;
    }

    /**
     * Getter for the tile's piece.
     * @return the piece object of the tile.
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * Setter for the tile's piece.
     * @param piece the piece object of the tile.
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * Gives the move-type of a piece
     * @return an integer which corresponds to a certain move-type.
     */
    public int getMoveType() {
        return moveType;
    }

    /**
     * Setter of the move-type of a piece
     * @param moveType an integer whoch corresponds to a certain move-type.
     */
    public void setMoveType(int moveType) {
        this.moveType = moveType;
    }
}
