import java.io.*;

public class FenConverter {
    private static final int BOARD_SIZE = 8;

    public static void main(String[] args) {
        // Test conversion from FEN string to Piece array
        String fen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        Piece[][] pieces = fenToPieceArray(fen);
        Board board = new Board();
        board.initialiseBoard();

        convertBoard(pieces, board);

        board.displayBoard();

        // Test conversion from Piece array to FEN string
        String fenString = pieceArrayToFen(pieces);

        System.out.println();
        System.out.print(fenString);

        // Save FEN string to test file
        writeFENToFile("testfile.txt", fenString);
    }

    public static void writeFENToFile(String filePath, String stringToWrite) {
        try {
            // create a FileWriter object in append mode
            FileWriter writer = new FileWriter(filePath, true);

            // write the string to the file and append a newline character
            writer.write(stringToWrite + System.lineSeparator());

            // close the writer to flush and release resources
            writer.close();

            System.out.println(": written to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
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
                    pieces[rowIndex][colIndex] = new Piece();
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

    public static void convertBoard(Piece[][] pieces, Board board){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece temp = pieces[j][i];
                board.setTile(i, j, temp.getPieceChar(), temp.getColour());
            }
        }
    }

    public static String pieceArrayToFen(Piece[][] pieces) {
        StringBuilder fenString = new StringBuilder();
        int emptySquareCount = 0;
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Piece piece = pieces[row][col];
                if (piece.getPieceChar() == ' ') {
                    emptySquareCount++;
                } else {
                    if (emptySquareCount > 0) {
                        fenString.append(emptySquareCount);
                        emptySquareCount = 0;
                    }
                    fenString.append(piece.getPieceChar());
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
        return fenString.toString();
    }

}