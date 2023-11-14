# Fully Dressed Use Case (1)

## Average Narrative Gamer - Plays a single session of a word game during full experience story mode
* **Priority**: High
* **Level**: User goal
* **Primary actor**: Average Narrative Gamer
* **Other stakeholders and interests**:
  * System: may want to analyze leisure user playing patterns to finetune features of each mini-game in quick mode, like difficulty levels, time constraints, etc.
  * e.g. how long players spend in each mini-game on average, how long it takes for players to reach a milestone, what choices players usually make at each decision point, etc.
* **Preconditions**:
  * User has chosen the appropriate mode (full experience story mode)
  * User is debriefed and understand sub-mechanisms like making choices and playing mini-games, how to save progress, as well as how each action affects overall narrative (can be done through in-game tutorial)
* **Postconditions**:
  * Points accumulated in each word game/choice must be recorded
  * Each significant action that alters narrative path must be recorded
  * The user's total accumulative score must be updated
  * The user's total accumulative score must be compared to a score threshold upon finishing each word game in order to determine if the user has beat the entirety of the game
  * The user is asked if they want to proceed to the next level of the word game or if they want to save their progress and quit
  * The user progresses to the next level of the word game
* **Workflow**:

```plantuml
skin rose
title Plays a single word game 
'define swimlanes 
|#b8f2bf|Player|
|#c6b8f2|Scoring System| 
|#f2b8ea|System|
|#b8e8f2|Word Puzzle Library|

|Player|
start
: Chooses full story game mode;
: Continues at checkpoint player last left the game at (default = start of game);
while (Keep playing?)  is (yes)
switch (Mini-game type?)
  case (narrative choice)
  |Player|
  :make narrative choices;
  case (word-puzzle)
  :Execute word-puzzle;
  |System|
  :Sets theme and settings for word-puzzle;
  |Word Puzzle Library|
  :Fetches appropriate API/library;
  |Player|
  :Plays word-puzzle;
endswitch
|Player|
:Completes one chapter of the game;
|Scoring System| 
:Tracks user's total score based on mini-games and significant choices/actions;
:Update user's total score from user's dialogue/interactions and playing word games;
:Print score on screen;
endwhile (no)
|Player|
:Saves progress;
:Quits game;
|System|
:Presents a screen that displays user's status;
stop 
```
###### *More details on how narrative flow and branching works to be fleshed out

* **Nonfunctional requirements**:
  * Intuitive UI
    * Distinct interface that demonstrates a player's progress within the story (e.g., a map that highlights word games "unlocked" and played and grays out word games "locked" and not yet played by the user)
    * Clear readability and accessibility to navigate through the narrative portion of the game
    * Incorporation of more graphic interfaces/iconography to help supplement the learning aspect within this mode
  * Built-in flexibility
    * Allowing players to pause and/or quit the word game when necessary
    * Stopping points throughout the narrative portion of the game, similar to chapters
