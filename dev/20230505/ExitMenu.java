package org.group18;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExitMenu extends Application {

    private Stage parentStage;

    public ExitMenu(Stage parentStage) {
        this.parentStage = parentStage;
    }

    @Override
    public void start(Stage stage) {
        // Set up the stage to be a modal dialog box
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parentStage);
        stage.setTitle("Exit");

        Label messageLabel = new Label("Do you want to quit the game?");

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            stage.close();
            parentStage.close();
        });
        noButton.setOnAction(e -> {
            stage.close();
        });

        // Add the label and buttons to a vertical box layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(messageLabel, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 250, 150);
        stage.setScene(scene);
        stage.showAndWait();
    }

}
