/**
 * A class that evaluates the logic of the Wordle game.
 *
 * @author Cherrie Chang, Josephine Man
 * @version v1.0
 */

package edu.vassar.neuroscientists;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import edu.vassar.neuroscientists.model.word_games.wordle.Wordle;

class WordleLogicTest {

    /**
     * Tests evaluate() by first evaluating the FeedbackArray of the user's input to the expected array.
     * 0 = incorrect character, 1 = correct char but wrong position, 2 = correct char and correct position.
     * For example, if user's input is "clean" and the target word is "glove", the expected array is {0,2,1,0,0}
     */
    @Test
    void evaluateFeedbackTest() throws IOException {
        char resultV = 'V';
        char resultW = 'W';
        Wordle wordle = new Wordle("ebony");

        int[] array1 = {1, 1, 0, 2, 0};
        assertArrayEquals(array1, wordle.evaluate("BEANS").getFeedbackArray());
        assertEquals(resultV, wordle.evaluate("BEANS").getResult());

        wordle = new Wordle("train");
        int[] array2 = {2, 2, 2, 2, 2};
        assertArrayEquals(array2, wordle.evaluate("TRAIN").getFeedbackArray());
        assertEquals(resultW, wordle.evaluate("TRAIN").getResult());

        wordle = new Wordle("chair");
        int[] array3 = {0, 0, 0, 0, 0};
        assertArrayEquals(array3, wordle.evaluate("LOOPS").getFeedbackArray());
        assertEquals(resultV, wordle.evaluate("LOOPS").getResult());

        wordle = new Wordle("pleat");
        int[] array4 = {1, 0, 0, 2, 0};
        assertArrayEquals(array4, wordle.evaluate("TTTAA").getFeedbackArray());
        assertEquals(resultV, wordle.evaluate("TTTAA").getResult());

        wordle = new Wordle("ultra");
        int[] array5 = {0, 2, 1, 0, 0};
        assertArrayEquals(array5, wordle.evaluate("SLULL").getFeedbackArray());
        assertEquals(resultV, wordle.evaluate("SLULL").getResult());

        wordle = new Wordle("swill");
        int[] array6 = {1, 1, 1, 2, 1};
        assertArrayEquals(array6, wordle.evaluate("WILLS").getFeedbackArray());
        assertEquals(resultV, wordle.evaluate("WILLS").getResult());
    }

    /**
     * Tests comparisonArray() by examining if the resultArray of the user's input generates the expected array.
     */
    @Test
    void comparisonArrayTest() throws IOException {
        Wordle wordle = new Wordle("beans");
        int[] winArray = {2, 2, 2, 2, 2};
        int[] loseArray = {0, 0, 0, 0, 0};
        int[] validArray1 = {0, 1, 0, 2, 1};
        int[] validArray2 = {2, 2, 0, 1, 2};
        int[] resultArray1 = wordle.comparisonArray("BEANS");
        int[] resultArray2 = wordle.comparisonArray("CADNE");
        int[] resultArray3 = wordle.comparisonArray("BECAS");
        assertArrayEquals(winArray, resultArray1);
        assertFalse(Arrays.equals(loseArray, resultArray1));
        assertArrayEquals(validArray1, resultArray2);
        assertFalse(Arrays.equals(validArray2, resultArray2));
        assertArrayEquals(validArray2, resultArray3);
    }

    /**
     * Tests charCount() by examining if number of characters in each string matches the expected value.
     */
    @Test
    void charCountTest() throws IOException {
        Wordle wordle = new Wordle("PAUSE");
        String target = wordle.getTarget();
        char i = 'I';
        char s = 'S';
        char a = 'A';
        char p = 'P';
        char o = 'O';
        char l = 'L';
        char t = 't';
        int index4 = 4;
        int index2 = 2;
        int index0 = 0;
        assertEquals(1, wordle.charCount(s, target, index4));
        assertEquals(0, wordle.charCount(i, target, index4));
        assertEquals(1, wordle.charCount(s, target, index4));
        assertEquals(0, wordle.charCount(s, target, index2));
        assertEquals(1, wordle.charCount(a, target, index2));
        assertEquals(1, wordle.charCount(p, target, index0));
        assertEquals(0, wordle.charCount(s, target, index0));

        wordle = new Wordle("ATOLL");
        target = wordle.getTarget();
        assertEquals(1, wordle.charCount(o, target, index4));
        assertEquals(2, wordle.charCount(l, target, index4));
        assertEquals(0, wordle.charCount(t, target, index4));
    }
}
