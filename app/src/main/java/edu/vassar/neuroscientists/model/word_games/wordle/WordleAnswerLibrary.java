/**
 * Class WordleAnswerLibrary represents the word library used for
 * generating the target answers in a Wordle game.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists.model.word_games.wordle;

import android.content.Context;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class WordleAnswerLibrary extends WordLibrary {

    /**
     * The set of target answers for Wordle games at easy difficulty.
     */
    private final Set<String> easyTargets = new HashSet<>();

    /**
     * The set of target answers for Wordle games at easy difficulty.
     */
    private final Set<String> mediumTargets = new HashSet<>();

    /**
     * The set of target answers for Wordle games at easy difficulty.
     */
    private final Set<String> hardTargets = new HashSet<>();

    /**
     * Constructor for class WordleAnswerLibrary.
     * @param context The context of the app.
     * @param fileId The ID of the text library in Assets folder.
     */
    public WordleAnswerLibrary(Context context, String fileId) throws IOException {
        super(context, fileId);

        int easyTargetsBorder = (int) (mainLib.size() * (1.0/6));
        int mediumTargetsBorder = (int) (mainLib.size() * (1.0/2));
        int hardTargetsBorder = mainLib.size();

        for (int i = 0; i < (easyTargetsBorder); i++) {
            easyTargets.add(mainLib.get(i));
        }
        for (int i = easyTargetsBorder; i < (mediumTargetsBorder); i++) {
            mediumTargets.add(mainLib.get(i));
        }
        for (int i = mediumTargetsBorder; i < hardTargetsBorder; i++) {
            hardTargets.add(mainLib.get(i));
        }
    }


    /**
     * Gets a random easy Wordle answer from the easy library.
     * @return A String representing an easy Wordle answer.
     */
    public String getTarget(int difficulty) {
        Set<String> subLibrary = new HashSet<>();
        switch (difficulty) {
            case 0:
                subLibrary = easyTargets;
                break;
            case 1:
                subLibrary = mediumTargets;
                break;
            case 2:
                subLibrary = hardTargets;
                break;
        }
        int randomIndex = (int) (Math.random() * subLibrary.size());
        Iterator<String> iterator = subLibrary.iterator();
        int currentIndex = 0;
        String target = null;
        while (iterator.hasNext()) {
            target = iterator.next();
            if (currentIndex == randomIndex) {
                return target;
            }
            currentIndex++;
        }
        return target;
    }
}
