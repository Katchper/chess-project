import java.util.ArrayList;

public class Game {
    private boolean turn;
    private Board board;


    /**
     * white to move = true
     */
    public Game(){
        turn = true;
        board = new Board();
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
        Tile chosenTile = board.getTile(5,5);
        generateAllMoves();
        displayMoves(chosenTile.getPiece().getAllMoves());
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
            for (Tile move : moves) {
                System.out.print(move.getX());
                System.out.println(move.getY());
            }
        } catch (Exception ignore) {

        }
    }
    /**
     * generates the moves for every piece the moves are stored within the piece itself
     */
     public void generateAllMoves(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board.getTile(i, j).getPiece().generateMoves(board, board.getTile(i, j));
            }
        }
    }
}
