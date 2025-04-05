package lab6.Utility;

import lab6.Entities.Weapons.WeaponMods.WeaponMod;
import lab6.game.StatusEffects.StatusEffect;

import java.util.List;

public class CycleThrough {

    public static Integer mods(Class<? extends WeaponMod> modClass, List<WeaponMod> weaponMods) {
        // returns 0 if the player doesnt have the required mod
        if (weaponMods != null) {
            for (WeaponMod mod : weaponMods) {
                if (modClass.isInstance(mod)) {
                    return mod.applyEffect();
                }
            }
        }
        return 0;
    }

    public Integer effects(Class<? extends StatusEffect> effectClass, List<StatusEffect> effects) {
        // returns 0 if the player doesnt have the required mod
        for (StatusEffect effect : effects) {
            if (effectClass.isInstance(effect)) {
                return null;
            }
        }
        return 0;
    }
}
