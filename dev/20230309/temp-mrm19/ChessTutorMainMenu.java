package src;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChessTutorMainMenu extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Chess Tutor");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            ChessTutor chessTutor = new ChessTutor();
            chessTutor.start(new Stage());
        });

        Button loadButton = new Button("Load Game");
        // Placeholder actions for load game and replay game buttons
        loadButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Load game button clicked");
            }
        });
        Button replayButton = new Button("Replay Game");
        replayButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Replay game button clicked");
            }
        });
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> stage.close());

        VBox menu = new VBox(10);
        menu.getChildren().addAll(startButton, loadButton, replayButton, quitButton);
        menu.setSpacing(10);
        menu.setAlignment(Pos.CENTER);

        menu.setStyle("-fx-background-color: #805136;");
        startButton.setStyle("-fx-background-color: #F5F5DC;");
        loadButton.setStyle("-fx-background-color: #F5F5DC;");
        replayButton.setStyle("-fx-background-color: #F5F5DC;");
        quitButton.setStyle("-fx-background-color: #F5F5DC;");


        Scene scene = new Scene(menu, 400, 400);
        stage.setScene(scene);
        stage.show();
    }

}
