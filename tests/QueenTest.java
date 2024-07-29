import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class handles all the tests about the queen piece to ensure its working as intended.
 * @author Muntazir Rashid [mrm19]
 * @version 1.0
 */
public class QueenTest {
   Board board;

   /**
    * Sets up a chess board for the test.
    */
   @Before
   public void setup(){
      board = new Board();
      Queen queen = new Queen(true);
      board.addPiece(3,3,queen);
   }

   /**
    * Tests that the queen is constructed and given the correct assigned colour.
    */
   @Test
   public void testQueenConstructor() {
      Queen queen = new Queen(true);
      assertEquals("white_queen", queen.getPieceName());
      assertTrue(queen.getColor());

      Queen queen2 = new Queen(false);
      assertEquals("black_queen", queen2.getPieceName());
      assertFalse(queen2.getColor());
   }

   /**
    * Tests that a queen piece generates the correct set of moves.
    */
   @Test
   public void testWhiteQueenGenerateMoves() {
      board.initializeBoard();
      Tile piece = board.getTile(5, 0);
      piece.getPiece().generateMoves(board, piece);
      assertEquals(0, piece.getPiece().getAllMoves().size());

   }
   @Test
   public void testBlackQueenGenerateMoves() {
      board.initializeBoard();
      Tile piece = board.getTile(5, 7);
      piece.getPiece().generateMoves(board, piece);
      assertEquals(0, piece.getPiece().getAllMoves().size());

   }

   /**
    * Tests that a queen piece generate the correct set of moves given its position in the middle of the board.
    */
   @Test
   public void testQueenGenerateMovesCenter() {
      board.getTile(3,3).getPiece().generateMoves(board, board.getTile(3,3));
      assertEquals(27,  board.getTile(3,3).getPiece().getAllMoves().size());

   }

   /**
    * Tests that a queen is able to capture an enemy piece.
    */
   @Test
   public void testQueenCapture() {
      Pawn pawn = new Pawn(false);
      board.addPiece(3,5,pawn);
      board.getTile(3,3).getPiece().generateMoves(board, board.getTile(3,3));
      assertEquals(25,  board.getTile(3,3).getPiece().getAllMoves().size());
      assertTrue(board.getTile(3,3).getPiece().getAllMoves().contains(board.getTile(3, 5)));
      assertFalse(board.getTile(3,3).getPiece().getAllMoves().contains(board.getTile(3, 6)));

   }

   /**
    * Tests that a queen cannot capture its own piece.
    */
   @Test
   public void testQueenCaptureOwnPiece() {
      Pawn pawn = new Pawn(true);
      board.addPiece(3,5,pawn);
      board.getTile(3,3).getPiece().generateMoves(board, board.getTile(3,3));
      assertEquals(24,  board.getTile(3,3).getPiece().getAllMoves().size());
      assertFalse(board.getTile(3,3).getPiece().getAllMoves().contains(board.getTile(3, 5)));

   }

}
