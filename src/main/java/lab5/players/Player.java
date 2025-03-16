package lab5.players;

import lab5.game.Board;
import lab5.game.Position;
import lab5.ui.Console;

/**
 * Represents a player in the game.
 *
 */
public abstract class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    /**
     * Prompts the player to pick their next move.
     * @param currentBoard The current state of the game board
     * @return The position on the board where the player wants to place their token
     **/
    public abstract void promptMove();

}
