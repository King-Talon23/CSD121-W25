package lab6.Entities.AlienStuff.Aliens.Mechnical;

import lab6.Entities.AlienStuff.Alien;
import lab6.Entities.AlienStuff.Aliens.BioMechanical;
import lab6.game.Items.Item;
import lab6.Entities.Weapons.Weapon;

import java.util.List;

public abstract class AlienMech extends Alien {

    public AlienMech(List<Item> items) {
        super(items);
    }

    @Override
    public BioMechanical mechOrFlesh() {
        return BioMechanical.MECHANICAL;
    }

}
