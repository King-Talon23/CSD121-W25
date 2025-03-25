package TK.Entities.SoldierStuff;

import java.util.HashMap;
import java.util.Map;

public enum Cover {
    FLANKED, NONE, HALF, FULL;

    public static final Map<Cover, Integer> coverValue = new HashMap<>();
    static {
        coverValue.put(FLANKED, -10);
        coverValue.put(NONE, 0);
        coverValue.put(HALF, 20);
        coverValue.put(FULL, 40);
    }
    public static final Map<Cover, Cover> explosionCoverMap = new HashMap<>();
    // when cover is blown up it downgrades the coverType
    static {
        explosionCoverMap.put(NONE,FLANKED);
        explosionCoverMap.put(HALF, NONE);
        explosionCoverMap.put(FULL, HALF);
    }

}
