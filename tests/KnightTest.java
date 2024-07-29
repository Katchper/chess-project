import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class handles all the tests about the knight piece to ensure that its working as intended.
 * @author Muntazir Rashid [mrm19]
 * @version 1.0
 */
public class KnightTest {
   Board board;

   /**
    * Sets up the board for the test.
    */
   @Before
   public void setup(){
      board = new Board();
      Knight knight = new Knight(true);
      board.addPiece(3,3,knight);
   }

   /**
    * Tests that a knight piece is constructed and given the correct assigned colour.
    */
   @Test
   public void testKnightConstructor() {
      Knight knight = new Knight(true);
      assertEquals("white_knight", knight.getPieceName());
      assertTrue(knight.getColor());

      Knight knight2 = new Knight(false);
      assertEquals("black_knight", knight2.getPieceName());
      assertFalse(knight2.getColor());
   }

   /**
    * Tests that a knight piece generates the correct set of moves.
    */
   @Test
   public void testWhiteKnightGenerateMoves() {
      board.initializeBoard();
      Tile piece = board.getTile(1, 0);
      piece.getPiece().generateMoves(board, piece);
      assertEquals(2, piece.getPiece().getAllMoves().size());
      assertTrue(piece.getPiece().getAllMoves().contains(board.getTile(2, 2)));
      assertTrue(piece.getPiece().getAllMoves().contains(board.getTile(0, 2)));

   }
   @Test
   public void testBlackKnightGenerateMoves() {
      board.initializeBoard();
      Tile piece = board.getTile(1, 7);
      piece.getPiece().generateMoves(board, piece);
      assertEquals(2, piece.getPiece().getAllMoves().size());
      assertTrue(piece.getPiece().getAllMoves().contains(board.getTile(2, 5)));
      assertTrue(piece.getPiece().getAllMoves().contains(board.getTile(0, 5)));

   }

   /**
    * Tests that a knight piece generates the correct set of moves given its position in the middle of the board.
    */
   @Test
   public void testKnightGenerateMovesCenter() {
      board.getTile(3,3).getPiece().generateMoves(board, board.getTile(3,3));
      assertEquals(8,  board.getTile(3,3).getPiece().getAllMoves().size());

   }

   /**
    * Tests that a knight piece is able to capture an enemy piece.
    */
   @Test
   public void testKnightCapture() {
      Pawn pawn = new Pawn(false);
      board.addPiece(2,5,pawn);
      board.getTile(3,3).getPiece().generateMoves(board, board.getTile(3,3));
      assertEquals(8,  board.getTile(3,3).getPiece().getAllMoves().size());
      assertTrue(board.getTile(3,3).getPiece().getAllMoves().contains(board.getTile(2, 5)));

   }

   /**
    * Tests that a knight piece cannot capture its own piece.
    */
   @Test
   public void testKnightCaptureOwnPiece() {
      Pawn pawn = new Pawn(true);
      board.addPiece(2,5,pawn);
      board.getTile(3,3).getPiece().generateMoves(board, board.getTile(3,3));
      assertEquals(7,  board.getTile(3,3).getPiece().getAllMoves().size());
      assertFalse(board.getTile(3,3).getPiece().getAllMoves().contains(board.getTile(2, 5)));

   }
}
