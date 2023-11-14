/**
 * The ADialogue class is an abstract class that describes a dialogue that can be displayed in a narrative fragment.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists.model.narrative.chapter_one.scene_one;

import android.util.Pair;
import java.util.LinkedList;
import java.util.List;

public class ADialogue {

    /**
     * List of chats that comprise the dialogue.
     */
    List<Pair<String, String>> dialogue;

    /**
     * Constructor method for ADialogue class.
     */
    public ADialogue() {
        this.dialogue = new LinkedList<>();
    }

    /**
     * Getter method for the dialogue
     * @return The dialogue contained in the ADialogue object.
     */
    public List<Pair<String, String>> getDialogue() {
        return this.dialogue;
    }
}
