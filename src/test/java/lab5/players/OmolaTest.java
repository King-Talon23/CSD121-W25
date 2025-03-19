package lab5.players;

import lab5.game.Board;
import lab5.game.Col;
import lab5.game.Position;
import lab5.game.Row;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OmolaTest {
    
    
    @Test
    void testOmolaPicksWinningMoveTopHorizontal(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeO(new Position(Row.Top, Col.Left));
        board.placeO(new Position(Row.Top, Col.Right));
        Position winningMove = omola.pickNextMove(board);
        board.placeO(winningMove);
        assertEquals(Board.Status.OWins, board.getStatus());
    }
    @Test
    void testOmolaPicksWinningMoveMiddleHorizontal(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeO(new Position(Row.Middle, Col.Left));
        board.placeO(new Position(Row.Middle, Col.Right));
        Position winningMove = omola.pickNextMove(board);
        board.placeO(winningMove);
        assertEquals(Board.Status.OWins, board.getStatus());
    }

    @Test
    void testOmolaPicksWinningMoveBottomHorizontal(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeO(new Position(Row.Bottom, Col.Left));
        board.placeO(new Position(Row.Bottom, Col.Right));
        Position winningMove = omola.pickNextMove(board);
        board.placeO(winningMove);
        assertEquals(Board.Status.OWins, board.getStatus());
    }
    @Test
    void testOmolaPicksWinningMoveLeftVertical(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeO(new Position(Row.Top, Col.Left));
        board.placeO(new Position(Row.Bottom, Col.Left));
        Position winningMove = omola.pickNextMove(board);
        board.placeO(winningMove);
        assertEquals(Board.Status.OWins, board.getStatus());
    }
    @Test
    void testOmolaPicksWinningMoveMiddleVertical(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeO(new Position(Row.Top, Col.Middle));
        board.placeO(new Position(Row.Bottom, Col.Middle));
        Position winningMove = omola.pickNextMove(board);
        board.placeO(winningMove);
        assertEquals(Board.Status.OWins, board.getStatus());
    }

    @Test
    void testOmolaPicksWinningMoveRightVertical(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeO(new Position(Row.Top, Col.Right));
        board.placeO(new Position(Row.Bottom, Col.Right));
        Position winningMove = omola.pickNextMove(board);
        board.placeO(winningMove);
        assertEquals(Board.Status.OWins, board.getStatus());
    }

    @Test
    void testOmolaPicksWinningMoveDiagonal(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeO(new Position(Row.Top, Col.Right));
        board.placeO(new Position(Row.Bottom, Col.Left));
        Position winningMove = omola.pickNextMove(board);
        board.placeO(winningMove);
        assertEquals(Board.Status.OWins, board.getStatus());
    }




    @Test
    void testOmolaPicksBlockingMoveTopHorizontal(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeX(new Position(Row.Top, Col.Left));
        board.placeX(new Position(Row.Top, Col.Right));
        Position blockedPosition = new Position(Row.Top, Col.Middle);
        Position blockingMove = omola.pickNextMove(board);
        board.placeO(blockingMove);
        assertEquals(blockingMove, blockedPosition);
    }

    @Test
    void testOmolaPicksBlockingMoveMiddleHorizontal(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeX(new Position(Row.Middle, Col.Left));
        board.placeX(new Position(Row.Middle, Col.Right));
        Position blockedPosition = new Position(Row.Middle, Col.Middle);
        Position blockingMove = omola.pickNextMove(board);
        board.placeO(blockingMove);
        assertEquals(blockingMove, blockedPosition);
    }

    @Test
    void testOmolaPicksBlockingMoveBottomHorizontal(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeX(new Position(Row.Bottom, Col.Left));
        board.placeX(new Position(Row.Bottom, Col.Right));
        Position blockedPosition = new Position(Row.Bottom, Col.Middle);
        Position blockingMove = omola.pickNextMove(board);
        board.placeO(blockingMove);
        assertEquals(blockingMove, blockedPosition);
    }
    @Test
    void testOmolaPicksBlockingMoveLeftVertical(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeX(new Position(Row.Top, Col.Left));
        board.placeX(new Position(Row.Bottom, Col.Left));
        Position blockedPosition = new Position(Row.Middle, Col.Left);
        Position blockingMove = omola.pickNextMove(board);
        board.placeO(blockingMove);
        assertEquals(blockingMove, blockedPosition);
    }
    @Test
    void testOmolaPicksBlockingMoveMiddleVertical(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeX(new Position(Row.Top, Col.Middle));
        board.placeX(new Position(Row.Bottom, Col.Middle));
        Position blockedPosition = new Position(Row.Middle, Col.Middle);
        Position blockingMove = omola.pickNextMove(board);
        board.placeO(blockingMove);
        assertEquals(blockingMove, blockedPosition);
    }

    @Test
    void testOmolaPicksBlockingMoveRightVertical(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeX(new Position(Row.Top, Col.Right));
        board.placeX(new Position(Row.Bottom, Col.Right));
        Position blockedPosition = new Position(Row.Middle, Col.Right);
        Position blockingMove = omola.pickNextMove(board);
        board.placeO(blockingMove);
        assertEquals(blockingMove, blockedPosition);
    }
    @Test
    void testOmolaPicksBlockingMoveDiagonal(){
        Board board = new Board();
        Omola omola = new Omola("Omola");
        board.placeX(new Position(Row.Top, Col.Left));
        board.placeX(new Position(Row.Bottom, Col.Right));
        Position blockedPosition = new Position(Row.Middle, Col.Middle);
        Position blockingMove = omola.pickNextMove(board);
        board.placeO(blockingMove);
        assertEquals(blockingMove, blockedPosition);
    }
    
    
    
    

    @Test
    void testOmolaIsRandomWhenBoardIsEmpty(){
        Board board = new Board();
        int BoardTests = 10000;
        Omola omola = new Omola("Omola");
        List<Position> moveFrequency = new ArrayList<>();



        for (int testLoop = 0; testLoop <BoardTests; testLoop++)
        {
            Position chosenMove = omola.pickNextMove(board);
            moveFrequency.add(chosenMove);
        }


        Map<Position, Integer> moveCount = new HashMap<>();
        for (Position move : moveFrequency){
            moveCount.put(move, moveCount.getOrDefault(move, 0) +1);
        }


        // deviancy of approximately 0.15% allowed, 10000/9 = 1111, + or - .15% = 1278++ & 944--
        System.out.println("10000 Tests run, deviancy should not be greater than 1278 or lesser than 944");
        System.out.println("OMOLA RANDOMIZER TEST RESULTS: ");
        int lowerbounds = 944;
        int upperbounds = 1278;
        for (Map.Entry<Position, Integer> entry : moveCount.entrySet()) {
            System.out.println(entry.getKey() + "" + entry.getValue());
            int deviancyCount = entry.getValue();
            assertTrue(deviancyCount >= lowerbounds && deviancyCount <= upperbounds, "Move distribution too skewed"  );

        }
    }

}