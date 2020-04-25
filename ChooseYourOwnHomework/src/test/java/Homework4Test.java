import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Homework4Test {
    private GameTemplate game;

    // gameInitRPS:
    // Initialize a game for rock paper scissors
    @Test
    void gameRPSInit() {
        game = new RockPaperScissors();
        assertEquals("RockPaperScissors", game.getClass().getName(), "Error: Incorrect game class");
    } // End of gameInitRPS

    // setPlayerRPS:
    // Set what the player plays in rock paper scissors
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void setPlayerRPS(int play) {
        game = new RockPaperScissors();
        game.setPlayerPlays(play);
        assertEquals(play, game.getPlayerPlays(), "Error: Player played unexpected value");
    } // End of setPlayerRPS

    // setComputerRPS:
    // Set what the computer plays in rock paper scissors
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void setComputerRPS(int play) {
        game = new RockPaperScissors();
        game.setComputerPlays(play);
        assertEquals(play, game.getComputerPlays(), "Error: Player played unexpected value");
    } // End of setComputerRPS

    // playRPS:
    // Assert that calling play from game returns a valid number between 0-2
    // Play should return the winner
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void playRPS(int play) {
        game = new RockPaperScissors();
        int result = game.play(play);
        assertTrue(result >= 0 && result <= 2, "Error: Play returned unexpected value");
    } // End of playRPS:

    // evalGameRPS:
    // Create instance of RockPaperScissors and check evalGame method
    // Should return the following:
    //    - 0 = draw
    //    - 1 = player wins
    //    - 2 = computer wins
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void evalGameRPS(int playerPlays) {
        RockPaperScissors tmpGame = new RockPaperScissors();
        int computerPlays = 1;
        tmpGame.setComputerPlays(computerPlays);
        tmpGame.setPlayerPlays(playerPlays);
        assertEquals((playerPlays-computerPlays), tmpGame.evalGame(), "Error: evalGame return unexpected value");
    } // End of evalGameRPS

    // gameInitJBJ:
    // Initialize a game for jelly bean jar
    @Test
    void gameInitJBJ() {
        game = new JellyBeanJar();
        assertEquals("JellyBeanJar", game.getClass().getName(), "Error: Incorrect game class");
    } // End of gameInitJBJ

    // setPlayerJBJ:
    // Set what the player plays in jelly bean jar
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 534, 245, 11, 992})
    void setPlayerJBJ(int play) {
        game = new JellyBeanJar();
        game.setPlayerPlays(play);
        assertEquals(play, game.getPlayerPlays(), "Error: Player played unexpected value");
    } // End of setPlayerJBJ

    // setComputerJBJ:
    // Set what the computer plays in jelly bean jar
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 534, 245, 11, 992})
    void setComputerJBJ(int play) {
        game = new JellyBeanJar();
        game.setComputerPlays(play);
        assertEquals(play, game.getComputerPlays(), "Error: Player played unexpected value");
    } // End of setComputerJBJ

    // playJBJ:
    // Assert that calling play from game returns same number consistently
    // Play should return the total number of beans
    // The second call asserts that the total number of beans does not change
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 534, 245, 11, 992})
    void playJBJ(int play) {
        game = new JellyBeanJar();
        int totalBeans = game.play(play);
        assertEquals(totalBeans, game.play(play), "Error: Play returned unexpected value");
        assertEquals(totalBeans, game.play(play), "Error: Play returned unexpected value");
    } // End of playJBJ

    // playRangeJBJ:
    // Assert that calling play from game returns a valid number between 0-1000
    @ParameterizedTest
    @ValueSource(ints = {0, 1000, 534, 245, 11, 992})
    void playRangeJBJ(int play) {
        game = new JellyBeanJar();
        int totalBeans = game.play(play);
        assertTrue(totalBeans >= 0 && totalBeans <= 1000, "Error: Play returned unexpected value");
    } // End of playRangeJBJ

    // evalGameJBJ:
    // Create instance of JellyBeanJar and check evalGame method
    // Should return the total number of beans
    @Test
    void evalGameJBJ() {
        JellyBeanJar tmpGame = new JellyBeanJar();
        int totalBeans = tmpGame.play(10);
        tmpGame.setPlayerPlays(50);
        tmpGame.setComputerPlays(900);
        assertEquals(totalBeans, tmpGame.evalGame(), "Error: evalGame returned unexpected value");
    } // End of evalGameJBJ
}
