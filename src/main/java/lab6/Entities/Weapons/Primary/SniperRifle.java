package TK.Entities.Weapons.Primary;

import TK.Entities.Weapons.Range;
import TK.Entities.Weapons.Weapon;
import TK.Entities.Weapons.WeaponTier;

import java.util.Map;

import static TK.Entities.Weapons.WeaponTier.*;

public class SniperRifle extends Weapon {
    public SniperRifle(WeaponTier weaponTier) {
        super(weaponTier);
    }

    @Override
    public String getSound() {
        return "";
    }

    @Override
    public String getName() {
        Map<WeaponTier, String> swordNames = Map.of(
                CONVENTIONAL, "Sniper Rifle",
                MAGNETIC, "Gauss Rifle",
                PLASMA, "Plasma Lance"
        );

        return swordNames.get(this.weaponTier);
    }

    @Override
    public Integer getClipSize() {
        return 3;
    }

    @Override
    public Integer getBaseDamage() {
        return switch (this.weaponTier) {
            case CONVENTIONAL -> 4;
            case MAGNETIC -> 6;
            case PLASMA -> 8;
        };
    }

    @Override
    public Integer getCritChance() {
        return 10;
    }

    @Override
    public Integer getDamageOnCrit() {
        return switch (this.weaponTier) {
            case CONVENTIONAL -> 2;
            case MAGNETIC -> 3;
            case PLASMA -> 4;
        };
    }

    @Override
    public Integer getDamageSpread() {
        return 2;
    }

    @Override
    public Integer getPlusOneChance() {
        return 0;
    }

    @Override
    public Integer getAimBonus() {
        return 10;
    }

    public Range getEffectiveRange() {
        return Range.HIGH;
    }

    @Override
    public Integer getArmourShredding() {
        return 0;
    }
}
