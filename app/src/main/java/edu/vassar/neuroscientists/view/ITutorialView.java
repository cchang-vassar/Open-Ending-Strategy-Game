/**
 * Interface ITutorialView defines functionality of the TutorialFragment.
 */

package edu.vassar.neuroscientists.view;

/**
 * This interface defines the methods that the view component of the tutorial feature should implement.
 */
public interface ITutorialView {
    /**
     * The listener interface for finishing the tutorial
     */
    interface Listener {

        /**
         * Called when the "OK" button is clicked
         */
        void onOkButtonClicked();
    }
}



