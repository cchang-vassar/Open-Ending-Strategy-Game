/**
 * A class designed to test the interactions of the Wordle game
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasFocus;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isFocusable;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import edu.vassar.neuroscientists.controller.Controller;
import edu.vassar.neuroscientists.view.EditLetter;

@RunWith(AndroidJUnit4.class)
public class WordleInteractionTest {

    @org.junit.Rule
    public ActivityScenarioRule<Controller> activityRule = new ActivityScenarioRule<>(Controller.class);


    /**
     * Test getting past intro
     */
    @Test
    public void testIntro() {
        for (int i = 0; i < 9; i++) {
            onView(withId(R.id.constraint_layout)).perform(click());
        }
        onView(isAssignableFrom(Button.class)).check(matches(withId(R.id.tutorial_ok_button)));
    }

    /**
     * Test for finishing the Wordle tutorial and pressing the Ok button, which leads to the Wordle fragment
     */
    @Test
    public void testTutorial(){
        testIntro();
        onView(withId(R.id.tutorial_ok_button)).perform(click());
        onView(allOf(
                isDescendantOfA(withId(R.id.wordle_layout)),
                isAssignableFrom(EditText.class),
                isFocusable()
        )).check(matches(withText("")));
    }


    /**
     * Tests for finishing the game and pressing the TRY AGAIN button.
     */
    @Test
    public void testTryAgain() {
        testTutorial();

        String[] testWord = new String[] {"N", "E", "R", "D", "Y"};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                ViewInteraction focusedEditText = onView(allOf(
                        isDescendantOfA(withId(R.id.wordle_layout)),
                        isAssignableFrom(EditText.class),
                        hasFocus(),
                        isFocusable()
                ));

                if (j == 4) {
                    focusedEditText.perform(typeTextIntoFocusedView(testWord[j]), pressImeActionButton());
                } else {
                    focusedEditText.perform(typeTextIntoFocusedView(testWord[j]));
                }
            }
        }
        onView(withId(R.id.wordle_try_again_button)).check(matches(allOf(
                isDisplayed(),
                isAssignableFrom(Button.class),
                withText("TRY AGAIN")
        )));

        onView(withId((R.id.wordle_try_again_button))).perform(click());
        onView(allOf(
                isDescendantOfA(withId(R.id.wordle_layout)),
                isAssignableFrom(EditText.class),
                hasFocus(),
                isFocusable()
        )).check(matches(withText("")));
    }

    /**
     * Tests for inputting an word of less than 5 letters.
     */
    @Test
    public void testShortInput() {
        testTutorial();

        for (int j = 0; j < 4; j++) {
            ViewInteraction focusedEditText = onView(allOf(
                    isDescendantOfA(withId(R.id.wordle_layout)),
                    isAssignableFrom(EditText.class),
                    hasFocus(),
                    isFocusable()
            ));

            if (j == 3) {
                focusedEditText.perform(typeTextIntoFocusedView("X"), pressImeActionButton());
            } else {
                focusedEditText.perform(typeTextIntoFocusedView("X"));
            }
        }
        onView(allOf(hasFocus(), isAssignableFrom(EditLetter.class))).check(matches(withText("")));
    }

    /**
     * Test for pressing I QUIT after playing Wordle
     */
    @Test
    public void testQuit(){
        testTutorial();

        String[] testWord = new String[] {"N", "E", "R", "D", "Y"};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                ViewInteraction focusedEditText = onView(allOf(
                        isDescendantOfA(withId(R.id.wordle_layout)),
                        isAssignableFrom(EditText.class),
                        hasFocus(),
                        isFocusable()
                ));

                if (j == 4) {
                    focusedEditText.perform(typeTextIntoFocusedView(testWord[j]), pressImeActionButton());
                } else {
                    focusedEditText.perform(typeTextIntoFocusedView(testWord[j]));
                }
            }
        }
        onView(withId(R.id.wordle_quit_button)).check(matches(allOf(
                isDisplayed(),
                isAssignableFrom(Button.class),
                withText("I QUIT")
        )));
        onView(withId((R.id.wordle_quit_button))).perform(click());
        onView(allOf(isAssignableFrom(ImageView.class),
                not(isDescendantOfA(withId(R.id.nemur))),
                not(isDescendantOfA(withId(R.id.strauss))))).check(matches(isDisplayed()));
    }

    /**
     * Test for pressing START OVER at the narrative's ending
     */
    @Test
    public void testStartOver(){
        testQuit();
        for (int i = 0; i < 3; i++) {
            onView(withId(R.id.constraint_layout)).perform(click());
        }
        onView(withId(R.id.start_over_button)).check(matches(allOf(
                isDisplayed(),
                isAssignableFrom(Button.class),
                withText("START OVER")
        )));
        onView(withId(R.id.start_over_button)).perform(click());
        onView(allOf(isAssignableFrom(ImageView.class),
                not(isDescendantOfA(withId(R.id.nemur))),
                not(isDescendantOfA(withId(R.id.strauss))))).check(matches(isDisplayed()));
    }
}




