package TK.Utility;

import java.util.*;

public class GetRandom {
    static Random rd = new Random();
    public static int IntInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("Minimum cannot be higher than maximum");
        }
        return rd.nextInt((max - min) + 1) + min;
    }

    public static String StringListItem(List<String> list) {
        if (!(list instanceof List<String>)) {
            throw new IllegalArgumentException("Wrong list type");
        }
        return list.get(rd.nextInt(list.size()));
    }
}
