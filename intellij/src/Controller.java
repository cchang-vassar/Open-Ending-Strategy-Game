/**
 * class Controller represents the main controller of the app
 * that handles how the Model logic and View logic interact with each other to run the app.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

import java.io.IOException;

public class Controller {
    /**
     * The View object handling the app's user interface.
     */
    private final View view;

    /**
     * The Model object handling the app's game logic.
     */
    private final Model model;

    /**
     * Instantiates a Controller object.
     *
     * @param view The View object handling the app's user interface.
     * @param model The Model object handling the app's game logic.
     */
    Controller (View view, Model model) {
        this.view = view;
        this.model = model;
    }

    /**
     * Runs a single Wordle game:
     * displays game settings, starts the timer and runs the Wordle game
     * while the timer is running and the target word has not been guessed.
     */
    public void runWordle() throws InterruptedException {
        Wordle game = model.createWordle();
        view.displaySettings(game.getDifficulty(), game.getTime());
        view.print(game.getTutorial()); // should change later to not print every round
        // lambda function instantiating a Wordle Thread that handles the single Wordle game
        // (so it can get interrupted later)
        Thread wordleThread = new Thread(() -> {
            try {
                loop: while (true) {
                    String input = view.getInput();
                    if (input == null) { // timer ran out while player typing
                        break;
                    }
                    Wordle.Feedback feedback = model.evaluateInput(input);
                    view.print(feedback.getMessage());
                    switch (feedback.getResult()) {
                        case 'I': // invalid input case
                            break;
                        case 'V': // valid input
                            game.increaseCurrentTry();
                            break;
                        case 'W': // win case
                        case 'L': // lose case - all 6 tries used up
                            break loop;
                    }
                }
            }
            // when timer interrupts thread
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // instantiate TimeController object
        TimeController timer = new TimeController(game.getTime(), view);
        // add created Wordle Thread to list of Threads timer Thread will interrupt when it dies
        timer.addToInterruptThreads(wordleThread);
        // instantiate TimeController as a Thread to run it
        Thread timerThread = new Thread(timer);
        wordleThread.start();
        timerThread.start();
        // blocks current Thread until the Wordle Thread dies
        wordleThread.join();
        // because the next line in current Thread is timerThread.interrupt(),
        // this ensures the timerThread doesn't prematurely die, which interrupts the Wordle Thread
        // so Wordle Thread continues to run until it dies,
        // or the timer Thread runs out of time and interrupts the Wordle Thread via its run() method
        // and then the timer Thread dies as well
        timerThread.interrupt();
        // so the above line of code is only needed to handle the case where the player wins
        // or loses by using up all 6 tries, in which case the timer Thread needs to be killed prematurely
        // to start the next game
    }

    /**
     * Continues to run rounds of Wordle games
     * as long as the player agrees to keep playing
     */
    public void run() {
        try {
            do {
                runWordle();
            } while (view.askContinue());
        }
        catch (InterruptedException ignored) {
        }
    }
}