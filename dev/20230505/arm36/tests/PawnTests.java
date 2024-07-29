package src;

/**
 * dev.Tests.Tests for the pawn class to ensure everything returns the correct values
 * @author Archie Malvern
 * @version 1.1
 */

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;

public class PawnTests {

    @Test
    void testGenerateMoves(){
        Game testGame = new Game();
        testGame.initiateGame();
        testGame.generateAllMoves();
        Board testBoard = testGame.getBoard();
        Tile testTile = testBoard.getTile(1,6);
        Piece testPiece = testTile.getPiece();
        testPiece.generateMoves(testBoard, testTile);
        boolean testTrue = testPiece.getAllMoves().get(0).isEmpty();
        Assertions.assertEquals(true, testTrue);
    }

    @Test
    void testDoubleMoveUpdate(){
        Pawn testPawn = new Pawn(false);
        testPawn.doubleMoveUpdate();
        Assertions.assertEquals(true, testPawn.doubleMoveLast());
    }

    @Test
    void testUpdateMoveStatus(){
        Pawn testPawn = new Pawn(false);
        testPawn.doubleMoveUpdate();
        testPawn.updateMoveStatus();
        Assertions.assertEquals(false, testPawn.doubleMoveLast());
    }
}
