# CMPU-203 S23 - Team 2E

## The Nice Neuroscientists: A narrative game full of word puzzles

## Description

#####  You, the player, magically bump into Dr. Strauss and Dr. Nemur—the two leading pioneers in science at the National Science Foundation. You overhear them talking about you and your potential as their next lab recruit when suddenly they challenge you to a game of Wordle!

**Functionality:**
* The game launches with dialogue between our two main characters, Dr. Strauss and Dr. Nemur. The player taps anywhere on their screen to read through the dialogue of Scene I in Chapter 1. 
* Towards the end of Scene I, Dr. Strauss will challenge the player to a game of Wordle. Once the player proceeds to tap their screen, a tutorial screen of how to play Wordle appears. The player will click the 'OK' button to exit the tutorial screen which will launch a Wordle game. 
* One game of Wordle will launch, prompting the countdown timer to appear in the lower right corner which will immediately start counting down from a certain time depending on the randomized level you receive (i.e., an easy game makes the timer count down from 20 mins while a hard game makes the timer count down from 5 mins). 
* When the player completes guesses a single character, the cursor of the current cell shifts to the next cell and is highlighted gray. When a player wants to delete a single character, the cursor of the current cell shifts to the previous cell and is highlighted gray.
* The player inputs their guess and the grid displays immediate color-coded feedback for each character in the input.
* Upon the player guessing a complete word, the cursor shifts to the first cell in the next row and is highlighted gray.
* When the player has successfully guessed the target word, a congratulatory message appears at the bottom of the screen and the player has the option to play again or quit.
* If the player did not guess the target word after using up all six guesses, a message stating they didn't win appears, along with the answer to the target word. The player has the option to play again or quit.

**Limitations:**.
* No stats system or use of a user database with Cloud Firestore. 
* There is no button for players to quit the game in the middle of the round.
* No player authentication capabilities.

**How to run the prototype:**
* Select the app from the run configurations menu.
* In the target device menu, select the Pixel 6 API 30 as the device to run the app on.
* Click Run.
