public class Tile {

    private Piece piece;
    private int x;
    private int y;
    private boolean empty;

    /**
     * tile stores coords as well as if it's empty and a piece
     * @param x
     * @param y
     * @param piece
     */

    public Tile(int x, int y, Piece piece){
        this.piece = piece;
        this.x = x;
        this.y = y;
    }
    public Tile(int x, int y){
        this.empty = true;
        this.x = x;
        this.y = y;
        piece = new EmptyPiece();
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void flipCoords(){
        this.x = Math.abs(this.y-7);
        this.y = Math.abs(this.y-7);
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }


}
