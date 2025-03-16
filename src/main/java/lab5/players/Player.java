package tictactoe.players;

import tictactoe.game.Board;
import tictactoe.game.Position;
import tictactoe.ui.Console;

/**
 * Represents a player in the game.
 *
 * TODO: Make this an abstract class with various subclasses for different types of players
 */
public class Player {

    private String name;

    public Player(String name) {
        this.name = name;
    }

    public String getName() { return name; }

    /**
     * Prompts the player to pick their next move.
     * @param currentBoard The current state of the game board
     * @return The position on the board where the player wants to place their token
     *
     * TODO: Make this an abstract method in an abstract class,
     *          and use this implementation in a HumanPlayer subclass
     */
    public Position pickNextMove(Board currentBoard) {
        while (true) {
            var move = Console.promptForPosition(getName() + " pick your next move: ");
            if (currentBoard.isEmptyAt(move)) {
                return move;
            } else {
                Console.printAlert("That position is already taken.");
            }
        }
    }
}
