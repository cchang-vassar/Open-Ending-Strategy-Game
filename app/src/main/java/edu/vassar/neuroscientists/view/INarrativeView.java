/**
 * Interface INarrativeView defines logic for a NarrativeFragment.
 */

package edu.vassar.neuroscientists.view;

import edu.vassar.neuroscientists.model.narrative.NarrativeProgress;

public interface INarrativeView {

    /**
     * Defines requirements for a Listener for a Narrative Fragment.
     */
    interface Listener{

        /**
         * Called when the user clicks on the NarrativeFragment.
         * @param narrativeFragment The NarrativeFragment being clicked.
         * @param dialogueId The ID of the current narrative progress.
         */
        void onNarrativeContainerClicked(NarrativeFragment narrativeFragment, NarrativeProgress dialogueId);
    }
}
