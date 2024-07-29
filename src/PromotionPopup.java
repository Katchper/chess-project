import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * This class handles the promotion screen prompt
 *
 * @author Kacper Dziedzic [ktd1]
 * @version 1.0 first iteration of javadoc
 */
public class PromotionPopup extends Application {

    private final boolean playerColor;
    private final Stage parentStage;

    /**
     * Constructor of the object WinPopUp
     * @param parentStage the stage.
     */
    public PromotionPopup(Stage parentStage, boolean playerColor) {
        this.playerColor = playerColor;
        this.parentStage = parentStage;
    }

    int pieceNo = 0;

    /**
     * This method draws out the window pop-up prompt when called.
     * @param stage the stage.
     */
    @Override
    public void start(Stage stage) {
        // pieceNo 0=queen 1=rook 2=bishop 3=knight
        // Set up the stage to be a modal dialog box
        stage.initStyle(StageStyle.UTILITY);
        stage.initOwner(parentStage);
        stage.setTitle("Promotion");

        Pane root = new Pane();

        String color;
        if (playerColor){
            color = "white";
        } else {
            color = "black";
        }

        Button queenButton = addButton("queen", 10, 10);
        Button bishopButton = addButton("bishop", 10, 70);
        Button rookButton = addButton("rook", 70, 10);
        Button knightButton = addButton("knight", 70, 70);


        //Button events which set the piece number and close the prompt
        queenButton.setOnAction(e -> {
            stage.close();
            pieceNo = 0;
        });
        rookButton.setOnAction(e -> {
            stage.close();
            pieceNo = 1;
        });
        bishopButton.setOnAction(e -> {
            stage.close();
            pieceNo = 2;
        });
        knightButton.setOnAction(e -> {
            stage.close();
            pieceNo = 3;
        });

        root.getChildren().addAll(queenButton, rookButton, bishopButton, knightButton);

        Scene scene = new Scene(root, 130, 130);
        stage.getIcons().add(new Image("file:resources/" + color + "_pawn.png"));
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.showAndWait();
    }

    /**
     * adds a button onto the screen using the parameters
     * @param name the name of the piece placed on the button
     * @param x coordinate on the x-axis
     * @param y coordinate on the y-axis
     * @return a complete button to be added to the scene
     */
    public Button addButton(String name, int x, int y){
        String color;
        if (playerColor){
            color = "white";
        } else {
            color = "black";
        }
        Image piece = new Image("file:resources/"+color+"_"+name+".png");
        ImageView view1 = new ImageView(piece);
        view1.setFitHeight(50);
        view1.setFitWidth(50);
        Button button = new Button("", view1);
        button.setMinSize(50,50);
        button.setMaxSize(50,50);
        button.setLayoutX(x);
        button.setLayoutY(y);

        return button;
    }


}
