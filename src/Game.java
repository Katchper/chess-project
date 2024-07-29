import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * The Game class of the program, which initializes parts of the chess tutor.
 * This is written referencing the Software Engineering Group 18 Project Design Specification.
 * @author Archie Malvern, Kacper Dziedzic [ktd1]
 * @version 1.0
 */

public class Game {

    private boolean turn;
    private Board board;
    private final Check check;
    private final String fenSave;
    private final String Player1;
    private final String Player2;
    private String fileName;
    private ArrayList<String> fenList;


    /**
     * The constructor for the game class. Creates a Board and Check object.
     */
    public Game(String Player1, String Player2) {
        LocalTime localTime = LocalTime.now();
        Calendar calendar = Calendar.getInstance();
        turn = true;
        board = new Board();
        check = new Check();
        fenSave = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 0";
        this.Player1 = Player1;
        this.Player2 = Player2;
        fileName = "resources/saveGames/"+Player1+Player2+localTime.getHour()+"-"
                +localTime.getMinute()+"-"+localTime.getSecond()+"-"
                +calendar.get(Calendar.DATE)+"-"+calendar.get(Calendar.MONTH)
                +"-"+calendar.get(Calendar.YEAR)+".txt";

        System.out.println(fileName);
    }

    public Game(String directory, String Player1, String Player2) {
        LocalTime localTime = LocalTime.now();
        Calendar calendar = Calendar.getInstance();
        turn = true;
        board = new Board();
        check = new Check();
        fenSave = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 0";
        this.Player1 = Player1;
        this.Player2 = Player2;

        if (directory.contains(".txt")){
            fileName = directory;
        } else {
            fileName = "resources/saveGames/"+Player1+Player2+localTime.getHour()+"-"+localTime.getMinute()+"-"
                    +localTime.getSecond()+"-"+calendar.get(Calendar.DATE)+"-"
                    +calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.YEAR)+".txt";
        }
    }
    public Game(String directory){
        turn = true;
        board = new Board();
        check = new Check();
        fileName = directory;
        this.Player1 = "test1";
        this.Player2 = "test2";
        FenConverter fenConv = new FenConverter();
        fenSave = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 0";
        try {
            this.fenList = fenConv.getAllFenStrings(fileName);
            board = fenConv.loadBoardFromFen(fenList.get(0));
        } catch (Exception exception){
            board = fenConv.loadBoardFromFen(fenSave);
        }
    }
    /**
     * Initiates and displays the game board.
     */
    public void initiateGame(){
        FenConverter fenConv = new FenConverter();
        try {
            this.fenList = fenConv.getAllFenStrings(fileName);
            board = fenConv.loadBoardFromFen(fenConv.topFenString(fileName));
        } catch (Exception exception){
            board = fenConv.loadBoardFromFen(fenSave);
        }

        if (!fenConv.isTurnColor()){
            turnExecution();
        }

        if (fileName.contains(".txt")){
            fenConv.writeLineToFile(fileName, Player1);
            fenConv.writeLineToFile(fileName, Player2);
            fenConv.writeLineToFile(fileName, fenConv.boardToFen(board, turn));
        }

        board.generateAllMoves();
        board.filterAllMoves();
    }

    /**
     * Loads a game from a FEN string.
     */
    public void loadGame(String gameFEN){
        FenConverter fen = new FenConverter();
        board = fen.loadBoardFromFen(gameFEN);
    }

    /**
     * Iterates over every pawn of the colour opposite to the turn
     * and updates their "move status"
     * @param color color of the pawns being switched
     */
    public void updateColorPawns(boolean color){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Piece piece = getBoard().getActualTile(i,j).getPiece();
                if (piece.getColor() != color){
                    if (piece.getPieceName().equals("white_pawn") || piece.getPieceName().equals("black_pawn")){
                        Pawn temp = (Pawn)getBoard().getActualTile(i,j).getPiece();
                        temp.updateMoveStatus();
                    }
                }
            }
        }
    }

    /**
     * checks to see whether a check is active and returns the corresponding
     * tile containing the king in check
     * @return the tile with the king in check
     */
    public Tile checkCheck(){
        Tile coords = null;
        int gameState = check.checkCheck(board.findKing(turn), board);

        if (gameState == 1){
            Tile kingCoords = board.findKing(turn);
            coords = new Tile(kingCoords.getX(), kingCoords.getY());
            // move type 100 = check
            coords.setMoveType(100);
        } else if (gameState == 2) {
            Tile kingCoords = board.findKing(turn);
            coords = new Tile(kingCoords.getX(), kingCoords.getY());
            // move type 999 = checkmate
            coords.setMoveType(999);
            FenConverter fen = new FenConverter();
            if (!kingCoords.getPiece().getColor()){
                fen.writeLineToFile(fileName, Player1);
            } else {
                fen.writeLineToFile(fileName, Player2);
            }


        }

        return coords;
    }

    /**
     * check if move entered on GUI is valid 1 if valid 0 if not
     * @param firstX the piece X coordinate
     * @param firstY the piece Y coordinate
     * @param destX the piece destination X or the target square X
     * @param destY the piece destination Y or the target square Y
     * @return 1 or 0
     */
    public int checkMove(int firstX, int firstY, int destX, int destY){
        Tile piece = board.getActualTile(firstX, firstY);
        Tile destination = board.getActualTile(destX, destY);

        for (Tile currentMove : piece.getPiece().getAllMoves()) {
            int x = currentMove.getX();
            int y = currentMove.getY();
            if (destination.getX() == x && destination.getY() == y){
                return 1;
            }
        }
        return 0;
    }

    /**
     * return all the tiles of the board in an ArrayList
     * @return the ArrayList of every tile
     */
    public ArrayList<Tile> pieceTiles(){
        ArrayList<Tile> pieces = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pieces.add(board.getActualTile(i, j));
            }
        }
        return pieces;
    }

    /**
     * the replay method for updating the board based on an index
     * to obtain a relevant fenstring.
     * @param index the index of the board
     * @return checkSquareTile
     */
    public Tile replayExecution(int index){
        FenConverter fenConv = new FenConverter();
        return checkCheck();
    }

    /**
     * the sequence which occurs when the turn is going to be switched
     */
    public void turnExecution(){
        FenConverter fenConv = new FenConverter();
        if(turn) {
            fenConv.writeLineToFile(fileName, fenConv.boardToFen(board, turn));
        }
        updateColorPawns(turn);
        board.flipBoard();
        if(!turn) {
            fenConv.writeLineToFile(fileName, fenConv.boardToFen(board, turn));
        }
        changeTurn();
        board.generateAllMoves();
        board.filterAllMoves();
    }

    /**
     * returns the current turn
     * @return which color's turn it is
     */
    public boolean isTurn() {
        return turn;
    }

    /**
     * switches the turn to the opposite color
     */
    public void changeTurn() {
        this.turn = !turn;
    }

    /**
     * gets the board and returns it
     * @return the board
     */
    public Board getBoard() {
        return board;
    }

    public ArrayList<String> getFenList() {
        return fenList;
    }
}
