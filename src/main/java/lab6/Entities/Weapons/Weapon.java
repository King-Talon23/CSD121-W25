package lab6.Entities.Weapons;

import lab6.Entities.Weapons.WeaponMods.WeaponMod;
import lab6.Entities.Weapons.WeaponMods.*;
import lab6.Utility.GetRandom;
import lab6.Utility.CycleThrough;
import static lab6.Entities.Weapons.ShotBehaviour.*;

import java.util.List;
import java.util.Map;


public abstract class Weapon {
    public String name;
    public WeaponTier weaponTier;
    public Integer damage;
    public Integer ammo;
    public Integer clipSize;
    public Integer armourShredding;
    public Integer aimBonus;
    public Integer freeReloads;

    public List<WeaponMod> weaponMods;

    public Weapon(WeaponTier weaponTier) {
        this.weaponTier = weaponTier;
        this.name = getName();
        this.damage = getBaseDamage();
        this.ammo = getTrueClipSize();
        this.clipSize = getTrueClipSize();
        this.armourShredding = getArmourShredding();
        this.aimBonus = getTrueAimBonus();
    }

    public void reload() {
        this.ammo = this.clipSize;
    }

    public Integer normalShot() {
        // damage range is between base damage and the damage spread added to the base
        return GetRandom.IntInRange(getBaseDamage(), (getBaseDamage() + getDamageSpread()));
    }

    public Integer critShot() {
        // adds crit bonus to normal damage
        return normalShot() + getDamageOnCrit();
    }

    public Integer grazedShot() {
        // one quarter of normal damage inflicted on dodge/graze
        return (int) (normalShot() * 0.25);
    }


    public Integer getTrueAimBonus() {
        return (getAimBonus() + CycleThrough.mods(Scope.class, weaponMods));
    }

    public Integer getTrueCritChance() {
        return getCritChance() + CycleThrough.mods(LaserSight.class, weaponMods);
    }

    public Integer getMissedShotDamage() {
        return CycleThrough.mods(Stock.class, weaponMods);
    }

    public Integer getTrueClipSize() {
        return getClipSize() + CycleThrough.mods(ExtendedMagazine.class, weaponMods);
    }

    public Integer getInstantKillChance() {
        return CycleThrough.mods(Repeater.class, weaponMods);
    }

    public Integer getFreeReloads() {
        return CycleThrough.mods(AutoLoader.class, weaponMods);
    }

    public Integer getAllowedMods() {
        // returns the amount of mods that can be added to a weapon based off tier
        return switch (weaponTier) {
            case CONVENTIONAL -> 1;
            case MAGNETIC, PLASMA -> 2;
        };
    }

    public abstract String getSound();

    public abstract String getName();

    public abstract Integer getClipSize();

    public abstract Integer getCritChance();

    public abstract Integer getPlusOneChance();

    public abstract Integer getAimBonus();

    public abstract Integer getArmourShredding();

    public abstract Integer getDamageOnCrit();

    public abstract Integer getDamageSpread();

    public abstract Integer getBaseDamage();
}
