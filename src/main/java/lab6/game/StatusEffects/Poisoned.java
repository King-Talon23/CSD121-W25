package TK.game.StatusEffects;

import static TK.game.game.getRandomIntInRange;

public class Poisoned extends StatusEffect implements StatusEffect.DamageEffect, StatusEffect.StatEffect {
    public Poisoned() {
        // lasts between 3 and 5 turns, unless cured
        super(getRandomIntInRange(3,5));
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
