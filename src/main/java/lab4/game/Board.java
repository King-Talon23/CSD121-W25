package lab4.game;

import static lab4.game.Board.PlayerToken.O;
import static lab4.game.Board.PlayerToken.X;

/**
 * Represents a TicTacToe game board
 */
public class Board {

    /**
     * A data type to represent each token on the board
     */
    public static enum PlayerToken { X, O }

    /**
     * Represents the high-level status of the game board
     */
    public static enum Status { InProgress, Draw, XWins, OWins }

    /**
     * The current game board state
     */
    private final PlayerToken[][] board = new PlayerToken[3][3];

    /**
     * Initialize a new, empty tictactoe board
     */
    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = null;
            }
        }
    }

    /**
     * @return The current status of the game board
     */
    public Status getStatus() {
        switch (this.getWinner()) {
            case X: return Status.XWins;
            case O: return Status.OWins;
            case null: {
                if (this.isFull()) {
                    return Status.Draw;
                } else {
                    return Status.InProgress;
                }
            }
        }
    }

    /**
     * @param pos A board position
     * @return The row index in this.board corresponding to the given board position
     */
    private int rowIdx(Position pos) {
        return switch (pos.row()) {
            case Top -> 0;
            case Middle -> 1;
            case Bottom -> 2;
        };
    }

    /**
     * @param pos A board position
     * @return The column index in this.board corresponding to the given board position
     */
    private int colIdx(Position pos) {
        return switch (pos.col()) {
            case Left -> 0;
            case Middle -> 1;
            case Right -> 2;
        };
    }

    /**
     * @return The PlayerToken for the winner of the game, or null if there is currently no winner
     */
    private PlayerToken getWinner() {
        // Check the rows and columns
        for (int i = 0; i < 3; i++) {
            // Check if the row is all the same
            if (board[i][0] != null && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                return board[i][0];
            }
            // Check if the column is all the same
            if (board[0][i] != null && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                return board[0][i];
            }
        }
        // Check the diagonals
        if (board[0][0] != null && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            return board[0][2];
        }
        if (board[0][2] != null && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            return board[0][2];
        }
        return null;
    }

    /**
     * @return true if the board is full; false otherwise
     */
    public boolean isFull() {
        for (var row : board) {
            for (var cell : row) {
                if (cell == null) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param pos A game board position
     * @return true if there is already a token at the given board position; false otherwise
     */
    public boolean isOccupiedAt(Position pos) {
        return board[rowIdx(pos)][colIdx(pos)] != null;
    }

    /**
     * Places an X token on the game board at the given position
     * @param pos A game board position
     */
    public void placeX(Position pos) {
        board[rowIdx(pos)][colIdx(pos)] = X;
    }

    /**
     * Places an O token on the game board at the given position
     * @param pos A game board position
     */
    public void placeO(Position pos) {
        board[rowIdx(pos)][colIdx(pos)] = O;
    }

    /**
     * @return A 3x3 string representation of the game board
     *         E.g.
     *               .XO
     *               .X.
     *               O..
     */
    @Override
    public String toString() {
        var boardString = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardString.append(board[i][j] == null ? '.' : board[i][j]);
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }
}
