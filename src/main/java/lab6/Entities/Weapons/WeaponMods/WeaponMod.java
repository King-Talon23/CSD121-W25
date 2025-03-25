package TK.Entities.Weapons.WeaponMods;

import static TK.Entities.Weapons.WeaponMods.ModTier.modTierNameMap;

public abstract class WeaponMod {
    ModTier tier;

    public WeaponMod(ModTier tier) {
        this.tier = tier;
    }

    public String modName(){
      return modTierNameMap.get(this.tier) + " " +  getName();
    };

    public abstract Integer applyEffect();


    public abstract String getName();

    public abstract String getDescription();



}
