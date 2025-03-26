package lab6.Entities.Weapons.Primary;


import lab6.Entities.Weapons.*;
import static lab6.Entities.Weapons.WeaponTier.*;

import java.util.Map;



public class AssaultRifle extends Weapon {
    public AssaultRifle(WeaponTier weaponTier) {
        super(weaponTier);
    }

    @Override
    public String getSound() {
        return "";
    }

    @Override
    public String getName() {
        Map<WeaponTier, String> shotgunNames = Map.of(
                CONVENTIONAL, "Assault Rifle",
                MAGNETIC, "Magnetic Rifle",
                PLASMA, "Plasma Rifle"
        );

        return shotgunNames.get(weaponTier);
    }

    @Override
    public Integer getBaseDamage() {
        return switch (weaponTier) {
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
        return 0;
    }


}
