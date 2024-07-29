public class Board {
    private Tile[][] gameBoard;

    public Board(int size){
        gameBoard = new Tile[size][size];
    }
    public Board(){
        gameBoard = new Tile[8][8];
    }

    public void initialiseBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[i][j] = new Tile(i, Math.abs(i-7), new Piece());
            }
        }
    }
    public void displayBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print("[" + getTile(j,i).getPiece().getPieceChar() +"]");
            }
            System.out.println();
        }
    }
    public Tile getTile(int x, int y){
        //System.out.print(x);
        //System.out.print(Math.abs(y-7));
        return gameBoard[x][Math.abs(y-7)];
    }

    public void setTile(int x, int y, char piece, boolean isWhite) {
        gameBoard[x][Math.abs(y-7)].setPiece(new Piece(piece, isWhite));
    }

}
