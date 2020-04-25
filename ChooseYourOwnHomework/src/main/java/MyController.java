import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MyController implements Initializable {

    @FXML
    private HBox root;

    @FXML
    private VBox gameroot;

    @FXML
    private VBox gameOne, gameTwo;

    @FXML
    private MenuBar menubar;

    @FXML
    private Label gameLabel, playerLabel, computerLabel, pScoreLabel, cScoreLabel, minMaxLabel;

    @FXML
    private ImageView jbjIV, rpsIV;

    @FXML
    private Spinner<Integer> spinnerP, spinnerC;

    @FXML
    private SpinnerValueFactory<Integer> spinPVal, spinCVal;

    private static GameTemplate game;
    private static String gameString, playerPlays, computerPlays, minMaxString;
    private static int pScore, cScore, min, max;
    private static MyController myctr;

    // initialize:
    // Initialize important variables
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gameString = playerPlays = computerPlays = "";
        pScore = cScore = 0;
    } // End of initialize

    // setLabels:
    // Set the labels
    public void setLabels() {
        gameLabel.setText(gameString);
        playerLabel.setText(playerPlays);
        computerLabel.setText(computerPlays);
        pScoreLabel.setText("Player: " + pScore);
        cScoreLabel.setText("Computer: " + cScore);
    } // setLabels

    // setMinMaxLabel
    // Set label for min and max values
    public void setMinMaxLabel() {
        minMaxLabel.setText(min + " ≤ val ≤ " + max);
    } // End of setMinMaxLabel

    // newGame:
    // Helper function to create new game
    public void newGame() {
        gameString = playerPlays = computerPlays = "";
        pScore = cScore = 0;
        setLabels();
    } // End of newGame

    // newGameJBJ:
    // Function called to reset all game components for JBJ
    public void newGameJBJ() {
        newGame();
        min = 0; max = 1000;
        myctr.setMinMaxLabel();
        myctr.setLabels();
    }

    // newGameRPS:
    // Function called to reset all game components for RPS
    public void newGameRPS() {
        newGame();
        myctr.setLabels();
    } // End of newgame

    // setJBJScene:
    // Set the scene for jelly bean jar
    public void setJBJScene(ActionEvent e) throws IOException {
        //System.out.println("Creating the scene for tic tac toe");

        game = new JellyBeanJar(); // Create instance of JellyBeanJar
        //System.out.println("Class: " + game.getClass().toString());

        // Get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/JellyBeanJarFXML.fxml"));
        Parent gameroot = loader.load(); // Load view into parent
        myctr = loader.getController();
        gameroot.getStylesheets().add("Styles/jelly-bean-jar.css"); // Set style

        pScore = cScore = min = 0;
        max = 1000;

        myctr.setMinMaxLabel();

        root.getScene().setRoot(gameroot);
    } // End of setTTTScene

    // setRPSScene:
    // Set the scene for rock paper scissors
    public void setRPSScene(ActionEvent e) throws IOException {
        //System.out.println("Creating the scene for rock paper scissors");

        game = new RockPaperScissors(); // Create instance of RockPaperScissors
        //System.out.println("Class: " + game.getClass().toString());

        // Get instance of the loader class
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/RockPaperScissorsFXML.fxml"));
        Parent gameroot = loader.load(); // Load view into parent
        myctr = loader.getController();
        gameroot.getStylesheets().add("/Styles/rock-paper-scissors.css"); // Set style

        pScore = cScore = 0; // Reset components

        root.getScene().setRoot(gameroot); // Update scene graph
    } // End of setRPSScene

    // addRPSPlayed:
    // Return string value of what was played
    public String addRPSPlayed(int p) {
        if(p == 1) {
            return "rock";
        } else if(p == 2) {
            return "paper";
        } else {
            return "scissors";
        }
    } // End of addRPSPlayed

    // getWinnerRPS:
    // Uses switch case to determine the winner and update corresponding information
    //    - 0 = Draw
    //    - 1 = Player won
    //    - 2 = Computer won
    public void getWinnerRPS(int w) {
        switch(w) {
            case -1: // Error testing
                System.out.println("Error: Game not evaluated properly");
                break;
            case 0: // Implement code for a draw
                //System.out.println("It's a draw!");
                gameString = "Both drew with ";
                gameString += addRPSPlayed(game.getPlayerPlays());
                break;
            case 1: // Implement code for player won
                //System.out.println("Player Won!");
                gameString = "Player Won with ";
                pScore++;
                gameString += addRPSPlayed(game.getPlayerPlays());
                break;
            case 2: // Implement code for computer won
                //System.out.println("Computer Won!");
                gameString = "Computer Won with ";
                cScore++;
                gameString += addRPSPlayed(game.getComputerPlays());
                break;
        }
        game.resetGame();
    } // End of getWinnerRPS

    // resetJBJ
    // Helper function to reset JBJ components
    public void resetJBJ() {
        min = 0;
        max = 1000;
        spinCVal.setValue(0);
        spinPVal.setValue(0);
    } // End of JBJ

    // getWinnerJBJ:
    // Uses total beans returned from play to calculate winner
    public void getWinnerJBJ(int t, int p, int c) {
        //System.out.println("T: " + t + "\nP: " + p + "\nC: " + c);
        if(p < t) {
            if(p >= min) {
                min = p+1;
            }
        } else if(p > t) {
            if(p <= max) {
                max = p-1;
            }
        }
        if(c < t) {
            if(c >= min) {
                min = c+1;
            }
        } else if(c > t) {
            if(c <= max) {
                max = c-1;
            }
        }
        if(p == t) {
            if(c == t) {
                gameString = "Both guessed correctly!";
            } else {
                gameString = "Player guessed correctly!";
                pScore++;
            }
            resetJBJ();
            game.resetGame();
        } else if(c == t) {
            gameString = "Computer guessed correctly!";
            cScore++;
            resetJBJ();
            game.resetGame();
        } else {
            gameString = "No one guessed correctly!";
        }
    }

    // playRock:
    // Event handler to handle button playing rock
    public void playRock(ActionEvent e) throws IOException {
        try {
            //System.out.println("Class play: " + game.getClass().toString());
            getWinnerRPS(game.play(1));
            playerPlays = "Player plays rock";
            computerPlays = "Computer plays " + addRPSPlayed(game.getComputerPlays());
            myctr.setLabels();
        } catch(Exception n) {
            System.out.println("Game \"RockPaperScissors\" not initialized!");
        }
    } // End of playRock

    // playPaper:
    // Event handler to handle button playing paper
    public void playPaper(ActionEvent e) throws IOException {
        try {
            //System.out.println("Class play: " + game.getClass().toString());
            getWinnerRPS(game.play(2));
            playerPlays = "Player plays paper";
            computerPlays = "Computer plays " + addRPSPlayed(game.getComputerPlays());
            myctr.setLabels();
        } catch(Exception n) {
            System.out.println("Game \"RockPaperScissors\" not initialized!");
        }
    } // End of playPaper

    // playScissor:
    // Event handler to handle button playing scissors
    public void playScissor(ActionEvent e) throws IOException {
        try {
            //System.out.println("Class play: " + game.getClass().toString());
            getWinnerRPS(game.play(3));
            playerPlays = "Player plays scissors";
            computerPlays = "Computer plays " + addRPSPlayed(game.getComputerPlays());
            myctr.setLabels();
        } catch(Exception n) {
            System.out.println("Game \"RockPaperScissors\" not initialized!");
        }
    } // End of playScissor

    // playGuess:
    // Event handler to hand button to submit guess
    public void playGuess(ActionEvent e) throws IOException {
        try {
            // Player and computer guesses
            int p = spinPVal.getValue();
            int total = game.play(p);
            int c = game.getComputerPlays();
            spinCVal.setValue(c);
            playerPlays = "Player guesses " + p + ". ";
            computerPlays = "Computer guesses " + c + ". ";
            getWinnerJBJ(total, p, c);
            myctr.setLabels();
            myctr.setMinMaxLabel();
        } catch(Exception n) {
            System.out.println("Game \"JellyBeanJar\" not initialized!");
        }
    } // End of playGuess

    // mainMenu:
    // Set scene back to main menu
    public void mainMenu(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SelectGameFXML.fxml"));
        Parent root = loader.load();
        gameroot.getScene().setRoot(root);
    } // End of mainMenu
}