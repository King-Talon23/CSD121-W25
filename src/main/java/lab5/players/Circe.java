package lab5.players;

import lab5.game.Board;
import lab5.game.Board.*;
import lab5.game.Position;

import java.util.*;

public class Circe extends Player {
    String currentPos = "m m";
    static Map<String, String> moveOrder = new HashMap<>();
    static {
        moveOrder.put("m m", "t m");
        moveOrder.put("t m", "t r");
        moveOrder.put("t r", "m r");
        moveOrder.put("m r", "b r");
        moveOrder.put("b r", "b m");
        moveOrder.put("b m", "b l");
        moveOrder.put("b l", "m l");
        moveOrder.put("m l", "t l");
        moveOrder.put("t l", "d d"); // done&done case
    }

    public Circe(String name) {
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
