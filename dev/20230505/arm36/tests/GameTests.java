package src;

/**
 * dev.Tests.Tests none of the methods in the Game class throw any exceptions
 * @author Archie Malvern
 * @version 1.1
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GameTests {

    @Test
    void testInitiateGame() {
        Game testGame = new Game();
        testGame.initiateGame();
    }

    @Test
    void testLoadGame(){
        Game testGame = new Game();
        testGame.loadGame("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    @Test
    void testGenerateAllMoves() {
        Game testGame = new Game();
        testGame.initiateGame();
        testGame.generateAllMoves();
    }

    @Test
    void testMovePiece() {
        Game testGame = new Game();
        testGame.initiateGame();
        testGame.movePiece(1,6,1,4);
    }

    @Test
    void testTurnExecution() {
        Game testGame = new Game();
        testGame.initiateGame();
        testGame.turnExecution();
    }

    @Test
    void testCheckMove() {
        Game testGame = new Game();
        testGame.initiateGame();
        testGame.generateAllMoves();
        Assertions.assertEquals(1, testGame.checkMove(1,6,1,4));
    }
}