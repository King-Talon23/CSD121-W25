package lab5.game;

import java.util.ArrayList;
import java.util.List;

import static lab5.game.PlayerToken.O;
import static lab5.game.PlayerToken.X;

/**
 * Represents a TicTacToe game board
 */
public class Board {

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
     * Copy constructor. Creates a new board that is a copy of the given one.
     * @param other The board to copy
     */
    public Board(Board other) {
        for (int i = 0; i < 3; i++) {
            System.arraycopy(other.board[i], 0, board[i], 0, 3);
        }
    }

    /**
     * Initialize a new board with the given state (useful for testing)
     * @param b A string representation of the board state.
     *          The string should be 3 lines long, with each line containing 3 characters,
     *          or one 9 character string.
     *          Any character other than 'X', 'O' is considered an empty spot on the board.
     *          E.g. "XOX-XO--O" or "XOX\n-XO\n--O"
     */
    public Board(String b) {
        b = b.replace("\n", "").toUpperCase();
        if (b.length() != 9) {
            throw new IllegalArgumentException("Invalid board state string");
        }
        for (int i = 0; i < b.length(); i++) {
            if (b.charAt(i) == 'X') {
                this.placeX(new Position(Row.values()[i / 3], Col.values()[i % 3]));
            } else if (b.charAt(i) == 'O') {
                this.placeO(new Position(Row.values()[i / 3], Col.values()[i % 3]));
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
     * @param token The token to count
     * @return The number of tokens of the given type on the board
     */
    private int count(PlayerToken token) {
        int count = 0;
        for (var row : board) {
            for (var cell : row) {
                if (cell == token ) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * @return The number of X tokens on the board
     */
    private int countXs() {
        return count(X);
    }

    /**
     * @return The number of O tokens on the board
     */
    private int countOs() {
        return count(O);
    }

    /**
     * @return The PlayerToken for the winner of the game, or null if there is currently no winner
     */
    public PlayerToken getWinner() {
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
            return board[0][0];
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
     * @return A list of all empty positions on the game board
     */
    public List<Position> getEmptyCells() {
        var emptyPositions = new ArrayList<Position>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                var pos = new Position(Row.values()[i], Col.values()[j]);
                if (isEmptyAt(pos)) {
                    emptyPositions.add(pos);
                }
            }
        }
        return emptyPositions;
    }

    /**
     * @param pos A game board position
     * @return true if the given board position is empty; false otherwise
     */
    public boolean isEmptyAt(Position pos) {
        return board[rowIdx(pos)][colIdx(pos)] == null;
    }


    /**
     * This function determines whose turn it is based on the current state.
     * ASSUMPTIONS:
     * - X was the first token placed on the board
     * - Players have alternated turns since the beginning of the game
     * If the above assumptions are not met, this function may not return a correct result.
     * @return The token of the player whose may place the next token.
     */
    public PlayerToken getNextTurnToken() {
        return countXs() > countOs() ? O : X;
    }

    /**
     * Places an X token on the game board at the given position
     * @param pos A game board position
     * @throws IllegalArgumentException if the position is already occupied
     */
    public void placeX(Position pos) throws IllegalArgumentException {
        place(pos, X);
    }

    /**
     * Places an O token on the game board at the given position
     * @param pos A game board position
     * @throws IllegalArgumentException if the position is already occupied
     */
    public void placeO(Position pos) throws IllegalArgumentException {
        place(pos, O);
    }

    /**
     * Places the given token on the game board at the given position.
     * @param pos A game board position
     * @param token The token to place
     * @throws IllegalArgumentException if the position is already occupied
     */
    public void place(Position pos, PlayerToken token) throws IllegalArgumentException {

        if (!isEmptyAt(pos)) {
            throw new IllegalArgumentException("Position is already occupied");
        }

        board[rowIdx(pos)][colIdx(pos)] = token;
    }

    /**
     * Places the next token on the game board at the given position.
     * The next token is determined by the current state of the board,
     * and assumes that X is always the first token played. Thus, if
     * there are more X tokens on the board than O tokens, the next
     * token placed will be an O, and vice versa.
     * @param pos A game board position
     */
    public void placeNextToken(Position pos) {
        switch (getNextTurnToken()) {
            case X -> placeX(pos);
            case O -> placeO(pos);
        }
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
                boardString.append(board[i][j] == null ? '.' : board[i][j].toString());
            }
            boardString.append("\n");
        }
        return boardString.toString();
    }
}
