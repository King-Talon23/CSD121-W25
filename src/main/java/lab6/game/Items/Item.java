package TK.game.Items;

import TK.Entities.Entity;

public abstract class Item {
    String name;
    Integer apCost;
    String effectDescription;
    String actionKey;
    public Item(String name, Integer apCost, String key){
        this.name = name;
        this.apCost = apCost;
        this.effectDescription = getDescription();
        this.actionKey = key;
    }
    public abstract void useItem(Entity user);
    public abstract String getDescription();

}
