/**
 * class TimeController represents a timer that counts down by seconds
 * (functions as a type of controller).
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

import java.util.LinkedList;

public class TimeController implements Runnable {
    /**
     * The time limit the timer thread counts down from.
     */
    private final int timeLimit;

    /**
     * A list of Threads the timer interrupts when it runs out.
     */
    private final LinkedList<Thread> interruptThreads;

    /**
     * The app's main View object, so that the timer's count can be printed on screen.
     */
    private final View view;

    /**
     * Adds input Thread to list of Threads the timer interrupts when it dies.
     *
     * @param ToInterrupt The Thread to be added to the timer's list of Threads.
     */
    public void addToInterruptThreads(Thread ToInterrupt) {
        this.interruptThreads.add(ToInterrupt);
    }

    /**
     * Constructor for class TimeController.
     *
     * @param timeLimit The time limit the timer counts down from.
     * @param view The app's main View object.
     */
    public TimeController(int timeLimit, View view) {
        this.timeLimit = timeLimit;
        this.interruptThreads = new LinkedList<>();
        this.view = view;
    }

    /**
     * Calls View to print the time remaining after at certain time points.
     */
    @Override
    public void run() {
        for (int i = timeLimit; i >= 0; i--) {
            if (printTime(i)) {
                view.print(String.format("Time left: %ds", i));
            }
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                return;
            }
        }
        view.print("\nTime's up!");
        for (Thread interruptThread : interruptThreads) {
            interruptThread.interrupt();
        }
    }


    /**
     * Checks if the time left should be printed at the current time point:
     * every minute when there is more than 3 minutes left;
     * every 30s when there is more than 30 seconds left;
     * and every 1s when there is less than 30 seconds left.
     *
     * @param i Represents the time left in seconds.
     * @return Whether the time left should be printed.
     */
    private boolean printTime(int i) {
        if (i > 180) {
            return i % 60 == 0;
        }
        else if (i > 30){
            return i % 30 == 0;
        }
        else {
            return true;
        }
    }
}
