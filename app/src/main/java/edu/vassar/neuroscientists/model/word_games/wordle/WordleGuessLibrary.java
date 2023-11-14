/**
 * Class WordleGuessLibrary represents the word library used for
 * validating user guesses against in the Wordle games.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists.model.word_games.wordle;

import android.content.Context;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WordleGuessLibrary extends WordLibrary {

    /**
     * The library containing the valid guess words.
     */
    Set<String> mainLibSet = new HashSet<>(mainLib);

    /**
     * Constructor for class WordleGuessLibrary.
     * @param context The context of the app.
     * @param fileId The ID of the text library from Assets folder.
     * @throws IOException
     */
    public WordleGuessLibrary(Context context, String fileId) throws IOException {
        super(context, fileId);
    }
}
