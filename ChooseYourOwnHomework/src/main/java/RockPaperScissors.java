import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.Random;

public class RockPaperScissors extends GameTemplate {
    private Random rand;

    // init:
    // Initialize the game variables
    @Override
    public void init() {
        rand = new Random(); // Initialize the random variable
    } // End of init

    // computerMove:
    // Method overridden to set the computer's guess to a random number:
    //    - 1 = rock
    //    - 2 = paper
    //    - 3 = scissor
    @Override
    public void computerMove() {
        setComputerPlays(rand.nextInt(3)+1);
    } // End of computerMove

    // evalGame:
    // uses the player's guess and the computer's guess to evaluate the game and return the winner
    //    - 0 = draw
    //    - 1 = player won
    //    - 2 = computer won
    @Override
    public int evalGame() {
        int player = getPlayerPlays();
        int computer = getComputerPlays();
        int winner = -1; // -1 for error testing
        if(player == computer) { // Both players selected the same object
            winner = 0;
        } else if(player == 3) { // Player selected scissors
            if(computer == 1) {
                winner = 2;
            } else {
                winner = 1;
            }
        } else if(computer == 3) { // Computer selected scissors
            if(player == 1) {
                winner = 1;
            } else {
                winner = 2;
            }
        } else {
            if(player > computer) {
                winner = 1;
            } else {
                winner = 2;
            }
        }
        return winner; // Return the winner of the game
    } // End of evalGame

    // resetGame:
    // Resets all of the initialized variables
    @Override
    public void resetGame() {
        rand = null;
    } // End of resetGame
}

