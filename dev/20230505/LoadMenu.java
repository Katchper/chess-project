package org.group18;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class LoadMenu extends Application {
    private File selectedFile = null;
    private TextField fileNameTextField;
    private TextField fenStringTextField;

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));


        Button loadButton = new Button("Load");
        loadButton.setOnAction(event -> {
            // Read the selected file and generate the FEN string
            if (selectedFile != null) {
                try {
                    byte[] bytes = Files.readAllBytes(selectedFile.toPath());
                    String fenString = new String(bytes, StandardCharsets.UTF_8);
                    fenStringTextField.setText(fenString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });


        Button eraseButton = new Button("Erase");
        eraseButton.setOnAction(event -> {
            // Clear the file name and FEN string text fields
            fileNameTextField.setText("");
            fenStringTextField.setText("");
            selectedFile = null;
        });


        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
            // Close the load menu
            primaryStage.close();
        });

        // Create the bottom layout with the load, erase, and cancel buttons
        HBox bottomLayout = new HBox(10);
        bottomLayout.setAlignment(Pos.CENTER_RIGHT);
        bottomLayout.getChildren().addAll(loadButton, eraseButton, cancelButton);
        root.setBottom(bottomLayout);

        // Create the center layout with the file name and FEN string text fields
        VBox centerLayout = new VBox(10);
        centerLayout.setAlignment(Pos.CENTER);
        root.setCenter(centerLayout);

        Label fileNameLabel = new Label("File Name:");
        fileNameTextField = new TextField();
        fileNameTextField.setEditable(false);

        Label fenStringLabel = new Label("FEN String:");
        fenStringTextField = new TextField();
        fenStringTextField.setEditable(false);

        centerLayout.getChildren().addAll(fileNameLabel, fileNameTextField, fenStringLabel, fenStringTextField);

        // Create the top layout with the "Load Game" label and the file chooser button
        HBox topLayout = new HBox(10);
        topLayout.setAlignment(Pos.CENTER_LEFT);

        Label loadLabel = new Label("Load Game");
        topLayout.getChildren().add(loadLabel);

        Button fileChooserButton = new Button("Select File");
        fileChooserButton.setOnAction(event -> {
            // Open the file chooser dialog and set the selected file
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Select FEN File");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("FEN Files", "*.fen"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                selectedFile = file;
                fileNameTextField.setText(file.getName());
            }
        });
        topLayout.getChildren().add(fileChooserButton);

        root.setTop(topLayout);


        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Load Menu");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
