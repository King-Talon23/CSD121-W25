package TK.Entities.Weapons.Primary;

import TK.Entities.Weapons.Range;
import TK.Entities.Weapons.Weapon;
import TK.Entities.Weapons.WeaponTier;

import java.util.Map;

import static TK.Entities.Weapons.WeaponTier.*;
import static TK.game.game.getRandomListItem;

public class ShotGun extends Weapon {

    public ShotGun(WeaponTier weaponTier) {
        super(weaponTier);
    }

    @Override
    public String getSound() {
        return getRandomListItem(heavyGunSounds);
    }

    @Override
    public String getName() {
        Map<WeaponTier, String> shotgunNames = Map.of(
                CONVENTIONAL, "Shotgun",
                MAGNETIC, "Shard Gun",
                PLASMA, "Storm Gun"
        );

        return shotgunNames.get(this.weaponTier);
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
    public Integer getClipSize() {
        return 4;
    }

    @Override
    public Integer getCritChance() {
        return switch (this.weaponTier) {
            case CONVENTIONAL -> 10;

            case MAGNETIC -> 15;

            case PLASMA -> 20;
        };
    }

    @Override
    public Integer getDamageOnCrit() {
        return switch (this.weaponTier) {
            case CONVENTIONAL -> 3;

            case MAGNETIC -> 4;

            case PLASMA -> 5;
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
        return Range.LOW;
    }

    @Override
    public Integer getArmourShredding() {
        return 0;
    }


}



