package lab4.game;

import com.diogonunes.jcolor.Attribute;

import static lab4.game.Board.Status.InProgress;

/**
 * Represents the current state of a tictactoe game, including which player's turn it is,
 * and the current state of the game board
 */
public class TicTacToeGame {

    /**
     * The names of the players in the game
     */
    private final String[] players = new String[2];

    /**
     * The current state of the game board
     */
    private final Board board = new Board();

    /**
     * Keeps track of whose turn it is (either 0 or 1)
     */
    private int turnIdx = 0;

    /**
     * Initialize a new game with the given names for the 2 players
     * @param player1
     * @param player2
     */
    public TicTacToeGame(String player1, String player2) {
        players[0] = player1;
        players[1] = player2;
    }

    /**
     * Advances the game by placing a token for the current player at the given position on the board.
     * @param pos The position that the current player wants to place a token onto
     * @throws IllegalArgumentException if the given position is already taken on the game board
     */
    public void doNextTurn(Position pos) {

        if (board.isOccupiedAt(pos)) {


            throw new IllegalArgumentException("Position %s is already taken".formatted(pos));
        }

        if ( turnIdx == 0 ) {
            board.placeX(pos);
        } else {
            board.placeO(pos);
        }

        // Only advance the turn if the move does NOT put us into an end-game state
        if ( board.getStatus() == InProgress ) {
            turnIdx = (turnIdx + 1) % 2;
        }

    }

    public Board getBoard() {
        return board;
    }

    public String whoseTurn() {
        return players[turnIdx];
    }
}
