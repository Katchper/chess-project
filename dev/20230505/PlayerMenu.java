package org.group18;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PlayerMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(10);
        root.setPadding(new Insets(10));

        Label player1Label = new Label("Player 1 Name:");
        TextField player1TextField = new TextField();
        Label player2Label = new Label("Player 2 Name:");
        TextField player2TextField = new TextField();

        HBox colorBox = new HBox();
        colorBox.setAlignment(Pos.CENTER);
        colorBox.setSpacing(10);
        Label colorLabel = new Label("Player 1 Color:");

        CheckBox blackBox = new CheckBox("Black");
        CheckBox whiteBox = new CheckBox("White");

        // Group the checkboxes so only one can be selected
        blackBox.setOnAction(e -> {
            if (blackBox.isSelected()) {
                whiteBox.setSelected(false);
            }
        });
        whiteBox.setOnAction(e -> {
            if (whiteBox.isSelected()) {
                blackBox.setSelected(false);
            }
        });

        colorBox.getChildren().addAll(colorLabel, blackBox, whiteBox);

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> {
            String player1Name = player1TextField.getText();
            String player2Name = player2TextField.getText();
            String player1Color = "";
            if (blackBox.isSelected()) {
                player1Color = "Black";
            } else if (whiteBox.isSelected()) {
                player1Color = "White";
            }
            System.out.println("Playing with " + player1Name + " (" + player1Color + ") and " + player2Name);

            ChessBoard chessBoard = new ChessBoard();
            chessBoard.start(primaryStage);
        });

        // Add the labels, text fields, and button to the layout
        root.getChildren().addAll(player1Label, player1TextField, player2Label, player2TextField, colorBox, playButton);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Player Menu");
        primaryStage.show();
    }
}
