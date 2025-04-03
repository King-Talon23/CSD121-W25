package lab6.Utility;

import javafx.scene.control.Button;
import lab6.Entities.AlienStuff.Alien;
import lab6.Entities.AlienStuff.Aliens.AdventOfficer;

import java.util.*;

import lab6.Entities.AlienStuff.Aliens.*;
import lab6.Entities.SoldierStuff.Cover;
import lab6.Entities.SoldierStuff.Soldier;

import static lab6.Entities.SoldierStuff.Cover.*;
import static lab6.Entities.SoldierStuff.Cover.FULL;

public class GetRandom {
    static Random rd = new Random();

    public static int intInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Minimum cannot be higher than maximum");
        }
        return rd.nextInt((max - min) + 1) + min;
    }

    public static String stringListItem(List<String> list) {
        if (!(list instanceof List<String>)) {
            throw new IllegalArgumentException("Wrong list type");
        }
        return list.get(rd.nextInt(list.size()));
    }

    public static Alien anyAlien() {
        int num = intInRange(1, 1);
        return switch (num) {
            case 1 -> new AdventOfficer();
            default -> throw new IllegalStateException("Unexpected value: " + num);
        };
    }

    public static Cover cover() {
        Cover[] coverMap = new Cover[]{FLANKED, FLANKED, NONE, NONE, NONE, HALF, HALF, HALF, FULL, FULL};
        return coverMap[rd.nextInt(coverMap.length)];
    }
}
