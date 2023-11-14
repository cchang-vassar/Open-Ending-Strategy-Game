/**
 * class Wordle represents a single game of Wordle,
 * extending from the abstract class WordGame.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

public class Wordle extends AWordGame {
    /**
     * The difficulty level of the current Wordle game:
     * 0 for easy, 1 for medium, 2 for hard.
     * Difficulty level also sets the game's time limit
     * and the collection of words the target word is chosen from.
     */
    private final int difficulty; // final within each game of Wordle, different across different games of Wordle

    /**
     * The textual tutorial appearing at the start of each round of Wordle.
     */
    private final String tutorial;

    /**
     * The number of tries a player gets for each Wordle game (always 6).
     */
    private final int tries;

    /**
     * The number of characters allowed in the target word and the player's input (always 5).
     */
    private final int WORD_LENGTH = 5;

    /**
     * String that changes subsequent output color to yellow when appended.
     */
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * String that changes subsequent output color to green when appended.
     */
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     * String that resets subsequent output color to original color when appended.
     */
    public static final String ANSI_RESET = "\u001B[39m";

    /**
     * Time limit of the current game of Wordle:
     * 20 minutes for easy, 10 minutes for medium and 5 minutes for hard.
     */
    private int time; // easy: 1200s, medium: 600s, hard: 300s;

    /**
     * The word to be guessed for the current Wordle game.
     */
    private String target;

    /**
     * The try number the player is currently on,
     * i.e. (currentTry - 1) represents the number of tries that have been used.
     */
    private int currentTry;

    /**
     * Gets the difficulty level of the current game of Wordle.
     *
     * @return The difficulty level as an integer.
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Gets the tutorial message displayed to the player prior to round 1 of each Wordle game.
     *
     * @return The tutorial message as a String.
     */
    public String getTutorial() {
        return tutorial;
    }

    /**
     * Gets the time limit for the current Wordle game in seconds.
     *
     * @return The time limit in seconds.
     */
    public int getTime() {
        return time;
    }

    /**
     * Gets the target word in the current Wordle game.
     *
     * @return The target word as a String.
     */
    public String getTarget() {
        return target;
    }

    /**
     * Updates the currentTry value to reflect how many tries the player has used.
     */
    public void increaseCurrentTry() {
        currentTry++;
    } // instead of a "setter" for currentTry

    /**
     * Instantiates a Wordle game and sets its difficulty level (random), time limit and target word.
     */
    public Wordle() {
        WordleLibrary lib = new WordleLibrary();
        difficulty = (int) (Math.random() * 3);
        tutorial =  "In each round of Wordle, you have 6 tries to guess a target random 5-letter English word.\n" +
                    "For each try, you must input a 5-letter word, or your input will be counted as invalid.\n" +
                    "An invalid input will not be counted as a try, and you will be asked to input another guess.\n" +
                    "For each valid guess, you will receive a feedback message that color-codes each character your guess in the following ways:\n" +
                    "*  A character that is present and in the same position in your guess as in the target word will be coded " + ANSI_GREEN + "green" + ANSI_RESET + ".\n" +
                    "*  A character that is present but not in the same position as in the target word will be coded " + ANSI_YELLOW + "yellow" + ANSI_RESET + ".\n";
        tries = 6;
        currentTry = 1;
        int targetIndex = (int) (Math.random() * 10);
        switch (difficulty) {
            case 0:
                time = 1200;
                target = lib.getEasy().get(targetIndex);
                break;
            case 1:
                time = 600;
                target = lib.getMed().get(targetIndex);
                break;
            case 2:
                time = 300;
                target = lib.getHard().get(targetIndex);
                break;
        }
    }

    /**
     * class Feedback represents the feedback message a player receives after inputting a guess,
     * the validity of that guess and how well it matches the target word.
     */
    public static class Feedback {
        /**
         * The feedback message to be displayed on screen after the player's input.
         */
        private final String message; // String shown as feedback after player input

        /**
         * The validity of the input, whether it matches the target word
         * and whether all 6 tries have been used up:
         * 'I' for invalid input, 'V' for valid but not win, 'W' for win, 'L' for lose (used up all 6 tries).
         */
        private final char result;

        /**
         * Gets the feedback message of the current Feedback object.
         *
         * @return The feedback message as a String.
         */
        public String getMessage() {
            return message;
        }

        /**
         * Gets the feedback result of the current Feedback object.
         *
         * @return The feedback result as a char.
         */
        public char getResult() {
            return result;
        }

        /**
         * Instantiates a Feedback object.
         *
         * @param message The feedback message as a String.
         * @param result The result of the player's input as a char.
         */
        public Feedback(String message, char result) {
            this.message = message;
            this.result = result;
        }
    }

    /**
     * Evaluates the player's input and returns a Feedback object.
     *
     * @param input The player's input as a string
     * @return The feedback message as a String based on player's word input.
     */
    public Feedback evaluate(String input) {
        Feedback feedback;
        if (input.length() != WORD_LENGTH || !input.matches("[a-zA-Z]+")) { // invalid input: not 5 alphabets
            feedback = new Feedback("Invalid input. Please enter a 5-letter word.", 'I');
        }
        else if (input.toUpperCase().equals(target)) { // input matches the target word
            feedback = new Feedback("You got the word. Congratulations!\n", 'W');
        }
        else if (currentTry < tries){ // input is valid, does not match target word, but still have tries left
            feedback = new Feedback(comparisonString(input), 'V');
        }
        else { // input is valid, does not match target word, and no more tries left
            feedback = new Feedback("You did not get the word. Better luck next time!\n", 'L');
        }
        return feedback;
    }

    /**
     * Helper method that compares the player's word input to the target word
     * and sets the appropriate color of each character in the input to either green. yellow, or grey (default)
     * depending on whether the character is also present in the target word;
     * and if so, whether its position matches that of the target word.
     *
     * The number of yellow-coded/green-coded versions of one character parallels the number of that character in the target word.
     * So if the player inputs a String with 3 duplicates of the same character while the target word only has 2,
     * only 2 will be yellow-coded/green-coded in the feedback message.
     * Any green-coded character is prioritized over a yellow-coded version of it.
     *
     * @param input The player's word input as a String.
     * @return  The result of the player's word input with correctly positioned characters displayed in green,
     *          correct but wrongly positioned characters in yellow, and wrong characters in grey as a String.
     */
    private String comparisonString(String input) {
        StringBuilder retStr = new StringBuilder();
        for (int i = 0; i < WORD_LENGTH; i++) {
            char curChar = input.toUpperCase().charAt(i);
            if (curChar == target.charAt(i)) {
                if (charCount(curChar, retStr.toString()) >= charCount(curChar, target)) { // if there is a perfect match, "cancel out" a previous yellow-code of the current char
                    int index = retStr.indexOf(ANSI_YELLOW + curChar); // get leftmost index of yellow-coded current char in the feedback string built up to now
                    retStr.replace(index, index + ANSI_YELLOW.length(), ANSI_RESET); // replace ANSI_YELLOW with ANSI_RESET
                }
                retStr.append(ANSI_GREEN).append(curChar).append(ANSI_RESET);
            }
            else if (target.contains(String.valueOf(curChar))) { // if not perfect match, but target word contains the current char
                if (charCount(curChar, retStr.toString()) < charCount(curChar, target)) { // if there are not already the same number of yellow-coded current chars in the retStr so far as in the target
                    retStr.append(ANSI_YELLOW).append(curChar).append(ANSI_RESET);
                }
                else {
                    retStr.append(curChar);
                }
            }
            else {
                retStr.append(curChar);
            }
        }
        return retStr.toString();
    }

    /**
     * Helper method to count the number of a certain character in a String.
     *
     * @param c The character to be searched for in the input String.
     * @param str The input String to be searched.
     * @return An integer representing the number of a certain character in a String
     */
    private int charCount(char c, String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count ++;
            }
        }
        return count;
    }
}