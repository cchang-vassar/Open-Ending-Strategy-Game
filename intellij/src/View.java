/**
 * class View represents the app's user interface.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class View {
    /**
     * Prints the settings of a single game of Wordle: difficulty level and time limit.
     *
     * @param difficulty Level of difficulty of a single Wordle game.
     * @param time Time limit of a single Wordle game.
     */
    void displaySettings(int difficulty, int time) {
        System.out.printf("difficulty: %d\ntime: %d\n", difficulty, time);
    }

    /**
     * Prints the String passed into this method in a new line.
     *
     * @param input The String to be printed.
     */
    void print(String input) {
        System.out.println(input);
    }

    /**
     * Continuously reads player's input unless the Thread it is embedded in is interrupted.
     *
     * @return Player's input continuously read using a BufferedReader.
     */
    String getInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        print("Please input your guess here: ");
        String input;
        do {
            try {
                while (!br.ready()) {
                    Thread.sleep(200); // temporary fix for wasting CPU, not optimal
                }
                input = br.readLine();
            }
            catch (InterruptedException e) {
                return null;
            }
        } while (input.equals(""));
        return input;
    }

    /**
     * Evaluates if a player wants to continue and play the next round..
     *
     * @return True if player types 'y',
     *         false if player types 'n'.
     */
    boolean askContinue() {
        while (true) {
            System.out.println("Up for the next round? [y/n] NOTE: If the first try of inputting " +
                    "'y' or 'n' gives an invalid input feedback, the second try should work.");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("y")) {
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                print("Program exited. Goodbye!");
                return false;
            } else {
                print("Invalid input. Please type 'y' to keep playing, or 'n' to quit.");
            }
        }
    }
}
