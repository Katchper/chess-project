/**
 * The chessBoard class, handles interaction between UI and the backend.
 * @author Muntazir Rashid [mrm19], Kacper Dziedzic [ktd1], Archie Malvern [arm36]
 * @version 1.4
 * @
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class ReplayBoard extends Application {
   private final String playerOne;
   private final String playerTwo;
   private final boolean playerOneColor;
   private final Stage parentStage;
   private String fenSave;
   String currentPlayer;
   private ArrayList<String> boardPositions= new ArrayList<>();


   private int count = 0;

   public ReplayBoard(Stage parentStage, String nameOne, String nameTwo, boolean turn) {
      this.parentStage = parentStage;
      this.playerOne = nameOne;
      this.playerTwo = nameTwo;
      this.playerOneColor = turn;

      if(this.playerOneColor)
         this.currentPlayer = playerOne;
      else {
         this.currentPlayer = playerTwo;
      }
   }

   public ReplayBoard(Stage parentStage, String fenSave, String nameOne, String nameTwo, boolean turn) {
      this.parentStage = parentStage;
      this.fenSave = fenSave;
      this.playerOne = nameOne;
      this.playerTwo = nameTwo;
      this.playerOneColor = turn;
      if(this.playerOneColor)
         this.currentPlayer = playerOne;
      else {
         this.currentPlayer = playerTwo;
      }
   }
   private Paint lastCheckSquareColour;
   private Tile squareInCheck;
   private ArrayList<Circle> dotsList;
   private int selectedX;
   private int selectedY;
   private Tile heldPiece;
   private Rectangle[][] tileStore;
   private ArrayList<ImageView> pieces;
   private Game game;

   /**
    * Starts the chess game and creates its window.
    * @param stage  The top level JavaFX container.
    */
   @Override
   public void start(Stage stage) {
      pieces = new ArrayList<>();
      game = new Game(fenSave);
      tileStore = new Rectangle[8][8];
      boardPositions.addAll(game.getFenList());

      Pane root = new Pane();
      Scene scene = new Scene(root, 1280, 720);

      root = initText(root, playerOne, playerTwo);
      root = addButtons(root);

      game.initiateGame();
      createBoard(root);

      // here goes any test code you want

      customPiecesToBoard(root, game.pieceTiles());

      stage.setTitle("Chess");
      stage.setScene(scene);
      stage.centerOnScreen();
      stage.setResizable(false);
      stage.getIcons().add(new Image("file:resources/icon.png"));
      stage.show();
   }

   /**
    * Creates and draws the board.
    * @param root The pane we are drawing to.
    */
   public void createBoard(Pane root){
      for (int i = 0; i < 8; i++) {
         for (int j = 0; j < 8; j++) {
            Rectangle rect1 = new Rectangle((352+(72*i)),(72+(72*j)),72, 72);
            if ((i+j) % 2 == 0){
               rect1.setFill(Color.rgb(245, 245, 220));
            } else {
               rect1.setFill(Color.rgb(128, 81, 54));
            }
            tileStore[i][j] = rect1;
            root.getChildren().add(rect1);
         }
      }
   }

   /**
    * Removes pieces from the board and draws them in their new positions.
    * @param root The pane we are drawing to.
    */

   //i hate this method because i delete all the pieces then load them again lol
   private void removePieces(Pane root){
      root.getChildren().removeAll(pieces);
      customPiecesToBoard(root, game.pieceTiles());
   }

   /**
    * Draws a specified piece on a specified square.
    * @param root The pane we are drawing to.
    * @param imageName The name of the image representing the piece we want to draw.
    * @param x The X coordinate of the piece.
    * @param y The Y coordinate of the piece.
    */
   private void drawPiece(Pane root, String imageName, int x, int y){
      Image piece = new Image("file:./resources/"+imageName+".png");
      ImageView imageView = new ImageView(piece);
      imageView.setFitWidth(72);
      imageView.setFitHeight(72);

      imageView.setX(352+(72*x));
      imageView.setY(72+(72*y));

      pieces.add(imageView);
      root.getChildren().add(imageView);
   }

   // Javadoc not finished as we may make void
   /**
    * Loads a custom piece position to the board.
    *
    * @param root       The pane we are drawing to.
    * @param tileCoords The arrayList containing the pieces.
    */
   //USE THIS METHOD TO LOAD STUFF TO THE BOARD
   //the array list provided can be any form of tiles
   //if you look at the current usage of it I'm loading in the pieces
   //from the board class
   //use this to link up the fenstring thing with the board for the loading game feature
   public void customPiecesToBoard(Pane root, ArrayList<Tile> tileCoords){
      for (Tile currentTile: tileCoords) {
         drawPiece(root, currentTile.getPiece().getPieceName(), currentTile.getX(), currentTile.getY());
      }
   }

   /**
    * Initiates and writes text to window.
    * @param root The pane we are writing to.
    * @param playerA The name of player A.
    * @param playerB The name of player B.
    * @return Root, the pane we have written to.
    */
   public Pane initText(Pane root, String playerA, String playerB){
      Text[] textList = new Text[5];
      textList[0] = new Text(20,20,"");
      if (game.isTurn() == playerOneColor){
         textList[1] = new Text(1100,20,playerA +"'s turn");
      } else {
         textList[1] = new Text(1100,20,playerB +"'s turn");
      }
      textList[2] = new Text(20,660,"Player 1 - " + playerA);
      textList[3] = new Text(20,690,"Player 2 - " + playerB);
      textList[4] = new Text(20,40,"");

      textList[1].setTextAlignment(TextAlignment.RIGHT);

      for (int i = 0; i < 4; i++) {
         textList[i].setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
         root.getChildren().add(textList[i]);
      }
      textList[4].setFont(Font.font("verdana", FontWeight.THIN, FontPosture.REGULAR, 15));
      root.getChildren().add(textList[4]);

      return root;
   }


   /**
    * Changes a square back to its original colour when no longer in check.
    *
    * @param root The pane we are drawing to.
    */
   public void removeCheckSquare(Pane root){
      tileStore[squareInCheck.getX()][squareInCheck.getY()].setFill(lastCheckSquareColour);
      lastCheckSquareColour = tileStore[0][0].getFill();
      squareInCheck.setXY(0,0);
   }

   public void checkSquare(Pane root){
      lastCheckSquareColour = tileStore[squareInCheck.getX()][squareInCheck.getY()].getFill();
      tileStore[squareInCheck.getX()][squareInCheck.getY()].setFill(Color.rgb(234, 151, 145));
      squareInCheck.setXY(squareInCheck.getX(), squareInCheck.getY());
   }

   public Pane addButtons(Pane root){
      Image nextImage = new Image("file:resources/NextButton.png");
      Image previousImage = new Image("file:resources/PreviousButton.png");
      Image exitImage = new Image("file:resources/ExitButton.png");
      ImageView view1 = new ImageView(nextImage);
      ImageView view2 = new ImageView(previousImage);
      ImageView view3 = new ImageView(exitImage);

      view1.setFitHeight(66);
      view2.setFitHeight(66);
      view3.setFitHeight(66);
      view1.setPreserveRatio(true);
      view2.setPreserveRatio(true);
      view3.setPreserveRatio(true);

      Button next = new Button("", view1);
      Button previous = new Button("", view2);
      previous.setDisable(true);
      next.setOnAction(e -> {
         count++;
         previous.setDisable(false);
                 if (count<(boardPositions.size())) {
                    game.loadGame(boardPositions.get(count));
                    removePieces(root);
                    System.out.println("Next Move");
                    if (count==boardPositions.size()){
                       next.setDisable(true);
                       previous.setDisable(false);
                    }
                    if (count%2!=0) {
                       updateTurn(root, playerTwo);
                    }
                    else {
                       updateTurn(root,playerOne);
                    }
                 }else {
                    count--;
                    next.setDisable(true);
                 }
      });
         previous.setOnAction(e -> {
            count--;
            next.setDisable(false);
                    if (count>=0) {
                       next.setDisable(false);
                       game.loadGame(boardPositions.get(count));
                       removePieces(root);
                       System.out.println("Previous Move");
                       if (count%2!=0) {
                          updateTurn(root, playerTwo);
                       }
                       else {
                          updateTurn(root,playerOne);
                       }
                       if (count==0){
                          previous.setDisable(true);
                          next.setDisable(false);
                       }
                    }else {
                       previous.setDisable(true);
                       count++;
                    }
         });

      Button exit = new Button("", view3);
      exit.setOnAction(e -> {
         ExitMenu exitMenu = new ExitMenu(parentStage, "exit");
         exitMenu.start(new Stage());
         if (exitMenu.no) {
            Main main = new Main();
            main.start(new Stage());
         }
      });

      next.setPrefSize(200, 66);
      previous.setPrefSize(200, 66);
      exit.setPrefSize(200, 66);

      next.setLayoutX(1000);
      previous.setLayoutX(1000);
      exit.setLayoutX(1000);

      next.setLayoutY(150);
      previous.setLayoutY(300);
      exit.setLayoutY(450);

      root.getChildren().add(next);
      root.getChildren().add(previous);
      root.getChildren().add(exit);

      return root;
   }


   public void updateTurn(Pane root, String text){
      currentPlayer = text;
      Text turnText = (Text) root.getChildren().get(1);
      turnText.setText(text+"'s turn");
   }


}
