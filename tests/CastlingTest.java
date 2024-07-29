import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This class handles all the tests about the special move called castling in chess.
 * @author Muntazir Rashid [mrm19]
 * @version 1.0
 */
public class CastlingTest {
   Board board;

   /**
    * Sets up the board for the test.
    */
   @Before
   public void setup() {
      board = new Board();
      King king = new King(true);
      board.addPiece(4, 0, king);
      Rook rook = new Rook(true);
      board.addPiece(0, 0, rook);
      Rook rook2 = new Rook(true);
      board.addPiece(7, 0, rook2);
   }

   /**
    * Tests that king is able to castle with both left and right castle.
    */
   @Test
   public void leftCastle(){
      board.getTile(4,0).getPiece().generateMoves(board, board.getTile(4,0));
      assertTrue(board.getTile(4,0).getPiece().getAllMoves().contains(board.getActualTile(4,0).getPiece().getMove(0,0)));
   }
   @Test
   public void rightCastle(){
      board.getTile(4,0).getPiece().generateMoves(board, board.getTile(4,0));
      assertTrue(board.getTile(4,0).getPiece().getAllMoves().contains(board.getActualTile(4,0).getPiece().getMove(7,0)));
   }

   /**
    * Tests that the king cannot perform a castling when it is under a check.
    */
   @Test
   public void castleInCheck(){
      Rook enemyRook = new Rook(false);
      board.addPiece(4, 6, enemyRook);
      board.getTile(4,0).getPiece().generateMoves(board, board.getTile(4,0));
      assertFalse(board.getTile(4,0).getPiece().getAllMoves().contains(board.getActualTile(4,0).getPiece().getMove(7,0)));
   }

   /**
    * Tests that the king cannot perform castling if there is a piece in between the king and the rook.
    */
   @Test
   public void blockCastle(){
      Rook enemyRook = new Rook(false);
      board.addPiece(5, 6, enemyRook);
      board.getTile(4,0).getPiece().generateMoves(board, board.getTile(4,0));
      assertFalse(board.getTile(4,0).getPiece().getAllMoves().contains(board.getActualTile(4,0).getPiece().getMove(7,0)));
   }

   /**
    * Tests that the king cannot perform castling with a rook that has been moved and returned to its original spot.
    */
   @Test
   public void rookMoved(){
      board.getTile(0,0).getPiece().generateMoves(board, board.getTile(0,0));
      board.movePiece(0,0,1,0);
      board.getTile(4,0).getPiece().generateMoves(board, board.getTile(4,0));
      assertFalse(board.getTile(4,0).getPiece().getAllMoves().contains(board.getActualTile(4,0).getPiece().getMove(0,0)));
   }

   /**
    * Tests that the king cannot perform castling if is has moved and then returned to its original spot.
    */
   @Test
   public void kingMoved(){
      board.getTile(4,0).getPiece().generateMoves(board, board.getTile(4,0));
      board.movePiece(4,0,5,0);
      board.getTile(4,0).getPiece().generateMoves(board, board.getTile(4,0));
      assertFalse(board.getTile(4,0).getPiece().getAllMoves().contains(board.getActualTile(4,0).getPiece().getMove(7,0)));
   }

}