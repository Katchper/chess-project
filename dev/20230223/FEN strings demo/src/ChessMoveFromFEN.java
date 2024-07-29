public class ChessMoveFromFEN {
    public static void main(String[] args) {
        String fenStart = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
        String fenEnd = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1";

        String move = getMoveFromFEN(fenStart, fenEnd);
        System.out.println(move); // Output: e2e4
    }

    public static String getMoveFromFEN(String fenStart, String fenEnd) {
        Chessboard boardStart = new Chessboard(fenStart);
        Chessboard boardEnd = new Chessboard(fenEnd);

        int fromSquare = -1;
        for (int i = 0; i < 64; i++) {
            if (boardStart.getPiece(i) != boardEnd.getPiece(i)) {
                fromSquare = i;
                break;
            }
        }

        int toSquare = -1;
        for (int i = 0; i < 64; i++) {
            if (boardEnd.getPiece(i) != boardStart.getPiece(i)) {
                toSquare = i;
                break;
            }
        }

        String fromFile = Character.toString((char) ('a' + (fromSquare % 8)));
        String fromRank = Integer.toString(8 - (fromSquare / 8));
        String toFile = Character.toString((char) ('a' + (toSquare % 8)));
        String toRank = Integer.toString(8 - (toSquare / 8));

        Chessboard.Piece piece = boardStart.getPiece(fromSquare);

        boolean isCapture = boardEnd.getPiece(toSquare) != null;

        boolean isPromotion = piece.getType() == PieceType.PAWN && (toSquare / 8 == 0 || toSquare / 8 == 7);

        StringBuilder moveBuilder = new StringBuilder();
        moveBuilder.append(piece.getType().toString().charAt(0));
        moveBuilder.append(fromFile);
        moveBuilder.append(fromRank);
        if (isCapture) {
            moveBuilder.append('x');
        }
        moveBuilder.append(toFile);
        moveBuilder.append(toRank);
        if (isPromotion) {
            moveBuilder.append('=');
            moveBuilder.append(boardEnd.getPiece(toSquare).getType().toString().charAt(0));
        }

        return moveBuilder.toString();
    }
}