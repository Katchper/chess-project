import java.util.Arrays;

public class Chessboard {
    private Piece[] pieces;
    private Color toMove;

    public Chessboard(String fen) {
        pieces = new Piece[64];
        Arrays.fill(pieces, null);

        String[] fields = fen.split(" ");
        String[] rows = fields[0].split("/");
        int row = 0;
        int col = 0;
        for (String r : rows) {
            for (int i = 0; i < r.length(); i++) {
                char c = r.charAt(i);
                if (Character.isDigit(c)) {
                    col += Character.getNumericValue(c);
                } else {
                    pieces[row * 8 + col] = new Piece(getPieceType(c), getPieceColor(r.charAt(i)));
                    col++;
                }
            }
            row++;
            col = 0;
        }

        toMove = fields[1].equals("w") ? Color.WHITE : Color.BLACK;
    }

    public Piece getPiece(int square) {
        return pieces[square];
    }

    public Color getToMove() {
        return toMove;
    }

    private PieceType getPieceType(char c) {
        switch (c) {
            case 'p': return PieceType.PAWN;
            case 'n': return PieceType.KNIGHT;
            case 'b': return PieceType.BISHOP;
            case 'r': return PieceType.ROOK;
            case 'q': return PieceType.QUEEN;
            case 'k': return PieceType.KING;
            default: throw new IllegalArgumentException("Invalid FEN string: " + c);
        }
    }

    private Color getPieceColor(char c) {
        if (Character.isUpperCase(c)) {
            return Color.WHITE;
        } else {
            return Color.BLACK;
        }
    }

    public static class Piece {
        private final PieceType type;
        private final Color color;

        public Piece(PieceType type, Color color) {
            this.type = type;
            this.color = color;
        }

        public PieceType getType() {
            return type;
        }

        public Color getColor() {
            return color;
        }
    }
}
