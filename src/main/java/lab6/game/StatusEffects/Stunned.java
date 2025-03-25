package lab6.game.StatusEffects;

public class Stunned extends StatusEffect implements StatusEffect.ActionPointEffect {
    public Stunned() {
        super(1);
    }

    @Override
    public String getName() {
        return "Stunned";
    }

    @Override
    public int getActionPointDebuff() {
        return 1;
    }
}
