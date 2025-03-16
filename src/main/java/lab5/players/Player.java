package lab5.players;

import lab5.game.Board;
import lab5.game.Position;

/**
 * Represents a player in the game.
 *
 */
public abstract class Player {

    private final String name;

    public Player(String name) {
        this.name = name;
    }
    public abstract Position pickNextMove(Board board);


    public String getName() { return name; }


    public abstract void promptMove();

}
