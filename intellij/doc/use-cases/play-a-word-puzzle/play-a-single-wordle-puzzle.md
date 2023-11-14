# Fully Dressed Use Case (3)

## Play a single Wordle game
* **Priority**: High
* **Level**: Subfunction
* **Primary actor**: User
* **Other stakeholders and interests**:
* **Preconditions**:
    * User has chosen a game mode and played through its tutorial
    * The system chose "Wordle" as the word puzzle to be played
* **Post-conditions**:
    * Wordle game is terminated either when the user guesses the correct word within 6 tries or the user quits the game
      <br>
* **Workflow**:
###### _Legend:_
<ul style="color:grey">
<li>Words in <em>italics</em>: potential fields</li>
<li>Words in <tt>monospace</tt>: potential classes</li>
</ul>

```plantuml
skin rose
title Play a single wordle puzzle 
'define swimlanes 
|#f2c8b8|Player| 
|#b8c2f2|System|
|#bbf2b8|Word Library|

|System|
start
:Set wordle's //difficulty level//;
:Create empty wordle ""grid"";
|Word Library|
:Select random 5-letter //word//;
|System|
:Set retrieved 5-letter //word// as ""answer key"";
:Display empty wordle grid;
while (Grid exceeded?) is (No)

|Player|
if (Submit input?) then (Yes);
|System|
if (Correct //word// input?) then (Incorrect input)
if (Valid //word// input?) then (Valid input)
:Provide feedback;
:Move player cursor to next row;
else (Invalid input)
:Reject input;
endif
else (Correct input)
:Display player final score;
:Print "You guessed the correct word!";
:Exit wordle interface;
stop
endif

else (Quit)
|Player|
stop
endif
|System|
endwhile (Yes)
:Display player final score;
:Print correct word;
:Exit wordle interface;
end
```



