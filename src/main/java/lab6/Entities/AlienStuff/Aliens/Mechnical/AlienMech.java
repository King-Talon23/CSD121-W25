package TK.Entities.AlienStuff.Aliens.Mechnical;

import TK.Entities.AlienStuff.Alien;
import TK.Entities.AlienStuff.Aliens.BioMechanical;
import TK.game.Items.Item;
import TK.Entities.Weapons.Weapon;

import java.util.List;

public abstract class AlienMech extends Alien {

    public AlienMech(Integer hp, Integer aim, Integer mobility, Integer dodge, Integer will, Integer defense, Integer hack, Integer armour, Weapon weapon, List<Item> items, Integer xpWorth) {
        super(hp, aim, mobility, dodge, will, defense, hack, armour, weapon, items, xpWorth);
    }

    @Override
    public BioMechanical mechOrFlesh() {
        return BioMechanical.MECHANICAL;
    }

}
