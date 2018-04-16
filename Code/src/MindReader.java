import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MindReader {

    private int[] POINTS;
    private String GUESSES;
    private Scanner input;
    private Map<String, int[]> map;

    public MindReader() {
        POINTS = new int[2];
        GUESSES = "";
        map = new HashMap<>();
        input = new Scanner(System.in);
    }

    public void start() {

        System.out.println("AI: I will guess your next move in Heads or Tails");

        while (!win()) {
            System.out.println(": Points\n: -----------------\n: AI: " + POINTS[0] + " | Player: " + POINTS[1]);
            System.out.print("AI: Heads or Tails? (h, t): ");
            String AI = guess();
            String in = input.next();
            updateList(in.substring(0, 1));
            if (AI.equals(in)) {
                POINTS[0] += 1;
                System.out.println("AI: Yay, I guessed " + AI + "\n");
            } else {
                System.out.println("AI: No, I guessed " + AI + "\n");
                POINTS[1] += 1;
            }

        }

        System.out.println(": Final Score\n: AI: " + POINTS[0] + ", Player: " + POINTS[1]);
        if (POINTS[0] > POINTS[1]){
            System.out.println(": AI Wins!");
        }
        else{
            System.out.println("Player Wins!");
        }

    }

    private boolean win() {
        return POINTS[0] >= 25 || POINTS[1] >= 25;
    }

    private void updateList(String letter) {
        if (GUESSES.length() >= 4) {

            int[] update = map.get(GUESSES);

            if (update == null) {
                update = new int[]{0, 0};
            }

            if (letter.equals("h")) {
                update[0] += 1;
            } else {
                update[1] += 1;
            }

            map.put(GUESSES, update);

            GUESSES = GUESSES.substring(1) + letter;

        } else {
            GUESSES = GUESSES + letter;
        }
    }

    private String guess(){

        try {

            int[] check = map.get(GUESSES);

            if (check[0] > check[1]) {
                return "h";
            } else {
                return "t";
            }
        }
        catch (NullPointerException n) {
            if (GUESSES.length() == 0) {
                return "h";
            } else {
                return GUESSES.substring(GUESSES.length()-1);
            }
        }

    }
}
