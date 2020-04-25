import java.util.Random;

public class JellyBeanJar extends GameTemplate {
    private Integer totalBeans, min, max;
    private Random rand;

    // init:
    // Initialize the game variables
    @Override
    public void init() {
        rand = new Random();
        if(totalBeans == null) {
            totalBeans = rand.nextInt(1001);
        }
        if(min == null) {
            min = 0;
        }
        if(max == null) {
            max = 1000;
        }
    } // End of init

    // computerMove:
    // Method overridden to set the computer's guess to a random number:
    @Override
    public void computerMove() {
        // Guess a number within range for the computer
        setComputerPlays(rand.nextInt(max-min+1) + min);
    } // End of computerMove

    // evalGame:
    // Uses the player's guess and the computer's guess to evaluate the game and return the winner
    // If there is no winner, update the min and max values
    @Override
    public int evalGame() {
        int computer = getComputerPlays();
        int player = getPlayerPlays();
        if(computer < totalBeans) { // Update min value
            if(computer >= min) {
                min = computer+1;
            }
        } else if(computer > totalBeans) { // Update max value
            if(computer <= max) {
                max = computer-1;
            }
        }
        if(player < totalBeans) { // Update min value
            if(player >= min) {
                min = player+1;
            }
        } else if(computer > totalBeans) { // Update max value
            if(player <= max) {
                max = player-1;
            }
        }
        return totalBeans; // Return the total number. Game will be evaluated in controller
    } // End of evalGame

    // Resets all of the initialized variables
    @Override
    public void resetGame() {
        rand = null;
        totalBeans = min = max = null;
    } // End of resetGame
}
