import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class fenConvertor {

    public static Board loadFENFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String fenString = reader.readLine();
        reader.close();
        return loadBoardFromFen(fenString);
    }
    public static Board loadBoardFromFen(String fen) {
        Board board = new Board();
        String[] fenSplit = fen.split(" ");

        int row = 0;
        int col = 0;

        for (char c : fenSplit[0].toCharArray()) {
            if (c == '/') {
                row++;
                col = 0;
            } else if (Character.isDigit(c)) {
                col += Character.getNumericValue(c);
            } else {
                boolean isWhite = Character.isUpperCase(c);
                Piece piece;
                switch (Character.toLowerCase(c)) {
                    case 'k':
                        piece = new King(isWhite);
                        break;
                    case 'q':
                        piece = new Queen(isWhite);
                        break;
                    case 'r':
                        piece = new Rook(isWhite);
                        break;
                    case 'b':
                        piece = new Bishop(isWhite);
                        break;
                    case 'n':
                        piece = new Knight(isWhite);
                        break;
                    case 'p':
                        piece = new Pawn(isWhite);
                        break;
                    case ' ':
                    default:
                        piece = new EmptyPiece();
                        break;
                }
                board.setTile(col, row, new Tile(col, row, piece));
                col++;
            }
        }

        return board;
    }
    public static void writeFENToFile(String filePath, StringBuilder fenString) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(fenString.toString() + System.lineSeparator());
            writer.close();
            System.out.println("saved to file" +filePath);
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    public static void boardToFen(Board board) {
        StringBuilder fen = new StringBuilder();

        // board state
        int emptySquares = 0;
        for (int rank = 7; rank >= 0; rank--) {
            emptySquares = 0;
            for (int file = 0; file < 8; file++) {
                Tile tile = board.getTile(file, rank);
                if (tile.isEmpty()) {
                    emptySquares++;
                } else {
                    if (emptySquares > 0) {
                        fen.append(emptySquares);
                        emptySquares = 0;
                    }
                    fen.append(tile.getPiece().getPieceChar());
                }
            }
            if (emptySquares > 0) {
                fen.append(emptySquares);
            }
            if (rank > 0) {
                fen.append('/');
            }
        }
        // castling availability (not done)
        fen.append(" -");

        // en passant target square (not done)
        fen.append(" -");
        System.out.println(fen);
        fenConvertor.writeFENToFile("SavedFile.txt", fen);
    }
}