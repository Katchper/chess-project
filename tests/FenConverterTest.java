/**
 * This class handles all the test about the FEN String converting algorithms.
 * @author Jason Smith [jas160]
 * @version 1.0
 */

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FenConverterTest {
    FenConverter fenConverter;
    Board testBoard;
    String player1;
    String player2;

    /**
     * Sets up the board for the test
     * @throws Exception if the file does not exist.
     */
    @Before
    public void setUp() throws Exception {
        File testFile = new File("testFile.txt");
        Assertions.assertTrue(testFile.exists());
        FileWriter writer = new FileWriter(testFile);
        writer.write("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b");
        writer.close();
        File outputFile = new File("testplayernames.txt");
        Assertions.assertTrue(outputFile.exists());
        fenConverter = new FenConverter();
        testBoard = new Board();
        testBoard.initializeBoard();
        player1 = "Player 1";
        player2 = "Player 2";


    }

    /**
     * Tests that a FEN String is loaded successfully when extracted from a save file.
     * @throws IOException
     */
    @Test
    void testLoadFENFromFile() throws IOException {
        fenConverter = new FenConverter();
        File testFile = new File("testFile.txt");
        Assertions.assertTrue(testFile.exists());
        FileWriter writer = new FileWriter(testFile);
        writer.write("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -");
        writer.close();
        testBoard = new Board();
        testBoard = fenConverter.loadFENFromFile(String.valueOf(testFile));
        assertNotNull(testBoard);
    }

    /**
     * Tests that a game is successfully loaded when a save file is selected.
     * @throws IOException
     */
    @Test
    void testLoadGameFromFile() throws IOException {
        fenConverter = new FenConverter();
        ArrayList<String> fenStrings = fenConverter.loadGameFromFile("testFile.txt");
        String expectedFenStrings = "[rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -]";
        assertEquals(expectedFenStrings, fenStrings.toString());
    }

    /**
     * Tests that a board is loaded correctly according to a selected FEN String.
     */
    @Test
    void testLoadBoardFromFen() {
        fenConverter = new FenConverter();
        testBoard = new Board();
        String fenString = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -";
        testBoard= fenConverter.loadBoardFromFen(fenString);
        assertNotNull(testBoard);
    }

    /**
     * Tests that the FEN String indicates which sides to play  first correctly.
     */
    @Test
    void testIsTurnColor() {
        FenConverter fenConverter = new FenConverter();
        boolean turnColor = fenConverter.isTurnColor();
        assertFalse(turnColor);
    }

    /**
     * Tests that a FEN String is successfully written to a savefile.
     */
    @Test
    void testWriteFENToFile() {
        FenConverter fenConverter = new FenConverter();
        StringBuilder fenString = new StringBuilder("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -");
        String filePath = "testOutput.txt";
        String player1 = "Player 1";
        String player2 = "Player 2";

        // Call the method under test
        fenConverter.writeFENToFile(filePath, fenString, player1, player2);

        // Check that the file was written successfully
        File outputFile = new File(filePath);
        Assertions.assertTrue(outputFile.exists());

        // Read the contents of the file
        String fileContents = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            fileContents += reader.readLine() + "\n";
            fileContents += reader.readLine() + "\n";
            fileContents += reader.readLine();
        } catch (IOException e) {
            Assertions.fail("IOException occurred while reading file: " + e.getMessage());
        }

        // Check that the contents of the file match what was expected
        assertEquals("Player 1\nPlayer 2\nrnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq -", fileContents);
    }
    /**
     * Tests that a player name is successfully written to a save file.
     * @throws IOException
     */
    @Test
    void testWriteNamesToFile() throws IOException {
        FenConverter fenConverter = new FenConverter();
        Board testBoard = new Board();
        testBoard.initializeBoard();
        String player1 = "Player 1";
        String player2 = "Player 2";

        String filePath = "testplayernames.txt";
        fenConverter.writeNamesToFile(filePath, player1, player2);

        // Verify that the file was created successfully
        File outputFile = new File(filePath);
        Assertions.assertTrue(outputFile.exists());

        // Verify the contents of the file
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        assertEquals(2, lines.size());
        assertEquals(player1, lines.get(0));
        assertEquals(player2, lines.get(1));
    }
    /**
     * Tests if this method findPawnWithMoveStatusWhenNone performs correctly.
     */
    @Test
    void testFindPawnWithMoveStatusWhenNone() {
        FenConverter fenConverter = new FenConverter();
        Board testBoard = new Board();
        testBoard.initializeBoard();
        String pawnMoveStatus = fenConverter.findPawnWithMoveStatus(testBoard);
        assertEquals("-", pawnMoveStatus);
    }
    /**
     * Tests if the method findPawnWithMoveStatus performs correctly.
     */
    @Test
    void testFindPawnWithMoveStatus() {
        FenConverter fenConverter = new FenConverter();
        Board testBoard = new Board();
        testBoard.initializeBoard();
        Tile tempTile = testBoard.getActualTile(1, 1);
        Pawn tempPawn = (Pawn) tempTile.getPiece();
        tempPawn.setMoveStatus(1);
        testBoard.addPiece(1, 1, tempPawn);
        assertEquals("a2", fenConverter.findPawnWithMoveStatus(testBoard));
    }
    
    @Test
    void testBoardToFen() {
        fenConverter = new FenConverter();
        testBoard = new Board();
        testBoard.initializeBoard();
        player1 = "Player 1";
        player2 = "Player 2";
        fenConverter.boardToFen(testBoard, true, player1, player2);
        String fileContents = "";
        String actualFen = "";
        try (BufferedReader reader = new BufferedReader(new FileReader("./resources/saveGames/SavedFile.txt"))) {
            fileContents = reader.readLine();
            fileContents = reader.readLine();
            actualFen = reader.readLine();
            System.out.println(actualFen);


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertEquals("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR b KQkq - ", actualFen);
    }
}
