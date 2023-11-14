/**
 * The Controller class is responsible for controlling the interactions between the user interface and the Model class.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists.controller;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Pair;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import edu.vassar.neuroscientists.R;
import edu.vassar.neuroscientists.model.Model;
import edu.vassar.neuroscientists.model.narrative.NarrativeProgress;
import edu.vassar.neuroscientists.model.word_games.wordle.Wordle;
import edu.vassar.neuroscientists.model.word_games.wordle.WordleResult;
import edu.vassar.neuroscientists.view.EndingFragment;
import edu.vassar.neuroscientists.view.IEndingView;
import edu.vassar.neuroscientists.view.IMainView;
import edu.vassar.neuroscientists.view.NarrativeFragment;
import edu.vassar.neuroscientists.view.INarrativeView;
import edu.vassar.neuroscientists.view.IWordleView;
import edu.vassar.neuroscientists.view.MainView;
import edu.vassar.neuroscientists.view.TutorialFragment;
import edu.vassar.neuroscientists.view.ITutorialView;
import edu.vassar.neuroscientists.view.WordleFragment;

public class Controller extends AppCompatActivity implements IWordleView.Listener, INarrativeView.Listener, ITutorialView.Listener, IEndingView.Listener {

    /**
     * The context of the app.
     */
    Context context;

    /**
     * The main View object.
     */
    MainView view;

    /**
     * The main Model object.
     */
    Model model;

    /**
     * Timer for counting down a Wordle game.
     */
    CountDownTimer timer;

    /**
     * Called when the activity is starting. The method initializes the MainView and Model instances,
     * then starts a new Wordle game.
     * @param savedInstanceState The saved state of the activity (null if no previous instantiation exists).
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        view = new MainView(this);
        setContentView(view.getRootView());
        model = new Model(context);
        startChapterOne();
    }

    /**
     * Handles response to each user click during a narrative.
     * If narrative runs out of dialogue, determines the next fragment to display.
     * @param narrativeFragment The narrative fragment to be displayed.
     * @param dialogueId The ID of the dialogue being displayed through the narrative fragment.
     */
    @Override
    public void onNarrativeContainerClicked(NarrativeFragment narrativeFragment, NarrativeProgress dialogueId) {
        Pair<String, String> chat = model.getCurrentChat(dialogueId);
        if (chat != null) {
            narrativeFragment.displayChat(chat.first, chat.second);
        } else {
            switch (model.advanceNarrative(dialogueId)) {
                case TUTORIAL:
                    view.displayFragment(new TutorialFragment(this), false, "startWordleTutorial");
                    break;
                case SUCCESS_ENDING:
                    view.displayFragment(new EndingFragment(true, this), false, "endGame");
                    break;
                case FAILURE_ENDING:
                    view.displayFragment(new EndingFragment(false, this), false, "endGame");
                    break;
            }
        }
    }


    /**
     * Called when the user has pressed the Start button on the tutorial fragment,
     * move onto Wordle fragment.
     */
    public void onOkButtonClicked() {
        // move to Wordle game screen
        try {
            startWordle();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Starts a new Wordle game, displaying it on the screen.
     */
    private void startWordle() throws IOException {
        model.createWordle();
        WordleFragment wordleFragment = new WordleFragment(this);
        view.displayFragment(wordleFragment, false, "WordleFragment");
        long timeLimit = model.getGame().getTime();
        timer = new CountDownTimer(timeLimit * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long secsUntilFinished = millisUntilFinished / 1000;
                int minsUntilFinished = (int) secsUntilFinished / 60;
                wordleFragment.countdown(minsUntilFinished, secsUntilFinished % 60);
            }

            @Override
            public void onFinish() {
                wordleFragment.countdown(0, 0);
                wordleFragment.disableInput();
                wordleFragment.popup(getResources().getString(R.string.wordle_lose, model.getGame().getTarget()));
                wordleFragment.showTryAgainButton();
                wordleFragment.showQuitButton();
                if (!model.getWordleWon()) {
                    model.incrementWordleTries();
                }
            }
        }.start();
    }

    /**
     * Cancels the timer.
     */
    public void cancelTimer() {
        timer.cancel();
    }

    /**
     * Starts the first chapter of the game by creating a narrative fragment
     * to display the first dialogue in the game.
     */
    private void startChapterOne() {
        view.displayFragment(new NarrativeFragment(this, NarrativeProgress.CONFERENCE), true, "NarrativeFragment");
    }

    /**
     * Called when a word is submitted by the user in the WordleFragment. The method calls the
     * evaluateInput method of the Model instance to evaluate the input and return feedback.
     * @param answer The user's input as a string.
     * @return The feedback generated by the model as a Wordle.Feedback instance.
     */
    public Wordle.Feedback onWordleAnswerDetected(String answer) {
        return model.evaluateInput(answer);
    }

    /**
     * Called when the user loses the game. This method calls the getTarget method
     * of the Model instance to get the target word, which is then shown to the user.
     */
    public void onLost(IWordleView wordleView) {
        cancelTimer();
        wordleView.createTargetWordPopup(model.getGame().getTarget());
    }

    /**
     * Called when the "Try Again" button is clicked in the Wordle game UI,
     * which restarts the Wordle game with a new target word and difficulty.
     */
    public void onTryAgain() throws IOException {
        startWordle();
    }

    /**
     * Called when the "Quit" button is clicked in the Wordle game UI,
     * which moves the game onto the next narrative chunk.
     */
    public void onQuit() {
        model.processWordleResults();
        view.displayFragment(new NarrativeFragment(this, NarrativeProgress.WORDLE_REVIEW), false, "Wordle Narrative Feedback");
    }

    /**
     * Calls model to save the Wordle result, which changes parts of the subsequent narrative
     * @param result The result of the Wordle, which is whether the user won a round,
     *               and how many tries it took.
     */
    public void saveWordleResult(WordleResult result) {
        model.saveWordleResult(result);
    }

    public void onStartOver() {
        model = new Model(context);  // reset state
        startChapterOne();
    }
}
