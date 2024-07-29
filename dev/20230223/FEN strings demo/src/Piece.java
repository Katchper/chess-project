public class Piece {
    private final char symbol;
    private final boolean isWhite;

    public Piece(char symbol, boolean isWhite) {
        this.symbol = symbol;
        this.isWhite = isWhite;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isWhite() {
        return isWhite;
    }
}