/**
 * Class Model represents the main Model of the app that contains
 * individual game modules that handle game logic.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists.model;

import android.content.Context;
import android.util.Pair;

import java.io.IOException;

import edu.vassar.neuroscientists.model.narrative.Narrative;
import edu.vassar.neuroscientists.model.narrative.NarrativeProgress;
import edu.vassar.neuroscientists.model.word_games.wordle.Wordle;
import edu.vassar.neuroscientists.model.word_games.wordle.WordleResult;

public class Model {
    /**
     * The context of the app.
     */
    private final Context context;

    /**
     * The Narrative class containing narrative logic for our first interaction with NPCs.
     */
    private final Narrative narrative;

    /**
     * The Wordle game being played.
     */
    private Wordle game;

    /**
     * Number of tries the user has used so far before getting a win.
     */
    int wordleTries = 0;

    /**
     * Flip tracking whether the user has won a Wordle round or not.
     */

    boolean wordleWon = false;

    /**
     * Gets the current Wordle game being played.
     * @return The current Wordle game being played.
     */
    public Wordle getGame() {
        return game;
    }

    /**
     * Gets the current narrative.
     * @return The current narrative.
     */
    public Narrative getNarrative() {
        return narrative;
    }

    /**
     * Gets the win status of user. Resets upon try again or quit.
     * @return The current win/loss status.
     */
    public boolean getWordleWon() {
        return wordleWon;
    }

    /**
     * Sets the win status of user.
     */
    public void setWordleWon(boolean won) {
        wordleWon = won;
    }

    /**
     * Increments number of tries user used before winning.
     */
    public void incrementWordleTries() {
        wordleTries++;
    }

    /**
     * Resets number of tries user used before winning.
     */
    public void resetWordleTries() {
        wordleTries = 0;
    }

    /**
     * Constructor for Model object.
     */
    public Model(Context context) {
        this.context = context;
        narrative = new Narrative();
    }

    /**
     * Creates a new Wordle game.
     */
    public void createWordle() throws IOException {
        game = new Wordle();
        game.setContext(context);
        game.setWordleSettings();
    }

    /**
     * Evaluates the player's input and returns a Feedback object.
     * @param input The player's word input.
     * @return A Feedback object containing the feedback message to be shown on screen:
     * whether the input is valid, and whether the input matches the target word.
     */
    public Wordle.Feedback evaluateInput(String input) {
        return game.evaluate(input);
    }

    /**
     * Gets the current chat displayed.
     * @param dialogueId The current dialogue in progress.
     * @return The current chat.
     */
    public Pair<String, String> getCurrentChat(NarrativeProgress dialogueId) {
        Pair<String, String> chat;
        switch (dialogueId) {
            case CONFERENCE:
                chat = narrative.playConferenceDialogue();
                break;
            case WORDLE_REVIEW:
                chat = narrative.playWordleReviewDialogue();
                break;
            default:
                chat = null;
                break;
        }
        narrative.incrementDialogueIndex();
        return chat;
    }

    /**
     * Advances the narrative to the next part of the progress.
     * Resets the dialogue index so the next is read from the first line.
     * Resets number of Wordle wins to 0 so that when user is forced to try again by narrative,
     * they start with 0 tries.
     * @param dialogueId The current dialogue in progress.
     * @return The next part of the narrative progress.
     */
    public NarrativeProgress advanceNarrative(NarrativeProgress dialogueId) {
        NarrativeProgress nextScene = narrative.getNextScene(dialogueId, wordleTries);
        narrative.resetDialogueIndex();
        resetWordleTries();
        setWordleWon(false);
        return nextScene;
    }

    /**
     * Processes the Wordle results to give final tries number and update the narrative.
     */
    public void processWordleResults() {
        if (!wordleWon) wordleTries = -1;
        narrative.updateTriesProgress(wordleTries);
    }

    /**
     * Saves the Wordle results after each round depending on whether it was won or lost.
     * @param result The result of one round of Wordle.
     */
    public void saveWordleResult(WordleResult result) {
        switch (result) {
            case WIN:
                wordleTries++;
                wordleWon = true;
                break;
            case LOSS:
                if (!wordleWon) wordleTries++;
                break;
        }
    }
}
