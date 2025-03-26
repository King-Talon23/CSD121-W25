package lab6.game.Items.Consumable.healingItems;

import lab6.Entities.Entity;
import lab6.game.Items.Item;



public abstract class HealingItem extends Item {
    Integer lowEndHealing;
    Integer highEndHealing;

    public HealingItem(String name, Integer apCost, String key, Integer lowEndHealing, Integer highEndHealing) {
        super(name, apCost, key);
        this.lowEndHealing = lowEndHealing;
        this.highEndHealing = highEndHealing;
    }

    @Override
    public void useItem(Entity user) {
    }

}
