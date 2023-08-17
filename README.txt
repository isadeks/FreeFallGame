=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 1200 Game Project README
PennKey: sphia
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Collections,
  I used an ArrayList. I will be using it for the platforms displayed on the screen.
  The game will start with a set amount of rows of platform and each row has 3 platforms.
  The ArrayList updates everytime a platform is off the screen, and removes the platform,
  and inserts a new one. The reason why I used the ArrayList is for the fast insertion and
  removal, but also the size is constantly changing. I traverse the ArrayList when I draw
  the platform, or check to see if the character is on top of one.

  2. Inheritance, I will use inheritance for the different types of platforms available. I have the
  superclass Platform, and the subclasses are BrokenPlatform which adds a new method that
  checks if it was touched to make sure it breaks, and MoneyPlatform also has a different method that
  updates the score.

  3. I/O, If the user exists the game window, but did not lose yet, the next time they open the
  game it load automatically its last progress. The file is called "saved.txt", and
  stores the entire game state which includes the positioning of the character, all the positions
  of the platforms, and the score.

  4. JUnit Testing, used this to make sure the methods involving the game objects were properly
  functioning. I tested edge cases when the character gets out of bounds, either goes too far up, or falls
  too far down, the game ends. The second edge case is when the platform is out of bounds, it is removed
  from the array list and a new one is added that comes from the bottom. The other three tests
  test the attributes of different platforms, for example moneyplatform if character
  lands on it the score should increase, if the character lands on poison they should die, or if
  the character is on a borken platform, it should fall down after the platform breaks.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  Background class, their function is to display the cloud background scrolling up when
  the game is playing.

  BrokenPlatform class, it inherits from the Platform class, it makes sure
  to disappear (breakdown) when the character lands on it.

  Character class, they are the main movable object, controled by the usr with left
  and right keys. The character falls down with a velocity of 1, and when on a platform
  it gets picked up. When the character goes out of bounds or touches poison, the game loses

  GameCourt class, the class that writes and reads saved data, has the main components when
  the game runs, where to reset, and draw the layout

  GameObj, the class that supports platforms and character objects. they all have velocities,
  positioning. Deals with collisions when the character lands on top of a platform. has move function

  BrokenPlatform class, it inherits from the Platform class, it makes sure
  to increase the score by one, when the character lands on it.

   Platform, class that deals with the platform objects, a superclass, and creates
   random positioning for the platforms.

   BrokenPlatform class, it inherits from the Platform class, it makes sure
   the game is over, when the character lands on it.

   RunFreeFall class, just sets up the layout of the window, and runs the game.
   It will load pre-saved data if there exists, if not resets and starts new game.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

    There was a stumbling block when dealing with the character landing on platforms.
    I had a tough time trying to make sure it would not fall through, and get picked up
    by the platform.


- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

   The private state is encapsulated like the platforms can't be ,modified by outside users.
   I would maybe try to refactor the platform classes to try and not overwrite the draw method.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.

  https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html
