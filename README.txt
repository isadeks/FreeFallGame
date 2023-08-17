  1. Collections,
  I used an ArrayList. I will be using it for the platforms displayed on the screen.
  The game will start with a set amount of rows of platforms and each row has 3 platforms.
  The ArrayList updates every time a platform is off the screen, removes the platform,
  and inserts a new one. The reason why I used the ArrayList is for the fast insertion and
  removal, but also the size is constantly changing. I traverse the ArrayList when I draw
  the platform or check to see if the character is on top of one.

  2. Inheritance, I will use inheritance for the different types of platforms available. I have the
  superclass Platform and the subclasses are BrokenPlatform which adds a new method that
  checks if it was touched to make sure it breaks, and MoneyPlatform also has a different method that
  updates the score.

  3. I/O, If the user exists the game window, but did not lose yet, the next time they open the
  game it load automatically its last progress. The file is called "saved.txt", and
  stores the entire game state which includes the positioning of the character, all the positions
  of the platforms, and the score.

  4. JUnit Testing, used this to make sure the methods involving the game objects were adequately
  functioning. I tested edge cases when the character gets out of bounds, either goes too far up, or falls
  too far down, and the game ends. The second edge case is when the platform is out of bounds, it is removed
  from the array list and a new one is added that comes from the bottom. The other three tests
  test the attributes of different platforms, for example, moneyplatform if character
  lands on it the score should increase, if the character lands on poison they should die, or if
  the character is on a broken platform, it should fall down after the platform breaks.

====================================
=: Classes and Their Descriptions :=
====================================

  Background class, their function is to display the cloud background scrolling up when
  the game is playing.
  
  Platform, class that deals with the platform objects, a superclass, and creates
  random positioning for the platforms.
  
  BrokenPlatform class, it inherits from the Platform class, it makes sure
  to disappear (breakdown) when the character lands on it.

  Character class, they are the main movable object, controled by the usr with left
  and right keys. The character falls down with a velocity of 1, and when on a platform
  it gets picked up. When the character goes out of bounds or touches poison, the game loses

  GameCourt class, the class that writes and reads saved data, has the main components when
  the game runs, where to reset, and draw the layout

  GameObj, the class that supports platforms and character objects. they all have velocities,
  positioning. Deals with collisions when the character lands on top of a platform. has move function

  RunFreeFall class, just sets up the layout of the window, and runs the game.
  It will load pre-saved data if there exists, if not resets and starts new game.
