package TK.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class game {
    static Random rd = new Random();


    public static void createMap(){
        int totalAmountOfRooms = getRandomIntInRange(50, 100);
        while ((totalAmountOfRooms / 10) != 0) {
            totalAmountOfRooms += 1;
        }
        totalAmountOfRooms += 1; // game end room
        ArrayList<Objects> room = new ArrayList<>();
        int currentRoomNumber = 0;
    }

    public static void print(String text) {
        System.out.print(text);
    }
    public static void println(String text) {
        System.out.println(text);
    }
}
