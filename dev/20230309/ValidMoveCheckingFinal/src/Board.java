public class Board {
    private Tile[][] gameBoard;
    private boolean flipped;

    public Board(int size){
        flipped = false;
        gameBoard = new Tile[size][size];
    }

    public Board(){
        gameBoard = new Tile[8][8];
    }


    /**
     * function which sets the board into its default state
     *
     * 00 10 20 30 40 50 60 70
     * 01 11 21 31 41 51 61 71
     * 02 12 22 32 42 52 62 72
     * 03 13 23 33 43 53 63 73
     * 04 14 24 34 44 54 64 74
     * 05 15 25 35 45 55 65 75
     * 06 16 26 36 46 56 66 76
     * 07 17 27 37 47 57 67 77
     *
     * these are the default board coordinates (use for testing)
     */


    public void initialiseBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[j][i] = new Tile(j, i);
            }
        }
        for (int i = 0; i < 8; i++) {
            gameBoard[i][6] = new Tile(i,6, new Pawn(false));
            gameBoard[i][1] = new Tile(i , 1, new Pawn(true));
        }

        gameBoard[0][0] = new Tile(0,0, new Rook(true));
        gameBoard[7][0] = new Tile(7,0, new Rook(true));
        gameBoard[1][0] = new Tile(1,0, new Knight(true));
        gameBoard[6][0] = new Tile(6,0, new Knight(true));
        gameBoard[2][0] = new Tile(2,0, new Bishop(true));
        gameBoard[5][0] = new Tile(5,0, new Bishop(true));
        gameBoard[3][0] = new Tile(3,0, new Queen(true));
        gameBoard[4][0] = new Tile(4,0, new King(true));

        gameBoard[1][7] = new Tile(1,7, new Knight(false));
        gameBoard[6][7] = new Tile(6,7, new Knight(false));
        gameBoard[2][7] = new Tile(2,7, new Bishop(false));
        gameBoard[5][7] = new Tile(5,7, new Bishop(false));
        gameBoard[3][7] = new Tile(3,7, new Queen(false));
        gameBoard[4][7] = new Tile(4,7, new King(false));
        gameBoard[0][7] = new Tile(0,7, new Rook(false));
        gameBoard[7][7] = new Tile(7,7, new Rook(false));

        gameBoard[1][3] = new Tile(1,3, new Queen(true));
        //gameBoard[4][3] = new Tile(4,3, new Pawn(false, 1));
        //gameBoard[4][4] = new Tile(4,4, new Pawn(false));
    }

    /**
     * basic function to display the board
     */
    public void displayBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                char temp = getTile(j,i).getPiece().getPieceChar();
                System.out.print("[" + temp +"]");
            }
            System.out.println();
        }
    }

    /**
     * gets the specified tile based on x and y coordinates and whether its flipped
     * but it currently works by searching the gameboard coordinates instead of the specific tile coordinates
     * @param x
     * @param y
     * @return
     */
    public Tile getTile(int x, int y){
        if (x < 0 || x > 7){
            return null;
        } else if (y < 0 || y > 7) {
            return null;
        }
        if (flipped){
            y = Math.abs(y-7);
        }
        return gameBoard[x][y];
    }

    /**
     * currently being made, it's supposed to flip the coordinates within the tiles
     * but it's very confusing and needs some time until it works
     */

    public void flipBoard(){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                gameBoard[j][i].flipCoords();
            }
        }
        flipped = !flipped;
    }


}
