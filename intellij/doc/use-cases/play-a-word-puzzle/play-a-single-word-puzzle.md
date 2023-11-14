# Fully Dressed Use Case (1)

## Play a single word puzzle
* **Priority**: High
* **Level**: User goal
* **Primary actor**: User
* **Other stakeholders and interests**:
* **Preconditions**:
    * User has chosen a game mode
    * The system determines "playing a word puzzle" as the next action the user should take
* **Post-conditions**:
    * _[Story mode]_ The system loads the next page of the user's narrative path
    * _[Quickgame mode]_ The system loads the next level/word puzzle  
<br>
* **Workflow**:
###### _Legend:_
<ul style="color:grey">
<li>Words in <em>italics</em>: potential fields</li>
<li>Words in <tt>monospace</tt>: potential classes</li>
</ul>

```plantuml
skin rose
title Play a word puzzle 
'define swimlanes 
|#b8c2f2|System|

|System|
:Determine word puzzle type to play;
switch (Puzzle type?)
case (Crossword)
:Execute __Play a single crossword puzzle__;
case (Anagram Maze)
:Execute __Play a single anagram maze puzzle__;
case (Wordle)
: Execute __Play a single wordle puzzle__;
endswitch
:Enter results page;
:Print user's //final score/win state/loss state//;
:Quit puzzle interface;
:Update user ""profile/stats"" based on user //score//;
switch (Game mode?)
case (Story mode)
:Update user ""narrative path""
based on user //score//;
case (Quickgame mode)
:Determine next word puzzle //difficulty level//
and //time constraints// based on user //score//;
endswitch
stop 
```

* **Nonfunctional requirements**:
    * Card that indicates end of tutorial and counts down to the start of the game (esp if timer is involved)
    * Card showing that the results page will now be displayed
    * Card explaining to user how their performance affects their overall stats/game progress