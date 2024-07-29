/**
 * The chessBoard class, handles interaction between UI and the backend.
 * @author Muntazir Rashid [mrm19], Kacper Dziedzic [ktd1], Archie Malvern [arm36]
 * @version 1.4
 * @version 1.5 updated javadoc
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

public class ChessBoard extends Application {
    private final String playerOne;
    private final String playerTwo;
    private final boolean playerOneColor;
    private final Stage parentStage;
    private String fenSave;
    String currentPlayer;

    /**
     * This is a constructor which initiates a chess board with the following information:
     * @param parentStage   The main stage.
     * @param nameOne       The name of player 1
     * @param nameTwo       The name of player 2
     * @param turn          The boolean value representing which player's turns to play.
     */
    public ChessBoard(Stage parentStage, String nameOne, String nameTwo, boolean turn) {
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

    public ChessBoard(Stage parentStage, String fenSave, String nameOne, String nameTwo, boolean turn) {
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

    private Paint lastSquareColor;
    private Tile lastSquareCoords;
    private Paint lastCheckSquareColor;
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
        heldPiece = new Tile(7,7, new EmptyPiece());
        lastSquareColor = Color.rgb(245, 245, 220);
        lastSquareCoords = new Tile(0,0);
        lastCheckSquareColor = Color.rgb(245, 245, 220);
        squareInCheck = new Tile(0,0);

        if (fenSave != null) {
            game = new Game(fenSave, playerOne, playerTwo);
        }
        else{
            game = new Game(playerOne, playerTwo);
        }
        tileStore = new Rectangle[8][8];
        dotsList = new ArrayList<>();

        Pane root = new Pane();
        Scene scene = new Scene(root, 1280, 720);

        initText(root, playerOne, playerTwo);
        root = addButtons(root);
        game.initiateGame();
        createBoard(root);
        customPiecesToBoard(root, game.pieceTiles());

        // here goes any methods for quick testing
        //

        stage.setTitle("Chess");
        stage.getIcons().add(new Image("file:resources/icon.png"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
    }

    /**
     * Creates and draws the board. Adding click events listeners to every tile generated
     * @param root The pane we are drawing to.
     */
    public void createBoard(Pane root){
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Rectangle rect1 = new Rectangle((352+(72*i)),(72+(72*j)),72, 72);
                rect1.setOnMouseClicked(mouseEvent -> {
                    selectedX = convertCoordsX((int) rect1.getX());
                    selectedY = convertCoordsY((int) rect1.getY());

                    updatePiece(root, selectedX, selectedY);
                    selectedPiece(root, selectedX, selectedY);
                });
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

    private void removePieces(Pane root){
        root.getChildren().removeAll(pieces);
        customPiecesToBoard(root, game.pieceTiles());
    }

    /**
     * Draws a specified piece on a specified square. adding mouse click event listeners to every piece drawn.
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

        imageView.setOnMouseClicked(mouseEvent -> {
            selectedX = convertCoordsX((int) imageView.getX());
            selectedY = convertCoordsY((int) imageView.getY());
            updatePiece(root, selectedX, selectedY);
            selectedPiece(root, selectedX, selectedY);
        });

        pieces.add(imageView);
        root.getChildren().add(imageView);
    }

    /**
     * Loads a custom piece position to the board.
     * @param root       The pane we are drawing to.
     * @param tileCoords The arrayList containing the pieces.
     */
    public void customPiecesToBoard(Pane root, ArrayList<Tile> tileCoords){
        for (Tile currentTile: tileCoords) {
            drawPiece(root, currentTile.getPiece().getPieceName(), currentTile.getX(), currentTile.getY());
        }
    }

    /**
     * Initiates and writes text to window.
     * @param root    The pane we are writing to.
     * @param playerA The name of player A.
     * @param playerB The name of player B.
     */
    public void initText(Pane root, String playerA, String playerB){
        Text[] textList = new Text[5];
        textList[0] = new Text(20,20,"Selected Piece");
        textList[1] = new Text(1180,20,"'s turn");
        textList[2] = new Text(20,670,"Player 1 - " + playerA);
        textList[3] = new Text(20,700,"Player 2 - " + playerB);
        textList[4] = new Text(20,40,"");

        textList[1].setTextAlignment(TextAlignment.RIGHT);

        for (int i = 0; i < 4; i++) {
            textList[i].setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            root.getChildren().add(textList[i]);
        }
        if (game.isTurn() == playerOneColor){
            updateTurn(root, playerOne);
        } else {
            updateTurn(root, playerTwo);
        }
        textList[4].setFont(Font.font("verdana", FontWeight.THIN, FontPosture.REGULAR, 15));
        root.getChildren().add(textList[4]);

    }

    /**
     * Adds a dot to a specified location.
     *
     * @param root The pane we are drawing to.
     * @param x    The X coordinate of the square we are drawing on.
     * @param y    The Y coordinate of the square we are drawing on.
     */
    // 00 - 77 coords
    public void addDot(Pane root, int x, int y){

        Circle circle1 = new Circle((352+(72*x)+36),(72+(72*y)+36),7.5);
        circle1.setFill(Color.rgb(128, 128, 128));
        dotsList.add(circle1);
        circle1.setOnMouseClicked(mouseEvent -> {
            selectedX = convertCoordsX((int) circle1.getCenterX());
            selectedY = convertCoordsY((int) circle1.getCenterY());

            updatePiece(root, selectedX, selectedY);
            selectedPiece(root, selectedX, selectedY);
        });
        root.getChildren().add(circle1);
    }

    /**
     * Converts a piece X coordinate into an X coordinate for drawing to a pane.
     * @param x The X coordinate to be converted.
     * @return The converted X coordinate.
     */
    public int convertCoordsX(int x){
        x = (x-352)/72;
        return x;
    }

    /**
     * Converts a piece Y coordinate into an Y coordinate for drawing to a pane.
     * @param y The Y coordinate to be converted.
     * @return The converted Y coordinate.
     */
    public int convertCoordsY(int y){
        y = (y-72)/72;
        return y;
    }

    /**
     * Changes a square back to its original color when no longer in check.
     */
    public void removeCheckSquare(){
        tileStore[squareInCheck.getX()][squareInCheck.getY()].setFill(lastCheckSquareColor);
        lastCheckSquareColor = tileStore[0][0].getFill();
        squareInCheck.setXY(0,0);
    }

    /**
     * Removes the selected square from the
     * @param x coordinate on x-axis
     * @param y coordinate on y-axis
     */
    public void removeSelectedSquare(int x, int y){
        tileStore[lastSquareCoords.getX()][lastSquareCoords.getY()].setFill(lastSquareColor);
        lastSquareColor = tileStore[x][y].getFill();
    }

    /**
     * Removes all the dots from the screen using a variable
     * @param root the pane storing all GUI details
     */
    public void removeDots(Pane root){
        for (Circle current: dotsList) {
            root.getChildren().remove(current);
        }
        dotsList.clear();
    }

    /**
     * a large method which executes whenever any tile, piece, image or dot is selected.
     * Based on certain inputs a move will be executed along with a number of other operations including:
     * updating the selected square, updating the string details on the screen, changing the turn when a valid
     * move is made, redrawing the pieces when turn is changed, opening a prompt when en passant is detected
     * @param root the main Pane which holds the GUI components
     * @param x the X coordinate of the selected area
     * @param y the Y coordinate of the selected area
     */
    public void selectedPiece(Pane root, int x, int y){

        int lastX = lastSquareCoords.getX();
        int lastY = lastSquareCoords.getY();

        tileStore[lastSquareCoords.getX()][lastSquareCoords.getY()].setFill(lastSquareColor);
        lastSquareColor = tileStore[x][y].getFill();
        lastSquareCoords.setX(x);
        lastSquareCoords.setY(y);
        tileStore[x][y].setFill(Color.rgb(186, 163, 137));

        int tileX = game.getBoard().getActualTile(x,y).getX();
        int tileY = game.getBoard().getActualTile(x,y).getY();
        int validMove = game.checkMove(lastX, lastY, tileX,tileY);
        Piece selectedSquare = game.getBoard().getActualTile(tileX,tileY).getPiece();
        String heldPieceName = heldPiece.getPiece().getPieceName();

        if (heldPieceName.equals("") && selectedSquare.getColor() == game.isTurn()){
            //if not holding a piece and selected piece color is the same as the turn
            heldPiece.setPiece(selectedSquare);
            removeDots(root);
            drawAllDots(root, tileX, tileY );

        } else if (!heldPieceName.equals("") && selectedSquare.getPieceName().equals("") && validMove == 1 && heldPiece.getPiece().getColor() == game.isTurn()){
            // holding a piece and select a valid tile move
            turnUpdates(root, lastX, lastY, tileX, tileY, x, y);

        } else if (!heldPieceName.equals("") && selectedSquare.getColor() != game.isTurn() && validMove == 1 && heldPiece.getPiece().getColor() == game.isTurn()){
            // capturing a piece
            turnUpdates(root, lastX, lastY, tileX, tileY, x, y);

        } else if (!heldPieceName.equals("") && selectedSquare.getColor() == game.isTurn()) {
            //if holding a piece already but select a piece of your color

            //check if the held piece is a king and selects a rook with a valid move
            if (heldPiece.getPiece().checkWhichPiece(heldPiece) == 2 && selectedSquare.checkWhichPiece(game.getBoard().getActualTile(tileX,tileY)) == 4 && validMove == 1){
                turnUpdates(root, lastX, lastY, tileX, tileY, x, y);
            } else {
                removeDots(root);
                heldPiece.setPiece(selectedSquare);
                drawAllDots(root, tileX, tileY);
            }

        } else {
            heldPiece.setPiece(selectedSquare);
            removeDots(root);
            removeSelectedSquare(x, y);
        }
        checkAndCheckmate();
    }

    /**
     * Executes the following code when turn is ended
     * @param root the pane which holds the GUI
     * @param lastX the X coordinates of the last selected square
     * @param lastY the Y coordinates of the last selected square
     * @param tileX the X coordinate of the tile chosen
     * @param tileY the Y coordinate of the tile chosen
     * @param mouseX the X coordinate the mouse clicked
     * @param mouseY the Y coordinate the mouse clicked
     */
    public void turnUpdates(Pane root, int lastX, int lastY, int tileX, int tileY, int mouseX, int mouseY){
        removeDots(root);
        game.getBoard().movePiece(lastX, lastY, tileX,tileY);
        int pieceType = game.getBoard().getActualTile(tileX, tileY).getPiece().checkWhichPiece(game.getBoard().getActualTile(tileX, tileY));
        // checks if a pawn needs to promote
        if (pieceType == 1 && tileY == 0){
            PromotionPopup promotionPopup = new PromotionPopup(parentStage, game.isTurn());
            promotionPopup.start(new Stage());
            switch (promotionPopup.pieceNo) {
                case 1 -> game.getBoard().getActualTile(tileX, tileY).setPiece(new Rook(game.isTurn()));
                case 2 -> game.getBoard().getActualTile(tileX, tileY).setPiece(new Bishop(game.isTurn()));
                case 3 -> game.getBoard().getActualTile(tileX, tileY).setPiece(new Knight(game.isTurn()));
                default -> game.getBoard().getActualTile(tileX, tileY).setPiece(new Queen(game.isTurn()));
            }
        }
        game.turnExecution();
        removeSelectedSquare(mouseX, mouseY);
        removePieces(root);
        heldPiece.setPiece(new EmptyPiece());
        if (game.isTurn() == playerOneColor){
            updateTurn(root, playerOne);
        } else {
            updateTurn(root, playerTwo);
        }
        Text pieceText = (Text) root.getChildren().get(4);
        pieceText.setText(" ");
    }

    /**
     * method which executes the according procedure if there is a check present
     * or a checkmate, ending the game.
     */
    public void checkAndCheckmate(){
        Tile checkSquare = game.checkCheck();
        removeCheckSquare();

        if (checkSquare != null){
            if (checkSquare.getMoveType() == 100){
                squareInCheck.setXY(checkSquare.getX(), checkSquare.getY());
                checkSquare();
                System.out.println("Check!");

            } else if (checkSquare.getMoveType() == 999){
                squareInCheck.setXY(checkSquare.getX(), checkSquare.getY());
                checkSquare();
                System.out.println("Checkmate!");
                String popUpMessage;
                if (currentPlayer.equals(playerOne)){
                    popUpMessage = playerTwo + " Won!";
                } else {
                    popUpMessage = playerOne + " Won!";
                }

                WinPopUp popup = new WinPopUp(parentStage, popUpMessage);
                popup.start(new Stage());
            }
        }
    }

    /**
     * This method will use the provided x and y coordinates to get the Tiles associated
     * moves and output them onto the screen in the form of dots.
     * @param root the Pane which holds the GUI to draw to
     * @param x X coordinate of a tile
     * @param y Y coordinate of a tile
     */
    public void drawAllDots(Pane root, int x, int y){
        for (Tile currentMove: game.getBoard().getActualTile(x, y).getPiece().getAllMoves()) {
            addDot(root,currentMove.getX(), currentMove.getY());
        }
    }

    /**
     * draws a checkSquare on the provided coordinates
     */
    public void checkSquare(){
        lastCheckSquareColor = tileStore[squareInCheck.getX()][squareInCheck.getY()].getFill();
        tileStore[squareInCheck.getX()][squareInCheck.getY()].setFill(Color.rgb(234, 151, 145));
        squareInCheck.setXY(squareInCheck.getX(), squareInCheck.getY());
    }

    /**
     * draws the 3 buttons onto the GUI, the draw button, resign button and exit button which
     * each have different interactions
     * @param root the Pane which holds the GUI
     * @return the original pane with the buttons added
     */
    public Pane addButtons(Pane root){
        Image drawImage = new Image("file:resources/DrawButton.png");
        Image resignImage = new Image("file:resources/Resign.png");
        Image exitImage = new Image("file:resources/ExitButton.png");
        ImageView view1 = new ImageView(drawImage);
        ImageView view2 = new ImageView(resignImage);
        ImageView view3 = new ImageView(exitImage);

        view1.setFitHeight(66);
        view2.setFitHeight(66);
        view3.setFitHeight(66);
        view1.setPreserveRatio(true);
        view2.setPreserveRatio(true);
        view3.setPreserveRatio(true);

        Button draw = new Button("", view1);
        draw.setOnAction(e -> {
            WinPopUp popup = new WinPopUp(parentStage, "Do you want to Offer a Draw", true, false);
            popup.start(new Stage());

        });
        Button resign = new Button("", view2);
        resign.setOnAction(e -> {
            String popUpMessage;
            if(currentPlayer == playerOne){
                popUpMessage = playerTwo + " Won!";
            }else {
                popUpMessage = playerOne + " Won!";
            }
            ExitMenu exitMenu = new ExitMenu(parentStage, "resign");
            exitMenu.start(new Stage());
            if (exitMenu.no) {
                WinPopUp popup = new WinPopUp(parentStage, popUpMessage);
                popup.start(new Stage());
                System.out.println(currentPlayer+" lost");
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

        draw.setPrefSize(200, 66);
        resign.setPrefSize(200, 66);
        exit.setPrefSize(200, 66);

        draw.setLayoutX(1000);
        resign.setLayoutX(1000);
        exit.setLayoutX(1000);

        draw.setLayoutY(150);
        resign.setLayoutY(300);
        exit.setLayoutY(450);

        root.getChildren().add(draw);
        root.getChildren().add(resign);
        root.getChildren().add(exit);

        return root;
    }

    /**
     * Will get the contents of the tile at the corresponding coordinates
     * and display both the piece on the tile and the coordinates on the screen.
     * @param root the Pane we are drawing to
     * @param x X coordinate of the selected tile
     * @param y Y coordinate of the selected tile
     */
    public void updatePiece(Pane root, int x, int y){
        String pieceName = "";
        if (game.getBoard().getActualTile(x,y).getPiece().getPieceName() != null){
            pieceName = game.getBoard().getActualTile(x,y).getPiece().getPieceName();
        }
        //uses ascii value calculations to determine the character to be displayed
        char boardChar;
        if (!game.isTurn()){
            boardChar = (char)(104-x);
            y+=1;
        } else {
            boardChar = (char)(97+x);
            y = Math.abs(y-8);
        }
        Text pieceText = (Text) root.getChildren().get(4);
        pieceText.setText(pieceName + " "+ boardChar + "" + y);
    }

    /**
     * Updates the player's turn text to the appropriate player
     * @param root the pane which holds the GUI we are drawing to
     * @param text the name of the player
     */
    public void updateTurn(Pane root, String text){
        currentPlayer = text;
        Text turnText = (Text) root.getChildren().get(1);

        turnText.setX(1180-(text.length()*15));
        turnText.setText(text+"'s turn");
    }
}
