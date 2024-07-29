import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * This class handles the window prompt where the user is required to load a file into the game.
 * @author Muntazir Rashid [mrm19]
 * @version 1.0 javadoc
 */
public class LoadMenu extends Application {
    private File selectedFile = null;
    private TextField fileNameTextField;
    private TextField fenStringTextField;
    private String title;
    private boolean whichMenu;
    private Stage parentStage;

    /**
     * Constructor for the object LoadMenu
     * @param parentStage the stage
     * @param title the title of the window
     * @param whichMenu boolean value representing what kind of menu is it (see code comment)
     */
    public LoadMenu(Stage parentStage, String title, boolean whichMenu) {
        this.title = title;
        this.parentStage = parentStage;
        this.whichMenu = whichMenu; // true is replay menu
    }


    /**
     * This methods loads the load menu window which allow user to interact with.
     * @param primaryStage the stage which shows the window.
     */
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
            // Delete the selected file
            if (selectedFile != null && selectedFile.exists()) {
                try {
                    Files.delete(selectedFile.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
             //Clear the file name and FEN string text fields
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
        fenStringTextField.setEditable(true);

        EventHandler<ActionEvent> fenInput = new EventHandler<ActionEvent>() {

            /**
             * Handles the loading of the file when the user selects a file to load.
             * @param f (INSERT DESCRIPTION HERE)
             */
            public void handle(ActionEvent f) {
                if (fileNameTextField.getText().contains(".txt")) {
                    FenConverter fenConv = new FenConverter();
                    if (whichMenu){
                        ReplayBoard replayBoard;
                        String p1 = "p1";
                        String p2 = "p2";
                        try {
                            p1 = fenConv.loadGameFromFile(fileNameTextField.getText()).get(0);
                            p2 = fenConv.loadGameFromFile(fileNameTextField.getText()).get(1);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        replayBoard = new ReplayBoard(primaryStage,fileNameTextField.getText(), p1, p2, true);
                        replayBoard.start(primaryStage);
                        System.out.println("Play");
                    }
                    else {
                        ChessBoard chessBoard;
                        String p1 = "p1";
                        String p2 = "p2";
                        try {
                            p1 = fenConv.loadGameFromFile(fileNameTextField.getText()).get(0);
                            p2 = fenConv.loadGameFromFile(fileNameTextField.getText()).get(1);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        chessBoard = new ChessBoard(primaryStage,fileNameTextField.getText(), p1, p2, true);
                        chessBoard.start(primaryStage);
                        System.out.println("Play");
                    }

                }
                else {
                    if (whichMenu){
                        ReplayBoard replayBoard;
                        replayBoard = new ReplayBoard(primaryStage,fenStringTextField.getText(), "p1", "p2", true);
                        replayBoard.start(primaryStage);
                        System.out.println("Play");
                    }
                    else {
                        ChessBoard chessBoard;
                        chessBoard = new ChessBoard(primaryStage,fenStringTextField.getText(), "p1", "p2", true);
                        chessBoard.start(primaryStage);
                        System.out.println("Play");
                    }

                }
                parentStage.close();
            }
        };
        loadButton.setOnAction(fenInput);

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
            fileChooser.setInitialDirectory(new File("resources/saveGames")); // set the initial directory
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("TXT Files", "*.txt"),
                    new FileChooser.ExtensionFilter("All Files", "*.*"));
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                selectedFile = file;
                fileNameTextField.setText(file.getAbsolutePath());
            }
        });
        topLayout.getChildren().add(fileChooserButton);

        root.setTop(topLayout);


        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle(title);
        primaryStage.getIcons().add(new Image("file:resources/icon.png"));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

}
