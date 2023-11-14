/**
 * The Narrative class handles the business logic of a narrative, which contains scenes.
 *
 *  @author Cherrie Chang, Josephine Man
 *  @version v1.0
 */

package edu.vassar.neuroscientists.model.narrative;

import android.util.Pair;
import java.util.LinkedList;
import java.util.List;

import edu.vassar.neuroscientists.model.narrative.chapter_one.scene_one.ADialogue;
import edu.vassar.neuroscientists.model.narrative.chapter_one.scene_one.AScene;
import edu.vassar.neuroscientists.model.narrative.chapter_one.scene_one.NemurStraussWordleReview;
import edu.vassar.neuroscientists.model.narrative.chapter_one.scene_one.SceneOne;


public class Narrative {
    List<AScene> sceneList;
    int dialogueIndex = 0;

    /**
     * Constructor for class Narrative.
     */
    public Narrative() {
        sceneList = new LinkedList<>();
        this.sceneList.add(new SceneOne());
    }

    /**
     * Wrapper method for playing the conference dialogue, which is the first dialogue at the start of the game
     * where Dr Nemur and Dr Strauss talk to each other, deciding whether to make a move on the user.
     * @return Calls playDialogue() to play the first dialogue in SceneOne.
     */
    public Pair<String, String> playConferenceDialogue() {
        return playDialogue(0);
    }

    /**
     * Wrapper method for playing the wordle review dialogue, which is the second dialogue of the game
     * where Dr Nemur and Dr Strauss review the user's Wordle performance and decides whether to recruit him.
     * @return Calls playDialogue() to play the second dialogue in SceneOne.
     */
    public Pair<String, String> playWordleReviewDialogue() {
        return playDialogue(1);
    }

    /**
     * Method for retrieving each line in a dialogue.
     * @param index The index of the dialogue to be retrieved from the scene object.
     * @return The line at the given global dialogueIndex of the dialogue retrieved
     */
    private Pair<String, String> playDialogue(int index) {
        List<ADialogue> dialogueList = sceneList.get(0).getDialogueList();
        List<Pair<String, String>> dialogue = dialogueList.get(index).getDialogue();

        if (dialogueIndex >= dialogue.size()) {
            return null;
        } else {
            return dialogue.get(dialogueIndex);
        }
    }

    /**
     * Adds a NemurStraussWordleReview to the list of dialogues to be displayed,
     * and updating it based on how many tries the user took to win a Wordle.
     * @param tries The user's number of tries
     */
    public void updateTriesProgress(int tries) {
        SceneOne sceneOne = (SceneOne) sceneList.get(0);
        sceneOne.addDialogue(new NemurStraussWordleReview());
        NemurStraussWordleReview wordleReview = (NemurStraussWordleReview) sceneOne.getDialogueList().get(1);
        wordleReview.updateWordleReview(tries);
    }

    /**
     * Increments the index determining which line of the dialogue to display.
     */
    public void incrementDialogueIndex() {
        dialogueIndex++;
    }

    /**
     * Resets the dialogue index.
     */
    public void resetDialogueIndex() {
        dialogueIndex = 0;
    }

    /**
     * Retrieves the next scene to be displayed.
     * @param scene The current scene being displayed.
     * @param wordleTries The number of tries the user took to get a Wordle win
     * @return The next narrative progress to be displayed
     */
    public NarrativeProgress getNextScene(NarrativeProgress scene, int wordleTries) {
        switch (scene) {
            case CONFERENCE:
                return NarrativeProgress.TUTORIAL;
            case TUTORIAL:
                return NarrativeProgress.WORDLE;
            case WORDLE:
                return NarrativeProgress.WORDLE_REVIEW;
            case WORDLE_REVIEW:
                if (0 < wordleTries && wordleTries <= 2) {
                    return NarrativeProgress.SUCCESS_ENDING;
                } else if (wordleTries > 2) {
                    SceneOne sceneOne = (SceneOne) sceneList.get(0);
                    sceneOne.popDialogue();
                    return NarrativeProgress.TUTORIAL;
                } else {
                    return NarrativeProgress.FAILURE_ENDING;
                }
            default:
                return null;
        }
    }
}
