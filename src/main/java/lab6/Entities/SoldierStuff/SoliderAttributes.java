package lab6.Entities.SoldierStuff;

import lab6.Entities.Weapons.Primary.*;
import lab6.Entities.Weapons.Weapon;
import lab6.Entities.Weapons.WeaponTier;

import java.util.*;

import static lab6.Entities.SoldierStuff.SoliderAttributes.Rank.*;

public class SoliderAttributes {

    public enum Rank {
        ROOKIE, SQUADDIE, CORPORAL, SERGEANT, LIEUTENANT, CAPTAIN, MAJOR, COLONEL;

    }

    public enum ClassType {
        SPECIALIST, GRENADIER, RANGER, SHARPSHOOTER
    }

    public static Map<ClassType, Weapon> classWeapon = Map.of(
            ClassType.SPECIALIST, new AssaultRifle(WeaponTier.CONVENTIONAL),
            ClassType.GRENADIER, new Cannon(WeaponTier.CONVENTIONAL),
            ClassType.RANGER, new Shotgun(WeaponTier.CONVENTIONAL),
            ClassType.SHARPSHOOTER, new SniperRifle(WeaponTier.CONVENTIONAL)
    );

    public static Map<Rank, Rank> rankMap = new HashMap<> ();
    static {
        rankMap.put(ROOKIE, SQUADDIE);
        rankMap.put(SQUADDIE, CORPORAL);
        rankMap.put(CORPORAL, SERGEANT);
        rankMap.put(SERGEANT, LIEUTENANT);
        rankMap.put(LIEUTENANT, CAPTAIN);
        rankMap.put(CAPTAIN, MAJOR);
        rankMap.put(MAJOR, COLONEL);
    }

    /**
     * All of these stats are the real Values used in XCOM 2
     */
    private static final int[][] AIM = {
            // specialist - grenadier - ranger - sharpshooter
            {65, 65, 65, 65}, // Rookie
            {68, 67, 68, 75}, // Squaddie
            {71, 69, 71, 79}, // Corporal
            {74, 71, 74, 82}, // Sergeant
            {76, 72, 76, 85}, // Lieutenant
            {78, 73, 78, 87}, // Captain
            {79, 74, 79, 89}, // Major
            {80, 75, 80, 91}  // Colonel
    };

    private static final int[][] HEALTH = {
            {4, 4, 4, 4},
            {5, 5, 5, 5},
            {6, 6, 6, 5},
            {7, 7, 7, 6},
            {8, 8, 8, 6},
            {8, 8, 8, 7},
            {9, 9, 9, 7},
            {10, 10, 10, 8}
    };

    private static final int[][] HACK = {
            {5, 5, 5, 5},
            {50, 5, 5, 5},
            {55, 10, 10, 5},
            {60, 10, 10, 10},
            {65, 15, 10, 10},
            {70, 15, 15, 15},
            {75, 20, 15, 15},
            {80, 20, 20, 20}
    };

    public static int setAim(Rank rank, ClassType classType) {
        return AIM[rank.ordinal()][classType.ordinal()];
    }

    public static int setHealth(Rank rank, ClassType classType) {
        return HEALTH[rank.ordinal()][classType.ordinal()];
    }

    public static int setHack(Rank rank, ClassType classType) {
        return HACK[rank.ordinal()][classType.ordinal()];
    }

}


