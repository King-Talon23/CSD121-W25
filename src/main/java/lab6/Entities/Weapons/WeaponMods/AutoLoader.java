package TK.Entities.Weapons.WeaponMods;

public class AutoLoader extends WeaponMod{
    public AutoLoader(ModTier tier) {
        super(tier);
    }

    @Override
    public Integer applyEffect() {
        return this.tier.ordinal() + 1; // 1-2-3
    }

    @Override
    public String getName() {
        return "Auto-Loader";
    }

    @Override
    public String getDescription() {
        return "Grants a limited number of free reloads per mission";
    }
}
