package edu.vassar.neuroscientists.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.vassar.neuroscientists.R;
import edu.vassar.neuroscientists.databinding.FragmentEndingBinding;

public class EndingFragment extends Fragment implements IEndingView {

     /**
     * View binding object for this fragment
     */
    FragmentEndingBinding binding;

    /**
     * Flip for whether the user successfully completed the Wordle challenge
     * to lead to them being recruited.
     */
    boolean isSuccess;

    /**
     * Listener for fragment, i.e. the Controller.
     */
    Listener listener;

    public EndingFragment(boolean isSuccess, IEndingView.Listener listener) {
        this.isSuccess = isSuccess;
        this.listener = listener;
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
        binding = FragmentEndingBinding.inflate(inflater);
        return binding.getRoot();
    }

    /**
     * Called when the fragment is created.
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (isSuccess) {
            binding.endingText.setText(R.string.success_end);

        } else {
            binding.endingText.setText(R.string.failure_end);
            binding.startOverButton.setVisibility(View.VISIBLE);
        }

        binding.startOverButton.setOnClickListener(v -> listener.onStartOver());
    }
}
