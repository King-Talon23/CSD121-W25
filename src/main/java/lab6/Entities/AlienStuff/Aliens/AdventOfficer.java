package lab6.Entities.AlienStuff.Aliens;

import lab6.Entities.AlienStuff.Alien;
import lab6.Entities.Weapons.Primary.AssaultRifle;
import lab6.Entities.Weapons.Weapon;
import lab6.Entities.Weapons.WeaponTier;
import org.jetbrains.annotations.NotNull;

public class AdventOfficer extends Alien {
    public AdventOfficer() {
        super();
    }

    @Override
    public String getName() {
        return "ADVENT Officer";
    }

    @Override
    public Integer getDodge() {
        return 0;
    }

    @Override
    public Integer getAim() {
        return 65;
    }

    @Override
    public Integer getMobility() {
        return 4;
    }

    @Override
    public Integer getWill() {
        return 20;
    }

    @Override
    public Integer getMaxHack() {
        return 20;
    }

    @NotNull
    @Override
    public Integer getMaxHP() {
        return 4;
    }

    @Override
    public Integer getDefense() {
        return 0;
    }

    @Override
    public Weapon getWeapon() {
        return new AssaultRifle(WeaponTier.CONVENTIONAL);
    }

    @Override
    public Integer getArmour() {
        return 0;
    }

    @Override
    public Integer getXPWorth() {
        return 1;
    }

}
