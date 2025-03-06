package lab4;

import lab4.game.TicTacToeGame;
import lab4.ui.Console;
import static com.diogonunes.jcolor.Ansi.*;

import static com.diogonunes.jcolor.Attribute.*;
import static lab4.game.Board.Status.InProgress;

public class Main {

    public static void main(String[] args) {


        var player1 = Console.prompt("Player 1, what is your name? ");
        var player2 = Console.prompt("Player 2, what is your name? ");

        var game = new TicTacToeGame(player1, player2);

        while (true) {

            var move = Console.promptForPosition(game.whoseTurn() + ", pick your move: ", game.getBoard());

            Console.println(colorize("%s plays %s %s", GREEN_TEXT(),BLACK_BACK()).formatted(game.whoseTurn(), move.row(), move.col()));

            // Advance the game based on the player's selected move
            game.doNextTurn(move);

            Console.showBoard(game.getBoard());

            // Decide what to do next based on the current status of the game
            var status = game.getBoard().getStatus();
            switch (status) {
                case XWins -> System.out.println(colorize(player1 + " wins!", GREEN_TEXT(),BLACK_BACK()));
                case OWins -> System.out.println(colorize(player2 + " wins!", GREEN_TEXT(),BLACK_BACK()));
                case Draw -> System.out.println(colorize("It's a draw!", YELLOW_TEXT(),BLACK_BACK()));
            }

            // Any status other than InProgress is an end-game state, so break out of the loop
            if ( status != InProgress ) {
                break;
            }

        }
    }
}
