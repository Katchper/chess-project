import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class handles all the window prompt when a player select either
 * "Resign", "Draw", or "Exit" during their turn in the middle of the game.
 *
 * @author Muntazir Rashid [mrm19]
 * @version 1.1 updated Javadoc
 */
public class ExitMenu extends Application {

    private Stage parentStage;
    private String message;

    public ExitMenu(Stage parentStage, String message) {
        this.parentStage = parentStage;
        this.message = message;
    }

    boolean no = false;

    @Override
    public void start(Stage stage) {
        // Set up the stage to be a modal dialog box
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(parentStage);
        stage.setTitle(message.toUpperCase());

        Label messageLabel = new Label("Do you want to "+message+" ?");

        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");

        yesButton.setOnAction(e -> {
            stage.close();
            parentStage.close();
            no = true;
        });
        noButton.setOnAction(e -> {
            stage.close();
        });

        // Add the label and buttons to a vertical box layout
        VBox layout = new VBox(10);
        layout.getChildren().addAll(messageLabel, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 250, 150);
        stage.setResizable(false);
        stage.getIcons().add(new Image("file:resources/icon.png"));
        stage.setScene(scene);
        stage.showAndWait();
    }

}
