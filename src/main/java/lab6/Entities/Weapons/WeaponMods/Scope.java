package TK.Entities.Weapons.WeaponMods;

public class Scope extends WeaponMod{
    public Scope(ModTier tier) {
        super(tier);
    }

    @Override
    public Integer applyEffect() {
        return (this.tier.ordinal() + 1) * 5; // 5-10-15

    }

    @Override
    public String getName() {
        return "Scope";
    }

    @Override
    public String getDescription() {
        return "Increases Aim";
    }
}
