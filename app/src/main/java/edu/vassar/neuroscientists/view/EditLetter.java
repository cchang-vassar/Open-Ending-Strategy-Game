/**
 * A custom EditText for a single-letter text box in any word game.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists.view;


import android.content.Context;
import android.os.Build;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

public class EditLetter extends AppCompatEditText {
    /**
     * A Wordle EditText only has a maximum of one character.
     */
    private final int MAX_LENGTH = 1;

    /**
     * The previous and next letters in the current word.
     */
    private EditLetter previousLetter, nextLetter;

    /**
     * The WordleFragment in which the letter is contained.
     */
    private WordleFragment fragment;

    /**
     * Current word in which the letter is located.
     */
    private LinearWord currentWord;

    /**
     * Instantiates an EditLetter object.
     * @param context the Context of the view.
     */
    public EditLetter(@NonNull Context context) {
        this(context, null);
    }

    /**
     * Instantiates a new EditLetter with the given context and attribute set.
     * @param context The context of the view.
     * @param attrs The AttributeSet for this view.
     */
    public EditLetter(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * Instantiates a new EditLetter instance with the given Context, AttributeSet,
     * and default style attribute, then configures the single-letter text box.
     * @param context The context of the view.
     * @param attrs The AttributeSet for this view.
     * @param defStyleAttr specifies a default style attribute for the view.
     */
    public EditLetter(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        configure();
    }

    /**
     * Configures the EditText view so that it will become a single-letter text box used in Wordle.
     * This involves attributes for filters, focus, accessibility, input type, soft keyboard and
     * hard keyboard listeners.
     */
    private void configure() {
        setFilters(new InputFilter[]{
                new AlphabeticInputFilter(),
                new InputFilter.AllCaps(),
                new InputFilter.LengthFilter(MAX_LENGTH)});
        setEnabled(false);
        setFocusable(false);
        setFocusableInTouchMode(false);
        setCursorVisible(false);
        setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            setImportantForAutofill(View.IMPORTANT_FOR_AUTOFILL_NO);
        }
        setImeOptions(EditorInfo.IME_FLAG_NO_FULLSCREEN);
        setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
                | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
        setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                fragment.analyzeWord(nextLetter == null && length() == MAX_LENGTH ? currentWord : null);
                return true;
            }
            return false;
        });
    }

    /**
     * Called whenever the selection changes within the EditLetter view, so that
     * the cursor is always after the text.
     * @param selectionStart the starting index of the selection.
     * @param selectionEnd the ending index of the selection.
     */
    @Override
    protected void onSelectionChanged(int selectionStart, int selectionEnd) {
        super.onSelectionChanged(selectionStart, selectionEnd);
        int length = this.length();
        if (selectionStart == selectionEnd && selectionEnd != length)
            setSelection(length);
    }

    /**
     * Called when a key is pressed down. Handles different key press such as BackSpace and Enter
     * on a hardware keyboard.
     * @param keyCode The key code of the pressed key.
     * @param event The KeyEvent object containing additional information about the event.
     * @return Returns true if the event was handled, false otherwise.
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DEL:
                moveBack();
                break;
            case KeyEvent.KEYCODE_ENTER:
                boolean isValid = (nextLetter == null && length() == MAX_LENGTH);
                fragment.analyzeWord(isValid ? currentWord : null);
                break;
            default:
                char c = (char) event.getUnicodeChar();
                moveForward(c);
                break;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * Creates a new InputConnection for this view; wraps the original InputConnection to handle
     * input of letters and other special instructions such as BackSpace and Enter on a soft keyboard.
     * @param outAttrs EditorInfo object that receives metadata information about the text.
     * input needed to display the software keyboard.
     * @return a new LetterInputConnection object, wrapping the original InputConnection.
     */
    @Override
    public InputConnection onCreateInputConnection(@NonNull EditorInfo outAttrs) {
        return new LetterInputConnection(super.onCreateInputConnection(outAttrs), true);
    }

    /**
     * Sets the focus to the EditLetter view and makes it focusable and touchable.
     */
    void focus() {
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        if (previousLetter != null) {
            previousLetter.setFocusable(false);
            previousLetter.setFocusableInTouchMode(false);
        }
        if (nextLetter != null) {
            nextLetter.setFocusable(false);
            nextLetter.setFocusableInTouchMode(false);
        }
    }

    /**
     * Moves the focus to the previous letter if it exists. If the current letter is empty,
     * deletes the previous letter's text and moves the focus to it.
     */
    private void moveBack() {
        if (length() == 0 && previousLetter != null) {
            previousLetter.setText("");
            previousLetter.focus();
        } else if (previousLetter != null) {
            previousLetter.focus();
        }
    }


    /**
     * Moves the focus to the next letter and sets the given text on the next letter if it exists
     * and the given text is a letter.
     * @param text the text to set on the next letter
     */
    private void moveForward(char text) {
        if (nextLetter != null && Character.isLetter(text)) {
            if (length() == 1) nextLetter.setText(String.valueOf(text));
            nextLetter.focus();
        }
    }

    /**
     * Setter method for the previous letter.
     * @param previousLetter the previous letter.
     */
    public void setPreviousLetter(@NonNull EditLetter previousLetter) {
        this.previousLetter = previousLetter;
    }

    /**
     * Setter method for the next letter.
     * @param nextLetter the next letter.
     */

    public void setNextLetter(@NonNull EditLetter nextLetter) {
        this.nextLetter = nextLetter;
    }

    /**
     * Setter method for the WordleFragment instance in which the letter is located.
     * @param fragment the WordleFragment instance.
     */
    public void setFragment(WordleFragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Setter method for the current word in which the letter is contained.
     * @param currentWord the LinearWord instance.
     */
    public void setCurrentWord(LinearWord currentWord) {
        this.currentWord = currentWord;
    }

    /**
     * A custom implementation of the InputFilter interface which filters input text
     * to only allow alphabetic characters.
     */
    private static class AlphabeticInputFilter implements InputFilter {
        /**
         * Filters the input text to only allow alphabetic characters.
         * @param source the input text being typed by the user.
         * @param start the start index of the new text being added.
         * @param end the end index of the new text being added.
         * @param destination the text that the new text is being added to.
         * @param destinationStart the start index of the text that the new text is being added to.
         * @param destinationEnd the end index of the text that the new text is being added to.
         * @return the filtered text that only contains alphabetic characters, or null if all characters are valid.
         */
        @Override
        public CharSequence filter(CharSequence source, int start, int end,
                                   Spanned destination, int destinationStart, int destinationEnd) {
            StringBuilder builder = new StringBuilder();
            for (int i = start; i < end; i++) {
                char c = source.charAt(i);
                if (Character.isLetter(c)) {
                    builder.append(c);
                }
            }
            boolean allCharactersValid = (builder.length() == end - start);
            return allCharactersValid ? null : builder.toString();
        }
    }

    /**
     * A custom implementation of InputConnectionWrapper which handles letter input in a Wordle game.
     */
    private class LetterInputConnection extends InputConnectionWrapper {

        /**
         *
         * Creates a new LetterInputConnection object with the specified InputConnection and mutable flag.
         * @param target the InputConnection to wrap.
         * @param mutable whether the input text is mutable.
         */
        public LetterInputConnection(InputConnection target, boolean mutable) {
            super(target, mutable);
        }

        /**
         * Overrides the commitText() method to move the input cursor to the next EditLetter
         * when a letter is typed into the soft keyboard.
         * @param text the text to commit.
         * @param newCursorPosition the new cursor position.
         * @return true if the commit succeeded, false otherwise.
         */
        @Override
        public boolean commitText(CharSequence text, int newCursorPosition) {
            moveForward(text.charAt(0));
            return super.commitText(text, newCursorPosition);
        }

        /**
         * Overrides the deleteSurroundingText() method to move to the previous EditLetter
         * when BackSpace is pressed on a soft keyboard.
         * @param before the number of characters to delete before the cursor.
         * @param after the number of characters to delete after the cursor.
         * @return true if the deletion succeeded, false otherwise.
         */
        @Override
        public boolean deleteSurroundingText(int before, int after) {
            if (before == 1 && after == 0) {
                moveBack();
            }
            return super.deleteSurroundingText(before, after);
        }
    }
}
