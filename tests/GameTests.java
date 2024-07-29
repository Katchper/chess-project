/**
 * dev.Tests.Tests none of the methods in the Game class throw any exceptions
 * @author Archie Malvern
 * @version 1.1
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTests {

    /**
     * Tests that a game is initiated properly.
     */
    @Test
    void testInitiateGame() {
        Game testGame = new Game("p1", "p2");
        testGame.initiateGame();
    }

    /**
     * Tests that a game is loaded properly.
     */
    @Test
    void testLoadGame(){
        Game testGame = new Game("p1", "p2");
        testGame.loadGame("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    /**
     * Tests that a piece is moved correctly.
     */
    @Test
    void testMovePiece() {
        Game testGame = new Game("p1", "p2");
        testGame.initiateGame();
        testGame.checkMove(1,6,1,4);
    }

    /**
     * Tests that the method turnExecution works properly.
     */
    @Test
    void testTurnExecution() {
        Game testGame = new Game("p1", "p2");
        testGame.initiateGame();
        testGame.turnExecution();
    }

    /**
     * Tests that the method checkMove works properly.
     */
    @Test
    void testCheckMove() {
        Game testGame = new Game("p1", "p2");
        testGame.initiateGame();
        Assertions.assertEquals(1, testGame.checkMove(1,6,1,4));
    }
}