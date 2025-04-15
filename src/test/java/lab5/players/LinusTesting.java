package lab5.players;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Position;
import lab5.game.Row;
import org.junit.jupiter.api.Test;

import static lab5.game.PlayerToken.O;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinusTesting {
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

    @Test
    public void testInitialization() {
        Linus player = new Linus("Linus");
        assertEquals(topLeft, player.currentMove);
        assertEquals("Linus", player.getName());
    }

    @Test
    public void testMoveOrder() {
        // check the entirety of Linus's move order
        Linus player = new Linus("Linus");
        Board board = new Board();

        assertEquals(topLeft, player.pickNextMove(board));
        assertEquals(topMid, player.pickNextMove(board));
        assertEquals(topRight, player.pickNextMove(board));

        assertEquals(midLeft, player.pickNextMove(board));
        assertEquals(midMid, player.pickNextMove(board));
        assertEquals(midRight, player.pickNextMove(board));

        assertEquals(botLeft, player.pickNextMove(board));
        assertEquals(botMid, player.pickNextMove(board));
        assertEquals(botRight, player.pickNextMove(board));
    }

    @Test
    public void testSkipOccupiedPositions() {
        // make sure Linus skips over filled positions
        Linus player = new Linus("Linus");
        Board board = new Board();
        board.place(midMid, O);
        board.place(topMid, O);

        assertEquals(topRight, player.pickNextMove(board));
    }
}
