package edu.vassar.neuroscientists.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import edu.vassar.neuroscientists.databinding.FragmentTutorialBinding;


public class TutorialFragment extends DialogFragment implements ITutorialView {

    /**
     * View binding object for this fragment,
     */
    FragmentTutorialBinding binding;

    /**
     * Listener object for callbacks to the parent activity,
     */
    ITutorialView.Listener listener;

    /**
     * Constructor for the TutorialFragment class.
     * @param listener the listener object for callbacks to the parent activity,
     */
    public TutorialFragment(@NonNull ITutorialView.Listener listener) {this.listener = listener;}


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
        binding = FragmentTutorialBinding.inflate(inflater);
        return binding.getRoot();
    }

    /**
     * Initializes the view, and sets up the click listener for the "OK" button
     * @param view the root of the fragment
     * @param savedInstanceState the Bundle object containing saved instance state information.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.tutorialOkButton.setOnClickListener(view1 -> {
            // method to handle button click
            listener.onOkButtonClicked(); // notify controller that start button has been clicked
        });
    }
}

