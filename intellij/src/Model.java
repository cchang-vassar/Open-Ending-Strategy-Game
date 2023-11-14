/**
 * class Model represents the main Model of the app
 * that handles the whole app's game logic
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */
public class Model {
    /**
     * The Wordle class containing game logic for a single Wordle game.
     */
    private Wordle game;

    /**
     * Instantiates a Model object.
     */
    public Model() {}
    /**
     * Instantiates a new Wordle game.
     *
     * @return A new Wordle object.
     */
    public Wordle createWordle() {
        game = new Wordle();
        return game;
    }

    /**
     * Evaluates the player's input and returns a Feedback object.
     *
     * @param input The player's word input
     * @return A Feedback object containing the feedback message to be shown on screen,
     * whether the input is valid, and whether the input matches the target word.
     */
    public Wordle.Feedback evaluateInput(String input) {
        return game.evaluate(input);
    }
}
