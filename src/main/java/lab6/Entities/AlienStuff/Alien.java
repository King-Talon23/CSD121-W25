package lab6.Entities.AlienStuff;

import lab6.Entities.AlienStuff.Aliens.BioMechanical;
import lab6.Entities.Entity;
import lab6.game.Items.Item;

import java.util.List;

public abstract class Alien extends Entity {
    BioMechanical bioType;

    public Alien(List<Item> items) {
        super(items);

        this.bioType = mechOrFlesh();
        this.dodge = getDodge();
    }
    public abstract String getName();

    public abstract Integer getDodge();

    public abstract Integer getXPWorth();

    public abstract BioMechanical mechOrFlesh();

    @Override
    public void handleDeath() {
        // drop body
        // drop loot
        // give solider xp
    }
}
