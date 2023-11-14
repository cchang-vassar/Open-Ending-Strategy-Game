/**
 * The NarrativeFragment class is a fragment that displays narratives.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import edu.vassar.neuroscientists.R;
import edu.vassar.neuroscientists.model.narrative.NarrativeProgress;
import edu.vassar.neuroscientists.databinding.FragmentNarrativeBinding;

public class NarrativeFragment extends Fragment implements INarrativeView {
    /**
     * View binding object for this fragment
     */
    FragmentNarrativeBinding binding;

    /**
     * Listener object for callbacks to the parent activity
     */
    Listener listener;

    /**
     * The progress in the narrative of the current fragment.
     */
    NarrativeProgress dialogueId;

    /**
     * Constructor for the NarrativeFragment class.
     * @param listener the listener object for callbacks to the parent activity
     */
    public NarrativeFragment(INarrativeView.Listener listener, NarrativeProgress dialogueId) {
        this.listener = listener;
        this.dialogueId = dialogueId;
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
        binding = FragmentNarrativeBinding.inflate(inflater);
        return binding.getRoot();
    }

    /**
     * Called when the narrative fragment is created
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        ConstraintLayout narrativeContainer = binding.constraintLayout;
        narrativeContainer.setOnClickListener(v -> {
            listener.onNarrativeContainerClicked(this, dialogueId);
        });
    }

    /**
     * Displays a single chat, i.e. a Pair containing the name of the speaker and the message.
     *
     * @param name The name of the speaker.
     * @param speech The speaker's message
     */
    public void displayChat(String name, String speech) {
        ConstraintLayout constraintLayout;
        ConstraintLayout otherLayout;
        ImageView currentBubble;
        TextView currentText;
        if (name.equals("nemur")) {
            constraintLayout = binding.nemur;
            otherLayout = binding.strauss;
            currentBubble = constraintLayout.findViewById(R.id.nemur_bubble);
            currentText = constraintLayout.findViewById(R.id.nemur_text);
            ImageView straussBubble = otherLayout.findViewById(R.id.strauss_bubble);
            TextView straussText = otherLayout.findViewById(R.id.strauss_text);
            if (straussBubble.getVisibility() == View.VISIBLE) {
                straussBubble.setVisibility(View.INVISIBLE);
                straussText.setVisibility(View.INVISIBLE);
            }
        }
        else {
            constraintLayout = binding.strauss;
            otherLayout = binding.nemur;
            currentBubble = constraintLayout.findViewById(R.id.strauss_bubble);
            currentText = constraintLayout.findViewById(R.id.strauss_text);
            ImageView nemurBubble = otherLayout.findViewById(R.id.nemur_bubble);
            TextView nemurText = otherLayout.findViewById(R.id.nemur_text);
            if (nemurBubble.getVisibility() == View.VISIBLE) {
                nemurBubble.setVisibility(View.INVISIBLE);
                nemurText.setVisibility(View.INVISIBLE);
            }
        }
        currentBubble.setVisibility(View.VISIBLE);
        currentBubble.bringToFront();
        currentText.setVisibility(View.VISIBLE);
        currentText.bringToFront();
        currentText.setText(speech);
    }
}
