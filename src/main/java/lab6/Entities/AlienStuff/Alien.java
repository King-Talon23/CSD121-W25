package TK.Entities.AlienStuff;

import TK.Entities.AlienStuff.Aliens.BioMechanical;
import TK.Entities.Entity;
import TK.game.Items.Item;

import java.util.List;

public abstract class Alien extends Entity {
    Integer xpWorth;
    BioMechanical bioType;

    public Alien(List<Item> items) {
        super(items);

        this.xpWorth = getXPWorth();
        this.bioType = mechOrFlesh();
        this.dodge = getDodge(); // only mechs have base dodge since they cant use cover to hunker down

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
