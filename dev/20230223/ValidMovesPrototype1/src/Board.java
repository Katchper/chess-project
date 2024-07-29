public class Board {

    private char[][] board;
    private int size;
    public Board(int size){
        this.size = size;
        board = new char[size][size];
    }


    public void initiateBoard(){
        board[0][0] = 'R';
        board[0][7] = 'R';
        board[0][2] = 'B';
        board[0][5] = 'B';
        board[0][1] = 'N';
        board[0][6] = 'N';
        board[0][3] = 'Q';
        board[0][4] = 'K';

        board[7][0] = 'r';
        board[7][7] = 'r';
        board[7][2] = 'b';
        board[7][5] = 'b';
        board[7][1] = 'n';
        board[7][6] = 'n';
        board[7][3] = 'q';
        board[7][4] = 'k';

        for (int i = 0; i < size; i++) {
            board[6][i] = 'p';
            board[1][i] = 'P';
            board[5][i] = ' ';
            board[4][i] = ' ';
            board[3][i] = ' ';
            board[2][i] = ' ';
        }
        board[4][4] = 'P';
        board[4][5] = 'p';
    }

    public void generateBoard(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("["+board[i][j]+"]");
            }
            System.out.println();
        }
    }

    public void movePiece(Tuple piece, Tuple location){
        char temp = board[piece.getY()][piece.getX()];
        board[piece.getY()][piece.getX()] = ' ';
        board[location.getX()][location.getY()] = temp;
    }

    public void validPawnMoves(Tuple location){
        //move
        if(board[location.getY()+1][location.getX()] == ' '){
            System.out.println((location.getY()+1)+" "+location.getX());
        }
        if(board[location.getY()+2][location.getX()] == ' '){
            System.out.println((location.getY()+2)+" "+location.getX() );
        }
        //capture
        try {
            if(board[location.getY()+1][location.getX()-1] == 'p'){
                System.out.println((location.getY()-1)+" "+(location.getX()+1));
            }
        } catch (Exception ignore){
        }
        try {
            if(board[location.getY()+1][location.getX()+1] == 'p'){
                System.out.println((location.getY()+1)+" "+(location.getX()+1));
            }
        } catch (Exception ignore){
        }
        //en passant
        try {
            if(board[location.getY()][location.getX()+1] == 'p'){
                System.out.println((location.getY()+1)+" "+(location.getX()+1));
            }
        } catch (Exception ignore){
        }
        try {
            if(board[location.getY()][location.getX()-1] == 'p'){
                System.out.println((location.getY()+1)+" "+(location.getX()-1));
            }
        } catch (Exception ignore){
        }
    }

    public void validRookMoves(Tuple location){
        for (int i = -1; i < 3; i+=2) {

            for (int j = 1; j < 9; j++) {
                try {
                    if (board[location.getY()+(j*i)][location.getX()] == ' '){
                        System.out.println((location.getY()+(j*i))+" "+(location.getX()));
                    } else {
                        int charValue = board[location.getY()+(j*i)][location.getX()];
                        if (charValue > 95){
                            System.out.println((location.getY()+(j*i))+" "+(location.getX()));
                        }
                        break;
                    }
                } catch (Exception ignore){
                    break;
                }

            }
            for (int j = 1; j < 8; j++) {
                try {
                    if (board[location.getY()][location.getX()+(j*i)] == ' '){
                        System.out.println((location.getY())+" "+(location.getX()+(j*i)));
                    } else {
                        int charValue = board[location.getY()][location.getX()+(j*i)];
                        if (charValue > 95){
                            System.out.println((location.getY())+" "+(location.getX()+(j*i)));
                        }
                        break;
                    }
                } catch (Exception ignore){
                    break;
                }

            }
        }
    }

    public void validBishopMoves(Tuple location) {
        for (int j = 1; j < 8; j++) {
            try {
                if (board[location.getY() + j][location.getX() - j] == ' ') {
                    System.out.println((location.getY() + j) + " " + (location.getX() - j));
                } else {
                    int charValue = board[location.getY() + j][location.getX() - j];
                    if (charValue > 95) {
                        System.out.println((location.getY() + j) + " " + (location.getX() - j));
                    }
                    break;
                }
            } catch (Exception ignore) {
                break;
            }
        }
        for (int j = 1; j < 8; j++) {
            try {
                if (board[location.getY() - j][location.getX() - j] == ' ') {
                    System.out.println((location.getY() - j) + " " + (location.getX() - j));
                } else {
                    int charValue = board[location.getY() - j][location.getX() - j];
                    if (charValue > 95) {
                        System.out.println((location.getY() - j) + " " + (location.getX() - j));
                    }
                    break;
                }
            } catch (Exception ignore) {
                break;
            }

        }
        for (int j = 1; j < 8; j++) {
            try {
                if (board[location.getY() - j][location.getX() + j] == ' ') {
                    System.out.println((location.getY() - j) + " " + (location.getX() + j));
                } else {
                    int charValue = board[location.getY() - j][location.getX() + j];
                    if (charValue > 95) {
                        System.out.println((location.getY() - j) + " " + (location.getX() + j));
                    }
                    break;
                }
            } catch (Exception ignore) {
                break;
            }

        }
        for (int j = 1; j < 8; j++) {
            try {
                if (board[location.getY() + j][location.getX() + j] == ' ') {
                    System.out.println((location.getY() + j) + " " + (location.getX() + j));
                } else {
                    int charValue = board[location.getY() + j][location.getX() + j];
                    if (charValue > 95) {
                        System.out.println((location.getY() + j) + " " + (location.getX() + j));
                    }
                    break;
                }
            } catch (Exception ignore) {
                break;
            }
        }
    }

    public void validQueenMoves(Tuple location){
        validRookMoves(location);
        validBishopMoves(location);
    }

    public void validKnightMoves(Tuple location){
        for (int i = -2; i < 3; i+=4) {
            for (int j = -1; j < 2; j+=2) {

            }
        }
    }


        //00 01 02 03 04 05 06 07
        //10 11 12 13 14 15 16 17
        //20 21 22 23 24 25 26 27
        //30 31 32 33 34 35 36 37
        //40 41 42 43 44 45 46 47
        //50 51 52 53 54 55 56 57
        //60 61 62 63 64 65 66 67
        //70 71 72 73 74 75 76 77

}


