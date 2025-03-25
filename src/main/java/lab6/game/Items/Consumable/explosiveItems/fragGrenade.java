package TK.game.Items.Consumable.explosiveItems;

public class fragGrenade extends ExplosiveItem{

    public fragGrenade(String name, Integer apCost, String key, Integer lowEndDamage, Integer highEndDamage) {
        super(name, apCost, key, lowEndDamage, highEndDamage);
    }

    @Override
    public String getDescription() {
        return "Boom";
    }
}
