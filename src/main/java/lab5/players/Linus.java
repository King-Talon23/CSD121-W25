package lab5.players;

import lab5.game.Board;
import lab5.game.Position;
import lab5.players.Player.*;

import java.util.*;

public class Linus extends Player implements hasSetMoves{
    public Position currentMove = topLeft;
    static final Map<Position, Position> moveOrder = Map.of(
            topLeft, topMid,
            topMid, topRight,
            topRight, midLeft,
            midLeft, midMid,
            midMid, midRight,
            midRight, botLeft,
            botLeft, botMid,
            botMid, botRight
    );

    public Linus(String name) {
        super(name);
    }

    @Override
    public Position pickNextMove(Board board) {
        Position move = findNextMove(currentMove, moveOrder, board);
        currentMove = moveOrder.get(move); // store the next move in sequence for the next turn
        return move;
    }


    @Override
    public void promptMove() {

    }
}

