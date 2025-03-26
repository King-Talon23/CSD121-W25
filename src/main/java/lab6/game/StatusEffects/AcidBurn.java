package lab6.game.StatusEffects;
import lab6.Utility.GetRandom;

public class AcidBurn extends StatusEffect implements StatusEffect.DamageEffect {
    public AcidBurn() {
        super(2);
    }

    @Override
    public int getDamage() {
        return GetRandom.IntInRange(1,3);
    }

    @Override
    public String getName() {
        return "Acid Burn";
    }

}
