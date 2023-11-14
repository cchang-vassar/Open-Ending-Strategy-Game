## Iteration 3 Plan

###  Items that we should be thinking about during the third iteration of the elaboration phase.


| Rank   | Requirement (use case)                                                                | Comments                                                                                                                                                                                |
|--------|---------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| High   | Implementing time constraints in each round; implementing difficulty settings before starting the first round; implementing tutorial dialog at the beginning of the first round | Overall, we plan to streamline the UI of the game and add more fragments before the user dives into the game (e.g., blinking timer shown at the bottom of the screen, step-by-step tutorial of the game, play game button, etc.)|
| Medium | Implementation of our next word game, a crossword puzzle; begin to map out the narrative aspect of our game | Things to think about: how should we choose to display the crossword clues (e.g., dialog, within the main view fragment, etc.), what type of feedback should we give the player (e.g., feedback for wrong characters vs. wrong words), recording stats for each word game to introduce logic for narrative aspect |
| Low   | Adding flexibility points (i.e., pausing & quitting all of our word games), start-up menu for the game | Flexibility points should be treated as its own use case; we will need to work with the Timer Thread logic to make it sleep during pauses | 