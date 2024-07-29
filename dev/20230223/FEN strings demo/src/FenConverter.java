import java.util.Arrays;

public class FenConverter {
    private static final int BOARD_SIZE = 8;

    public static void main(String[] args) {
        // Test conversion from FEN string to Piece array
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        Piece[][] pieces = fenToPieceArray(fen);
        System.out.println(pieceArrayToText(pieces));

        // Test conversion from Piece array to FEN string
        String fenString = pieceArrayToFen(pieces);
        System.out.println(fenString);
    }

    public static Piece[][] fenToPieceArray(String fen) {
        Piece[][] pieces = new Piece[BOARD_SIZE][BOARD_SIZE];
        String[] fenComponents = fen.split(" ");
        String boardLayout = fenComponents[0];
        int rowIndex = 0;
        int colIndex = 0;

        for (int i = 0; i < boardLayout.length(); i++) {
            char c = boardLayout.charAt(i);
            if (c == '/') {
                rowIndex++;
                colIndex = 0;
            } else if (Character.isDigit(c)) {
                int emptySquares = c - '0';
                for (int j = 0; j < emptySquares; j++) {
                    pieces[rowIndex][colIndex] = null;
                    colIndex++;
                }
            } else {
                boolean isWhite = Character.isUpperCase(c);
                pieces[rowIndex][colIndex] = new Piece(c, isWhite);
                colIndex++;
            }
        }

        return pieces;
    }

    public static String pieceArrayToFen(Piece[][] pieces) {
        StringBuilder fenString = new StringBuilder();
        int emptySquareCount = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = pieces[row][col];
                if (piece == null) {
                    emptySquareCount++;
                } else {
                    if (emptySquareCount > 0) {
                        fenString.append(emptySquareCount);
                        emptySquareCount = 0;
                    }
                    fenString.append(piece.getSymbol());
                }
            }
            if (emptySquareCount > 0) {
                fenString.append(emptySquareCount);
                emptySquareCount = 0;
            }
            if (row != BOARD_SIZE - 1) {
                fenString.append("/");
            }
        }
        fenString.append(" w KQkq - 0 1");
        return fenString.toString();
    }

    public static String pieceArrayToText(Piece[][] pieces) {
        StringBuilder text = new StringBuilder();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = pieces[row][col];
                if (piece == null) {
                    text.append("- ");
                } else {
                    text.append(piece.getSymbol()).append(" ");
                }
            }
            text.append("\n");
        }
        return text.toString();
    }

}