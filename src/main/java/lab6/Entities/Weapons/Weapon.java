package lab6.Entities.Weapons;

import lab6.Entities.Weapons.WeaponMods.WeaponMod;
import lab6.Entities.Weapons.WeaponMods.*;
import lab6.Utility.GetRandom;
import lab6.Utility.CycleThrough;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static lab6.Entities.Weapons.ShotBehaviour.*;
import static lab6.Entities.Weapons.ShotBehaviour.CRIT;


public abstract class Weapon {
    public String name;
    public WeaponTier weaponTier;
    public Integer damage;
    public Integer ammo;
    public Integer clipSize;
    public Integer armourShredding;
    public Integer aimBonus;
    public Integer freeReloads;
    public Integer critChance;

    public List<WeaponMod> weaponMods = new ArrayList<>();


    public Weapon(WeaponTier weaponTier) {
        this.weaponTier = weaponTier;
        this.name = getName();
        this.damage = getBaseDamage();
        this.ammo = getTrueClipSize();
        this.clipSize = getTrueClipSize();
        this.armourShredding = getArmourShredding();
        this.aimBonus = getAimBonus() + CycleThrough.mods(Scope.class, weaponMods); // scope increases aim
        this.critChance = getCritChance() + CycleThrough.mods(LaserSight.class, weaponMods); // laser sight increases crit chance
    }

    public void reload() {
        this.ammo = this.clipSize;
    }

    public Integer normalShot() {
        // damage range is between base damage and the damage spread added to the base
        return GetRandom.intInRange(getBaseDamage(), (getBaseDamage() + getDamageSpread()));
    }

    public Integer critShot() {
        // adds crit bonus to normal damage
        return normalShot() + getDamageOnCrit();
    }

    public Integer grazedShot() {
        // half of normal damage inflicted on graze
        // minimum 1 damage
        return Math.max(1, normalShot() / 2);
    }

    public Integer getMissedShotDamage() {
        // stock provides damage on missed shots
        return CycleThrough.mods(Stock.class, weaponMods);
    }

    public final Map<ShotBehaviour, Integer> shotTypeMap;
    {
        shotTypeMap = Map.of(
                MISS, getMissedShotDamage(), // 0 unless they have a stock mod
                GRAZE, grazedShot(),
                HIT, normalShot(),
                CRIT, critShot()
        );
    }

    public Integer getTrueClipSize() {
        // extended mag increases clip size
        return getClipSize() + CycleThrough.mods(ExtendedMagazine.class, weaponMods);
    }

    public Integer getInstantKillChance() {
        return CycleThrough.mods(Repeater.class, weaponMods);
    }

    public Integer getFreeReloads() {
        //auto-loaders gives a num of reloads that dont cost an action point
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

    public abstract Integer getAimBonus();

    public abstract Integer getArmourShredding();

    public abstract Integer getDamageOnCrit();

    public abstract Integer getDamageSpread();

    public abstract Integer getBaseDamage();
}
