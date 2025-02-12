import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BlackBoxGiven {

    // Method that supplies instances of different classes to the parameterized test
    static Stream<Arguments> provideGuessingGameInstances() {
        return Stream.of(
                Arguments.of(new Game0()),
                Arguments.of(new Game1()),
                Arguments.of(new Game2()),
                Arguments.of(new Game3()),
                Arguments.of(new Game4())
        );
    }

    // Parameterized test that tests the same method on different classes
    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void statusWin(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("lion");
        assertEquals(0.0, response, 0.0);
        assertEquals(14, game.getPoints());
        assertEquals(1, game.getGameStatus());
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void testIncorrectWordSameLength(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("fast");
        assertEquals(2.0, response, 0.0, "Should return 2.0 for incorrect guess of correct length");
        assertEquals(11, game.getPoints(), "Points should increase by 1");
        assertEquals(0, game.getGameStatus(), "Game should still be in progress");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void testIncorrectWordTooLong(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("lumberjacks");
        assertEquals(2.1, response, 0.0, "Should return 2.1 for word that is too long");
        assertTrue(game.getPoints() < 10, "Points should decrease based on difference in length");

    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void testIncorrectWordTooShort(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("hi");
        assertEquals(2.2, response, 0.0, "Should return 2.2 for word that is too short");
        assertTrue(game.getPoints() < 11, "Points should decrease based on difference in length");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void testCorrectLetterGuess(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("o");
        assertEquals(1.1, response, 0.0, "Decimal value should return for the occurrences of letter");
        assertTrue(game.getPoints() > 10, "Points should increase if letter is in the word by that amount");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void testIncorrectLetterGuess(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("z");
        assertEquals(1.0, response, 0.0, "Should return 1.0 for incorrect letter");
        assertEquals(10, game.getPoints(), "Points should not change");
    }


    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void testPartialWordMatch(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("lio");
        assertEquals(3.0, response, 0.0, "Should return 3.0 for partial word match");
        assertEquals(12, game.getPoints(), "Points should increase by 2");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void testRepeatedGuess(Game game) {
        game.initGame("lion", "Dr. M");

        game.makeGuess("fans");
        double response = game.makeGuess("fans");

        assertEquals(4.0, response, 0.0, "Should return 4.0 for repeated guess");
        assertEquals(9, game.getPoints(), "Points should increase 1 and then decrease 2");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void testInvalidGuessWithNumbers(Game game) {
        game.initGame("lion", "Dr. M");

        double response = game.makeGuess("@123");
        assertEquals(4.1, response, 0.0, "Should return 4.1 for Symbols/number guess");
        assertEquals(7, game.getPoints(), "Points should decrease by 3");
    }


    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void testGameOverAfter10Guesses(Game game) {
        game.initGame("lion", "Dr. M");

        for (int i = 0; i < 9; i++) {
            game.makeGuess("wrong" + i);
        }

        double response = game.makeGuess("extra");
        assertEquals(5.0, response, 0.0, "10th incorrect guess should return 5.0");
        assertEquals(2, game.getGameStatus(), "Game should be marked as over");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void testGuessingAfterGameOver(Game game) {
        game.initGame("lion", "Dr. M");

        for (int i = 0; i < 10; i++) {
            game.makeGuess("wrong" + i);
        }

        double response = game.makeGuess("extra");
        assertEquals(5.1, response, 0.0, "Guessing after game over should return 5.1");
        assertEquals(2, game.getGameStatus(), "Game should be marked as over");
    }

    @ParameterizedTest
    @MethodSource("provideGuessingGameInstances")
    public void testCaseSensitivity(Game game) {
        game.initGame("lion", "Dr. M");


        double resultUpper = game.makeGuess("LION");

        assertEquals(0,resultUpper, 0.0, "Uppercase should be treated as lowercase");
    }




}