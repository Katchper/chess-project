/**
 * dev.Tests.Tests for the pawn class to ensure everything returns the correct values
 * @author Archie Malvern
 * @version 1.1
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;

public class PawnTests {

    /**
     * Tests that a pawn piece generates the correct set of moves.
     */
    @Test
    void testGenerateMoves(){
        Game testGame = new Game("p1", "p2");
        testGame.initiateGame();
        Board testBoard = testGame.getBoard();
        Tile testTile = testBoard.getTile(1,6);
        Piece testPiece = testTile.getPiece();
        testPiece.generateMoves(testBoard, testTile);
        boolean testTrue = testPiece.getAllMoves().get(0).isEmpty();
        Assertions.assertEquals(true, testTrue);
    }

    /**
     * Tests that a pawn updates its move status after it performs a double move.
     */
    @Test
    void testDoubleMoveUpdate(){
        Pawn testPawn = new Pawn(false);
        testPawn.setMoveStatus(1);
        Assertions.assertEquals(true, testPawn.doubleMoveLast());
    }

    /**
     * Tests that a pawn updates its move status correctly.
     */
    @Test
    void testUpdateMoveStatus(){
        Pawn testPawn = new Pawn(false);
        testPawn.setMoveStatus(2);
        Assertions.assertEquals(false, testPawn.doubleMoveLast());
    }
}
