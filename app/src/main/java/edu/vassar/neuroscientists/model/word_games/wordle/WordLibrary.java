/**
 * WordLibrary class is an abstract class specifying the functionality of a library handling
 * words involved in a Wordle.
 */

package edu.vassar.neuroscientists.model.word_games.wordle;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class WordLibrary {

    /**
     * The library of words.
     */
    List<String> mainLib = new ArrayList<>();

    /**
     * Constructor for WordLibrary class. Reads from a given text file.
     * @param context The app's context.
     * @param fileId The ID of the text file to be read.
     * @throws IOException
     */
    public WordLibrary(Context context, String fileId) throws IOException {
        InputStream inputStream = context.getAssets().open(fileId);
        Scanner scanner = new Scanner(inputStream);
        while(scanner.hasNextLine()) {
            this.mainLib.add(scanner.nextLine().toUpperCase());
        }
    }

}
