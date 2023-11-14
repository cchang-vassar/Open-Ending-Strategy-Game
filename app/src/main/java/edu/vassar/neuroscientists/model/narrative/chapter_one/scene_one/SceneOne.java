/**
 * The SceneOne class is a scene that contains the dialogue between Dr Nemur and Dr Strauss
 * at the conference at the start of the game.
 *
 *  @author Cherrie Chang, Josephine Man
 *  @version v1.0
 */

package edu.vassar.neuroscientists.model.narrative.chapter_one.scene_one;

public class SceneOne extends AScene {

    /**
     * Constructor for class SceneOne.
     */
    public SceneOne() {
        super();
        addDialogue(new NemurStraussConferenceDialogue());
    }
}
