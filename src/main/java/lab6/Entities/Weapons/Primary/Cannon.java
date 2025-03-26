package lab6.Entities.Weapons.Primary;

import lab6.Entities.Weapons.Weapon;
import lab6.Entities.Weapons.WeaponTier;
import static lab6.Entities.Weapons.WeaponTier.*;


import java.util.Map;



public class Cannon extends Weapon {
    public Cannon(WeaponTier weaponTier) {
        super(weaponTier);
    }

    @Override
    public String getSound() {
        return "";
    }

    @Override
    public String getName() {
        Map<WeaponTier, String> shotgunNames = Map.of(
                CONVENTIONAL, "Cannon",
                MAGNETIC, "Mag Cannon",
                PLASMA, "Beam Cannon"
        );

        return shotgunNames.get(weaponTier);
    }

    @Override
    public Integer getBaseDamage() {
        return switch (weaponTier) {
            case CONVENTIONAL -> 4;

            case MAGNETIC -> 6;

            case PLASMA -> 8;
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

    @Override
    public Integer getDamageOnCrit() {
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
    public Integer getArmourShredding() {
        // Cannon shreds +1 armor each tier
        // CONVENTIONAL -> 1
        // MAGNETIC -> 2
        // PLASMA -> 3
        return weaponTier.ordinal() + 1;
    }


}

