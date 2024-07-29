import java.util.ArrayList;

/**
 * This class handles everything about the pawn piece of the chessboard, and it extends the interface Piece.
 * @author Kacper Dziedzic [ktd1]
 * @version 1.0 first iteration of javadoc
 */
public class Pawn extends Piece{

    private int moveStatus;
    // 2 = can double move, 1 = double move last turn 0 = cant double move

    /**
     * The constructor of the pawn piece.
     * @param color the color of the pawn piece.
     */
    public Pawn(boolean color){
        super(color);
        this.moveStatus = 2;
        if (color){
            this.pieceName = "white_pawn";
        } else {
            this.pieceName = "black_pawn";
        }
    }

    /**
     * a constructor which will be used for loading games, the move status of the pawn is passed through as
     * a
     * @param color the color of the pawn
     * @param moveStatus
     */
    public Pawn(boolean color, int moveStatus){
        super(color);
        this.moveStatus = moveStatus;
        if (color){
            this.pieceName = "white_pawn";
        } else {
            this.pieceName = "black_pawn";
        }
    }

    /**
     * method used to update the pawn's current move status, 1 means it double moved last turn
     * this method checks that and fixes it at the start of a turn (en passant requires a pawn
     * to have double moved last turn)
     */
    public void updateMoveStatus(){
        if (moveStatus == 1){
            moveStatus = 0;
        }
    }

    /**
     * sets the move status to whatever the pawn has done (will be done whenever a pawn does any move)
     */

    public void setMoveStatus(int moveStatus){
        this.moveStatus = moveStatus;
    }
    /**
     * checks if this pawn has done a double move last turn
     * @return if it double-moved
     */
    public boolean doubleMoveLast(){
        return moveStatus == 1;
    }

    public int getMoveStatus(){
        return moveStatus;
    }

    /**
     * generates all the possible moves for the pawn the 2 tiles above the pawn as well as capturing
     * it also calls the en passant method adding any valid en passant squares to the list
     * @param board the board in play
     * @param piece the Tile containing the pawn
     */
    public void generateMoves(Board board, Tile piece){
        ArrayList<Tile> squares = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();
        int direction;
        if (!board.isFlipped()){
            if (piece.getPiece().getColor()){
                direction = -1;
            } else {
                direction = 1;
            }
        } else {
            if (piece.getPiece().getColor()){
                direction = 1;
            } else {
                direction = -1;
            }
        }
        Tile upOne = board.getActualTile(x, y+direction);
        Tile upTwo = board.getActualTile(x, y+(direction*2));
        Tile topLeft = board.getActualTile(x-1, y+direction);
        Tile topRight = board.getActualTile(x+1, y+direction);

        Tile upOneMove = new Tile(x, y+direction);
        Tile upTwoMove = new Tile(x, y+(direction*2));
        Tile capLeftMove = new Tile(x-1, y+direction);
        Tile capRightMove = new Tile(x+1, y+direction);

        //the 2 straight line moves for a pawn
        if (checkForPiece(upOne) == 0){
            upOneMove.setMoveType(0);
            squares.add(upOneMove);
            if (checkForPiece(upTwo) == 0 && moveStatus == 2){
                upTwoMove.setMoveType(1);
                squares.add(upTwoMove);
            }
        }
        //capturing another piece
        if (checkForPiece(topRight) == 1){
            capRightMove.setMoveType(0);
            squares.add(capRightMove);
        }
        if (checkForPiece(topLeft) == 1){
            capLeftMove.setMoveType(0);
            squares.add(capLeftMove);
        }

        squares.addAll(enPassantMoves(board, piece, direction));
        this.moves = squares;
    }

    /**
     * En passant method which completes all the pre-requisite checks to see if en passant can be
     * done on either the left or right square
     * @param board the board in play
     * @param piece the Tile containing the pawn
     * @return list of possible en passant moves
     */
    public ArrayList<Tile> enPassantMoves(Board board, Tile piece, int direction){
        ArrayList<Tile> squares = new ArrayList<>();
        int x = piece.getX();
        int y = piece.getY();
        Tile move1 = new Tile(x-1, y+direction);
        Tile move2 = new Tile(x+1, y+direction);

        Tile topLeft = board.getActualTile(x-1, y+direction);
        Tile topRight = board.getActualTile(x+1, y+direction);
        Tile leftTile = board.getActualTile(x-1, y);
        Tile rightTile = board.getActualTile(x+1, y);

        if (leftTile != null){
            if (leftTile.getPiece().checkWhichPiece(leftTile) == 1){
                Pawn leftPawn = (Pawn)leftTile.getPiece();
                if (leftPawn.doubleMoveLast()){
                    if (topLeft.isEmpty()){
                        move1.setMoveType(3);
                        squares.add(move1);
                    }

                }
            }
        }

        if (rightTile != null){
            if (rightTile.getPiece().checkWhichPiece(rightTile) == 1){
                Pawn rightPawn = (Pawn)rightTile.getPiece();
                if (rightPawn.doubleMoveLast()){
                    if (topRight.isEmpty()){
                        move2.setMoveType(3);
                        squares.add(move2);
                    }

                }
            }
        }

        return squares;
    }

}
