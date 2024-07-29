import java.util.ArrayList;

public class Check {

    public void checkCheck(Tile tile, Board board){
        int oX = tile.getX();
        int oY = tile.getY();
        System.out.println("checkCheck has been triggered.");
        int size = tile.getPiece().getAllMoves().size();
        Tile array[] = new Tile[size];
        tile.getPiece().getAllMoves().toArray(array);
        for (int i = 0; i < size; i++){
            int x = array[i].getX();
            int y = array[i].getY();
            if(board.getTile(x, y).getPiece().checkWhichPiece(array[i]) == 2){
                System.out.println("Check state is in affect.");
                    Tile newTile = board.getTile(x, y);
                    int newSize = newTile.getPiece().getAllMoves().size();
                    Tile newArray[] = new Tile[newSize];
                    newTile.getPiece().getAllMoves().toArray();
                    // Insert checkmate here
                    // Below is placeholder / dogshite
                if (board.getTile(x, y).getPiece().getAllMoves() == null){
                    System.out.println("Checkmate requirements have been met.");
                }
                break;
            }

        }

    }
    
    
}
