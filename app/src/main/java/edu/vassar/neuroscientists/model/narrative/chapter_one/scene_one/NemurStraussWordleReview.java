/**
 * The NemurStraussWordleReview class is a dialogue that contains every line in the
 * dialogue between Dr Nemur and Dr Strauss after the user finishes playing Wordle.
 *
 *  @author Cherrie Chang, Josephine Man
 *  @version v1.0
 */

package edu.vassar.neuroscientists.model.narrative.chapter_one.scene_one;

import android.util.Pair;


public class NemurStraussWordleReview extends ADialogue{

    /**
     * Method for updating the lines in the dialogue based on the user's performance in Wordle.
     * @param tries The number of tries the user took to get a Wordle win,
     *              -1 if the user quit without winning.
     */
    public void updateWordleReview(int tries) {
        String nemurReview;
        String straussReview;
        if (tries <= 2 && tries > 0) {
            nemurReview = String.format("A win within %d tries...\nNot bad for a newbie.", tries);
            straussReview = "What an outstanding brain!\nI shall milk it to its limits.";
        }
        else if (tries > 2) {
            nemurReview = String.format("A win after %d tries...\nStrauss, I didn't know psychology\nundergraduates could be dumber than Charlie.", tries);
            straussReview = "Truly unfortunate. I was excited to see\nwhat we could accomplish...Try again, son.";
        }
        else {
            nemurReview = "Well aren't you a quitter?";
            straussReview = "Shame, youngsters nowadays...\nLet's use someone else.";
        }

        dialogue.add(new Pair<>("nemur", nemurReview));
        dialogue.add(new Pair<>("strauss", straussReview));

        if (tries <= 2 && tries > 0) {
            dialogue.add(new Pair<>("nemur", "Tonight at eight?"));
        }
    }
}
