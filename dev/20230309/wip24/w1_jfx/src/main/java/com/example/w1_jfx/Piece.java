package com.example.w1_jfx;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;


public class Piece extends StackPane {

    private int tileSize = ChessApplication.tileSize; // Grabbing tileSize from ChessApplication

    private double pieceSize = tileSize * 0.25; // Pieces will be set to 25% of the size of the tileSize

    private double mouseX; // Used for getting mouse location, X axis
    private double mouseY; // Used for getting mouse location, Y axis

    public Piece(boolean white, int x, int y){
        relocate(x * tileSize, y * tileSize); // Sets piece starting location

        Ellipse pc = new Ellipse(pieceSize, pieceSize); // Setting up the piece

        pc.setFill(white ? Color.rgb(245, 245, 245) : Color.rgb(50, 50, 50)); // Setting colour of piece, depending on boolean

        pc.setTranslateX((tileSize - pieceSize * 2) / 2); // Sets piece in the middle of tile

        pc.setTranslateY((tileSize - pieceSize * 2) / 2); // Sets piece in the middle of tile

        setOnMousePressed(e -> {
            mouseX = e.getSceneX(); // Mouse location
            mouseY = e.getSceneY(); // Mouse location
            relocate(mouseX - 70, mouseY - 70); // Sends piece to mouse location
        });

        setOnMouseDragged(e -> {
            mouseX = e.getSceneX(); // Mouse location
            mouseY = e.getSceneY(); // Mouse location
            relocate(mouseX - 70, mouseY - 70); // Sends piece to mouse location
        });

        setOnMouseReleased(e -> {
            int gridX = (int)e.getSceneX() / tileSize; // Getting the nearest tile location
            int gridY = (int)e.getSceneY() / tileSize; // Getting the nearest tile location
            mouseX = (tileSize / 2 + tileSize * gridX); // Setting up so on release, piece will move to middle of tile
            mouseY = (tileSize / 2 + tileSize * gridY); // Setting up so on release, piece will move to middle of tile
            relocate(mouseX - 74, mouseY - 74); // Moves piece to middle of nearest tile

        });



        getChildren().addAll(pc);
    }
}
