package lab5.players;
import java.util.*;

import lab5.game.Board;
import lab5.game.*;


public class Omola extends Player  {
private final Random randomMove = new Random();

    public Omola(String name) {
        super(name);
    }

    @Override
    public Position pickNextMove(Board currentBoard) {
        List<Position> availableMoves = currentBoard.getEmptyCells();
        PlayerToken omolaToken = currentBoard.getNextTurnToken();
        PlayerToken opponentToken = (omolaToken == PlayerToken.X) ? PlayerToken.O : PlayerToken.X;

        for (Position move : availableMoves){
            Board boardCopy = new Board(currentBoard);
            boardCopy.place(move, omolaToken);
            if (boardCopy.getWinner() == omolaToken){
                return move;
            }
        }

        for (Position move : availableMoves){
            Board boardCopy = new Board(currentBoard);
            boardCopy.place(move, opponentToken);
            if (boardCopy.getWinner() == opponentToken) {
                return move;
            }


        }
        return availableMoves.get(randomMove.nextInt(availableMoves.size()));

    }



    @Override
    public void promptMove() {

    }
}
