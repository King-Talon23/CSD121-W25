package tictactoe;

import tictactoe.game.TicTacToeGame;
import tictactoe.ui.Console;

import static tictactoe.game.PlayerToken.O;
import static tictactoe.game.PlayerToken.X;

public class Main {

    public static void main(String[] args) {

        Console.println("""
                Welcome to Tic Tac Toe!
                Players can be human or computer.  When prompted for player names use one of the following:
                - To play as a human, just enter a name
                - To have the player played by the computer enter @ followed by one of the following names:
                  - TODO: Add the names of the computer players you have implemented here!
                """);
        var player1 = Console.promptForPlayer(X);
        var player2 = Console.promptForPlayer(O);
        var game = new TicTacToeGame(player1, player2);

        while (true) {

            // Advance the game based on the player's selected move, and get the results
            var turnRecord = game.doNextTurn();

            // Display the results of the turn
            Console.println("%s plays %s at %s %s".formatted(turnRecord.whoseTurn().getName(), turnRecord.token(), turnRecord.positionPlayed().row(), turnRecord.positionPlayed().col()));
            var newBoard = turnRecord.newBoardState();
            Console.showBoard(newBoard);

            // Decide what to do next based on the current status of the game
            switch ( newBoard.getStatus() ) {
                case Draw -> {
                    Console.println("It's a draw!");
                    System.exit(0);
                }
                case XWins, OWins -> {
                    Console.println("%s wins!".formatted(turnRecord.whoseTurn().getName()));
                    System.exit(0);
                }
            }

        }
    }
}
