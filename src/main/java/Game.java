import java.util.*;

/**
 * Class for handling some game logic for hangman game.
 * Every game starts with a score of 10 and the points are reduced or increased based on the
 * description of "makeGuess".
 * Points holds the current score for one game.
 * Game is lost when the user made 10 guesses and did not guess the word.
 */
public class Game {

    /**
     * Holds the points for the game.
     */
    private int points; //SER316 TASK 2 SPOTBUGS FIX

    /**
     * Holds the round of the game.
     */

    //SER316 TASK 2 SPOTBUGS FIX int a;

    /**
     * Holds the player name for the game.
     */
    //SER316 TASK 2 SPOTBUGS FIX String name;

    /**
     * Holds the answer for the current game.
     */
    String answer;


    /**
     * The path to the file holding the leaderboard.
     */

    //SER316 TASK 2 SPOTBUGS FIX


    /**
     * The status of the game. {0 - In progress, 1 - Game won, 2 - game over}
     */
    protected int gameStatus = 0;

    // all things that were already guessed, needs to be cleared for each game
    ArrayList<String> guesses = new ArrayList<String>();

    // all answers from makeGuess that were already returned
    ArrayList<Double> answers = new ArrayList<Double>();

    /**
     * Gets the name for the game.
     *
     * @return String The name.
     */
    public String getName() {
        return this.answer;
    }

    /**
     * Gets the answer for the game and puts it lowercase.
     *
     * @return String The Answer.
     */
    public String getAnswer() {
        return this.answer.toLowerCase(Locale.ENGLISH); //SER316 TASK 2 SPOTBUGS FIX
    }

    /**
     * Gets the current status of the game.
     *
     */
    public int getGameStatus() {
        return this.gameStatus;
    }

    /**
     * Sets the score for the game.
     *
     */
    public void setPoints(int points) {
        this.points = points;
    }


    /**
     * Gets the score for the game.
     */
    public int getPoints() {
        return this.points;
    }

    /**
     * Counts the number of letters that have been guessed correctly during the game.
     */
    public int countCorrectLetters() {
        int result = 0;
        if (!guesses.isEmpty()) {
            for (int i = 0; i < this.answer.length(); i++) {
                String current = String.valueOf(this.answer.charAt(i));
                if (guesses.contains(current)) {
                    result++;
                    System.out.print(this.answer.charAt(i));
                } else {
                    System.out.print('_');
                }
            }

            System.out.println();}
        else {
            return 0;
        }
        return result;
    }

    /**
     * Counts how often a letter occurs
     *
     */
    public int countLetters(char letter) {
        int count = 0;
        int i = 0;
        while (this.getAnswer().indexOf(letter, i) >= 0) {
            i = this.getAnswer().indexOf(letter, i) + 1;
            count++;
        }
        return count;
    }

    /**
     * Constructs a new game with a random word.
     *
     */
    public Game(String name) {
        //SER316 TASK 2 SPOTBUGS FIX ; this.name = name;
        setRandomWord();
        setPoints(5);

    }

    /**
     * Constructs a new game with a given word and given name.
     *
     */
    public Game(String fixedWord, String name) {
        //SER316 TASK 2 SPOTBUGS FIX ; this.name = "Anna";
        this.answer = fixedWord;
        setPoints(10);
    }

    /**
     * Constructs a new game with no arguments, empty name and answer.
     */
    public Game() {
        //SER316 TASK 2 SPOTBUGS FIX ; this.name = "";
        this.answer = "";
        setPoints(10);
    }

    /**
     * Sets the name and answers of an already existing game and clears the guesses.
     */
    public void initGame(String answer, String name) {
        //SER316 TASK 2 SPOTBUGS FIX ; this.name = name;
        this.answer = answer;
        this.gameStatus = 0;
        this.guesses.clear();
        this.answers.clear();
        setPoints(10);
    }

    /**
     * Checks if the guess made is correct, should ignore upper/lower case.
     * Should give points based on made guess.
     * Method returns a double, number of the double has different meanings
     * 0 Correct guess
     * 1.x Letter is in the word, x represents the number of times the letter is in the word
     * 2.0 Guess has correct length
     * 2.1 Guess is too long (only if it was a word)
     * 2.2 Guess is too short (only if it was a word)
     * 3.0 Guess is partially included in the word (only if it was a word),
     * given instead of 2.2 if it is a partial word.
     * 4.0 This guess was already used
     * 4.1 Guess includes symbols, numbers (not just letters or one letter)
     * 5. After 10 guesses the game ends and is set to game over
     * 5.1 If the player keeps guessing even though the status is not InProgress
     * <p>The returned answer and the guess needs to be added to the respective lists for tracking.<p>
     * If letter:
     * Return 1.NumOfOccurrence, 1.0 for the letter not being in the word, 1.1 for being in there once etc.
     * Add points according to NumOfOccurance
     * <p>If word (go by this order of checks):
     * If word is correct return 0.0 and add points based on the length of the word (e.g. dog - 3 points,
     * horse - 5 points), set game status to won
     * If word is incorrect but has correct length return 2.0 and add 1 point
     * If word is incorrect and is partially included in the word return 3.0 and add 2 points
     * If word is too long or too short return 2.1, 2.2 accordingly and reduce points based on how off the word is
     * (e.g. how many letters off)<p>
     * For either:
     * Guess was already used, reduce points by 2 and return 4.0 (checked before 4.1 error). Guess still counts toward
     * made guesses.
     * Guess includes numbers/symbols etc. (so more than just letters) reduce points by 3 and return 4.1, the guess is
     * still added to the list of guesses<p>
     * <p>Score can also be negative, that is no problem.
     * When the player guessed 10 times and did not guess the word set the game to game over (status) and return 5.0
     * (no matter if there was another error).
     * If the player guesses again, even though game status is won or game over return 5.1.
     *
     * @return double returns the appropriate number
     */
    public double makeGuess(String guess) {
        if (gameStatus != 0) {
            return 5.1;
        }
        guess = guess.toLowerCase(Locale.ENGLISH);//SER316 TASK 2 SPOTBUGS FIX

        guesses.add(guess);

        if (guesses.size() >= 10) {
            gameStatus = 2;
            return 5.0;
        }

        if (!guess.matches("[a-zA-Z]+")) {
            points -= 3;
            return 4.1;
        }

        if (guesses.indexOf(guess) != guesses.size() - 1) {
            points -= 2;
            return 4.0;
        }


        if (guess.length() == 1) {
            int count = countLetters(guess.charAt(0));
            if (count > 0) {
                points += count;
                return 1.0 + count / 10.0;
            }
            return 1.0;
        }
        //Correct Guess
        if (guess.equals(answer)) {
            points += answer.length();
            gameStatus = 1;
            return 0.0;
        }

        if (guess.length() == answer.length()) {
            points += 1;
            return 2.0;
        } else if (guess.length() > answer.length()) {
            points -= (guess.length() - answer.length());
            return 2.1;
        } else {
            if (answer.contains(guess)) {
                points += 2;
                return 3.0;
            }
            points -= (answer.length() - guess.length());
            return 2.2;
        }

    }

    /**
     * Pulls out a random animal and sets it as answer.
     */
    public final void setRandomWord() { //SER316 TASK 2 SPOTBUGS FIX
        String[] animals = {"dog", "horse", "pony", "cat", "lion", "bear", "lioncub",};
        int randomNum;
        randomNum = (int) (Math.floor(Math.random() * (100 - 2 + 1) + 2) % animals.length);
        this.answer = animals[randomNum];
    }

}