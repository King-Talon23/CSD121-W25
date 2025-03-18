package lab5.players;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Position;
import lab5.game.Row;

import java.util.*;

public class Circe extends Player implements Player.hasSetMoves {
    Position currentMove = midMid;
    static Map<Position, Position> moveOrder = new HashMap<>();
    static {
        moveOrder.put(midMid, topMid);
        moveOrder.put(topMid, topRight);
        moveOrder.put(topRight, midRight);
        moveOrder.put(midRight, botRight);
        moveOrder.put(botRight, botMid);
        moveOrder.put(botMid, botLeft);
        moveOrder.put(botLeft, midLeft);
        moveOrder.put(midLeft, topLeft);
        moveOrder.put(topLeft, null);
    }

    public Circe(String name) {
        super(name);
    }

    @Override
    public Position pickNextMove(Board board) {
        Position move = findNextMove(currentMove, moveOrder, board);
        // start checking what is the next move in sequence next turn
        currentMove = moveOrder.get(move);
        return move;
    }


    @Override
    public void promptMove() {

    }
}
