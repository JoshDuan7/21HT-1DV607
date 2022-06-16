# <ins>A2</ins>

By: Atakan Osman Coban, Fabian Dacic, Yuyao Duan and Fredric Eriksson Sepulveda

## How to use

* Simply build & run with gradle. After the app has started, follow the console prompts to do what you gotta do.
* Boats exist as a property of a member only. Therefore, to add, view, edit and delete boats first pick a member.
* To pick a member:
    1. List all members (both options work) and remember the ID of the member you'd like to choose.
    2. Choose the option to pick a member and enter their ID.
* When a member is chosen, their boats will automatically be listed. Remember a boat's ID also to pick it.

## Changes made

* The Strategy pattern used to make searches (From grade 3) has now been changed into a composite pattern to support
  complex searches.

* The persistence implementation is now more elaborate with two different alternatives to choose from (JSON and .txt).
  Their respective classes implement the persistence interface and then the registry loads the members from the format. 

## Things to know

* If you make changes to a member/boat, and then you go to the previous menu, then come back to change more info, it
  will only make the changes you made in the second session. Therefore, after making a change to a member/boat, go all
  the way back to the main menu to save.

* If you want to change the loading from JSON to .txt instead, you will need to go to the Registry class (model.domain.Registry) and 
  comment out whichever option you don't want to use and leave the one you actually want to use uncommented. The way it works is that the
  main method checks if the flag for JSON is set or not and depending on that, the program either loads the files from the .txt file or the
  JSON one.

## Hardcoded Persistence

Although at this instance of the project there is no loading or saving of sessions, there are 3 default members (and
their boats) that are loaded into the app everytime it is run. Use those to play around with the app along with other
members and boats you might create during session. Check the previous subsections for more information on how the persistence
has changed in this iteration.

## Complex Search

Our complex search mechanism can be tested in MemberTests. Simply run BracketTest2, 3 and 4.

## Some of our work was influenced by

* https://github.com/tobias-dv-lnu/1dv607/tree/master/studsys
* https://github.com/tobias-dv-lnu/1dv607/tree/master/2021/DiceGame
