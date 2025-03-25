package TK.Entities.Weapons;

import java.util.HashMap;
import java.util.Map;

public enum WeaponTier {
    CONVENTIONAL, MAGNETIC, PLASMA;
    public static final Map<WeaponTier, WeaponTier> tierMap = new HashMap<>();
    static {
        tierMap.put(CONVENTIONAL, MAGNETIC);
        tierMap.put(MAGNETIC, PLASMA);
    }
}
