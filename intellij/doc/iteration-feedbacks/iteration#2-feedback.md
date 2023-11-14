## Iteration 2 Feedback

### Requirements analysis update
- [ ] Seems largely the same as before.

- [ ] "Average narrative gamer" would be better named something like "play narrative"?
- [ ] It is also unclear what exactly narrative choices are or what their complete effect is - is it just the score or is there something more to it? 
- [ ] Also, there should be no distinction between system, word library and scoring system. Should be just system, as use cases are only meant to model the interaction between the user and the system as a monolithic entity, not the system's internal design. 
- [ ] "Plays word puzzle" and other puzzle details should be removed, as it is all embedded into "execute word puzzle".


### Design

#### Sequence diagrams

- Representative of an MVC architecture, which is great.

- [ ] In "Play wordle games", it is the WordleFragment receiving the try again command, rather than MainView.

- [ ] As before, the Model class still seems like an unnecessary extra level of indirection.

- [ ] The level of detail on the method calls internal to an object could be substantially reduced. The main intent of the sequence diagrams is to model interactions between different objects.

- [ ] Your WordLibrary isn't a system, but a software object in your app. Therefore it shouldn't be represented as a stick figure.

   
#### Class diagram
- [ ] You can write the Listener inside of IWordleView as a regular class called IWordleView.Listener.


### Prototype

#### Coding

- Good MVC implementation. You took prior feedback regarding this into account, which is great to see.

- Good Javadoc, much appreciated. 
      
- Nice job extending the built-in views to adapt them to your purposes.

- [ ] Using an Enum to represent the different possibilities for a character guess would improve readability, reduce potential for mistakes. Same logic applies to game difficulty level, since there are only 3 different values.

- [ ] I encourage the use of general types such as List instead of ArrayList, whenever possible. A list can be instantiated as an ArrayList, but be treated as a List for return value and other purposes. This gives you more freedom to change the design in the future, without affecting client code. An example is the WordleLibrary class.

 
#### Functionality
- The amount of functionality is appropriate for a first Android prototype. Wish you had done the timer though.
- Usability is good when using the hardware keyboard, but not so much when using the software keyboard. It doesn't move to the next letter right away. I had to press space and then it repeated the previous letter. This would make the game hard to use on an actual phone.
  - [ ] FIX: lag on soft keyboard 
- The win/loss message disappears quite quickly, so it can be easy to miss. Would probably make sense to have it be a permanent part of the screen until the word is reset.
  - [ ] FIX: make message permanent 

#### Testing
- There is a good number of unit tests, testing the most important functionality. They're well documented too.
- Would have liked to see you test detection of correct guesses. You could set a flag for testing somewhere to always get the same word, to make it predictable. Or create a different activity that extends Controller and the only thing it does differently is choose a certain predictable word. Yet another possibility would be to have a guess that when entered makes the word be something specific - like a cheat code.

### Construction phase plan
- Definitely add the timer (you can use android.os.CountdownTimer) and more words to the wordle library (see hint under general feedback). I would then focus on incorporating the narrative aspect of your original idea, as it is one of its more unique aspects. I would leave the crossword puzzle as a lower priority stretch goal.

### General feedback
   - Readme is good.
   - I mentioned this before but your could increase the number of words on your game by relying on a publicly-available dictionary such as https://github.com/manassharma07/English-Dictionary-CSV. You could preprocess it to filter out the non-five-letter words.