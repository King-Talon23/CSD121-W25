package lab6.Entities.Weapons.WeaponMods;

public class HairTrigger extends WeaponMod{
    public HairTrigger(ModTier tier) {
        super(tier);
    }

    @Override
    public Integer applyEffect() {
        return (this.tier.ordinal() + 1) * 5; // 5-10-15
    }

    @Override
    public String getName() {
        return "Hair Trigger";
    }

    @Override
    public String getDescription() {
        return "Chance to gain a free action on hit";
    }
}
