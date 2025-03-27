package lab6.Entities.AlienStuff.Aliens;

import lab6.Entities.AlienStuff.Alien;
import lab6.Entities.Weapons.Weapon;

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
        return 0;
    }

    @Override
    public Integer getMobility() {
        return 0;
    }

    @Override
    public Integer getWill() {
        return 0;
    }

    @Override
    public Integer getHack() {
        return 0;
    }

    @Override
    public Integer getHP() {
        return 0;
    }

    @Override
    public Integer getDefense() {
        return 0;
    }

    @Override
    public Weapon getWeapon() {
        return null;
    }

    @Override
    public Integer getArmour() {
        return 0;
    }

    @Override
    public Integer getXPWorth() {
        return 0;
    }

}
