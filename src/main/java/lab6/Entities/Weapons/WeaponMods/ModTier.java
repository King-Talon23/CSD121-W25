package TK.Entities.Weapons.WeaponMods;

import java.util.HashMap;
import java.util.Map;

public enum ModTier {
    BASIC, ADVANCED, SUPERIOR;

    public static final Map<ModTier, String> modTierNameMap = new HashMap<>();
    static {
        modTierNameMap.put(BASIC, "Basic");
        modTierNameMap.put(ADVANCED, "Advanced");
        modTierNameMap.put(SUPERIOR, "Superior");
    }
}
