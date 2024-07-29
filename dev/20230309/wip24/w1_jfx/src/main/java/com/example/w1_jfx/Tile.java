package com.example.w1_jfx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    public Tile (boolean white, int x, int y) // White so it sets the tile colour white or black, x = width, y = height.
    {
        setWidth(ChessApplication.tileSize); // Setting width of tile
        setHeight(ChessApplication.tileSize); // Setting height of tile

        relocate(x * ChessApplication.tileSize, y * ChessApplication.tileSize); // Moves tile to correct position

        setFill(white ? Color.rgb(255, 255, 204) : Color.rgb(102, 51, 0)); // Sets colour to white, if white, else to black
    }
}
