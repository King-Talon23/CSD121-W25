package TK.game.StatusEffects;

public abstract class StatusEffect {
    Integer statusLength;
    public StatusEffect(Integer lengthInTurns){
        this.statusLength = lengthInTurns;
    }
    
    public abstract String getName();
    
    public interface StatEffect {
        int getAimDebuff();
        int getMobilityDebuff();
    }
    
    public interface ActionPointEffect {
        int getActionPointDebuff();
    }

    public interface DamageEffect {
        int getDamage();
    }
}
