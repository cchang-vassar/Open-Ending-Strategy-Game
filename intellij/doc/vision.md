# Vision Document

## High Level Description
A 2-D text-based narrative game in which the player makes choices to explore an overall narrative, advancing by completing challenging word puzzles embedded in the narrative in each chapter. In the narrative, the player role-plays as the subject of a neuroscience experiment. The player is administered doses of intelligence-increasing drugs at certain intervals throughout the story, and their behavior is monitored at every moment. The player's final goal is to have a high enough total score to be released from the experiment.

Throughout the game, the neuroscientists interact with the player to test them through conversations, where the player can respond by choosing between a set of given choices. Depending on these choices, the player experiences different branches of the narrative, and therefore advances towards their final goal of escaping at different paces. The player's progress is estimated by a point system, with each interaction and decision adding a correspondent number of points to the total score. The player will also score points by completing word-based puzzles of varying difficulty levels and different sets of constraints (e.g. time) in each stage of the game. In turn, the player's current total score affects the current choices and actions available to the player, the version/branch of the narrative they experience as well as the difficulty levels of the word puzzles.

The game therefore unfolds in a mostly linear fashion, but with branches that are unravelled depending on the player’s choices, actions and resulting scores. In order to win, the player must try to zero in on the choices and behaviors the neuroscientist are looking for and tailor their decisions to it, which will earn them points.

## Main Features
* __3 Modes__: each targeted towards different types of players
  1. *Story Mode*: mode with narrative and word puzzles embedded within [not a real mode -> divided into the following two variations]<br>
    a.  *Main Mode*: suited for people who want to fully experience the game<br>
    b. [stretch] *Education Mode*: suited for young children with developing minds, aims to teach them themes like the ethics of science, respect for everyone despite their intelligence level, etc.
  3. *Quickgame Mode*: mode without narrative and only has the word puzzles [not a real mode -> divided into the following two variations]<br>
    a.  *Zen Mode*: suited for people with deteriorating cognitive capability, seeking to train their cognition in a slow-paced, entertaining way <br>
    b.  [stretch] *Lightning Mode*: suited for busy people who just want to play some word games for killing time and quick leisure
* __3 Word Puzzle Types__:
  * Crosswords
  * Maze-style word construction / Anagram
  * Wordle
* __Variations on Word Puzzles__:
  * Time constraints
  * Difficulty level
* __Stretch Goals__:
  * Support for multiple languages
  * Education Mode version of Story Mode
  * Lightning Mode version of Quickgame Mode
  * More branching of narrative
  * More types of word puzzles
  * Multiplayer mode (scrabble?)

###### _*The system's complexity can be scaled very high, roughly along 3 dimensions: 1) the no. of modes, 2) the complexity of the narrative structure and 3) the types and variations of word puzzles. Our non-stretch goal is to only have 1 mode, 1 linear narrative path and a set progression of word puzzles that are identical for all players_

## Target Audience
1. People looking for an engaging, challenging narrative game to experience fully
2. People looking to play a variety of word puzzles to kill time and relax
3. Very young children to which the game should be thought-provoking and educational
4. *People with declining cognitive capabilities or mental impairments

###### _*Target demographic that imposes more design constraints → design must cater to their needs_

## Value Proposition
* Use interactive storytelling to promote cognitive development in young children and improve cognitive health of the elderly/those with impairments
* Explore the ethics of behavioral scientific experiments and raise awareness of it
* Combined, narrative experience teaches player the importance of respect for all people equally

## Constraints
* UI/UX design must focus on being accessible and engaging for user demographic with wide range of age and cognitive capability
* Language used should be simple, laymen-friendly and respectful
* Should be implementable without heavily relying on extra graphic or development tools
  * e.g. not much need for 3D-modelling software or heavyweight game engine

## List of goals for each actor
| Actor                              | Type of actor        | Goal                                                                                                                                                                                                       |
|------------------------------------|----------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Narrative player              | Primary              | Enjoy the game's full experience; challenging puzzle gameplay; exploring and immersing in a mysterious narrative; fluctuating (narrative-dependent) levels of difficulty; saving progress; accruing points |
| Leisure player              | Primary              | Play various types of mini word puzzles; score high; no commitment to long narrative; steady increasing levels of difficulty; quit any time                                                                |
| Young children                     | Primary              | Smooth introduction to gaming; immersion in narrative and learning about ethics and science; emotional resonance with played character                                                                     |                                                                         |
| Cognitive impairment Player | Primary              | Play multiple rounds of cognition-training word puzzles; varying but generally low levels of difficulty; gentle learning curve; easy quitting of game                                                      |   
| Word Puzzle Library                | Supporting/non-human | Provide API or libraries for content of word puzzles                                                                                                                                                       |
                                                                                                            |


## UML Diagram
```plantuml
' human actors
actor "Narrative Player/Young Children" as narrativePlayer
actor "Leisure Player/Cognitive Impairment Player" as leisurePlayer

' system actors 
actor "Word Puzzle Library" <<system>> as wordLibrary 

' listing all use cases
package "NeuroscienceGame" {
    usecase "Play Game (in a specific mode)" as playGame
    usecase "Change App Settings" as changeAppSettings
    usecase "View Player Profile" as viewPlayerProfile
}

' listing relationships between actors and use cases
narrativePlayer --> playGame
narrativePlayer --> changeAppSettings
narrativePlayer --> viewPlayerProfile
leisurePlayer --> playGame
leisurePlayer --> changeAppSettings
leisurePlayer --> viewPlayerProfile
playGame --> wordLibrary


```
