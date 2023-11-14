/**
 * A custom linear layout class used for displaying a word in the Wordle game.
 * Contains methods for selecting and deselecting a word, setting the next word,
 * and getting the next word.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class LinearWord extends LinearLayout {
    /**
     * The Linear Layout for the potential next word in the game.
     */
    private LinearWord nextWord;

    /**
     * Instantiates a LinearWord object.
     * @param context the context in which the LinearWord is created.
     */
    public LinearWord(Context context) {
        this(context, null);
    }

    /**
     * Instantiates a LinearWord object.
     * @param context the context in which the LinearWord is created.
     * @param attrs the attributes of the LinearWord.
     */
    public LinearWord(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Instantiates a LinearWord object.
     * @param context the context in which the LinearWord is created.
     * @param attrs the attributes of the LinearWord.
     * @param defStyleAttr the default style attribute of the LinearWord.
     */
    public LinearWord(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    /**
     * Instantiates a LinearWord object.
     * @param context the context in which the LinearWord is created.
     * @param attrs the attributes of the LinearWord.
     * @param defStyleAttr the default style attribute of the LinearWord.
     * @param defStyleRes the default style resource of the LinearWord.
     */
    public LinearWord(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setEnabled(false);
    }

    /**
     * Selects a word and focuses on its first letter.
     */
    public void Select() {
        setEnabled(true);
        for (int i = 0; i < getChildCount(); i++) {
            EditLetter letter = (EditLetter) getChildAt(i);
            letter.setEnabled(true);
            if (i == 0) letter.focus();
        }
    }

    /**
     * Deselects a word.
     */
    public void Deselect() {
        setEnabled(false);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setEnabled(false);
        }
    }

    /**
     * Gets the next word in the game.
     * @return the next word.
     */
    public LinearWord getNextWord() {
        return nextWord;
    }

    /**
     * Sets the next word in the game.
     * @param nextWord the next word to be set.
     */
    public void setNextWord(LinearWord nextWord) {
        this.nextWord = nextWord;
    }
}
