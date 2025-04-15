package lab5.game;

import lab5.players.Player;

/**
 * Represents the current state of a tictactoe game, including which player's turn it is,
 * and the current state of the game board
 */
public class TicTacToeGame {

    /**
     * The players in the game
     */
    private final Player playerX;
    private final Player playerO;

    /**
     * The current state of the game board
     */
    private final Board board = new Board();

    /**
     * Initialize a new TicTacToe game with the given players
     * @param playerX The X player
     * @param playerO The O player
     */
    public TicTacToeGame(Player playerX, Player playerO) {
        this.playerX = playerX;
        this.playerO = playerO;
    }

    /**
     * Request where the next player wants to place a token on the board,
     * and then place that token, and update the game state accordingly.
     * @return A record of the turn that was just played
     * @throws IllegalArgumentException if the given position is already taken on the game board
     */
    public TurnRecord doNextTurn() throws IllegalArgumentException {
        var turnToken = board.getNextTurnToken();
        var whoseTurn = switch(turnToken) {
            case X -> playerX;
            case O -> playerO;
        };
        var pos = whoseTurn.pickNextMove(board);

        // If the player objects are implemented correctly, this should never happen
        if ( ! board.isEmptyAt(pos) ) {
            throw new IllegalArgumentException("Position %s is already taken".formatted(pos));
        }

        board.placeNextToken(pos);

        return new TurnRecord(whoseTurn, turnToken, pos, board);
    }

    /**
     * Represents what happened on one turn that was just played
     * @param whoseTurn The player who just played
     * @param positionPlayed The position on the board where the token was placed
     * @param newBoardState The new state of the game board after the turn was played
     */
    public record TurnRecord(Player whoseTurn, PlayerToken token, Position positionPlayed, Board newBoardState) {}

}
