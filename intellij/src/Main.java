/**
 * class Main is the main class of the app.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */
public class Main {

    /**
     * Main method of the app, instantiates a new View, Model, and Controller object.
     * @param args The array of Strings representing the code to be run for the entire app to work.
     */
    public static void main(String[] args){
        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);

        controller.run();
    }
}