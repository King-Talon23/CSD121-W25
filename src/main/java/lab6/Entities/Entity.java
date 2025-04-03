package lab6.Entities;

import lab6.Entities.SoldierStuff.Cover;
import lab6.Utility.GetRandom;
import lab6.Entities.Weapons.Weapon;
import lab6.game.StatusEffects.StatusEffect;
import lab6.Entities.Weapons.ShotBehaviour;
import org.jetbrains.annotations.NotNull;

import static lab6.Entities.SoldierStuff.Cover.*;
import static lab6.Entities.Weapons.ShotBehaviour.*;

import java.util.*;

public abstract class Entity {
    public int hp;
    public Integer aim;
    public Integer mobility;
    public Integer hack;
    public Integer dodge;
    public Integer will;
    public Integer defense;
    public Integer cover;
    public Integer armour;
    public Weapon weapon;
    public boolean isAlive;

    public boolean hasTurn;
    public boolean hunkerBonus;
    public Integer actionPoints;
    public List<StatusEffect> currentEffects;
    public Cover coverType;

    public Entity() {
        this.hp = getMaxHP();
        this.aim = getAim();
        this.mobility = getMobility();
        this.dodge = getDodge();
        this.hack = getMaxHack();
        this.will = getWill();
        this.defense = getDefense();
        this.armour = getArmour();
        this.weapon = getWeapon();
        this.coverType = NONE;
        this.cover = coverValue.get(this.coverType);
        this.actionPoints = 2;
        this.isAlive = true;
        this.hasTurn = true;
        this.hunkerBonus = false;
    }

    public void decreaseHP(int number){
        this.hp -= number;
    }
    public void increaseHP(int number){
        this.hp += number;
    }

    public void resetStats() {
        this.hp = getMaxHP();
        this.aim = getAim();
        this.mobility = getMobility();
        this.dodge = getDodge();
        this.hack = getMaxHack();
        this.will = getWill();
        this.defense = getDefense();
        this.armour = getArmour();
        this.weapon = getWeapon();
        this.coverType = NONE;
        this.cover = coverValue.get(this.coverType);
        this.actionPoints = 2;
        this.isAlive = true;
        this.hasTurn = true;
        this.hunkerBonus = false;
    }

    public abstract Integer getDodge();

    public abstract Integer getAim();

    public abstract Integer getMobility();

    public abstract Integer getWill();

    public abstract Integer getMaxHack();

    @NotNull
    public Integer getMaxHP() {return 0;}

    public abstract Integer getDefense();

    public abstract Weapon getWeapon();

    public abstract Integer getArmour();

    public void resetActionPoints() {
        this.actionPoints = 2;
    }

    public Boolean isDead() {
        return this.hp <= 0;
    }

    public void reload() {
        this.weapon.reload();
    }

    public void applyHunkerBonus() {
        this.dodge += 30;
        this.defense += 20;
    }

    public void removeHunkerBonus() {
        this.dodge -= 30;
        this.defense -= 20;
    }



    public void shootAtTarget(Entity target) {
        int[] aimResults = aimAtTarget(target);
        // doesDodge decreases the shot type from crit->hit->graze->miss
        boolean doesDodge = false; // always false if entity has a 100% chanceToHit target
        if (aimResults[2] < 100) {
            doesDodge = GetRandom.intInRange(1, 100) <= target.dodge;
        }

        // doesCrit increases the shot type from miss->graze->hit->crit
        boolean doesCrit = GetRandom.intInRange(1, 100) <= this.weapon.critChance;

        int shotResult = GetRandom.intInRange(1, 100);
        ShotBehaviour shotType = getShotType(shotResult, aimResults, doesCrit, doesDodge);
        if (shotType != MISS) {
            Integer damage = this.weapon.shotTypeMap.get(shotType);

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
        int GRAZE_CHANCE = 0;

        int hitChance = (this.aim + this.weapon.aimBonus) - (target.cover + target.defense);
        if (hitChance <= 20) { // cannot graze with a hitchance of 20 or below
            GRAZE_CHANCE = 20;
            hitChance -= 10; // half of graze chance is represented by the 10 lowest values of the hitChance
        }
        hitChance = Math.max(0, Math.min(hitChance, 100));

        int missChance = 100 - (GRAZE_CHANCE + hitChance);


        for (int i = 0; i < 100; i++) {
            if (i <= missChance) {
                finalResult[0]++; // miss chance
            } else if (i <= missChance + GRAZE_CHANCE) {
                finalResult[1]++;// graze chance
            } else {
                finalResult[2]++; // hit chance
            }
        }
        return finalResult;
    }

    public abstract void handleDeath();


    private ShotBehaviour getShotType(int result, int[] aimList, Boolean doesUpgrade, Boolean doesDowngrade) {
        /**
         * Determines the ShotBehaviour of a weapon based on result type (graze, hit, or miss)
         * and whether it should be upgraded or downgraded.
         *
         * @param doesUpgrade true if the weapon's crits, shot type is upgraded.
         * @param doesDowngrade true if the enemy dodges, shot type is  downgraded.
         * @return The determined ShotBehaviour value after applying upgrade or downgrade.
         */
        ShotBehaviour currentType = null;

        for (int i = 0; i < aimList.length; i++) {
            if (result <= aimList[i]) {
                switch (i) {
                    case 0 -> currentType = MISS;
                    case 1 -> currentType = GRAZE;
                    case 2 -> currentType = HIT;
                }
                break;
            }
        }

        if (doesUpgrade && !doesDowngrade) {
            // if upgrade and downgrade are both true, ignore them because they cancel each other out
            return shotUpgradeMap.get(currentType); // upgrade shot type +1
        } else if (!doesUpgrade && doesDowngrade) {
            return shotDowngradeMap.get(currentType); // downgrade shot type -1
        }

        return currentType;
    }
}
