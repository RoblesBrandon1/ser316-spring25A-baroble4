
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class GuessTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game("lion", "Dr. M"); // Test Value
    }

    @Test
    void testCorrectWordGuess() {
        double response = game.makeGuess("lion");
        assertEquals(0.0, response, "Should return 0.0 when the full word is guessed correctly");
        assertEquals(14, game.getPoints(), "Points should increase based on word length");
        assertEquals(1, game.getGameStatus(), "Game should be marked as won");
    }

    @Test
    void testIncorrectWordSameLength() {
        double response = game.makeGuess("fast");
        assertEquals(2.0, response, "Should return 2.0 for incorrect guess of correct length");
        assertEquals(11, game.getPoints(), "Points should increase by 1");
        assertEquals(0, game.getGameStatus(), "Game should still be in progress");
    }

    @Test
    void testIncorrectWordTooLong() {
        double response = game.makeGuess("lumberjacks");
        assertEquals(2.1, response, "Should return 2.1 for word that is too long");
        assertTrue(game.getPoints() < 10, "Points should decrease based on length difference");
    }

    @Test
    void testIncorrectWordTooShort() {
        double response = game.makeGuess("hi");
        assertEquals(2.2, response, "Should return 2.2 for word that is too short");
        assertTrue(game.getPoints() < 11, "Points should decrease based on length difference");
    }

    @Test
    void testCorrectLetterGuess() {
        double response = game.makeGuess("o");
        assertEquals(1.1, response, "Should return 1.x if letter is in the word");
        assertTrue(game.getPoints() > 10, "Points should increase based on occurrences");
    }

    @Test
    void testIncorrectLetterGuess() {
        double response = game.makeGuess("z");
        assertEquals(1.0, response, "Should return 1.0 for incorrect letter");
        assertEquals(10, game.getPoints(), "Points should not change");
    }

    @Test
    void testPartialWordMatch() {
        double response = game.makeGuess("lio");
        assertEquals(3.0, response, "Should return 3.0 for partial word match");
        assertEquals(12, game.getPoints(), "Points should increase by 2");
    }

    @Test
    void testRepeatedGuess() {
        game.makeGuess("fans");
        double response = game.makeGuess("fans");

        assertEquals(4.0, response, "Should return 4.0 for repeated guess");
        assertEquals(9, game.getPoints(), "Points should decrease by 2");
    }

    @Test
    void testInvalidGuessWithNumbers() {
        double response = game.makeGuess("@123");
        assertEquals(4.1, response, "Should return 4.1 for symbols/numbers in guess");
        assertEquals(7, game.getPoints(), "Points should decrease by 3");
    }

    @Test
    void testGameOverAfter10Guesses() {
        for (int i = 0; i < 9; i++) {
            game.makeGuess("wrong" + i);
        }
        double response = game.makeGuess("extra");
        assertEquals(5.0, response, "10th incorrect guess should return 5.0");
        assertEquals(2, game.getGameStatus(), "Game should be marked as over");
    }

    @Test
    void testGuessingAfterGameOver() {
        for (int i = 0; i < 10; i++) {
            game.makeGuess("wrong" + i);
        }
        double response = game.makeGuess("extra");
        assertEquals(5.1, response, "Guessing after game over should return 5.1");
        assertEquals(2, game.getGameStatus(), "Game should be marked as over");
    }

    @Test
    void testCaseSensitivity() {
        double resultUpper = game.makeGuess("LION");
        assertEquals(0.0, resultUpper, "Uppercase should be treated as lowercase");
    }

}