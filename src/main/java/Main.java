import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    //static Scanner scanner = new Scanner(System.in, StandardCharsets.UTF_8);//SER316 TASK 2 SPOTBUGS FIX
    //Asking to remove scanner because of no use, did not because change to it was also done
    public static void main(String[] args) {
        // just some calls
        System.out.println("Getting started");
        Game game = new Game("Student");
        System.out.println("Current word: " + game.answer);
        System.out.println(game.makeGuess("Dr. M","a"));
        System.out.println("Automatic guess a");

    }
}
