/**
 * The NemurStraussConferenceDialogue class is a dialogue that contains every line in the
 * dialogue between Dr Nemur and Dr Strauss when the user first launches the app.
 *
 *  @author Cherrie Chang, Josephine Man
 *  @version v1.0
 */

package edu.vassar.neuroscientists.model.narrative.chapter_one.scene_one;

import android.util.Pair;

public class NemurStraussConferenceDialogue extends ADialogue {

    /**
     * Constructor for NemurStraussConferenceDialogue class.
     */
    public NemurStraussConferenceDialogue() {
        super();
        dialogue.add(new Pair<>("nemur", "So, what do you think?\nShould we give it a go?"));
        dialogue.add(new Pair<>("strauss", "Well, he IS an aspiring\npsychologist..."));
        dialogue.add(new Pair<>("nemur", "True. He will definitely be\n smarter than silly Charlie."));
        dialogue.add(new Pair<>("strauss", "Although that will also\n make him harder to control."));
        dialogue.add(new Pair<>("strauss", "...It's worth a shot."));
        dialogue.add(new Pair<>("strauss", "Hey newbie!"));
        dialogue.add(new Pair<>("nemur", "We are inviting you to join us\n for dinner at our lab."));
        dialogue.add(new Pair<>("nemur", "Play this Wordle and show us\nhow smart you are."));
    }
}
