import java.util.ArrayList;
import java.util.List;

public class Player{
    private String name;
    private double score;
    private List<String> guesses;

    public Player(String name){
        this.name = name;
        this.score = 0;
        this.guesses = new ArrayList<String>();
    }
    public String getName(){
        return name;
    }
    public double getScore(){
        return score;
    }
    public void addGuess(String guess){
        if(!guesses.contains(guess)) {
            guesses.add(guess);
        }
    }
    public void increaseScore(double points){
        this.score += points;
    }
    public List<String> getGuesses(){
        return guesses;
    }
}
