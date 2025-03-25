package TK.game.Items.Consumable.miscItems;

import TK.Entities.Entity;
import TK.game.Items.Item;

public class EMP extends Item {
    @Override
    public void useItem(Entity user) {
        Entity[] affected = room[roomNumber];
        for (int i = 0; i < affected.size(); i++) {
            Entity enemy = affected.get(i);
            try {
                enemy.EMPActive = true;

            } catch (IndexOutOfBoundsException e) {
                break;  // Exit the loop if index is out of range
            }
    }

    @Override
    public String getDescription() {
        return "Disabled enemy fire for one turn";
    }
}
