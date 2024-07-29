package com.example.w1_jfx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class ChessApplication extends Application{

    public static int tileSize = 75; // Basic size of tile
    public static int widthAndHeight = 8; // Chess board is 8 x 8

    private Group tileGroup = new Group(); // Grouping the tiles together
    private Group pieceGroup = new Group(); // Grouping the pieces together

    private Parent createContent() {
        Pane root = new Pane(); // Create window
        root.setPrefSize(1280, 720); // Standard size of board
        root.getChildren().addAll(tileGroup, pieceGroup); // Adds tiles & pieces to window

        for (int y = 0; y < widthAndHeight; y++){ // For every column
            for (int x = 0; x < widthAndHeight; x++){ // For every row
                Tile tile = new Tile((y + x) % 2 == 0, y, x); // New tile where every other is different colour
                Piece piece; // Setting up piece
                boolean pieceColour; // Whether piece is black or white
                if (x > 5){ // If at lower end, it's white
                    pieceColour = true;
                }
                else { // Else it's black
                    pieceColour = false;
                }
                if (x < 2 || x > 5){ // Pieces only get create at the bottom & top two rows
                    // piece = makePiece(pieceColour, y, x);
                    // if (piece != null){
                        //pieceGroup.getChildren().add(piece);
                   // }

                }
                tileGroup.getChildren().add(tile);
            }
        }
        return root;
    }

    private Piece makePiece(boolean type, int x, int y){
        Piece piece = new Piece(type, x, y);

        return piece;
    }


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(createContent());
        stage.setTitle("Chess tutor");
        stage.setScene(scene);
        stage.show();
    }
}

