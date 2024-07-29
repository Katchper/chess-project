package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ChessTutor extends Application {
    private ImageView activePiece = null;

    @Override
    public void start(Stage stage) {
        GridPane board = new GridPane();
        int size = 8;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                Rectangle square = new Rectangle(50, 50);
                if ((row + col) % 2 == 0) {
                    square.setFill(Color.rgb(245, 245, 220));
                } else {
                    square.setFill(Color.rgb(128, 81, 54));
                }
                board.add(square, col, row);
                if (row == 1) {
                    ImageView blackPawn = new ImageView(new Image(getClass().getResourceAsStream("/resources/black_pawn.png")));
                    blackPawn.setFitWidth(50);
                    blackPawn.setFitHeight(50);
                    blackPawn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handlePieceClick(event));
                    board.add(blackPawn, col, row);
                }
                if (row == 6) {
                    ImageView whitePawn = new ImageView(new Image(getClass().getResourceAsStream("/resources/white_pawn.png")));
                    whitePawn.setFitWidth(50);
                    whitePawn.setFitHeight(50);
                    whitePawn.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handlePieceClick(event));
                    board.add(whitePawn, col, row);
                }
                if (row == 0 && (col == 0 || col == 7)) {
                    ImageView blackRook = new ImageView(new Image(getClass().getResourceAsStream("/resources/black_rook.png")));
                    blackRook.setFitWidth(50);
                    blackRook.setFitHeight(50);
                    blackRook.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handlePieceClick(event));
                    board.add(blackRook, col, row);
                }
                if (row == 0 && (col == 1 || col == 6)) {
                    ImageView blackKnight = new ImageView(new Image(getClass().getResourceAsStream("/resources/black_knight.png")));
                    blackKnight.setFitWidth(50);
                    blackKnight.setFitHeight(50);
                    blackKnight.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handlePieceClick(event));
                    board.add(blackKnight, col, row);
                }
                if (row == 0 && (col == 2 || col == 5)) {
                    ImageView blackBishop = new ImageView(new Image(getClass().getResourceAsStream("/resources/black_bishop.png")));
                    blackBishop.setFitWidth(50);
                    blackBishop.setFitHeight(50);
                    blackBishop.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handlePieceClick(event));
                    board.add(blackBishop, col, row);
                }
                if (row == 0 && col == 3) {
                    ImageView blackQueen = new ImageView(new Image(getClass().getResourceAsStream("/resources/black_queen.png")));
                    blackQueen.setFitWidth(50);
                    blackQueen.setFitHeight(50);
                    blackQueen.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handlePieceClick(event));
                    board.add(blackQueen, col, row);
                }
                if (row == 0 && col == 4) {
                    ImageView blackKing = new ImageView(new Image(getClass().getResourceAsStream("/resources/black_king.png")));
                    blackKing.setFitWidth(50);
                    blackKing.setFitHeight(50);
                    blackKing.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handlePieceClick(event));
                    board.add(blackKing, col, row);
                }
                if (row == 7 && (col == 0 || col == 7)) {
                    ImageView whiteRook = new ImageView(new Image(getClass().getResourceAsStream("/resources/white_rook.png")));
                    whiteRook.setFitWidth(50);
                    whiteRook.setFitHeight(50);
                    whiteRook.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handlePieceClick(event));
                    board.add(whiteRook, col, row);
                }
                if (row == 7 && (col == 1 || col == 6)) {
                    ImageView whiteKnight = new ImageView(new Image(getClass().getResourceAsStream("/resources/white_knight.png")));
                    whiteKnight.setFitWidth(50);
                    whiteKnight.setFitHeight(50);
                    whiteKnight.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handlePieceClick(event));
                    board.add(whiteKnight, col, row);
                }
                if (row == 7 && (col == 2 || col == 5)) {
                    ImageView whiteBishop = new ImageView(new Image(getClass().getResourceAsStream("/resources/white_bishop.png")));
                    whiteBishop.setFitWidth(50);
                    whiteBishop.setFitHeight(50);
                    whiteBishop.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handlePieceClick(event));
                    board.add(whiteBishop, col, row);
                }
                if (row == 7 && col == 3) {
                    ImageView whiteQueen = new ImageView(new Image(getClass().getResourceAsStream("/resources/white_queen.png")));
                    whiteQueen.setFitWidth(50);
                    whiteQueen.setFitHeight(50);
                    whiteQueen.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handlePieceClick(event));
                    board.add(whiteQueen, col, row);
                }
                if (row == 7 && col == 4) {
                    ImageView whiteKing = new ImageView(new Image(getClass().getResourceAsStream("/resources/white_king.png")));
                    whiteKing.setFitWidth(50);
                    whiteKing.setFitHeight(50);
                    whiteKing.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> handlePieceClick(event));
                    board.add(whiteKing, col, row);
                }
            }
        }

        Scene scene = new Scene(board);
        stage.setTitle("Chess Tutor");
        stage.setScene(scene);
        stage.show();
    }

    private void handlePieceClick(MouseEvent event) {
        ImageView piece = (ImageView) event.getSource();
        if (activePiece == null) {
            activePiece = piece;
        } else {
            GridPane board = (GridPane) piece.getParent();
            board.getChildren().remove(activePiece);
            board.add(activePiece, GridPane.getColumnIndex(piece), GridPane.getRowIndex(piece));
            activePiece = null;
        }
    }
}

