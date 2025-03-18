package lab5.players;

import lab5.game.Board;
import lab5.game.Position;
import lab5.players.Player.*;

import java.util.*;

public class Circe extends Player implements hasSetMoves {
    Position currentMove = midMid;
    static final Map<Position, Position> moveOrder = Map.of(
            midMid, topMid,
            topMid, topRight,
            topRight, midRight,
            midRight, botRight,
            botRight, botMid,
            botMid, botLeft,
            botLeft, midLeft,
            midLeft, topLeft
    );


    public Circe(String name) {
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
