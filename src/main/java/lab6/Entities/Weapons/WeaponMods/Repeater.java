package lab6.Entities.Weapons.WeaponMods;

public class Repeater extends WeaponMod{
    public Repeater(ModTier tier) {
        super(tier);
    }

    @Override
    public Integer applyEffect() {
        return (this.tier.ordinal() + 1) * 5; // 5-10-15
    }

    @Override
    public String getName() {
        return "Repeater";
    }

    @Override
    public String getDescription() {
        return "Chance to instantly kill a target on hit";
    }
}
