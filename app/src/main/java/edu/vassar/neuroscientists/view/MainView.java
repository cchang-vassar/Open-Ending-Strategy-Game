/**
 * The class provides a view representation of the main activity of the application.
 */

package edu.vassar.neuroscientists.view;

import android.util.Log;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import edu.vassar.neuroscientists.databinding.MainBinding;

public class MainView {
    /**
     * A fragment manager for fragment transactions.
     */
    FragmentManager fragmentManager;

    /**
     * A binding for the main layout
     */
    MainBinding binding;

    /**
     * Initializes the fragment manager and inflates the layout using MainBinding.
     * @param activity The fragment activity associated with this view.
     */
    public MainView(FragmentActivity activity) {
        fragmentManager = activity.getSupportFragmentManager();
        binding = MainBinding.inflate(activity.getLayoutInflater());
    }

    /**
     * Returns the root view associated with the MainView.
     * @return the root view
     */
    public View getRootView() {
        return binding.getRoot();
    }

    /**
     * Displays a specified fragment in the main activity.
     * @param fragment The fragment to be displayed.
     * @param reversible Enables or disables the back button.
     * @param name The name of the transaction.
     */
    public void displayFragment(Fragment fragment, boolean reversible, String name){
        FragmentTransaction fragmentTransaction = this.fragmentManager.beginTransaction();

        fragmentTransaction.replace(this.binding.fragmentContainer.getId(), fragment);

        if (reversible) fragmentTransaction.addToBackStack(name);
        else fragmentManager.popBackStack();

        fragmentTransaction.commit();
    }
    
}
