package TK.Entities.Weapons.Secondary;

import TK.Entities.Weapons.Weapon;

import java.util.Map;

import static TK.game.Weapons.Tier.*;

public class Sword extends Weapon {
    @Override
    public String getSound() {
        return "";
    }

    @Override
    public String getName() {
        Map<Tier, String> swordNames = Map.of(
                CONVENTIONAL, "Sword",
                BEAM, "Arc Blade",
                PLASMA, "Fusion Blade"
        );

        return swordNames.get(this.tier);
    }

    @Override
    public Integer getzClipSize() {
        // swords are not meant to be reloaded,
        return 9999999;
    }

    @Override
    public Integer getBaseDamage() {
        return switch (this.tier) {
            case CONVENTIONAL -> 3;
            case BEAM -> 4;
            case PLASMA -> 5;
        };
    }

    @Override
    public Integer getCritChance() {
        return switch (this.tier) {
            case CONVENTIONAL -> 10;
            case BEAM -> 15;
            case PLASMA -> 20;
        };
    }

    @Override
    public Integer getCritDamage() {
        return switch (this.tier) {
            case CONVENTIONAL, BEAM -> 2;
            case PLASMA -> 3;
        };
    }

    @Override
    public Integer getDamageSpread() {
        // damage spread is same between all swords
        return 2;
    }

    @Override
    public Integer getPlusOneChance() {
        return 40;
    }

    @Override
    public Integer getAimBonus() {
        return 20;
    }
}
