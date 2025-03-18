package lab5.players;

import lab5.game.Board;
import lab5.game.Position;

import java.util.*;

public class Linus extends Player implements Player.hasSetMoves{
    Position currentMove = topLeft;
    static Map<Position, Position> moveOrder = new HashMap<>();
    static {
        moveOrder.put(topLeft, topMid);
        moveOrder.put(topMid, topRight);
        moveOrder.put(topRight, midLeft);
        moveOrder.put(midLeft, midMid);
        moveOrder.put(midMid, midRight);
        moveOrder.put(midRight, botLeft);
        moveOrder.put(botLeft, botMid);
        moveOrder.put(botMid, botRight);
        moveOrder.put(botRight, null);
    }

    public Linus(String name) {
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

