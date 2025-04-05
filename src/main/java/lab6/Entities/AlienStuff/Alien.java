package lab6.Entities.AlienStuff;

import lab6.Entities.AlienStuff.Aliens.AdventOfficer;
import lab6.Entities.Entity;
import lab6.Utility.GetRandom;
import lab6.game.Items.Item;

import java.util.List;

public abstract class Alien extends Entity {
    public Alien() {
        super();
        this.dodge = getDodge();
    }
    public abstract String getName();

    public abstract Integer getDodge();

    public abstract Integer getXPWorth();

    @Override
    public void handleDeath() {
        // drop body
        // drop loot
        // give solider xp
    }

    public Alien generateRandomAlien() {
        return switch (GetRandom.intInRange(1,6)) {
            case 1 -> new AdventOfficer();
            default -> throw new IllegalArgumentException("Illegal number generation");
        };
    }
}
