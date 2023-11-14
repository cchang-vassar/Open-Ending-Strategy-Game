# Fully Dressed Use Case (2)

## Play a single crossword puzzle
* **Priority**: High
* **Level**: Subfunction
* **Primary actor**: User
* **Other stakeholders and interests**:
* **Preconditions**:
    * User has chosen a game mode and played through its tutorial
    * The system chose "Crossword" as the word puzzle to be played
* **Post-conditions**:
    * Crossword puzzle game is terminated, either by user completing the crossword, user quitting the puzzle, or the timer running out  
<br>
* **Workflow**:
###### _Legend:_
<ul style="color:grey">
<li>Words in <em>italics</em>: potential fields</li>
<li>Words in <tt>monospace</tt>: potential classes</li>
</ul>

```plantuml
skin rose
title Play a single crossword puzzle 
'define swimlanes 
|#f2c8b8|Player| 
|#b8c2f2|System|
|#bbf2b8|Word-Clue Library|

|System|
start
:Set puzzle //theme// & //difficulty level// & //time constraints//;
:Create empty ""crossword"";
while (""Crossword"" filled?) is (No);
:Pass //theme//;
|Word-Clue Library|
:Generate //word-clue pair// based on ""theme"";
|System|
:Read //word-clue pair// returned;
if (//word// is valid?) then (Yes)
:Add //word// to ""crossword"";
else (No)
:Throw away //word//;
endif
endwhile (Yes)
:Create filled version of final ""crossword"" with corresponding //clue list// as ""answer board"";
:Create unfilled version of ""answer board"" as ""display board"";
:Display ""display board"";

|Player|
: Player action;
|System|
while (Player quit game? OR //timer//'s up? OR ""crossword"" completed?) is (No)
:Read player ""move"";
if (Successful ""move""?) then (Yes)
switch (""move"" complete //row// or //col//?)
case (""move"" completes //row//)
:Color completed //row// in green;
case (""move"" completes //col//)
:Color completed //col// in green;
endswitch
else (No)
endif
endwhile (Yes)


|System|
:Compare final ""display board"" to ""answer board"";
:Compute player final //score//;
:Exit ""crossword"";
stop 
```

* **Nonfunctional requirements**:
    * Intuitive UI
        * Clear readability and accessibility to navigate through the narrative portion of the game 
        * TimeController display showing how much time is left
        * Score counter showing how many correct words in correct slots have been filled
    * Built-in flexibility
        * Allowing players to pause and/or quit the word game when necessary
        * Stopping points throughout the narrative portion of the game, similar to chapters

