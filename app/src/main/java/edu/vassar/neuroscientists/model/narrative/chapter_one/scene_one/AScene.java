/**
 * The AScene class is an abstract class that describes a scene that can contain multiple dialogues.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists.model.narrative.chapter_one.scene_one;

import java.util.LinkedList;
import java.util.List;

public abstract class AScene {

    /**
     * List of dialogues comprising the scene.
     */
    LinkedList<ADialogue> dialogueList;

    /**
     * Constructor for AScene class.
     */
    public AScene() {
        this.dialogueList = new LinkedList<>();
    }

    /**
     * Getter method for retrieving the list of dialogues in the AScene object.
     * @return The list of dialogues.
     */
    public List<ADialogue> getDialogueList() {
        return this.dialogueList;
    }

    /**
     * Add method for adding a dialogue to the list of dialogues.
     * @param dialogue Dialogue to be added.
     */
    public void addDialogue(ADialogue dialogue) {
        this.dialogueList.add(dialogue);
    }

    /**
     * Pop method for removing the first dialogue in the list of dialogues.
     */
    public void popDialogue() {
        this.dialogueList.pop();
    }
}
