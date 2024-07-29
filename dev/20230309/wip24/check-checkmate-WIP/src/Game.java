import java.util.ArrayList;

public class Game {
    private boolean turn;
    private Board board;
    private Check check;


    /**
     * white to move = true
     */
    public Game(){
        turn = true;
        board = new Board();
        check = new Check();
    }

    /**
     * test function, will change all the time depending on what im testing
     * currently displays the moves for a selected piece.
     * first i get the tile from the board then i pass it through the display moves
     * function here which goes through the moves array list and displays the x and y for each move
     */
    public void testGame(){
        board.initialiseBoard();
        //board.flipBoard();
        board.displayBoard();
        Tile chosenTile = board.getTile(5,3);
        //Tile chosenTile = board.getTile(1,3);
        generateAllMoves();
        displayMoves(chosenTile.getPiece().getAllMoves());
        check.checkCheck(chosenTile, board);
    }

    public Piece pieceConvert(Piece piece){
        return piece;
    }

    public void turnSequence(){

    }

    /**
     * temporary prototyping function which displays the moves which are generated with a generateMoves
     * @param moves
     */
    public void displayMoves(ArrayList<Tile> moves){
        try {
            System.out.println("Moves: ");
            for (Tile move : moves) {
                System.out.print("Tile x " + move.getX());
                System.out.println(". Tile y " + move.getY());
            }
        }
        catch (Exception ignore) {

        }
    }
    /**
     * generates the moves for every piece the moves are stored within the piece itself
     */
     public void generateAllMoves(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                int c = 1;
                c += 1;
                //System.out.println("Current iteration is: " + c + ".");
                //System.out.print("Current x is: " + i + ". Current y is: " + j + ".");
                //System.out.println(" Tile info: " + board.getTile(i, j).getPiece().toString() + ".");
                board.getTile(i, j).getPiece().generateMoves(board, board.getTile(i, j));
            }
        }
    }
}
