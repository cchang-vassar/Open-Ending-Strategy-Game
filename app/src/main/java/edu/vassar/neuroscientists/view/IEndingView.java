/**
 * Interface for the EndingFragment.
 */

package edu.vassar.neuroscientists.view;

public interface IEndingView {

    /**
     * Defines requirements for a Listener for the EndingFragment.
     */
    interface Listener {

        /**
         * Called when the user starts the game all over,
         * only an option if they fail to be recruited.
         */
        void onStartOver();
    }
}
