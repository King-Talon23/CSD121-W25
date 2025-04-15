package lab5.game;

/**
 * Represents the column position of a tictactoe board location
 */
public enum Col {
    Left, Middle, Right;

    /**
     * @param str A string representation of a column position
     * @return The column position corresponding to the given string representation
     * @throws IllegalArgumentException if the given string is not a valid representation for a column position
     */
    public static Col from(String str) {
        return switch (str.toLowerCase()) {
            case "1", "l" -> Left;
            case "2", "m", "c" -> Middle;
            case "3", "r" -> Right;
            default -> throw new IllegalArgumentException("Invalid column: " + str);
        };
    }
}
