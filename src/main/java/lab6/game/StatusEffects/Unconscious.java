package lab6.game.StatusEffects;

public class Unconscious extends StatusEffect implements StatusEffect.ActionPointEffect {
    public Unconscious() {
        super(1);
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public int getActionPointDebuff() {
        return 2;
    }
}
