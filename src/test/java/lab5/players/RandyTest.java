package lab5.players;

import lab5.game.Board;
import lab5.game.Position;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class RandyTest {


  @Test
  void testRandyIsRandom(){
    Board board = new Board();
    int BoardTests = 10000;
    Randy randy = new Randy("Randy");
    List<Position> moveFrequency = new ArrayList<>();



    for (int testLoop = 0; testLoop <BoardTests; testLoop++)
    {
      Position chosenMove = randy.pickNextMove(board);
      moveFrequency.add(chosenMove);
    }


    Map <Position, Integer> moveCount = new HashMap<>();
    for (Position move : moveFrequency){
      moveCount.put(move, moveCount.getOrDefault(move, 0) +1);
  }


    // deviancy of approximately 0.15% allowed, 10000/9 = 1111, + or - .15% = 1278++ & 944--
    System.out.println("10000 Tests run, deviancy should not be greater than 1278 or lesser than 944");
    System.out.println("RANDY RANDOMISER TEST RESULTS: ");
    int lowerbounds = 944;
    int upperbounds = 1278;
    for (Map.Entry<Position, Integer> entry : moveCount.entrySet()) {
      System.out.println(entry.getKey() + "" + entry.getValue());
      int deviancyCount = entry.getValue();
      assertTrue(deviancyCount >= lowerbounds && deviancyCount <= upperbounds, "Move distribution too skewed"  );

    }
  }

}
