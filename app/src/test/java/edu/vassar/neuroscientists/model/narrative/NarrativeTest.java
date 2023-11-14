package edu.vassar.neuroscientists.model.narrative;

import static org.junit.jupiter.api.Assertions.*;

import android.util.Pair;

import org.junit.jupiter.api.Test;

class NarrativeTest {

    /**
     * Test for playing the conference dialogue
     */
    @Test
    void playConferenceDialogue() {
        Narrative narrative = new Narrative();
        Pair<String, String> chat;
        Pair<String, String> dialogue1 = new Pair<>("nemur", "A win within 2 tries...\nNot bad for a newbie.");
        Pair<String, String> dialogue2 = new Pair<>("strauss", "Well, he IS an aspiring\npsychologist...");
        Pair<String, String> dialogue3 = new Pair<>("nemur", "True. He will definitely be\n smarter than silly Charlie.");

        chat = narrative.playConferenceDialogue();
        assertEquals(dialogue1.first, chat.first);
        assertEquals(dialogue1.second, chat.second);

        chat = narrative.playConferenceDialogue();
        assertEquals(dialogue2.first, chat.first);
        assertEquals(dialogue2.second, chat.second);

        chat = narrative.playConferenceDialogue();
        assertEquals(dialogue3.first, chat.first);
        assertEquals(dialogue3.second, chat.second);
    }

    /**
     * Test the logic of narrative progress
     */
    @Test
    void updateTriesProgress() {
        Narrative narrative = new Narrative();
        assertEquals(1, narrative.sceneList.get(0).getDialogueList().size());
        narrative.updateTriesProgress(1);
        assertEquals(2, narrative.sceneList.get(0).getDialogueList().size());
    }

    /**
     * Test getting the next scene in the narrative
     */
    @Test
    void getNextScene() {
        Narrative narrative = new Narrative();
        assertEquals(NarrativeProgress.TUTORIAL, narrative.getNextScene(NarrativeProgress.CONFERENCE, 0));
        assertEquals(NarrativeProgress.WORDLE, narrative.getNextScene(NarrativeProgress.TUTORIAL, 0));
        assertEquals(NarrativeProgress.WORDLE_REVIEW, narrative.getNextScene(NarrativeProgress.WORDLE, 0));
        assertEquals(NarrativeProgress.SUCCESS_ENDING, narrative.getNextScene(NarrativeProgress.WORDLE_REVIEW, 1));
        assertEquals(NarrativeProgress.TUTORIAL, narrative.getNextScene(NarrativeProgress.WORDLE_REVIEW, 3));
        assertEquals(NarrativeProgress.FAILURE_ENDING, narrative.getNextScene(NarrativeProgress.WORDLE_REVIEW, 0));
    }
}