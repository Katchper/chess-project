import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class handles the player selection menu, which is displayed after selecting "New Game" on the manin menu.
 * @author (INSERT NAME HERE)
 * @version 1.0 first iteration of javadoc
 */
public class PlayerMenu extends Application {

    private Stage parentStage;

    /**
     * Constructor of PlayerMenu.
     * @param parentStage the stage
     */
    public PlayerMenu(Stage parentStage) {
        this.parentStage = parentStage;
    }

    /**
     * This method draws the player menu.
     * @param primaryStage The stage to be shown.
     */
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
        whiteBox.setSelected(true);

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
            parentStage.close();
            ChessBoard chessBoard;
                chessBoard = new ChessBoard(primaryStage, player1Name, player2Name, whiteBox.isSelected());
            chessBoard.start(primaryStage);
        });

        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> {
            // Close the load menu
            primaryStage.close();
        });

        HBox bottomLayout = new HBox(10);
        bottomLayout.setAlignment(Pos.CENTER_RIGHT);
        bottomLayout.getChildren().addAll( playButton, cancelButton);

        // Add the labels, text fields, and button to the layout
        root.getChildren().addAll(player1Label, player1TextField, player2Label, player2TextField, colorBox, bottomLayout);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Player Menu");
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.getIcons().add(new Image("file:resources/icon.png"));
        primaryStage.show();
    }

    

}
