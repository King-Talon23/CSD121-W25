package lab5.game;

/**
 * Represents the row position of a tictactoe board location
 */
public enum Row {
    Top, Middle, Bottom;

    /**
     * @param str A string representation of a row position
     * @return The row position corresponding to the given string representation
     * @throws IllegalArgumentException if the given string is not a valid representation for a row position
     */
    public static Row from(String str) {
        return switch (str.toLowerCase()) {
            case "1", "t" -> Top;
            case "2", "m", "c" -> Middle;
            case "3", "b" -> Bottom;
            default -> throw new IllegalArgumentException("Invalid row: " + str);
        };
    }
}
