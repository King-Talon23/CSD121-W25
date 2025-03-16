package tictactoe.game;

/**
 * Represents a game board position
 * @param row
 * @param col
 */
public record Position(Row row, Col col) {

    /**
     * @param str A string representation of a position (e.g. "t l", "b m", "1 2", "3 1", etc.)
     * @return The position corresponding to the given string representation
     * @throws IllegalArgumentException if the given string is not a valid representation for a position
     */
    public static Position parse(String str) {
        var parts = str.split(" ");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid position: " + str);
        }
        return new Position(Row.from(parts[0]), Col.from(parts[1]));
    }
}
