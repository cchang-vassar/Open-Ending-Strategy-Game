# Iteration 1 Plan 

###  Items that we should be thinking about during the first iteration of the elaboration phase.


| Rank   | Requirement (use case)                             | Comments                                                                                                                                                                                                                                                                                                                                           |
|--------|----------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| High   | Playing a single round of Wordle                   | Needs to provide color-coded feedback for all valid player inputs; address conditions such as: player guesses a word with the correct character as the target word but the word has far too many duplicates of that character, resetting a previously incorrectly positioned character that is currently correctly positioned from yellow to green |
| Medium | Adding time constraints and difficulty levels      | Should display time remaining to players; How should difficulty levels be chosen?                                                                                                                                                                                                                                                                  |
| Low    | Building flexibility into game (quit and/or pause) | How to build this into the algorithm itself?                                                                                                                                                                                                                                                                                                       |

[//]: # (**TODO**)

[//]: # ()
[//]: # (1. Domain model of a randomized crossword)

[//]: # (    - Grid)

[//]: # (    - Target words)

[//]: # (    - Target words' location on grid -> Answer Key)

[//]: # (    - Player)

[//]: # (    - Crossword)

[//]: # (2. Design class diagram)

[//]: # (    -   Classes:)

[//]: # (        -   Empty grid class)

[//]: # (            -   2D array of slots)

[//]: # (        -   Word class)

[//]: # (            -   Fields)

[//]: # (                -   char[] word)

[//]: # (                -   String clues)

[//]: # (            -   Methods)

[//]: # (                -   Grab randomized word + clue from external library)

[//]: # (                -   Map word to slots in empty grid 2D array)

[//]: # (                -   Add successfully placed word clue to clues)

[//]: # (3. Sequence diagram)

[//]: # (    -   Fill answer key grid with target words at appropriate locations)

[//]: # (    -   Black out all non-word slots of answer key grid)

[//]: # (    -   Generate list of clues)

[//]: # (    -   Print empty grid)

[//]: # (    -   Black out all non-word slots of empty grid )

[//]: # (    -   Print list of clues)

[//]: # (    -   Read player input)

[//]: # (    -   Continually update grid status relative to answer key)
