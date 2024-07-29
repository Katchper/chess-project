import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class handles all the tests about the king piece so that it is working as intended.
 * @author Muntazir Rashid [mrm19]
 * @version 1.0
 */
public class KingTest {
   Board board;

   /**
    * Sets up the board for the test.
    */
   @Before
   public void setup(){
      board = new Board();
      King king = new King(true);
      board.addPiece(4,4,king);
   }

   /**
    * Tests that the king is constructed and given the correct assigned colour.
    */
   @Test
   public void testKingConstructor() {
      King king = new King(true);
      assertEquals("white_king", king.getPieceName());
      assertTrue(king.getColor());

      King king2 = new King(false);
      assertEquals("black_king", king2.getPieceName());
      assertFalse(king2.getColor());
   }

   /**
    * Tests that a king piece generates the correct set of moves.
    */
   @Test
   public void testWhiteKingGenerateMoves() {
      board.initializeBoard();
      Tile piece = board.getTile(4, 0);
      piece.getPiece().generateMoves(board, piece);
      assertEquals(0, piece.getPiece().getAllMoves().size());

   }
   @Test
   public void testBlackKingGenerateMoves() {
      board.initializeBoard();
      Tile piece = board.getTile(4, 7);
      piece.getPiece().generateMoves(board, piece);
      assertEquals(0, piece.getPiece().getAllMoves().size());

   }

   /**
    * Tests that a king piece generates the correct set of moves given its position in the middle of the board.
    */
   @Test
   public void testKingGenerateMovesCenter() {
      board.getTile(4,4).getPiece().generateMoves(board, board.getTile(4,4));
      assertEquals(8,  board.getTile(4,4).getPiece().getAllMoves().size());

   }

   /**
    * Tests that a king piece is able to capture an enemy piece.
    */
   @Test
   public void testKingCapture() {
      Pawn pawn = new Pawn(false);
      board.addPiece(3,5,pawn);
      board.getTile(4,4).getPiece().generateMoves(board, board.getTile(4,4));
      assertEquals(8,  board.getTile(4,4).getPiece().getAllMoves().size());
      assertTrue(board.getTile(4,4).getPiece().getAllMoves().contains(board.getTile(3, 5)));

   }

   /**
    * Tests that a king piece cannot capture its own piece.
    */
   @Test
   public void testKingCaptureOwnPiece() {
      Pawn pawn = new Pawn(true);
      board.addPiece(3,5,pawn);
      board.getTile(4,4).getPiece().generateMoves(board, board.getTile(4,4));
      assertEquals(7,  board.getTile(4,4).getPiece().getAllMoves().size());
      assertFalse(board.getTile(4,4).getPiece().getAllMoves().contains(board.getTile(3, 5)));

   }

}
