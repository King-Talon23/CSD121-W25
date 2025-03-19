package lab5;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Row;
import lab5.players.Circe;
import lab5.game.Position;
import org.junit.jupiter.api.Test;
import static lab5.game.PlayerToken.O;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CirceTesting {
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
        Circe player = new Circe("Circe");
        assertEquals(midMid, player.currentMove);
        assertEquals("Circe", player.getName());
    }

    @Test
    public void testMoveOrder() {
        // check the entirety of Circe's move order
        Circe player = new Circe("Circe");
        Board board = new Board();

        assertEquals(midMid, player.pickNextMove(board));
        assertEquals(topMid, player.pickNextMove(board));
        assertEquals(topRight, player.pickNextMove(board));

        assertEquals(midRight, player.pickNextMove(board));
        assertEquals(botRight, player.pickNextMove(board));
        assertEquals(botMid, player.pickNextMove(board));

        assertEquals(botLeft, player.pickNextMove(board));
        assertEquals(midLeft, player.pickNextMove(board));
        assertEquals(topLeft, player.pickNextMove(board));
    }

    @Test
    public void testSkipOccupiedPositions() {
        // make sure Circe skips over filled positions
        Circe circe = new Circe("Circe");
        Board board = new Board();
        board.place(midMid, O);
        board.place(topMid, O);

        assertEquals(topRight, circe.pickNextMove(board));
    }








}
