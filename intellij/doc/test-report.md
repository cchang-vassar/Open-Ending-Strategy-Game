# Tests
The screenshots below displays some tests of users and existing users using the program.

## Making a correct guess
![correct.png](use-cases%2Ftest-screenshots%2Fcorrect.png)
The system displays the timer and asks for the user to input their guess into the console. User input provided to the system 
was a valid five-letter word to which the system outputted the same five-letter word with its characters
color-coded. Upon the user guessing the correct five-letter word, the system outputs a congratulatory 
message and asks users if they'd like to continue to the next round or not, which was the desired outcome
for this condition.

_*Timer not shown in this screenshot as 'time left' at this particular moment displayed every minute._
## Making an invalid guess
![invalid.png](use-cases%2Ftest-screenshots%2Finvalid.png)
The system displays the timer and asks for the user to input their guess into the console. User input provided to the system were 
invalid inputs such as strings greater than five characters, numbers, and symbols. The system outputs with the message
"Invalid input. Please enter a 5-letter word" and prompts the user to enter a new guess, which was the desired outcome for this condition.

## Making an incorrect guess
![incorrect.png](use-cases%2Ftest-screenshots%2Fincorrect.png)
The system displays the timer and asks for the user to input their guess into the console. User input provided to the system is a valid
five-letter word but is not the target word. The system outputs immediate feedback in the form
of a string color-coded by character and prompts the user to enter a new guess. Characters that were formerly yellow in a previous guess
are updated to green once that character in the user's input matches the position of that  in the target word, which was the desired outcome for
this condition.

## Time or trial runs out
![timesup.png](use-cases%2Ftest-screenshots%2Ftimesup.png)
![trialsup.png](use-cases%2Ftest-screenshots%2Ftrialsup.png)
The system displays the timer and asks for the user to input their guess into the console. Upon receiving no user input from the console by the 
time there is 0s left, the system outputs the message "Times up!" and asks the user if they would like to continue to the next round or not. Upon the
user input exceeding six tries, the system should output a message stating that the word was not guessed and again, ask if the user would like to continue to the next round or not,
which was the desired outcome for this condition.

## Continue game
![continue.png](use-cases%2Ftest-screenshots%2Fcontinue.png)
The system displays a message that asks users if they would like to continue to the next round. When the user input
is a 'y', the timer should display again and the system should output a message that asks users to input their guess.
Most of the outputs matched the desired outcome for this condition except the tutorial which would only
be necessary prior to starting Round 1 of a Wordle game.

## Quit game
![quit.png](use-cases%2Ftest-screenshots%2Fquit.png)
The system displays a message that asks users if they would like to continue to the next round. When the user input
is an 'n', the system should display an exit message, which was the desired outcome for this condition.