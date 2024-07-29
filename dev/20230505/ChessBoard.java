package org.group18;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChessBoard {
    private GridPane board;
    private Tile[][] tiles;
    private Piece[] whitePieces;
    private Piece[] blackPieces;
    private int size = 8;
    private ImageView selectedPiece;
    private int selectedRow;
    private int selectedCol;

    public ChessBoard() {
        board = new GridPane();
        tiles = new Tile[size][size];
        whitePieces = new Piece[16];
        blackPieces = new Piece[16];

        // create and add tiles to the board
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Tile tile = new Tile(0, 0, true);
                tiles[row][col] = tile;

                Rectangle square = new Rectangle(50, 50);
                if ((row + col) % 2 == 0) {
                    square.setFill(Color.rgb(245, 245, 220));
                } else {
                    square.setFill(Color.rgb(128, 81, 54));
                }

                board.add(square, col, row);

                int finalRow = row;
                int finalCol = col;
                square.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
                    if (selectedPiece != null) {
                        // Move the selected piece to the clicked tile
                        int oldRow = selectedRow;
                        int oldCol = selectedCol;
                        Tile oldTile = tiles[oldRow][oldCol];
                        Tile newTile = tiles[finalRow][finalCol];
                        Piece piece = oldTile.getPiece();
                        piece.move(board, oldTile, newTile);
                        board.getChildren().remove(selectedPiece);
                        board.add(selectedPiece, finalCol, finalRow);

                        // Clear the selected piece
                        selectedPiece = null;
                        oldTile.setPiece(new EmptyPiece());
                        newTile.setPiece(piece);
                    }
                });
            }
        }

        // create and add pieces to the board
        for (int col = 0; col < size; col++) {
            // create and add black pawns
            blackPieces[col] = new Pawn(false);
            tiles[1][col].setPiece(blackPieces[col]);
            drawPiece("black_pawn", 1, col);

            // create and add white pawns
            whitePieces[col] = new Pawn(true);
            tiles[6][col].setPiece(whitePieces[col]);
            drawPiece("white_pawn", 6, col);
        }

        // create and add black rooks
        blackPieces[8] = new Rook(false);
        tiles[0][0].setPiece(blackPieces[8]);
        drawPiece("black_rook", 0, 0);

        blackPieces[9] = new Rook(false);
        tiles[0][7].setPiece(blackPieces[9]);
        drawPiece("black_rook", 0, 7);

        // create and add white rooks
        whitePieces[8] = new Rook(true);
        tiles[7][0].setPiece(whitePieces[8]);
        drawPiece("white_rook", 7, 0);

        whitePieces[9] = new Rook(true);
        tiles[7][7].setPiece(whitePieces[9]);
        drawPiece("white_rook", 7, 7);

        // create and add black knights
        blackPieces[10] = new Knight(false);
        tiles[0][1].setPiece(blackPieces[10]);
        drawPiece("black_knight", 0, 1);
        blackPieces[11] = new Knight(false);
        tiles[0][6].setPiece(blackPieces[11]);
        drawPiece("black_knight", 0, 6);

        // create and add white knights
        whitePieces[10] = new Knight(true);
        tiles[7][1].setPiece(whitePieces[10]);
        drawPiece("white_knight", 7, 1);

        whitePieces[11] = new Knight(true);
        tiles[7][6].setPiece(whitePieces[11]);
        drawPiece("white_knight", 7, 6);

        // create and add black bishops
        blackPieces[12] = new Bishop(false);
        tiles[0][2].setPiece(blackPieces[12]);
        drawPiece("black_bishop", 0, 2);

        blackPieces[13] = new Bishop(false);
        tiles[0][5].setPiece(blackPieces[13]);
        drawPiece("black_bishop", 0, 5);

        // create and add white bishops
        whitePieces[12] = new Bishop(true);
        tiles[7][2].setPiece(whitePieces[12]);
        drawPiece("white_bishop", 7, 2);

        whitePieces[13] = new Bishop(true);
        tiles[7][5].setPiece(whitePieces[13]);
        drawPiece("white_bishop", 7, 5);

        // create and add black queen
        blackPieces[14] = new Queen(false);
        tiles[0][3].setPiece(blackPieces[14]);
        drawPiece("black_queen", 0, 3);

        // create and add white queen
        whitePieces[14] = new Queen(true);
        tiles[7][3].setPiece(whitePieces[14]);
        drawPiece("white_queen", 7, 3);

        // create and add black king
        blackPieces[15] = new King(false);
        tiles[0][4].setPiece(blackPieces[15]);
        drawPiece("black_king", 0, 4);

        // create and add white king
        whitePieces[15] = new King(true);
        tiles[7][4].setPiece(whitePieces[15]);
        drawPiece("white_king", 7, 4);
    }

//    private void drawPiece(String imageName, int row, int col) {
//        Image image = new Image("file:src/main/resources/" + imageName + ".png");
//        ImageView imageView = new ImageView(image);
//        imageView.setFitWidth(50);
//        imageView.setFitHeight(50);
//        board.add(imageView, col, row);
//
//        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
//            // Select the clicked piece
//            selectedPiece = imageView;
//            selectedRow = row;
//            selectedCol = col;
//        });
//    }
private void drawPiece(String imageName, int row, int col) {
    Image image = new Image("file:src/main/resources/" + imageName + ".png");
    ImageView imageView = new ImageView(image);
    imageView.setFitWidth(50);
    imageView.setFitHeight(50);
    board.add(imageView, col, row);

    Piece piece = tiles[row][col].getPiece();
    piece.setImageView(imageView);

    imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
        // Select the clicked piece
        selectedPiece = imageView;
        selectedRow = row;
        selectedCol = col;
    });
}


    public Tile getTile(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return null; // Return null if the coordinates are out of bounds
        }
        return tiles[row][col];
    }



    public void start(Stage stage) {
        Scene scene = new Scene(board);
        stage.setTitle("Chess Tutor");
        stage.setScene(scene);
        stage.show();
    }
}
