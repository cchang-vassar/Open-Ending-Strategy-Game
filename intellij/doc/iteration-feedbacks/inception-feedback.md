# Inception Phase Feedback

## Vision

This is a very interesting game idea.
The target audience(s) and value proposition are clear and make a lot of sense. I like how this one
game can help different audiences accomplish slightly different goals, all related to cognitive
ability.

- [x] The distinction between training and lightning modes is a little unclear - can't they be
  modeled as one and the same?

- Regarding the high-level use case diagram:
    - [x] It's unclear whether leisure and curious players need to be distinguished?
    - [x] System actors are meant to represent 3-rd party systems the system under development must
      interact with. Concepts that are internal to your app, such as score system, should not be
      shown. What should be shown, on the other hand, would be a 3rd-party word library, if you plan
      on using one.

## Use cases

- [x] The functionality is not divided into elemental use cases that represent the parts that make
  up the game. Rather, they're named after actors and represent the game modes. This reduces the
  utility of use cases as a technique for functional decomposition. For example, there should be a "
  play word game" use case and a "play story mode" use case. The play story mode one would then call
  upon a "interact with story" use case, and the "play word game" one as well. There would be this
  web of use cases and connections between them.


- [x] Regarding "Average Leisure User - Plays a single session of a word game during quick mode":
    - [x] The system under development is not an actor themselves - they just fulfill the users'
      goals.
    - [ ] The activity diagram is are missing some system activities like "showing available games".
    - [x] The interaction between the player and the system should be more concrete than "interacts
      with word game UI" - for that you need to pick a specific game, such as crossword puzzle, and
      think of what concrete interactions are needed in such a game. These interactions can be
      extracted out to a separate use case so you can reuse the main game loop but then have
      specific interactions for different types of word games without repeating the entire game
      loop.


- Regarding "Average Narrative Gamer - Plays a single session of a word game during full experience
  story mode":
    - [x] At this point in the project (requirements analysis), we're modeling the interaction
      between the actors and the system under development, not designing the system under
      development. Therefore the scoring subsystem should not be represented separately from the
      system under development.
    - [x] The functionality of playing a game puzzle should not be repeated, but rather called upon
      - like a function call but for a use case.
    - [x] The narrative choice mechanic is not concrete enough - is there a list of options and the
      player picks one, or is it done in some other fashion?

## Non-functional spec

- Regarding supportability:
    - [ ] "Low-resolution mode for graphics to support slow wifi connections or limited bandwidth" -
      do you envision retrieving graphics remotely?
    - [ ] Since it is a word game, it would be nice to have multiple-language support as a stretch
      goal.

## Glossary

Is empty.

## Iteration plan

I see two paths, starting with the story component, or with the gaming component. Both are valid and
so I approve of you starting with the gaming component.

## General comments

I encourage you to revise the way in which you decompose the game into use cases. And please do have
a concrete word game in mind, like crossword puzzle. I would also encourage you to work with a local
dictionary instead of a remote one, for simplicity. Here is an English dictionary you can download
and use: https://github.com/manassharma07/English-Dictionary-CSV .
