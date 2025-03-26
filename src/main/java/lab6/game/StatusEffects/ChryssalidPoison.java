package lab6.game.StatusEffects;

import lab6.Utility.GetRandom;

public class ChryssalidPoison extends StatusEffect implements StatusEffect.DamageEffect {
    public ChryssalidPoison() {
        // lasts until cured or death
        super(999);
    }

    @Override
    public int getDamage() {
        return GetRandom.IntInRange(1,3);
    }


    @Override
    public String getName() {
        return "Chryssalid Poison";
    }
}
