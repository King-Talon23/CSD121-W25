package lab6.game.StatusEffects;

import static lab6.game.game.getRandomIntInRange;

public class ChryssalidPoison extends StatusEffect implements StatusEffect.DamageEffect {
    public ChryssalidPoison() {
        // lasts until cured or death
        super(999);
    }

    @Override
    public int getDamage() {
        return getRandomIntInRange(1,3);
    }


    @Override
    public String getName() {
        return "Chryssalid Poison";
    }
}
