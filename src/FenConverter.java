/**
 * The chess game's class that deals with FEN strings which are used for saving and loading.
 *
 * @author Jason Smith [jas160], Jasper Crabb [jac127], Archie Malvern [arm36], Kacper Dziedzic [ktd1]
 * @version 1.2
 * @
 */

import java.io.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Objects;

public class FenConverter {

   boolean turnColor;

   /**
    * This method loads a single FEN string from a file.
    *
    * @param fileName The file to be read.
    * @return A board with the correct positions.
    * @throws IOException As it uses FileReader.
    */
   public Board loadFENFromFile(String fileName) throws IOException {
      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      String fenString = reader.readLine();
      reader.close();
      return loadBoardFromFen(fenString);
   }

   /**
    * This fills an arrayList with all FEN strings in a file.
    *
    * @param fileName The file to be read.
    * @return An ArrayList with every move in the file, in order.
    * @throws IOException As it uses FileReader.
    */
   public ArrayList<String> loadGameFromFile(String fileName) throws IOException {
      ArrayList<String> fenStrings = new ArrayList<>();

      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      String currentLine;

      while ((currentLine = reader.readLine()) != null) {
         fenStrings.add(currentLine);
      }
      return fenStrings;
   }


   /**
    * Converts a FEN string into a board with pieces.
    *
    * @param fen The FEN string to be loaded.
    * @return The board with it pieces.
    */
   public Board loadBoardFromFen(String fen) {
      Board board = new Board();
      String[] fenSplit = fen.split(" ");
      //board.initializeBoard();

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
            board.getTile(col, row).setPiece(piece);
            col++;
         }
      }

      switch (fenSplit[1]) {
         case "w":
            turnColor = true;
            break;
         case "b":
            turnColor = false;
      }

      // castling rights (ensuring )
      if (board.getActualTile(0,0).getPiece().getPieceName().equals("black_rook")){
         Rook queenSideB = (Rook) board.getActualTile(0,0).getPiece();
         queenSideB.setHasMoved(true);
      }
      if (board.getActualTile(7,0).getPiece().getPieceName().equals("black_rook")){
         Rook kingSideB = (Rook) board.getActualTile(7,0).getPiece();
         kingSideB.setHasMoved(true);
      }
      if (board.getActualTile(0,7).getPiece().getPieceName().equals("white_rook")){
         Rook queenSideW = (Rook) board.getActualTile(0,7).getPiece();
         queenSideW.setHasMoved(true);
      }
      if (board.getActualTile(7,7).getPiece().getPieceName().equals("white_rook")){
         Rook kingSideW = (Rook) board.getActualTile(7,7).getPiece();
         kingSideW.setHasMoved(true);
      }

      for (char c : fenSplit[2].toCharArray()) {
         Rook temp;
         switch (c){
            case 'Q':
               temp = (Rook) board.getActualTile(0,7).getPiece();
               temp.setHasMoved(false);
               break;
            case 'K':
               temp = (Rook) board.getActualTile(7,7).getPiece();
               temp.setHasMoved(false);
               break;
            case 'q':
               temp = (Rook) board.getActualTile(0,0).getPiece();
               temp.setHasMoved(false);
               break;
            case 'k':
               temp = (Rook) board.getActualTile(7,0).getPiece();
               temp.setHasMoved(false);
               break;
            default:
               King tempKingW = (King) board.findKing(true).getPiece();
               King tempKingB = (King) board.findKing(false).getPiece();
               tempKingW.setHasMoved(true);
               tempKingB.setHasMoved(true);
               break;
         }
      }

      // check every pawn to see if it has moved set move status accordingly
      for (int i = 0; i < 8; i++) {
         for (int j = 0; j < 8; j++) {

            Tile currentTile = board.getTile(i,j);
            boolean pieceColor = currentTile.getPiece().getColor();
            // sets every tile with an empty piece in it to empty
            currentTile.setEmpty(currentTile.getPiece().getPieceName().equals(""));
            // check if the piece is a pawn
            if (currentTile.getPiece().checkWhichPiece(currentTile) == 1){
               // if piece = white and y does not equal 6 then set the move status to 0
               // meaning it cant double move anymore
               if (pieceColor && currentTile.getY() != 6){
                  Pawn currentPawn = (Pawn) currentTile.getPiece();
                  currentPawn.setMoveStatus(0);
               } else if (!pieceColor && currentTile.getY() != 1){
                  Pawn currentPawn = (Pawn) currentTile.getPiece();
                  currentPawn.setMoveStatus(0);
               }
            }
         }
      }

      //en passant move status
      int pieceX = 0;
      int pieceY = 0;
      for (char c : fenSplit[3].toCharArray()) {
         if (c < 60){
            pieceY = Math.abs((c-49) - 7);
         } else if (c >= 97 && c <= 122){
            pieceX = c-97;
         }
      }
      Pawn tempPawn;
      if (pieceY == 2){
         tempPawn = (Pawn) board.getTile(pieceX, pieceY + 1).getPiece();
         tempPawn.setMoveStatus(1);
      } else if (pieceY == 5){
         tempPawn = (Pawn) board.getTile(pieceX, pieceY - 1).getPiece();
         tempPawn.setMoveStatus(1);
      }



      return board;
   }

   public boolean isTurnColor() {
      return turnColor;
   }

   public void writeSaveOnly(Board board, boolean turn) {
      StringBuilder fen = new StringBuilder();
      String filePath = "./resources/saveGames/SavedFile.txt";

      // board state
      int emptySquares = 0;
      for (int rank = 0; rank < 8; rank++) {
         emptySquares = 0;
         for (int file = 0; file < 8; file++) {
            Tile tile = board.getActualTile(file, rank);
            if (tile.isEmpty()) {
               emptySquares++;
            } else {
               if (emptySquares > 0) {
                  fen.append(emptySquares);
                  emptySquares = 0;
               }
               String toAppend = "";
               if (tile.getPiece().getPieceName().charAt(0) == 'w') {
                  if (tile.getPiece().getPieceName().charAt(7) != 'n') {
                     toAppend = toAppend + tile.getPiece().getPieceName().charAt(6);
                  } else {
                     toAppend = toAppend + 'n';
                  }
                  toAppend = toAppend.toUpperCase();
               } else if (tile.getPiece().getPieceName().charAt(0) == 'b') {
                  if (tile.getPiece().getPieceName().charAt(7) != 'n') {
                     toAppend = toAppend + tile.getPiece().getPieceName().charAt(6);
                  } else {
                     toAppend = toAppend + 'n';
                  }
               }
               fen.append(toAppend);
            }
         }
         if (emptySquares > 0) {
            fen.append(emptySquares);
         }
         if (rank < 7) {
            fen.append('/');
         }
      }
      // player to move
      if (!turn) {
         fen.append(" w ");
      } else {
         fen.append(" b ");
      }
      //below is a temporary fix to allow for save loading
      fen.append("KQkq -");

      try {
         FileWriter writer = new FileWriter(filePath, true);
         writer.append(System.lineSeparator() + fen.toString());
         writer.close();
         System.out.println("saved to file" + filePath);
      } catch (IOException e) {
         System.out.println("An error occurred while writing to the file: " + e.getMessage());
      }
   }


   public void writeLineToFile(String filePath, String line) {
      File fileDest = new File(filePath);
      try{
         if(!fileDest.exists()){
            fileDest.createNewFile();
         }

         FileWriter fileWriter = new FileWriter(fileDest, true);
         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
         bufferedWriter.write(line + "\n");
         bufferedWriter.close();
      } catch(IOException e) {
         System.out.println("Error when writing to the file");
      }
   }

   public String topFenString(String fileName) throws IOException {
      ArrayList<String> fileLines = getAllFenStrings(fileName);
      return fileLines.get(fileLines.size()-1);
   }

   /**
    * Converts a board object and its pieces to a FEN string.
    *
    * @param board The board to be converted.
    * @param turn  The current turn
    */
   public String boardToFen(Board board, boolean turn) {
      StringBuilder fen = new StringBuilder();

      // board state
      int emptySquares = 0;
      for (int rank = 0; rank < 8; rank++) {
         emptySquares = 0;
         for (int file = 0; file < 8; file++) {
            Tile tile = board.getActualTile(file, rank);
            if (tile.isEmpty()) {
               emptySquares++;
            } else {
               if (emptySquares > 0) {
                  fen.append(emptySquares);
                  emptySquares = 0;
               }
               String toAppend = "";
               if (tile.getPiece().getPieceName().charAt(0) == 'w') {
                  if (tile.getPiece().getPieceName().charAt(7) != 'n') {
                     toAppend = toAppend + tile.getPiece().getPieceName().charAt(6);
                  } else {
                     toAppend = toAppend + 'n';
                  }
                  toAppend = toAppend.toUpperCase();
               } else if (tile.getPiece().getPieceName().charAt(0) == 'b') {
                  if (tile.getPiece().getPieceName().charAt(7) != 'n') {
                     toAppend = toAppend + tile.getPiece().getPieceName().charAt(6);
                  } else {
                     toAppend = toAppend + 'n';
                  }
               }
               fen.append(toAppend);
            }
         }
         if (emptySquares > 0) {
            fen.append(emptySquares);
         }
         if (rank < 7) {
            fen.append('/');
         }
      }
      // player to move
      if (!turn) {
         fen.append(" w ");
      } else {
         fen.append(" b ");
      }


      String castlingString = "";
      King blackKing = (King) board.findKing(false).getPiece();
      King whiteKing = (King) board.findKing(true).getPiece();

      if (!whiteKing.hasMoved()){
         //queen side
         if (board.getActualTile(0,7).getPiece().getPieceName().equals("white_rook")){
            Rook queenSideRook = (Rook) board.getActualTile(0,7).getPiece();
            if (!queenSideRook.hasMoved()){
               castlingString = castlingString + "K";
            }
         }
         //king side
         if (board.getActualTile(7,7).getPiece().getPieceName().equals("white_rook")){
            Rook kingSideRook = (Rook) board.getActualTile(7,7).getPiece();
            if (!kingSideRook.hasMoved()){
               castlingString = castlingString + "Q";
            }
         }
      }
      if (!blackKing.hasMoved()){
         //queen side
         if (board.getActualTile(7,0).getPiece().getPieceName().equals("black_rook")){
            Rook kingSideRook = (Rook) board.getActualTile(7,0).getPiece();
            if (!kingSideRook.hasMoved()){
               castlingString = castlingString + "k";
            }
         }
         if (board.getActualTile(0,0).getPiece().getPieceName().equals("black_rook")){
            Rook queenSideRook = (Rook) board.getActualTile(0,0).getPiece();
            if (!queenSideRook.hasMoved()){
               castlingString = castlingString + "q";
            }
         }

      }

      if (castlingString.equals("")){
         castlingString = "-";
      }

      fen.append(castlingString).append(" ");
      String enPassantString = "";
      for (int i = 0; i < 8; i++) {
         for (int j = 0; j < 8; j++) {
            if (board.getActualTile(i,j).getPiece().checkWhichPiece(board.getActualTile(i, j)) == 1){
               Pawn currentPawn = (Pawn)board.getActualTile(i,j).getPiece();
               if (currentPawn.getMoveStatus() == 1){
                  String number;
                  if (!currentPawn.getColor()){
                     char temp = (char)(97 + board.getActualTile(i,j).getX());
                     number = "6";
                  } else {
                     number = "3";
                  }
                  char temp = (char)(97 + board.getActualTile(i,j).getX());
                  enPassantString = temp + "" + number;
                  break;
               }
            }
         }
         if (!enPassantString.equals("")){
            break;
         }
      }
      if (enPassantString.equals("")){
         enPassantString = "-";
      }
      fen.append(enPassantString).append(" ");

      return fen.toString();
   }

   public ArrayList<String> getAllFenStrings(String fileName) throws IOException {
      ArrayList<String> fenStrings = new ArrayList<>();

      BufferedReader reader = new BufferedReader(new FileReader(fileName));
      String currentLine;

      while ((currentLine = reader.readLine()) != null) {
         fenStrings.add(currentLine);
      }
      fenStrings.remove(fenStrings.size()-1);

      if (fenStrings.get(fenStrings.size()-1).equals(fenStrings.get(0)) || fenStrings.get(fenStrings.size()-1).equals(fenStrings.get(1))){
         fenStrings.remove(fenStrings.size()-1);
      }
      fenStrings.remove(0);
      fenStrings.remove(1);
      return fenStrings;
   }
}
