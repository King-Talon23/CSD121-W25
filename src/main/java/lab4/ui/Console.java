package lab4.ui;

import lab4.game.*;

import java.util.Scanner;

import static com.diogonunes.jcolor.Ansi.*;
import static com.diogonunes.jcolor.Attribute.*;
/**
 * Helper methods for doing console-based user interaction
 */
public class Console {

    public static void println(String message) {
        System.out.println(message);
    }

    /**
     * Prompt the user for input using the given promptMessage
     * @param promptMessage The message to prompt the user with
     * @return The user's response
     */
    public static String prompt(String promptMessage) {
        System.out.print(colorize(promptMessage, GREEN_TEXT(),BLACK_BACK()));
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Display the given game board
     * @param board A tictactoe game board
     */
    public static void showBoard(Board board) {
        System.out.print(board);
    }

    /**
     * Repeatedly prompt the user for a position on which to place their next token.
     * If they enter an invalid response or an already-taken position they are re-prompted.
     * @param prompt The prompt to display to the user
     * @param board The current state of the game board
     * @return The position selected by the user
     */
    public static Position promptForPosition(String prompt, Board board) {


        var scanner = new Scanner(System.in);
        final String helpMe = "Input must be in the format 'row column', e.g., '1 2' or 't m' for the top middle cell.";
        final String helpMessage = colorize(helpMe, RED_TEXT(),BLACK_BACK());

        while ( true ) {
            System.out.print(colorize(prompt, GREEN_TEXT(),BLACK_BACK()));
            var input = scanner.nextLine().trim();

            if ( input.length() != 3 ) {
                System.out.println(helpMessage);
                continue;
            }

            var parts = input.split(" ");

            if ( parts.length != 2 ) {
                System.out.println(helpMessage);
                continue;
            }

            // The .from methods may throw if the user entered invalid location text, so we try/catch
            try {

                var pos = new Position(Row.from(parts[0]), Col.from(parts[1]));

                if (board.isOccupiedAt(pos)) {
                    System.out.println("That position is already taken.");
                    continue;
                }

                return pos;
            } catch ( IllegalArgumentException e ) {
                System.out.println(helpMessage);
            }
        }
    }
}
