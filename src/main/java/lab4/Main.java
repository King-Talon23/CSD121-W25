package lab4;

import lab4.game.TicTacToeGame;
import lab4.ui.Console;

import static lab4.game.Board.Status.InProgress;

public class Main {

    public static void main(String[] args) {


        var player1 = Console.prompt("Player 1, what is your name? ");
        var player2 = Console.prompt("Player 2, what is your name? ");

        var game = new TicTacToeGame(player1, player2);

        while (true) {

            var move = Console.promptForPosition(game.whoseTurn() + ", pick your move: ", game.getBoard());

            Console.println("%s plays %s %s".formatted(game.whoseTurn(), move.row(), move.col()));

            // Advance the game based on the player's selected move
            game.doNextTurn(move);

            Console.showBoard(game.getBoard());

            // Decide what to do next based on the current status of the game
            var status = game.getBoard().getStatus();
            switch (status) {
                case XWins -> System.out.println(player1 + " wins!");
                case OWins -> System.out.println(player2 + " wins!");
                case Draw -> System.out.println("It's a draw!");
            }

            // Any status other than InProgress is an end-game state, so break out of the loop
            if ( status != InProgress ) {
                break;
            }

        }
    }
}
