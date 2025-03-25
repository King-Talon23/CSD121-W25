package TK.Entities.AlienStuff.Aliens.Biological;

import TK.Entities.AlienStuff.Alien;
import TK.Entities.AlienStuff.Aliens.BioMechanical;
import TK.game.Items.Item;
import TK.Entities.Weapons.Weapon;

import java.util.List;

public abstract class BioAlien extends Alien {

    public BioAlien(Integer hp, Integer aim, Integer mobility, Integer dodge, Integer will, Integer defense, Integer hack, Integer armour, Weapon weapon, List<Item> items, Integer xpWorth) {
        super(hp, aim, mobility, dodge, will, defense, hack, armour, weapon, items, xpWorth);
    }

    @Override
    public BioMechanical mechOrFlesh() {
        return BioMechanical.BIOLOGICAL;
    }

    @Override
    public Integer getDodge() {
        return 10; // all non-mechanical entities have 10 dodge (soldiers too)
    }
}
