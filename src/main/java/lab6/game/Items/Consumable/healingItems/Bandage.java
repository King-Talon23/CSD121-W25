package TK.game.Items.Consumable.healingItems;

public class Bandage extends HealingItem{

    public Bandage(String name, Integer apCost, String key) {
        super(name, apCost, key, 3, 4);
    }

    @Override
    public String getDescription() {
        return "";
    }
}
