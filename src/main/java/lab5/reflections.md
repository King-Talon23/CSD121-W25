The reason you can return HumanPlayer when the return type for the metho if Player is because HumanPLayer is a subclass
of the Player class.

SubClasses of types can always be returned in place of their superClass but super classes cannot be returned 
in place of their subclass

i.e the class pipeline looks similar to this PLayer -> HumanPlayer -> <insert subType of HumanPlayer>
You can always return something lower down in the pipeline from the return type but never anything higher in the line 
than the return type

------------------------------------------------------------------------------------------------------------------------

pickNextMove needs to be abstract in the Player class because all subclasses of Player have different code to find 
their move. If the method was not abstract in the Player class the Player class would need to ave its own return 
statement. But we never actually call the Player class in the game to play, so it does not need to return a value, only 
its subclasses play the game and need to return moves

And if PickNextMove was abstract in the subclasses that would mean they are supposed to be defined in the subclasses of 
those subclasses, of which there are none.

------------------------------------------------------------------------------------------------------------------------

