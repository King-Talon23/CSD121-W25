package lab6.Entities.AlienStuff.Aliens.Mechnical;

import lab6.Entities.AlienStuff.Alien;
import lab6.Entities.AlienStuff.Aliens.BioMechanical;
import lab6.game.Items.Item;
import lab6.Entities.Weapons.Weapon;

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
