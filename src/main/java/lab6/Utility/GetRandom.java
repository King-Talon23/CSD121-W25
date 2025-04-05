package lab6.Utility;

import lab6.Entities.AlienStuff.Alien;
import lab6.Entities.AlienStuff.Aliens.AdventOfficer;

import java.util.*;

import lab6.Entities.AlienStuff.Aliens.*;
import lab6.Entities.SoldierStuff.Cover;

import static lab6.Entities.SoldierStuff.Cover.*;
import static lab6.Entities.SoldierStuff.Cover.FULL;

public class GetRandom {
    public static int intInRange(int min, int max) {
        Random rd = new Random();
        if (min > max) {
            throw new IllegalArgumentException("Minimum cannot be higher than maximum");
        }
        return rd.nextInt((max - min) + 1) + min;
    }

    public static String stringListItem(List<String> list) {
        Random rd = new Random();
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
         Random rd = new Random();
        Cover[] coverMap = new Cover[]{FLANKED, NONE, NONE, HALF, HALF, HALF, FULL, FULL};
        return coverMap[rd.nextInt(coverMap.length)];
    }
}
