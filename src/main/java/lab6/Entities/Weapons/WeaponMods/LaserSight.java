package lab6.Entities.Weapons.WeaponMods;

public class LaserSight extends WeaponMod {
    public LaserSight(ModTier tier) {
        super(tier);
    }

    @Override
    public Integer applyEffect() {
        return (this.tier.ordinal() + 1) * 5; // 5-10-15
    }

    @Override
    public String getName() {
        return "Laser Sight";
    }

    @Override
    public String getDescription() {
        return "Increases critical chance";
    }
}
