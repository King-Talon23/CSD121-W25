package lab5.ui;

import com.diogonunes.jcolor.*;
import lab5.game.PlayerToken;
import lab5.game.*;
import lab5.players.*;
import lab5.players.Circe;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.*;
import static com.diogonunes.jcolor.Attribute.*;


public class Console {

    // Define some colors and text styles for use in the console
    private static final AnsiFormat fPrompt = new AnsiFormat(GREEN_TEXT(), BOLD());
    private static final AnsiFormat fAlert = new AnsiFormat(YELLOW_TEXT());

    public static void println(String message) {
        System.out.println(message);
    }

    /**
     * Prompt the user for input using the given promptMessage
     * @param promptMessage The message to prompt the user with
     * @return The user's response
     */
    public static String prompt(String promptMessage) {
        System.out.print(fPrompt.format(promptMessage));
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Display an alert message to the user
     * @param message The message to display
     */
    public static void printAlert(String message) {
        System.out.println(fAlert.format(message));
    }

    /**
     * Repeatedly prompt the user to select a player for the given token
     * until the select a valid one of a set of valid players
     *
     * @param whichPlayer The player for which to prompt
     * @return A player object representing the user's chosen player
     */
    public static Player promptForPlayer(PlayerToken whichPlayer) {

        while ( true ) {
            var input = prompt(fPrompt.format("Who will play " + whichPlayer + "? "));

            if ( input.startsWith("@") ) {
                input = input.substring(1).toLowerCase(); // remove the '@' prefix

                switch ( input ) {
                    case "linus" -> { return new Linus("Linus"); }
                    case "circe" -> { return new Circe("Circe"); }
                    case "randy" -> { return new Randy("Randy"); }
                    default -> printAlert("TODO: Implement computer players");
                }
            } else {
                return new lab5.players.HumanPlayer(input);
            }
        }
    }

    /**
     * Display the given game board
     * @param board A tictactoe game board
     */
    public static void showBoard(Board board) {
        var sb = new StringBuilder();
        for (var c : board.toString().toCharArray()) {
            if ( c == 'X' ) {
                sb.append(colorize("X", BRIGHT_CYAN_TEXT()));
            } else if ( c == 'O' ) {
                sb.append(colorize("O", BRIGHT_MAGENTA_TEXT()));
            } else {
                sb.append(c);
            }
        }
        System.out.println(sb);
    }

    /**
     * Repeatedly prompt the user for a position on which to place their next token.
     * If they enter an invalid response they are re-prompted.
     * @param prompt The prompt to display to the user
     * @return The position selected by the user
     */
    public static Position promptForPosition(String prompt) {

        final String helpMessage = "Input must be in the format 'row column', e.g., '1 2' or 't m' for the top middle cell.";

        while ( true ) {
            var input = prompt(fPrompt.format(prompt));

            if ( input.length() != 3 ) {
                printAlert(helpMessage);
                continue;
            }

            var parts = input.split(" ");

            if ( parts.length != 2 ) {
                printAlert(helpMessage);
                continue;
            }

            try {
                return new Position(Row.from(parts[0]), Col.from(parts[1]));
            } catch ( IllegalArgumentException e ) {
                printAlert(helpMessage);
            }
        }
    }
}
