import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class handles all the test on the bishop piece to ensure the piece is working as intended.
 * @author Muntazir Rashid [mrm19]
 * @version 1.0
 */
public class BishopTest {

   Board board;

   /**
    * Sets up an experimental board to test bishop piece.
    */
   @Before
   public void setup(){
      board = new Board();
      Bishop bishop = new Bishop(true);
      board.addPiece(3,3,bishop);
   }

   /**
    * Tests that the bishop is assigned to the intended colour.
    */
   @Test
   public void testBishopConstructor() {
      Bishop bishop = new Bishop(true);
      assertEquals("white_bishop", bishop.getPieceName());
      assertTrue(bishop.getColor());

      Bishop bishop2 = new Bishop(false);
      assertEquals("black_bishop", bishop2.getPieceName());
      assertFalse(bishop2.getColor());
   }

   /**
    * Tests that the bishops generates the correct sets of moves.
    */
   @Test
   public void testWhiteBishopGenerateMoves() {
      board.initializeBoard();
      Tile piece = board.getTile(2, 0);
      piece.getPiece().generateMoves(board, piece);
      assertEquals(0, piece.getPiece().getAllMoves().size());

   }
   @Test
   public void testBlackBishopGenerateMoves() {
      board.initializeBoard();
      Tile piece = board.getTile(2, 7);
      piece.getPiece().generateMoves(board, piece);
      assertEquals(0, piece.getPiece().getAllMoves().size());

   }


   /**
    * Tests that the bishop generates the correct set of legal moves given its position at the middle of the board.
    */
   @Test
   public void testBishopGenerateMovesCenter() {
      board.getTile(3,3).getPiece().generateMoves(board, board.getTile(3,3));
      assertEquals(13,  board.getTile(3,3).getPiece().getAllMoves().size());

   }

   /**
    * Tests that the bishop is able to capture an enemy piece.
    */
   @Test
   public void testBishopCapture() {
      Pawn pawn = new Pawn(false);
      board.addPiece(5,5,pawn);
      board.getTile(3,3).getPiece().generateMoves(board, board.getTile(3,3));
      assertEquals(11,  board.getTile(3,3).getPiece().getAllMoves().size());
      assertTrue(board.getTile(3,3).getPiece().getAllMoves().contains(board.getTile(5, 5)));
      assertFalse(board.getTile(3,3).getPiece().getAllMoves().contains(board.getTile(6, 6)));

   }

   /**
    * Tests that the bishop cannot capture a friendly piece.
    */
   @Test
   public void testBishopCaptureOwnPiece() {
      Pawn pawn = new Pawn(true);
      board.addPiece(5,5,pawn);
      board.getTile(3,3).getPiece().generateMoves(board, board.getTile(3,3));
      assertEquals(10,  board.getTile(3,3).getPiece().getAllMoves().size());
      assertFalse(board.getTile(3,3).getPiece().getAllMoves().contains(board.getTile(5, 5)));

   }
}
