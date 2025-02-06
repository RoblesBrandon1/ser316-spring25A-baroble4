import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class MakeGuessTest {
    
    private Game game = new Game();

    /**
     * Tests the input of the Correct word chosen as first guess.
     */
    @Test
    public void correctGuess() {
        game.initGame("horse", "Zach");
        double result = game.makeGuess("horse");
        assertEquals(0, result, 0.0001);
    }

    /**
     * Tests the input if no guess is given
     */
    @Test
    void testCountCorrectLetters_EmptyGuesses() {
        Game game = new Game("lion", "Dr. M");
        assertEquals(0, game.countCorrectLetters(), "Should return 0 if guesses list is empty.");
    }
    /**
     * Tests the input if each correct letter is guessed individually
     */
    @Test
    void testCountCorrectLetters_AllCorrect() {
        Game game = new Game("lion", "Dr. M");
        game.makeGuess("l");
        game.makeGuess("i");
        game.makeGuess("o");
        game.makeGuess("n");
        assertEquals(4, game.countCorrectLetters(), "Should return 4 if guesses list is empty.");
    }
    /**
     * Tests the input of letters if only a few correct letters are guessed
     */
    @Test
    void testCountCorrectLetters_SomeCorrect(){
        Game game = new Game("lion", "Dr. M");
        game.makeGuess("l");
        game.makeGuess("i");
        assertEquals(2,game.countCorrectLetters(), "Should return 2 if guesses list is empty.");
    }
    /**
     * Tests the input if there are correct and incorrect letters guessed
     */
    @Test
    void testCountCorrectLetters_Mix(){
        Game game = new Game("lion", "Dr. M");
        game.makeGuess("l");
        game.makeGuess("j");
        game.makeGuess("o");
        assertEquals(2, game.countCorrectLetters(), "Should return 2 for correct guesses");
    }
    /**
     * Tests the Name is returned properly
     */
    @Test
    void testGetName(){
        Game game = new Game("horse", "Zach");
        assertEquals("horse", game.getName(), "Should return horse");
    }
    /**
     * Test that correct answer is returned
     */
    @Test
    void testGetAnswer(){
        Game game = new Game("horse", "Zach");
        assertEquals("horse", game.getAnswer(), "Should return horse");
    }
    /**
     * Test that game status is returned correctly
     */
    @Test
    void testGetGameStatus(){
        Game game = new Game("horse", "Zach");
        assertEquals(0,game.getGameStatus(), "Should return 0 if guesses list is empty and in progress");
        game.makeGuess("horse");
        assertEquals(1,game.getGameStatus(), "Should return 1 after correct guess and win");
    }
}