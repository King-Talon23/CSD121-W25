package lab4.game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {


    @Test
    void doesBoardStartEmpty() {
        Board board = new Board();
        for (Row row : Row.values()) {
            for (Col col : Col.values()) {
                Position pos = new Position(row, col);
                assertFalse(board.isOccupiedAt(pos));
            }
        }
    }


    @Test
    void GetStatusStartsInProgress() {
        Board board = new Board();
        assertEquals(Board.Status.InProgress, board.getStatus());
    }

    @Test
    void getStatusWinWorksForXVertical(){
        Board board = new Board();
        board.placeX(new Position(Row.Middle, Col.Middle));
        board.placeX(new Position(Row.Top, Col.Middle));
        board.placeX(new Position(Row.Bottom, Col.Middle));
        assertEquals(Board.Status.XWins, board.getStatus());
    }
    @Test
    void getStatusWinWorksForXDiagonal1(){
        Board board = new Board();
        board.placeX(new Position(Row.Middle, Col.Middle));
        board.placeX(new Position(Row.Top, Col.Right));
        board.placeX(new Position(Row.Bottom, Col.Left));
        assertEquals(Board.Status.XWins, board.getStatus());
    }
    @Test
    void getStatusWinWorksForXDiagonal2(){
        Board board = new Board();
        board.placeX(new Position(Row.Middle, Col.Middle));
        board.placeX(new Position(Row.Top, Col.Left));
        board.placeX(new Position(Row.Bottom, Col.Right));
        assertEquals(Board.Status.XWins, board.getStatus());
    }




    @Test
    void getStatusWinWorksForXHorizontal(){
        Board board = new Board();
        board.placeX(new Position(Row.Middle, Col.Middle));
        board.placeX(new Position(Row.Middle, Col.Left));
        board.placeX(new Position(Row.Middle, Col.Right));
        assertEquals(Board.Status.XWins, board.getStatus());
    }










    @Test
    void getStatusWinWorksForOVertical(){
        Board board = new Board();
        board.placeO(new Position(Row.Middle, Col.Middle));
        board.placeO(new Position(Row.Top, Col.Middle));
        board.placeO(new Position(Row.Bottom, Col.Middle));
        assertEquals(Board.Status.OWins, board.getStatus());
    }
    @Test
    void getStatusWinWorksForODiagonal1(){
        Board board = new Board();
        board.placeO(new Position(Row.Middle, Col.Middle));
        board.placeO(new Position(Row.Top, Col.Right));
        board.placeO(new Position(Row.Bottom, Col.Left));
        assertEquals(Board.Status.OWins, board.getStatus());
    }
    @Test
    void getStatusWinWorksForODiagonal2(){
        Board board = new Board();
        board.placeO(new Position(Row.Middle, Col.Middle));
        board.placeO(new Position(Row.Top, Col.Left));
        board.placeO(new Position(Row.Bottom, Col.Right));
        assertEquals(Board.Status.OWins, board.getStatus());
    }




    @Test
    void getStatusWinWorksForOHorizontal(){
        Board board = new Board();
        board.placeO(new Position(Row.Middle, Col.Middle));
        board.placeO(new Position(Row.Middle, Col.Left));
        board.placeO(new Position(Row.Middle, Col.Right));
        assertEquals(Board.Status.OWins, board.getStatus());
    }






    @Test
    void getStatusDrawWorks(){
        Board board = new Board();
        board.placeO(new Position(Row.Middle, Col.Middle));
        board.placeX(new Position(Row.Top, Col.Middle));
        board.placeX(new Position(Row.Bottom, Col.Middle));
        board.placeO(new Position(Row.Middle, Col.Right));
        board.placeX(new Position(Row.Top, Col.Right));
        board.placeX(new Position(Row.Bottom, Col.Right));
        board.placeX(new Position(Row.Middle, Col.Left));
        board.placeO(new Position(Row.Top, Col.Left));
        board.placeO(new Position(Row.Bottom, Col.Left));
        assertEquals(Board.Status.Draw, board.getStatus());
    }

    @Test
    void isFullWorksWhenFull() {
        Board board = new Board();
        for (Row row : Row.values()) {
            for (Col col : Col.values()) {
                Position pos = new Position(row, col);
                board.placeX(pos);

            }

        }
        assertTrue(board.isFull());
    }
    @Test
    void isFullFailsWhenEmpty() {
        Board board = new Board();
        assertFalse(board.isFull());
    }
    @Test
    void isFullFailsWhenNotFull() {
        Board board = new Board();
        board.placeO(new Position(Row.Middle, Col.Middle));
        assertFalse(board.isFull());
    }


    @Test
    void isOccupiedWorksWhenOccupied() {
        Board board = new Board();
        for (Row row : Row.values()) {
            for (Col col : Col.values()) {
                Position pos = new Position(row, col);
                board.placeX(pos);
                assertTrue(board.isOccupiedAt(pos));
            }

        }

    }
    @Test
    void isOccupiedFailsWhenNotOccupied() {
        Board board = new Board();
        assertFalse(board.isOccupiedAt(new Position(Row.Middle, Col.Middle)));
    }





    @Test
    void placeXWorks() {
        Board board = new Board();
        board.placeX(new Position(Row.Middle, Col.Middle));
        assertTrue(board.isOccupiedAt(new Position(Row.Middle, Col.Middle)));
    }
    @Test
    void placeOWorks() {
        Board board = new Board();
        board.placeO(new Position(Row.Middle, Col.Middle));
        assertTrue(board.isOccupiedAt(new Position(Row.Middle, Col.Middle)));
    }



    private String removeColours(String input){
    return input.replaceAll("\\e\\[[;\\d]*m","");

    }
    @Test
    void ToStringWorks() {
        Board board = new Board();
        board.placeO(new Position(Row.Middle, Col.Middle));
        board.placeX(new Position(Row.Top, Col.Middle));
        String expectedBoard =
                ".X.\n" +
                        ".O.\n" +
                        "...\n";
        assertEquals(expectedBoard, removeColours(board.toString()));
    }

        @Test
        void ToStringWorksWhenEmpty(){
            Board board = new Board();

            String expectedBoard =
                            "...\n" +
                            "...\n" +
                            "...\n";
            assertEquals(expectedBoard, removeColours(board.toString()));

    }








}

















