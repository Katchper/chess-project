public class Main {
    public static void main(String[] args) {
        Board newBoard = new Board(8);
        newBoard.initiateBoard();
        newBoard.generateBoard();
        Tuple location = new Tuple(0,0);
        Tuple location2 = new Tuple(4,4);
        //newBoard.movePiece(location, location2);
        //newBoard.generateBoard();
        newBoard.validPawnMoves(location2);
    }
}