# Iteration#1 (Elaboration) Feedback

## Requirements analysis update

Feedback from the previous iteration was largely incorporated, which is wonderful to see. The use
cases look much better than in the previous iteration.
I like the functional decomposition of "play a game" then calling upon "play crosswords" and "play
wordle".

- [ ] In the "play crosswords" use case, the loop must include the user specifying an action. Right
  now it doesn't, so the user only makes a single guess.

## Design

### Sequence diagrams

Overall a good diagram that shown an effort to create an MVC architecture. Minor points:

- [x] Using a separate diagram for playing a single turn, which would then be referred from the main
  diagram could help improve readability.
- [x] An object creation message (e.g., creating a Wordle instance) should be directed at the head
  of the participant instead of its lifeline (please see lecture slides for PlantUML notation).

### Class diagram

- [ ] If the AWordGame class is empty, then it doesn't make sense for it to exist. Are there things,
  attributes or methods, that all word games will need that could be define in AWordGame? For
  instance, do they all have a timing component?
- [ ] The Model class seems unnecessary. Its contents could be move to the Controller class.

## Prototype

### Coding

- Source code is neatly stored in an src subfolder.
- The Javadoc that is there is much appreciated.
- The code does MVC but there are some perfectible points. For example, the TimeController class is
  speaking to the UI in a way that is pretty much the same as calling System.out.println. Instead,
  it would be better to inform the UI not of a message to print but of an event, and let the UI
  decide how to best convey that information to the user. E.g., instead of view.print(
  String.format("Time left: %ds", i));, you could do view.updateTimeLeft(secs). Right now the view
  doesn't do enough of the user-app communication logic, it's almost like a glorified printer and
  scanner.
- [ ] On a related note it would be best to separate the feedback messages that are given to the
  user from the model, if possible.
    - [ ] The model could indicate to the interface a good/bad result (different types of bad
      inputs)
    - [ ] Perhaps have all the result types in an enum, but the process of how to express that to
      the user would best be determined by the UI. For instance a certain type of result could
      trigger a text message, but also a sound - error sound vs victory sound - depending on the
      interface in use.
- [ ] I encourage the use of general types such as List instead of ArrayList, whenever possible. A
  list can be instantiated as an ArrayList, but be treated as a List for return value and other
  purposes. This gives you more freedom to change the design in the future, without affecting client
  code. An example is the WordleLibrary class.
- [ ] Since there is a fixed, well-known number of difficulty levels, perhaps it could be an enum.
  The wordle library could store words on a map where the key is a difficulty level and the value is
  a collection of words associated with that particular difficulty level.

### Functionality

Very good amount of functionality for a first prototype. I can tell you put a significant amount of
effort into this submission. The colors are a nice touch.

- [ ] Minor thing - perhaps it would be nice to, at the end, show what the word was, if the player
  wasn't able to guess it.

### Testing

Good test report, covers a good variety of scenarios.

## Elaboration iteration 2 plan

The overarching goal for iteration 2 that isn't spelled out in your plan but that I infer from it is
that you plan to convert the existing functionality onto the Android platform. That is an
appropriate goal. If you have extra time at the end I'd encourage you to start designing the story
aspect of your app.

## General feedback

Good readme. The implemented functionality is clear.

- [x] Change-mode.md is an extraneous empty file.
