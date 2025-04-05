package lab6.game.StatusEffects;

import lab6.Utility.GetRandom;


public class Burning extends StatusEffect implements StatusEffect.DamageEffect {
    public Burning() {
        super(2);
    }

    @Override
    public int getDamage() {
        return GetRandom.intInRange(1,3);
    }


    @Override
    public String getName() {
        return "Burning";
    }

}
