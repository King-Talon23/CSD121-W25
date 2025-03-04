package lab4.ui;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;
import lab4.game.*;

import javax.swing.*;
import java.util.Scanner;

/**
 * Helper methods for doing console-based user interaction
 */
public class Console {

    static Attribute redText = Attribute.TEXT_COLOR(255, 0, 0);
    static Attribute whiteText = Attribute.TEXT_COLOR(255, 255, 255);

    static Attribute bold = Attribute.BOLD();

    public static void println(String message) {
        System.out.println(message);
    }

    /**
     * Prompt the user for input using the given promptMessage
     *
     * @param promptMessage The message to prompt the user with
     * @return The user's response
     */
    public static String prompt(String promptMessage) {
        System.out.print(promptMessage);
        var scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Display the given game board
     *
     * @param board A tictactoe game board
     */
    public static void showBoard(Board board) {
        System.out.print(board);
    }

    /**
     * Repeatedly prompt the user for a position on which to place their next token.
     * If they enter an invalid response or an already-taken position they are re-prompted.
     *
     * @param prompt The prompt to display to the user
     * @param board  The current state of the game board
     * @return The position selected by the user
     */
    public static Position promptForPosition(String prompt, Board board) {


        var scanner = new Scanner(System.in);
        // made help message bold
       final String helpMessage = Ansi.colorize("\nInput must be in the format 'row column'\ne.g., " +
               "'1 2' or 't m' for the top middle cell.\n",whiteText, bold);

        while (true) {
            System.out.print(prompt);
            var input = scanner.nextLine().trim();

            if (input.length() != 3) {
                System.out.println(helpMessage);
                continue;
            }

            var parts = input.split(" ");

            if (parts.length != 2) {
                System.out.println(helpMessage);
                continue;
            }

            // The .from methods may throw if the user entered invalid location text, so we try/catch
            try {

                var pos = new Position(Row.from(parts[0]), Col.from(parts[1]));

                if (board.isOccupiedAt(pos)) {
                    // made text red and bold, also added extra newline so its never touching other text
                    String alreadyTaken = Ansi.colorize("\nThat position is already taken.\n", redText, bold);
                    System.out.println(alreadyTaken);
                    continue;
                }

                return pos;
            } catch (IllegalArgumentException e) {
                System.out.println(helpMessage);
            }
        }
    }
}
