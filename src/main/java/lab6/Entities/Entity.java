package lab6.Entities;

import lab6.Entities.SoldierStuff.Cover;
import TK.Utility.GetRandom;
import lab6.game.Items.Item;
import lab6.Entities.Weapons.Weapon;
import lab6.game.StatusEffects.StatusEffect;


import java.util.*;


import static TK.Entities.SoldierStuff.Cover.*;
import static TK.Entities.SoldierStuff.Cover.coverValue;


public abstract class Entity {
    public Integer hp;
    public Integer aim;
    public Integer mobility;
    public Integer hack;
    public Integer dodge;
    public Integer will;
    public Integer defense;

    public Integer cover;
    public Integer armour;
    public Weapon weapon;
    public List<Item> items;
    public List<StatusEffect> currentEffects;

    public Cover coverType;

    Integer GRAZE_CHANCE = 20;


    public Entity(List<Item> items) {
        this.hp = getHP();
        this.aim = getAim();
        this.mobility = getMobility();
        this.dodge = getDodge();
        this.hack = getHack();
        this.will = getWill();
        this.defense = getDefense();
        this.armour = getArmour();
        this.weapon = getWeapon();
        this.items = items;
        this.coverType = NONE;
        this.cover = coverValue.get(this.coverType);
    }

    public abstract Integer getDodge();

    public abstract Integer getAim();

    public abstract Integer getMobility();

    public abstract Integer getWill();

    public abstract Integer getHack();

    public abstract Integer getHP();

    public abstract Integer getDefense();

    public abstract Weapon getWeapon();

    public abstract Integer getArmour();


    public Boolean isDead() {
        return this.hp <= 0;
    }

    public void reload() {
        this.weapon.reload();
    }

    public void shootAtTarget(Entity target) {
        int[] aimResults = aimAtTarget(target);
        // doesDodge decreases the shot type from crit->hit->graze->miss
        boolean doesDodge = false;
        if (aimResults[2] < 100) {  // always false if entity has a 100% chanceToHit
            doesDodge = GetRandom.IntInRange(1, 100) <= target.dodge;
        }

        // doesCrit increases the shot type from miss->graze->hit->crit
        boolean doesCrit = GetRandom.IntInRange(1, 100) <= this.weapon.getTrueCritChance();

        int shotResult = GetRandom.IntInRange(1, 100);
        Weapon.ShotBehaviour shotType = getShotType(shotResult, aimResults, doesCrit, doesDodge);
        if (shotType != Weapon.ShotBehaviour.MISS) {
            int damage = Weapon.shotTypeMap.get(shotType);

            // armour logic
            if (target.armour > 0) {
                int armourShredding = this.weapon.armourShredding;
                if (armourShredding >= damage) {
                    armourShredding -= 1;
                    damage = 1;
                }
                target.armour -= armourShredding;
                target.armour = Math.max(0, target.armour);
            }

            // target take damage logic
            target.hp -= damage - target.armour;
            if (target.isDead()) {
                target.handleDeath();
            }
        } else {
            System.out.print("missed! loser");
        }
        this.weapon.ammo--;
    }


    public int[] aimAtTarget(Entity target) {
        int[] finalResult = new int[3];

        int hitChance = (this.aim + this.weapon.aimBonus) - (target.cover + target.defense);
        hitChance -= 10; // half of graze chance is represented by the 10 lowest values of the hitChance
        hitChance = Math.max(0, Math.min(hitChance, 100));

        int missChance = 100 - (GRAZE_CHANCE + hitChance);


        for (int i = 0; i < 100; i++) {
            if (i <= missChance) {
                finalResult[0]++; // miss numbers
            } else if (i <= missChance + GRAZE_CHANCE) {
                finalResult[1]++;// graze numbers
            } else {
                finalResult[2]++; // hit numbers
            }
        }
        return finalResult;
    }

    public abstract void handleDeath();


    private Weapon.ShotBehaviour getShotType(int result, int[] aimList, Boolean doesUpgrade, Boolean doesDowngrade) {
        /**
         * Determines the ShotBehaviour of a weapon based on result type (graze, hit, or miss)
         * and whether it should be upgraded or downgraded.
         *
         * @param doesUpgrade true if the weapon's crits, shot type is upgraded.
         * @param doesDowngrade true if the enemy dodges, shot type is  downgraded.
         * @return The determined ShotBehaviour value after applying upgrade or downgrade.
         */
        Weapon.ShotBehaviour currentType = null; // Variable to store the current shot type

        // Determine current shot type based on result
        for (int i = 0; i < aimList.length; i++) {
            int x = (i > 0) ? 1 : 0; // Simplified initialization
            if (result <= aimList[i] && aimList[i - x] < result) {
                switch (i) {
                    case 0 -> currentType = Weapon.ShotBehaviour.MISS;
                    case 1 -> currentType = Weapon.ShotBehaviour.GRAZE;
                    case 2 -> currentType = Weapon.ShotBehaviour.HIT;
                }
                System.out.println("The number is in aimList[" + i + "].");
                break; // Exit loop once match is found
            }
        }
        // Apply upgrade or downgrade logic
        if (doesUpgrade && !doesDowngrade) {
            // if upgrade and downgrade are both true, ignore them because they cancel each other out
            return Weapon.shotUpgradeMap.get(currentType); // Upgrade shot type +1
        } else if (!doesUpgrade && doesDowngrade) {
            return Weapon.shotDowngradeMap.get(currentType); // Downgrade shot type -1
        }

        // Return the current shot type if neither upgrade nor downgrade is applied
        return currentType;
    }
}
