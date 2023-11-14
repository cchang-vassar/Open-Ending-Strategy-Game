# Non-functional Specifications

## Usability
* Simple, intuitive, minimal UI that is easily understood by wide range of age and cognitive capability
  * Clean, low-detail but colorful aesthetic to increase engagement and accessibility
  * Incorporation of more iconography to help supplement the learning aspect within education mode
* Distinct interface that demonstrates a player's progress within the story
  * e.g., a map that highlights word games "unlocked" and played and grays out word games "locked" and not yet played by the user
* Clear readability and accessibility to navigate through the narrative portion of the game
  * e.g., large left and right arrows or swiping left or right to transition between dialogue
* Built-in flexibility to pause and/or quit
  * Give players the agency to decide when to quit (pause button)
  * Build in many natural stop-points in every stage of the game (chapters)
## Reliabilty
* Prioritize saving player progress in list of task system should complete at a given time
  * If possible, implement auto-save at frequent intervals (more frequent than the user is explicitly shown)
  * Create back up database of player progress
* If external library/API fails (i.e., returns a 400 error):
  * Use a locally, hard-coded implementation of the word game as well as a local word library

## Performance
* Users may experience lag after finishing a complete session of a word game and returning to the narrative/dialogue portion of the game during full experience story mode; code of a third-party library should be monitored to ensure there are no bugs

## Supportability 
* Displayed text should be allowed to be made resizable by users (older players can make font size larger)
* Responsive design for different browsers and screen sizes
* (Stretch) Multi-language support
* (Stretch) Accessibility tools
  * High contrast color mode to support those with color vision difficulties
  * Design workflows in a fashion that require the user to move eyes/mouse clicks in a line and not in a zig-zag fashion.

## Implementation
* Must be run on Android OS
* Must be written using Java

## Interface
* Interface with external word library API must be both fast enough to respond to players' in-game interactions with the system and robust enough to offer a wide variety of words presented to each player
* System should ideally be able to keep track of what words have been pulled from the external web library at any given point to interact meaningfully with interact
  * i.e. each round of the mini-games should be tailored to the words pulled from the library
  
## Legal
* N/A
