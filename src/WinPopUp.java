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
 * This class handles all the pop-up prompt when a player select either "Draw", "Resign", or "Exit"
 * during their turn in the middle of the chess game.
 *
 * @author
 * @version 1.0 first iteration of javadoc
 */
public class WinPopUp extends Application {

   private Stage parentStage;
   private String message;
   boolean draw = false;
   boolean secondMenu = false;

   /**
    * Constructor of the object WinPopUp
    * @param parentStage the stage.
    * @param message the message to be shown on the pop-up prompt.
    */
   public WinPopUp(Stage parentStage, String message) {
      this.parentStage = parentStage;
      this.message = message;
   }
   public WinPopUp(Stage parentStage, String message, boolean draw, boolean secondMenu) {
      this.parentStage = parentStage;
      this.message = message;
      this.draw = draw;
      this.secondMenu = secondMenu;
   }

   boolean no = false;

   /**
    * This methods draws out the window pop-up prompt when called.
    * @param stage the stage.
    */
   @Override
   public void start(Stage stage) {
      // Set up the stage to be a modal dialog box
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.initOwner(parentStage);
      stage.setTitle(message.toUpperCase());
      stage.centerOnScreen();

      Label messageLabel = new Label(message);

      Button okButton = new Button("OK");
      Button noButton = new Button("NO");


      okButton.setOnAction(e -> {

         if (draw){
            WinPopUp popUp = new WinPopUp(parentStage, "Do you want to accept a Draw", false, true);
            popUp.start(new Stage());
            stage.close();
         }else {
            Main main = new Main();
            main.start(new Stage());
            parentStage.close();
            stage.close();
         }



      });

      noButton.setOnAction(e->{
         stage.close();
      });


      // Add the label and buttons to a vertical box layout
      VBox layout = new VBox(10);
      if(draw){
         layout.getChildren().addAll(messageLabel, okButton, noButton);
      } else if (secondMenu) {
         layout.getChildren().addAll(messageLabel, okButton, noButton);
      } else {
         layout.getChildren().addAll(messageLabel, okButton);
      }

      layout.setAlignment(Pos.CENTER);

      Scene scene = new Scene(layout, 250, 150);
      stage.setScene(scene);
      stage.setResizable(false);
      stage.getIcons().add(new Image("file:resources/icon.png"));
      stage.showAndWait();
   }

}
