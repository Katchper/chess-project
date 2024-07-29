/**
 * The class to test the board class. Uncompleted due to accidental deletion.
 * @author Jason Smith [jas160], Jasper Crabb [jac127].
 * @version 1.1
 */

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    Board testBoard;

    /**
     * This method sets up the variables and objects needed to test board.
     */
    @Before
    public void setup() {
        testBoard = new Board();
        testBoard.initializeBoard();
    }

    /**
     * This method tests if all tiles are present
     */
    @Test
    public void testTilesPresent() {
        testBoard = new Board();
        testBoard.initializeBoard();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                Tile tile = testBoard.getTile(x, y);
                assertNotNull(tile);
                assertNotNull(tile.getPiece());
            }
        }
    }

    /**
     * This method tests if all the pieces have been initialized in the correct locations
     */
    @Test
    void testInitializePieces() {
        testBoard = new Board();
        testBoard.initializeBoard();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        testBoard.displayBoard();
        // This should have a different output but the required changes haven't been made yet
        String expectedOutput = "[black_rook][black_pawn][][][][][white_pawn][white_rook]" + System.lineSeparator() +
                "[black_knight][black_pawn][][][][][white_pawn][white_knight]" + System.lineSeparator() +
                "[black_bishop][black_pawn][][][][][white_pawn][white_bishop]" + System.lineSeparator() +
                "[black_queen][black_pawn][][][][][white_pawn][white_queen]" + System.lineSeparator() +
                "[black_king][black_pawn][][][][][white_pawn][white_king]" + System.lineSeparator() +
                "[black_bishop][black_pawn][][][][][white_pawn][white_bishop]" + System.lineSeparator() +
                "[black_knight][black_pawn][][][][][white_pawn][white_knight]" + System.lineSeparator() +
                "[black_rook][black_pawn][][][][][white_pawn][white_rook]" + System.lineSeparator();
        assertEquals(expectedOutput, outputStream.toString());
    }

    /**
     * This method tests the getTile method works correctly.
     */
    @Test
    void testGetTile() {
        testBoard = new Board();
        testBoard.initializeBoard();

        // Test getting a tile that should exist
        Tile expectedTile = new Tile(0, 0, new Rook(true));

        testBoard.setTile(0, 0, expectedTile);
        Tile actualTile = testBoard.getActualTile(0, 0);
        assertEquals(expectedTile, actualTile);

        // Test getting a tile that should not exist
        Tile nullTile = testBoard.getTile(8, 8);
        assertNull(nullTile);
    }

    /**
     * This method tests the setTile method works correctly.
     */
    @Test
    void testSetTile() {
        testBoard = new Board();
        testBoard.initializeBoard();

        Tile expectedTile = new Tile(0, 0, new Rook(false));
        testBoard.setTile(6, 4, expectedTile);
        Tile actualTile = testBoard.getTile(6, 4);
        assertEquals(expectedTile, actualTile);
    }

    /**
     * This method tests the getActualTile method works correctly.
     */
    @Test
    void testGetActualTile() {
        testBoard = new Board();
        testBoard.initializeBoard();

        Tile expectedTile = new Tile(4, 4, new Rook(true));
        testBoard.setTile(0, 0, expectedTile);
        Tile actualTile = testBoard.getActualTile(4, 4);
        assertEquals(expectedTile, actualTile);


    }

    /**
     * This method tests the clone board method functions correctly.
     */
    @Test
    void cloneBoard() {
        testBoard = new Board();
        testBoard.initializeBoard();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        testBoard.displayBoard();
        String expected = outputStream.toString();
        Board newBoard = testBoard.cloneBoard();
        newBoard.displayBoard();
        String output = outputStream.toString();
        Assertions.assertEquals(expected, output);
    }

    /**
     * This method tests the flipBoard method functions correctly.
     */
    @Test
    void testFlipBoard() {
        testBoard = new Board();
        testBoard.initializeBoard();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Flip the board and check if it's flipped. This should have a different output but the required changes
        // haven't been made yet

        // tests would only work with the System.lineSeparator() method and not /n
        testBoard.flipBoard();
        testBoard.displayBoard();
        String expectedFlippedOutput = "[white_rook][white_pawn][][][][][black_pawn][black_rook]" + System.lineSeparator() +
                "[white_knight][white_pawn][][][][][black_pawn][black_knight]" + System.lineSeparator() +
                "[white_bishop][white_pawn][][][][][black_pawn][black_bishop]" + System.lineSeparator() +
                "[white_king][white_pawn][][][][][black_pawn][black_king]" + System.lineSeparator() +
                "[white_queen][white_pawn][][][][][black_pawn][black_queen]" + System.lineSeparator() +
                "[white_bishop][white_pawn][][][][][black_pawn][black_bishop]" + System.lineSeparator() +
                "[white_knight][white_pawn][][][][][black_pawn][black_knight]" + System.lineSeparator() +
                "[white_rook][white_pawn][][][][][black_pawn][black_rook]" + System.lineSeparator();
        assertEquals(expectedFlippedOutput, outputStream.toString());
        assertTrue(testBoard.isFlipped());

        // Flip the board again and check if it's back to normal
        outputStream.reset();
        testBoard.flipBoard();
        testBoard.displayBoard();
        String expectedNormalOutput = "[black_rook][black_pawn][][][][][white_pawn][white_rook]" + System.lineSeparator() +
                "[black_knight][black_pawn][][][][][white_pawn][white_knight]" + System.lineSeparator() +
                "[black_bishop][black_pawn][][][][][white_pawn][white_bishop]" + System.lineSeparator() +
                "[black_queen][black_pawn][][][][][white_pawn][white_queen]" + System.lineSeparator() +
                "[black_king][black_pawn][][][][][white_pawn][white_king]" + System.lineSeparator() +
                "[black_bishop][black_pawn][][][][][white_pawn][white_bishop]" + System.lineSeparator() +
                "[black_knight][black_pawn][][][][][white_pawn][white_knight]" + System.lineSeparator() +
                "[black_rook][black_pawn][][][][][white_pawn][white_rook]" + System.lineSeparator();
        assertEquals(expectedNormalOutput, outputStream.toString());
        assertFalse(testBoard.isFlipped());
    }

    /**
     * This checks the generateMostMoves method does not return an exception
     */
    @Test
    void testGenerateMostMoves() {
        testBoard = new Board();
        testBoard.initializeBoard();

        testBoard.generateMostMoves();

    }

    /**
     * This method tests the simpleMove method functions correctly.
     */
    @Test
    void testSimpleMove() {
        testBoard = new Board();
        testBoard.initializeBoard();

        int originalX = 0;
        int originalY = 1;
        int destX = 0;
        int destY = 2;

        // Tile originalTile = testBoard.getActualTile(originalX, originalY);

        testBoard.simpleMove(originalX, originalY, destX, destY);
    }

    /**
     * Tests movePiece method works correctly.
     */
    @Test
    void testMovePiece() {
        testBoard = new Board();
        testBoard.initializeBoard();

        int originalX = 0;
        int originalY = 1;
        int destX = 0;
        int destY = 2;

        testBoard.movePiece(originalX, originalY, destX, destY);
    }

    /**
     * Tests that specificPieceUpdate method works correctly.
     */
    @Test
    void testSpecificPieceUpdate() {
        testBoard = new Board();
        testBoard.initializeBoard();

        Tile testTile = new Tile(0, 0, new Pawn(true));
        testBoard.setTile(0, 0, testTile);

        testBoard.specificPieceUpdate(testTile, 1);
        Assertions.assertEquals(1, testBoard.getTile(0, 0).getMoveType());

        testBoard.specificPieceUpdate(testTile, 2);
        Assertions.assertEquals(2, testBoard.getTile(0, 0).getMoveType());

        testBoard.specificPieceUpdate(testTile, 3);
        Assertions.assertEquals(3, testBoard.getTile(0, 0).getMoveType());
    }

    /**
     * Tests if executeCastling doesn't throw an error.
     */
    @Test
    void testExecuteCastling() {
        testBoard = new Board();
        testBoard.initializeBoard();

        Tile testRook = new Tile(7, 7, new Rook(true));
        Tile testKing = new Tile(7, 5, new King(true));
        testBoard.executeCastling(testKing, testRook);
    }

    /**
     * Tests if executeEnPassant doesn't throw an error.
     */
    @Test
    void executeEnPassant() {
        testBoard = new Board();
        testBoard.initializeBoard();

        testBoard.executeEnPassant(1, 1);
    }

    /**
     * Tests if generateAllMoves doesn't throw an error.
     */
    @Test
    void generateAllMoves() {
        testBoard = new Board();
        testBoard.initializeBoard();

        testBoard.generateAllMoves();
    }

    /**
     * Tests if filterAllMoves doesn't throw an error.
     */
    @Test
    void filterAllMoves() {
        testBoard = new Board();
        testBoard.initializeBoard();

        testBoard.filterAllMoves();
    }

    /**
     * This checks if getAllColorMoves returns a valid output
     */
    @Test
    void testGetAllColorMoves() {
        testBoard = new Board();
        testBoard.initializeBoard();

        ArrayList<Tile> test = testBoard.getAllColorMoves(true);
        Assertions.assertNotNull(test.get(0));
    }

    /**
     * This tests the findKing method works correctly.
     */
    @Test
    void findKing() {
        testBoard = new Board();
        testBoard.initializeBoard();

        Tile kingWhite = testBoard.findKing(true);
        Tile kingBlack = testBoard.findKing(false);

        Assertions.assertTrue(kingWhite.getPiece().getColor());
        Assertions.assertFalse(kingBlack.getPiece().getColor());
    }

    /**
     * This tests the getter and setter for setFlipped.
     */
    @Test
    void setFlipped() {
        testBoard = new Board();
        testBoard.initializeBoard();

        testBoard.setFlipped(true);
        Assertions.assertTrue(testBoard.isFlipped());

        testBoard.setFlipped(false);
        Assertions.assertFalse(testBoard.isFlipped());
    }
}