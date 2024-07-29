import java.util.ArrayList;

public class Pawn extends Piece{

    private int moveStatus;
  // 2 = can double move, 1 = double move last turn 0 = cant double move
    public Pawn(char cr, boolean colour){
        super(cr, colour);
        this.moveStatus = 2;
        if (colour){
            this.pieceChar = 'p';
        } else {
            this.pieceChar = 'P';
        }
    }

    public void updateMoveStatus(){
        if (moveStatus == 1){
            moveStatus = 0;
        }
    }

    public void doubleMoveUpdate(){
        moveStatus = 1;
    }

    public boolean doubleMoveLast(){
        return moveStatus == 1;
    }

    public ArrayList<Tile> generateMoves(Board board, Tile piece){
        ArrayList<Tile> squares = new ArrayList<>();
        Piece pawn = piece.getPiece();
        int x = piece.getX();
        int y = piece.getY();

        Tile upOne = board.getTile(x, y+1);
        Tile upTwo = board.getTile(x, y+2);
        Tile left = board.getTile(x-1, y);
        Tile right = board.getTile(x-1, y);
        Tile topLeft = board.getTile(x-1, y+1);
        Tile topRight = board.getTile(x-1, y+1);

        if (checkForPiece(upOne) == 0){
                squares.add(new Tile(x, y+1, pawn));
            if (checkForPiece(upTwo) == 0){
                squares.add(new Tile(x, y+2, pawn));
            }
        }
        if (checkForPiece(topRight) == 1){
            squares.add(new Tile(x+1, y+1, pawn));
        }
        if (checkForPiece(topLeft) == 1){
            squares.add(new Tile(x-1, y+1, pawn));
        }

        if (checkForPiece(left) == 1 && checkWhichPiece(left) == 1 && checkForPiece(topLeft) == 0){
            Pawn newPawn = (Pawn) left.getPiece();
            if (newPawn.doubleMoveLast()){
                squares.add(new Tile(x-1, y+1, pawn));
            }

        }
        if (checkForPiece(right) == 1 && checkWhichPiece(right) == 1 && checkForPiece(topRight) == 0){
            Pawn newPawn = (Pawn) right.getPiece();
            if (newPawn.doubleMoveLast()){
                squares.add(new Tile(x+1, y+1, pawn));
            }

        }
        //(y+1)(Y+2)(x+1 y+1) (x-1 y+1)
        return squares;
    }


}