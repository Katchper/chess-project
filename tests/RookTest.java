import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class handles all the tests about the rook piece so that its working as intended.
 * @author Muntazir Rashid [mrm19]
 */
public class RookTest {
   Board board;

   /**
    * Sets up the board for the test.
    */
   @Before
   public void setup(){
      board = new Board();
      Rook rook = new Rook(true);
      board.addPiece(3,3,rook);
   }

   /**
    * Tests that a rook is constructed and is assigned to the correct colour.
    */
   @Test
   public void testRookConstructor() {
      Rook rook = new Rook(true);
      assertEquals("white_rook", rook.getPieceName());
      assertTrue(rook.getColor());

      Rook rook2 = new Rook(false);
      assertEquals("black_rook", rook2.getPieceName());
      assertFalse(rook2.getColor());
   }

   /**
    * Tests that a rook piece generates the correct set of moves.
    */
   @Test
   public void testWhiteRookGenerateMoves() {
      board.initializeBoard();
      Tile piece = board.getTile(0, 0);
      piece.getPiece().generateMoves(board, piece);
      assertEquals(0, piece.getPiece().getAllMoves().size());

   }
   @Test
   public void testBlackRookGenerateMoves() {
      board.initializeBoard();
      Tile piece = board.getTile(0, 7);
      piece.getPiece().generateMoves(board, piece);
      assertEquals(0, piece.getPiece().getAllMoves().size());

   }

   /**
    * Tests that the rook piece generates the correct sets of moves given its position in the middle of the board.
    */
   @Test
   public void testRookGenerateMovesCenter() {
      board.getTile(3,3).getPiece().generateMoves(board, board.getTile(3,3));
      assertEquals(14,  board.getTile(3,3).getPiece().getAllMoves().size());

   }

   /**
    * Tests that a rook piece is able to capture an enemy piece.
    */
   @Test
   public void testRookCapture() {
      Pawn pawn = new Pawn(false);
      board.addPiece(3,5,pawn);
      board.getTile(3,3).getPiece().generateMoves(board, board.getTile(3,3));
      assertEquals(12,  board.getTile(3,3).getPiece().getAllMoves().size());
      assertTrue(board.getTile(3,3).getPiece().getAllMoves().contains(board.getTile(3, 5)));
      assertFalse(board.getTile(3,3).getPiece().getAllMoves().contains(board.getTile(3, 6)));

   }

   /**
    * Tests that a rook piece cannot capture its own piece.
    */
   @Test
   public void testRookCaptureOwnPiece() {
      Pawn pawn = new Pawn(true);
      board.addPiece(3,5,pawn);
      board.getTile(3,3).getPiece().generateMoves(board, board.getTile(3,3));
      assertEquals(11,  board.getTile(3,3).getPiece().getAllMoves().size());
      assertFalse(board.getTile(3,3).getPiece().getAllMoves().contains(board.getTile(3, 5)));

   }

}
