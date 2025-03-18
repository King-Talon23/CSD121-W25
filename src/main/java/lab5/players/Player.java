package lab5.players;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Position;
import lab5.game.Row;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a player in the game.
 */
public abstract class Player {

    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public abstract Position pickNextMove(Board board);


    public String getName() {
        return name;
    }


    public abstract void promptMove();

    public interface hasSetMoves {
        Row top = Row.from("t");
        Row midRow = Row.from("m");
        Row bottom = Row.from("b");
        Col left = Col.from("l");
        Col midCol = Col.from("m");
        Col right = Col.from("r");
        Position topLeft = new Position(top, left);
        Position topMid = new Position(top, midCol);
        Position topRight = new Position(top, right);
        Position midLeft = new Position(midRow, left);
        Position midMid = new Position(midRow, midCol);
        Position midRight = new Position(midRow, right);
        Position botLeft = new Position(bottom, left);
        Position botMid = new Position(bottom, midCol);
        Position botRight = new Position(bottom, right);

        // only used for Linus and Circe to reduce repeated code
        default Position findNextMove(Position start, Map<Position, Position> moveOrder, Board board) {
            for (Position i = start; !Objects.equals(i, null); i = moveOrder.get(i)) {
                if (board.isEmptyAt(i)) {
                    return i;
                }
            }
            return null;
        }
    }

}
