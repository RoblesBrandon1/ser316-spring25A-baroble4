import java.util.ArrayList;
import java.util.List;

public class Player{
    private String name;
    private int score;
    private List<String> guesses;

    public Player(String name){
        this.name = name;
        this.score = 0;
        this.guesses = new ArrayList<String>();
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }
    public void addGuess(String guess){
        if(!guesses.contains(guess)) {
            guesses.add(guess);
        }
    }
    public void increaseScore(int points){
        this.score += points;
    }
    public List<String> getGuesses(){
        return guesses;
    }
}
