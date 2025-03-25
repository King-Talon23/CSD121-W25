package lab6.game.StatusEffects;

public class Disoriented extends StatusEffect implements StatusEffect.StatEffect {
    public Disoriented() {
        super(2);
    }

    @Override
    public int getAimDebuff() {
        return 20;
    }

    @Override
    public int getMobilityDebuff() {
        return 6;
    }

    @Override
    public String getName() {
        return "Disoriented";
    }
}
