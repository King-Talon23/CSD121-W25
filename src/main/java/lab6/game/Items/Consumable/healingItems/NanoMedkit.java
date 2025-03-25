package TK.game.Items.Consumable.healingItems;

public class NanoMedkit extends HealingItem{
    public NanoMedkit(String name, Integer apCost, String key) {
        super(name, apCost, key, 5, 6);
    }

    @Override
    public String getDescription() {
        return "";
    }
}
