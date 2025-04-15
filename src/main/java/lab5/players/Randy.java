package lab5.players;

import lab5.game.Board;
import lab5.game.Position;


import java.util.*;

public class Randy extends lab5.players.Player {
    public Randy(String name) {
        super(name);
    }


    @Override
    public Position pickNextMove(Board currentBoard) {
        List<Position> moveList = currentBoard.getEmptyCells();
        if (!moveList.isEmpty()) {
            Random randy = new Random();
            return moveList.get(randy.nextInt((moveList.size())));
        }
        return null;
    }




    @Override
    public void promptMove() {

    }
}
