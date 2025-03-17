package lab5.players;

import lab5.game.Board;
import lab5.game.Position;

import java.util.*;

public class Linus extends Player {
    String currentPos = "t l";
    static Map<String, String> moveOrder = new HashMap<>();
    static {
        moveOrder.put("t l", "t m");
        moveOrder.put("t m", "t r");
        moveOrder.put("t r", "m l");
        moveOrder.put("m l", "m m");
        moveOrder.put("m m", "m r");
        moveOrder.put("m r", "b l");
        moveOrder.put("b l", "b m");
        moveOrder.put("b m", "b r");
        moveOrder.put("b r", "d d"); // done&done case
    }

    public Linus(String name) {
        super(name);
    }

    @Override
    public Position pickNextMove(Board board) {

        for (String i = currentPos; !Objects.equals(i, "d d"); i = moveOrder.get(i)) {
            Position position = Position.parse(i);
            if (board.isEmptyAt(position)) {
                currentPos = i; // save current position
                return position;
            }
        }
        return null;
    }



    @Override
    public void promptMove() {

    }
}

