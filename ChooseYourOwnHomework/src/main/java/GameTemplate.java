public abstract class GameTemplate {
    private int playerPlays, computerPlays;

    public abstract void init(); // Initialize the game
    public abstract void computerMove(); // Get computer's move
    public abstract int evalGame(); // Evaluate the game
    public abstract void resetGame(); // Reset the game

    // play:
    // Method called by Template
    // Creates the scene and plays the game
    // This incorporates the template design pattern by calling the abstract methods computerMove
    // and evalGame directly from the method play.
    public final int play(int p) {
        // Implement code to play the game

        // Initialize the game variables
        init();

        // Set what player plays
        // This method is not abstract
        setPlayerPlays(p);

        // Computer makes move (player should have already played)
        computerMove();

        // Evaluate the game
        return evalGame();
    } // End of play

    // Return what the player plays
    public int getPlayerPlays() {
        return playerPlays;
    } // End of getPlayerPlays

    // Set what the player plays
    public void setPlayerPlays(int p) {
        playerPlays = p;
    } // End of setPlayerPlays

    // Return what the computer plays
    public int getComputerPlays() {
        return computerPlays;
    } // End of getComputerPlays

    // Set what the computer plays
    public void setComputerPlays(int c) {
        computerPlays = c;
    } // End of setComputerPlays
}

