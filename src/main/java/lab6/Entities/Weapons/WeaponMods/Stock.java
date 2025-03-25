package lab6.Entities.Weapons.WeaponMods;

public class Stock extends WeaponMod{
    public Stock(ModTier tier) {
        super(tier);
    }

    @Override
    public Integer applyEffect() {
        return this.tier.ordinal() + 1; // 1-2-3
    }

    @Override
    public String getName() {
        return "Stock";
    }

    @Override
    public String getDescription() {
        return "Missed shots deal damage";
    }
}
