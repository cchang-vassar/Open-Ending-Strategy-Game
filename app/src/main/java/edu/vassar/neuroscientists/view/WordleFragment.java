/**
 * This class represents a fragment for the Wordle game view.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.util.Objects;

import edu.vassar.neuroscientists.R;
import edu.vassar.neuroscientists.databinding.FragmentWordleBinding;
import edu.vassar.neuroscientists.model.word_games.wordle.Wordle;
import edu.vassar.neuroscientists.model.word_games.wordle.WordleResult;

public class WordleFragment extends Fragment implements IWordleView {

    /**
     * View binding object for this fragment
     */
    FragmentWordleBinding binding;

    /**
     * Listener object for callbacks to the parent activity
     */
    Listener listener;

    /**
     * Constructor for the WordleFragment class.
     * @param listener the listener object for callbacks to the parent activity
     */
    public WordleFragment(Listener listener) {
        this.listener = listener;
    }

    /**
     * Inflates the view for this fragment.
     * @param inflater the LayoutInflater object to inflate the view.
     * @param container the ViewGroup object containing the view.
     * @param savedInstanceState the Bundle object containing saved instance state information.
     * @return the inflated view for this fragment.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWordleBinding.inflate(inflater);
        return binding.getRoot();
    }

    /**
     * Initializes the view and sets up Wordle logic after the view has been created.
     * @param view the view object for this fragment.
     * @param savedInstanceState the Bundle object containing saved instance state information.
     */
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        LinearLayout grid = binding.wordleLayout;
        int gridHeight = grid.getChildCount();
        for (int i = 0; i < gridHeight; i++) {
            LinearWord word = (LinearWord) grid.getChildAt(i);
            int wordLength = word.getChildCount();
            if (i < gridHeight - 1) word.setNextWord((LinearWord) grid.getChildAt(i + 1));
            if (i == 0) word.Select();
            for (int j = 0; j < wordLength; j++) {
                EditLetter letter = (EditLetter) word.getChildAt(j);
                letter.setFragment(this);
                letter.setCurrentWord(word);

                if (j > 0) letter.setPreviousLetter((EditLetter) word.getChildAt(j - 1));
                if (j < wordLength - 1) letter.setNextLetter((EditLetter) word.getChildAt(j + 1));
                if (i == 0 && j == 0) letter.focus();
            }
        }

        binding.wordleTryAgainButton.setOnClickListener(v -> {
            try {
                listener.onTryAgain();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        binding.wordleQuitButton.setOnClickListener(v -> listener.onQuit());
    }

    /**
     * Analyzes a word entered by the user and updates the view accordingly.
     * @param word the LinearWord object representing the word entered by the user.
     */
    public void analyzeWord(@Nullable LinearWord word) {
        if (word == null) {
            popup(R.string.wordle_short);
        } else {
            StringBuilder answer = new StringBuilder();
            for (int i = 0; i < word.getChildCount(); i++) {
                EditLetter letter = (EditLetter) word.getChildAt(i);
                answer.append(Objects.requireNonNull(letter.getText()));
            }
            Wordle.Feedback feedback = listener.onWordleAnswerDetected(answer.toString());
            if (!(feedback.getResult() == 'I')) {
                colorFeedback(feedback.getFeedbackArray(), word);
            }
            switch (feedback.getResult()) {
                case 'W':
                    popup(R.string.wordle_win);
                    word.Deselect();
                    showTryAgainButton();
                    showQuitButton();
                    listener.cancelTimer();
                    listener.saveWordleResult(WordleResult.WIN);
                    break;
                case 'I':
                    popup(R.string.wordle_invalid);
                    break;
                case 'V':
                    if (word.getNextWord() == null) {  // loss
                        listener.saveWordleResult(WordleResult.LOSS);
                        listener.onLost(this);
                        showTryAgainButton();
                        showQuitButton();
                    } else {
                        word.getNextWord().Select();
                    }
                    word.Deselect();
                    break;

            }
        }
    }

    /**
     * This method sets the background color and text color for each EditLetter in the
     * LinearLayout "word" according to the corresponding color value specified in the
     * "colors" array. The color values are defined as follows:
     * 0 - gray
     * 1 - yellow
     * 2 - green
     * @param colors an array of integer values representing the color for each EditLetter in the LinearLayout "word."
     * @param word the LinearLayout containing the EditLetters to be colored.
     */
    private void colorFeedback(int[] colors, LinearWord word) {
        for (int i = 0; i < word.getChildCount(); i++) {
            EditLetter letter = (EditLetter) word.getChildAt(i);
            switch (colors[i]) {
                case 0:
                    letter.setBackgroundColor(getResources().getColor(R.color.wordle_gray, null));
                    break;
                case 1:
                    letter.setBackgroundColor(getResources().getColor(R.color.wordle_yellow, null));
                    break;
                case 2:
                    letter.setBackgroundColor(getResources().getColor(R.color.wordle_green, null));
                    break;
            }
            letter.setTextColor(getResources().getColor(R.color.white, null));
        }
    }

    /**
     * Displays a pop-up window for messages.
     * @param message the resource ID identifier for the string resource.
     */
    private void popup(int message) {
        popup(getResources().getString(message));
    }

    /**
     * Displays a pop-up window for messages.
     * @param message the message as a string.
     */
    public void popup(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays a countdown timer with the remaining time in mins and secs
     * @param min the mins remaining as a long
     * @param s the secs remaining as a long
     */
    public void countdown(long min, long s){
        if (getContext() != null) {
            binding.timerDisplay.setText(getResources().getString(R.string.wordle_timer, min, s));
        }
    }

    /**
     * Disables user input for the wordle game by setting all game cells to disabled
     */
    public void disableInput() {
        for (int i = 0; i < binding.wordleLayout.getChildCount(); i++) {
            LinearLayout row = (LinearLayout) binding.wordleLayout.getChildAt(i);
            for (int j = 0; j < row.getChildCount(); j++) {
                row.getChildAt(j).setEnabled(false);
            }
        }
    }

    /**
     * Shows the try again button to allow the user to play the game again
     */
    public void showTryAgainButton() {
        binding.wordleTryAgainButton.setVisibility(View.VISIBLE);
    }

    /**
     * Shows the quit button to allow the user to quit the game
     */
    public void showQuitButton() {
        binding.wordleQuitButton.setVisibility(View.VISIBLE);
    }

    public void createTargetWordPopup(String targetWord) {
        popup(getResources().getString(R.string.wordle_lose, targetWord));
    }
}
