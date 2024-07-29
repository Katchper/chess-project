import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * This class handles all the tests about the checking mechanisms.
 * @author Jason Smith [jas160]
 * @version 1.0
 */


public class CheckTest {

    /**
     * Tests that the king is not in check when no pieces are targeting it.
     */


    @Test
    void NoCheck() {
        String player1 = "Player 1";
        String player2 = "Player 2";

        Game testGame = new Game(player1, player2);
        testGame.initiateGame();
        Tile testTile;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                testGame.getBoard().setTile(i, j, new Tile(i, j));
            }
        }
        testTile = new Tile(3, 0, new King(true));
        testGame.getBoard().setTile(3, 0, testTile);
        testTile = new Tile(2, 1, new Pawn(true));
        testGame.getBoard().setTile(2, 1, testTile);
        testTile = new Tile(3, 1, new Pawn(true));
        testGame.getBoard().setTile(3, 1, testTile);
        testTile = new Tile(4, 1, new Pawn(true));
        testGame.getBoard().setTile(4, 1, testTile);

        testTile = new Tile(0, 3, new Queen(true));
        testGame.getBoard().setTile(0, 3, testTile);
        testTile = new Tile(3, 3, new Queen(true));
        testGame.getBoard().setTile(3, 3, testTile);
        testTile = new Tile(7, 3, new Queen(true));
        testGame.getBoard().setTile(7, 3, testTile);

        Check check = new Check();
        int actualOutput = check.checkCheck(testTile, testGame.getBoard());
        Assertions.assertEquals(0, actualOutput);
    }
}