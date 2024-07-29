/**
 * The class to test the tile class.
 * @author Jasper Crabb [jac127].
 * @version 1.2
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TileTests {
    Piece testPiece;
    Tile testTile;
    int testCoordX;
    int testCoordY;

    /**
     * This method sets up a new tile and its parameters for testing.
     */
    @Before
    public void setup() {
        testPiece = new Queen(true);
        testTile = new Tile(testCoordX, testCoordY, testPiece);
        testCoordX = 6;
        testCoordY = 4;
    }

    /**
     * This method tests the getCoords method works correctly.
     */
    @Test
    public void testGetCoords() {
        int x = testTile.getX();
        int y = testTile.getY();

        Assertions.assertEquals(testCoordX, x);
        Assertions.assertEquals(testCoordY, y);
    }

    /**
     * This method tests the getPiece method works correctly.
     */
    @Test
    public void testGetPiece() {
        Piece piece = testTile.getPiece();

        Assertions.assertEquals(testPiece, piece);
    }

    /**
     * This method tests the flipCoords method works correctly.
     */
    @Test
    public void testFlipCoords() {
        testTile.flipCoords();

        int x = testTile.getX();
        int y = testTile.getY();

        Assertions.assertEquals(Math.abs(testCoordX - 7), x);
        Assertions.assertEquals(Math.abs(testCoordY - 7), y);
    }

    /**
     * This method tests the isEmpty method works correctly.
     */
    @Test
    public void testIsEmpty() {
        boolean testEmpty = testTile.isEmpty();

        Assertions.assertFalse(testEmpty);
    }

    /**
     * This method tests the getMoveType method works correctly.
     */
    @Test
    public void testGetMoveType() {
        testTile.setMoveType(1);
        Assertions.assertEquals(1, testTile.getMoveType());
    }

    /**
     * This method tests that both the setX and setY work correctly.
     */
    @Test
    public void testSetCoords() {
        int newCoordX = 1;
        int newCoordY = 2;

        testTile.setX(newCoordX);
        testTile.setY(newCoordY);

        Assertions.assertEquals(newCoordX, testTile.getX());
        Assertions.assertEquals(newCoordY, testTile.getY());
    }

    /**
     * This method tests the setXY method works correctly.
     */
    @Test
    public void testSetXY() {
        int newCoordX = 7;
        int newCoordY = 3;

        testTile.setXY(newCoordX, newCoordY);

        Assertions.assertEquals(newCoordX, testTile.getX());
        Assertions.assertEquals(newCoordY, testTile.getY());
    }

    /**
     * This method tests the setPiece method works correctly.
     */
    @Test
    public void testSetPiece() {
        Piece newPiece = new Rook(true);

        testTile.setPiece(newPiece);

        Assertions.assertEquals(newPiece, testTile.getPiece());
    }

    /**
     * This method tests the setEmpty method works correctly.
     */
    @Test
    public void testSetEmpty() {
        testTile.setEmpty(true);

        Assertions.assertTrue(testTile.isEmpty());
    }

    /**
     * This method tests the setMoveType method works correctly.
     */
    @Test
    public void testSetMoveType() {
        testTile.setMoveType(2);
        Assertions.assertEquals(2, testTile.getMoveType());
    }
}
