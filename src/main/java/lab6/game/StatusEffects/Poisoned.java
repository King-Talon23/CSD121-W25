package lab6.game.StatusEffects;

import lab6.Utility.GetRandom;

public class Poisoned extends StatusEffect implements StatusEffect.DamageEffect, StatusEffect.StatEffect {
    public Poisoned() {
        // lasts between 3 and 5 turns, unless cured
        super(GetRandom.IntInRange(3,5));
    }

    @Override
    public int getDamage() {
        return 1;
    }

    @Override
    public int getAimDebuff() {
        return 30;
    }

    @Override
    public int getMobilityDebuff() {
        return 6;
    }

    @Override
    public String getName() {
        return "Poisoned";
    }
}
