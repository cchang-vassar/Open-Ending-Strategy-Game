/**
 * Defines the contract between the view and its listener for Wordle game events.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists.view;


import java.io.IOException;

import edu.vassar.neuroscientists.model.word_games.wordle.Wordle;
import edu.vassar.neuroscientists.model.word_games.wordle.WordleResult;

public interface IWordleView {
    /**
     * The listener interface for wordle answer detection events.
     */
    interface Listener {
        /**
         * Called when the user has given a possible answer for the game.
         * @param answer the answer that the user detected.
         * @return the feedback for the user's answer.
         */
        Wordle.Feedback onWordleAnswerDetected(String answer);

        /**
         * Called when the user has lost the game.
         */
        void onLost(IWordleView view);

        /**
         * Called when the user wants to try again after a game is over.
         */
        void onTryAgain() throws IOException;

        /**
         * Called when the user presses the "QUIT" button in Wordle.
         */
        void onQuit();

        /**
         * Cancels the Wordle timer.
         */
        void cancelTimer();

        /**
         * Saves the Wordle round's result.
         * @param result The Wordle round's result.
         */
        void saveWordleResult(WordleResult result);
    }

    /**
     * Creates a popup of the word.
     * @param targetWord The word to show in a popup.
     */
    void createTargetWordPopup(String targetWord);
}
