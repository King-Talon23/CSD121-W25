package tictactoe.game;

/**
 * A data type to represent each token on the board
 */
public enum PlayerToken {
    X, O;

    /**
     * @return The opponent of this token
     */
    public PlayerToken opponent() {
        return switch (this) {
            case X -> O;
            case O -> X;
        };
    }
}
