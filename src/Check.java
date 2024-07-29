import java.util.ArrayList;

/**
 * This class handle all the check mechanics in a chess game, including regular check and checkmate.
 * @author William Parry [wip24], Kacper Dziedzic [ktd1]
 * @version 1.1 First iteration of javadoc
 */

public class Check {

    /**
     * This method checks all the check condition on a king, and where said check is a checkmate or not.
     * @param originalTile the tile of the piece
     * @param originalBoard the board where the tile is on
     * @return  2 if checkmated, 1 if check, 0 otherwise.
     */
    public int checkCheck(Tile originalTile, Board originalBoard) {
        int originalX = originalTile.getX();
        int originalY = originalTile.getY();
        boolean check = false;
        boolean checkmate = false;
        // check if any tiles are going over your king if so then check = true
        // checkmate = true if there are no valid moves
        ArrayList<Tile> squares1 = new ArrayList<>(originalBoard.getAllColorMoves(originalTile.getPiece().getColor()));
        ArrayList<Tile> squares2 = new ArrayList<>(originalBoard.getAllColorMoves(!originalTile.getPiece().getColor()));

        if (squares1.size() == 0){
            checkmate = true;
        }

        for (Tile move: squares2) {
            if (move.getX() == originalX && move.getY() == originalY) {
                check = true;
                break;
            }
        }

        if (check && checkmate) {
            return 2;
        } else if (check) {
            return 1;
        }
        return 0;
    }
}
