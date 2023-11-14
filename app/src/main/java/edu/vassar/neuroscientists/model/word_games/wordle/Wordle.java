/**
 * Class Wordle represents a single game of Wordle, extending from the abstract class WordGame.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists.model.word_games.wordle;

import android.content.Context;

import java.io.IOException;

import edu.vassar.neuroscientists.model.word_games.AWordGame;

public class Wordle extends AWordGame {

    /**
     * The app's context so that Wordle class can retrieve res.
     */
    private Context context;

    /**
     * The text library from which Wordle answers can be chosen.
     */
    private WordleAnswerLibrary wordleAnswerLibrary;

    /**
     * The text library from which Wordle guesses can be input as valid.
     */
    private WordleGuessLibrary wordleGuessLibrary;

    /**
     * The difficulty level of the current Wordle game: 0 for easy, 1 for medium, 2 for hard.
     * Difficulty level also sets the game's time limit and the collection of words the target word
     * is chosen from.
     */
    private final int difficulty;

    /**
     * Time limit of the current game of Wordle:
     * 20 minutes for easy, 10 minutes for medium and 5 minutes for hard.
     */
    private int time;

    /**
     * The word to be guessed for the current Wordle game.
     */
    private String target;

    /**
     * The number of characters allowed in the target word and the player's input (always 5).
     */
    private final int WORD_LENGTH = 5;

    /**
     * Instantiates a Wordle game and sets its random difficulty level, time limit and target word.
     */
    public Wordle() {
        difficulty = (int) (Math.random() * 3);
    }

    /**
     * Method for setting the difficulty, time constraint and target word of a Wordle game.
     * @throws IOException
     */
    public void setWordleSettings() throws IOException {
        createWordleLibraries();
        switch (difficulty) {
            case 0:
                time = 1200;
                target = wordleAnswerLibrary.getTarget(0);
                break;
            case 1:
                time = 600;
                target = wordleAnswerLibrary.getTarget(1);
                break;
            case 2:
                time = 300;
                target = wordleAnswerLibrary.getTarget(2);
                break;
        }
    }

    /**
     * Method for creating the Wordle answer and input libraries.
     * @throws IOException
     */
    private void createWordleLibraries() throws IOException {
        wordleAnswerLibrary = new WordleAnswerLibrary(context, "text-libraries/wordle-answer-library.txt");
        wordleGuessLibrary = new WordleGuessLibrary(context, "text-libraries/wordle-guess-library.txt");
    }


    /**
     * Instantiates a Wordle game with a certain target, for the purpose of JUnit Testing
     * @param target The target word for the current Wordle game
     */
    public Wordle(String target) throws IOException {
        this();
        this.target = target.toUpperCase();
    }

    /**
     * Sets the context for the current game of Wordle,
     * for the purpose of passing it to create the Wordle answer and guess libraries.
     * @param context The context to set the current context field of the Wordle to.
     */
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * Gets the difficulty level of the current game of Wordle.
     * @return The difficulty level as an integer.
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * Gets the time limit for the current Wordle game in seconds.
     * @return The time limit in seconds.
     */
    public long getTime() {
        return time;
    }

    /**
     * Gets the target word in the current Wordle game.
     * @return The target word as a String.
     */
    public String getTarget() {
        return target;
    }


    /**
     * Evaluates the player's input and returns a Feedback object.
     * @param input The player's input as a string.
     * @return The feedback message as a String based on player's word input.
     */
    public Feedback evaluate(String input) {
       if (wordleGuessLibrary != null && !wordleGuessLibrary.mainLibSet.contains(input)) {
           return new Feedback(new int[]{-1, -1, -1, -1, -1}, 'I');
       }
        return input.equals(target) ?
                new Feedback(new int[]{2, 2, 2, 2, 2}, 'W') :
                new Feedback(comparisonArray(input), 'V');
    }

    /**
     * Helper method that compares the player's word input to the target word
     * and sets the appropriate color of each character in the input to either
     * green, yellow, or grey (default) depending on whether the character
     * is also present in the target word; and if so, whether its position matches
     * that of the target word.
     * The number of yellow-coded/green-coded versions of one character parallels
     * the number of that character in the target word. So if the player inputs
     * a String with three duplicates of the same character while the target word
     * only has two, only two of the characters will be represented by the integer 2
     * in the feedback message, while the last character will be represented by a 0.
     * Any green-coded character is prioritized over a yellow-coded version of it.
     * Access set to public for JUnit Testing.
     * @param input the input string to compare to the target word.
     * @return an integer array representing the feedback for the input string.
     */

    public int[] comparisonArray(String input) {
        int[] array = new int[WORD_LENGTH];
        for (int i = 0; i < WORD_LENGTH; i++) {
            char currentChar = input.charAt(i);
            if (currentChar == target.charAt(i)) {
                int index = input.indexOf(currentChar); // get leftmost index of yellow-coded current char in the feedback string built up to now
                if (charCount(currentChar, input, i - 1) >= charCount(currentChar, target, WORD_LENGTH - 1)) { // if there is a perfect match, "cancel out" a previous yellow-code of the current char
                    array[index] = 0; // replace index value of corresponding slot in array from 1 to 0
                }
                array[i] = 2;
            } else if (target.contains(String.valueOf(currentChar))) { // if not perfect match, but target word contains the current char
                if (charCount(currentChar, input, i - 1) < charCount(currentChar, target, WORD_LENGTH - 1)) { // if there are not already the same number of yellow-coded current chars in the retStr so far as in the target
                    array[i] = 1;
                } else {
                    array[i] = 0;
                }
            } else {
                array[i] = 0;
            }
        }
        return array;
    }

    /**
     * Helper method to count the number of a certain character in a String, up to an index.
     * Access set to public for JUnit Testing.
     * @param c     The character to be searched for in the input String.
     * @param str   The input String to be searched.
     * @param index The index up to which the search is done.
     * @return An integer representing the number of a certain character in a String up to an index.
     */
    public int charCount(char c, String str, int index) {
        int count = 0;
        for (int i = 0; i <= index; i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    /**
     * Class Feedback represents the feedback message a player receives after inputting a guess,
     * the validity of that guess and how well it matches the target word.
     */
    public static class Feedback {
        /**
         * The feedback message to be displayed on screen after the player's input.
         */
        private final int[] feedbackArray;

        /**
         * The validity of the input, whether it matches the target word
         * and whether all 6 tries have been used up:
         * 'I' for invalid input, 'V' for valid but not win, 'W' for win, 'L' for lose (used up all 6 tries).
         */
        private final char result;

        /**
         * Instantiates a Feedback object.
         * @param feedbackArray The feedback message as a String.
         * @param result The result of the player's input as a char.
         */
        public Feedback(int[] feedbackArray, char result) {
            this.feedbackArray = feedbackArray;
            this.result = result;
        }

        /**
         * Gets the feedback message of the current Feedback object.
         * @return The feedback message as a String.
         */
        public int[] getFeedbackArray() {
            return feedbackArray;
        }

        /**
         * Gets the feedback result of the current Feedback object.
         * @return The feedback result as a char.
         */
        public char getResult() {
            return result;
        }
    }
}