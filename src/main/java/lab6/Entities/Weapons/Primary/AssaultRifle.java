package TK.Entities.Weapons.Primary;

import TK.Entities.Weapons.Range;
import TK.Entities.Weapons.Weapon;
import TK.Entities.Weapons.WeaponTier;

import java.util.Map;

import static TK.Entities.Weapons.WeaponTier.*;
import static TK.game.game.getRandomListItem;

public class AssaultRifle extends Weapon {
    public AssaultRifle(WeaponTier weaponTier) {
        super(weaponTier);
    }

    @Override
    public String getSound() {
        return getRandomListItem(heavyGunSounds);
    }

    @Override
    public String getName() {
        Map<WeaponTier, String> shotgunNames = Map.of(
                CONVENTIONAL, "Assault Rifle",
                MAGNETIC, "Magnetic Rifle",
                PLASMA, "Plasma Rifle"
        );

        return shotgunNames.get(this.weaponTier);
    }

    @Override
    public Integer getBaseDamage() {
        return switch (this.weaponTier) {
            case CONVENTIONAL -> 3;

            case MAGNETIC -> 5;

            case PLASMA -> 7;
        };

    }

    @Override
    public Integer getClipSize() {
        return 4;
    }

    @Override
    public Integer getCritChance() {
        return 0;
    }

    public static Integer getDamageOnCrit() {
        return switch (weaponTier) {
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
        return 0;
    }

    @Override
    public Range getEffectiveRange() {
        return Range.MEDIUM;
    }

    @Override
    public Integer getArmourShredding() {
        return 0;
    }


}
