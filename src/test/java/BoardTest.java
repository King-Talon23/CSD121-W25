import lab4.game.Board;
import lab4.game.Col;
import lab4.game.Position;
import lab4.game.Row;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Row topRow  = Row.from("1");
    Col leftCol  = Col.from("1");
    Row midRow  = Row.from("2");
    Col midCOl  = Col.from("2");
    Row botRow  = Row.from("3");
    Col rightCol  = Col.from("3");

    @Test
    void testBoardInitialization() {
        Board board = new Board();
        assertFalse(board.isFull());
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                String stringRow = String.valueOf(row);
                String stringCol = String.valueOf(col);
                assertFalse(board.isOccupiedAt(new Position(Row.from(stringRow), Col.from(stringCol))));
            }
        }
    }

    @Test
    void testIsFull() {
        Board board = new Board();
        assertFalse(board.isFull()); // should be empty

        // Fill board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                String stringRow = String.valueOf(row);
                String stringCol = String.valueOf(col);
                board.placeX(new Position(Row.from(stringRow), Col.from(stringCol)));
            }
        }
        assertTrue(board.isFull()); // should be full
    }

    @Test
    void testIsOccupiedAt() {
        Board board = new Board();
        Position pos = new Position(topRow, leftCol);
        assertFalse(board.isOccupiedAt(pos));

        board.placeX(pos);
        assertTrue(board.isOccupiedAt(pos));
    }

    @Test
    void testPlaceXAndPlaceO() {
        Board board = new Board();
        Position posX = new Position(topRow, leftCol);
        Position posO = new Position(midRow, midCOl);

        board.placeX(posX);
        board.placeO(posO);

        assertTrue(board.isOccupiedAt(posX));
        assertTrue(board.isOccupiedAt(posO));
    }

    @Test
    void testGetStatusInProgress() {
        Board board = new Board();
        assertEquals(Board.Status.InProgress, board.getStatus());
    }

    @Test
    void testGetStatusDraw() {

        Board board = new Board();
        board.placeX(new Position(topRow, leftCol));
        board.placeO(new Position(topRow, midCOl));
        board.placeX(new Position(topRow, rightCol));
        board.placeO(new Position(midRow, leftCol));
        board.placeX(new Position(midRow, midCOl));
        board.placeO(new Position(midRow, rightCol));
        board.placeX(new Position(botRow, leftCol));
        board.placeO(new Position(botRow, midCOl));
        board.placeX(new Position(botRow, rightCol));

        assertEquals(Board.Status.Draw, board.getStatus());
    }

    @Test
    void testGetStatusXWins() {
        Board board = new Board();
        board.placeX(new Position(topRow, leftCol));
        board.placeX(new Position(topRow, midCOl));
        board.placeX(new Position(topRow, rightCol));

        assertEquals(Board.Status.XWins, board.getStatus());
    }

    @Test
    void testToString() {
        Board board = new Board();
        board.placeX(new Position(topRow, leftCol));
        board.placeO(new Position(midRow, midCOl));
        String expected = "X..\n.O.\n...\n";
        assertEquals(expected, board.toString());
    }


}

