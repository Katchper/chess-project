import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This is the main class where it runs first whenever we start this program.
 * @author (INSERT NAME HERE)
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Constructor of the main object
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Loads the main menu of the program
     * @param stage the stage which shows the main menu window.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Chess Tutor");

        Button startButton = new Button("Start Game");
        startButton.setOnAction(e -> {
            PlayerMenu chessTutor = new PlayerMenu(stage);
            chessTutor.start(new Stage());
            System.out.println("Play");
        });

        Button loadButton = new Button("Load Game");
        loadButton.setOnAction(e -> {
            LoadMenu loadMenu = new LoadMenu(stage,"Load Menu", false);
            loadMenu.start(new Stage());
            System.out.println("load game button clicked");
        });


        Button replayButton = new Button("Replay Game");
        replayButton.setOnAction(e -> {
            LoadMenu loadMenu = new LoadMenu(stage,"Replay Menu", true);
            loadMenu.start(new Stage());
            System.out.println("Replay game button clicked");

        });
        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> {
            // If "Quit" is clicked, show the exit menu
            ExitMenu exitMenu = new ExitMenu(stage, "quit");
            exitMenu.start(new Stage());
        });

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
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:resources/icon.png"));
        stage.show();
        stage.centerOnScreen();
    }

}
