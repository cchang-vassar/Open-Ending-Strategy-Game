/**
 * This class represents the word libraries used for a respective level of difficulty in each Wordle game
 */

import java.util.ArrayList;
import java.util.Arrays;

public class WordleLibrary {
    private final ArrayList<String> easy;
    private final ArrayList<String> med;
    private final ArrayList<String> hard;

    /**
     * Method that assigns word libraries to easy, medium, or hard level for use during a Wordle game
     */
    public WordleLibrary() {
        easy = new ArrayList<>(
                Arrays.asList(
                        "CLOTH", "TRAIN", "STORY", "METAL", "DRINK",
                        "CHEAT", "SMART", "SCARE", "PLANT", "TRASH"
                ));
        med = new ArrayList<>(
                Arrays.asList(
                        "LIGHT", "ULTRA", "SUGAR", "ROBIN", "CRIMP",
                        "PAUSE", "PLEAT", "COULD", "ROBOT", "SHIRE"
                ));
        hard = new ArrayList<>(
                Arrays.asList(
                        "TRICE", "KNOLL", "SMITE", "TACIT", "ATOLL",
                        "PINEY", "TROPE", "SWILL", "FLOUT", "INTER"
                ));
    }

    /**
     * Gets the word library for an easy round of Wordle
     * @return easy, an ArrayList of strings for the easy round
     */
    public ArrayList<String> getEasy() {
        return easy;
    }
    /**
     * Gets the word library for a medium round of Wordle
     * @return med, an ArrayList of strings for the medium round
     */
    public ArrayList<String> getMed() {
        return med;
    }

    /**
     * Gets the word library for a hard round of Wordle
     * @return hard, an ArrayList of strings for the hard round
     */
    public ArrayList<String> getHard() {
        return hard;
    }

}
