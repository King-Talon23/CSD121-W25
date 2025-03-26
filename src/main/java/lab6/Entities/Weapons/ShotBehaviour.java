package lab6.Entities.Weapons;

import java.util.Map;

public enum ShotBehaviour {
    MISS, GRAZE, HIT, CRIT;

    public static Map<ShotBehaviour, ShotBehaviour> shotUpgradeMap = Map.of(
            ShotBehaviour.MISS, ShotBehaviour.GRAZE,
            ShotBehaviour.GRAZE, ShotBehaviour.HIT,
            ShotBehaviour.HIT, ShotBehaviour.CRIT
    );

    public static Map<ShotBehaviour, ShotBehaviour> shotDowngradeMap = Map.of(
            ShotBehaviour.CRIT, ShotBehaviour.HIT,
            ShotBehaviour.HIT, ShotBehaviour.GRAZE,
            ShotBehaviour.GRAZE, ShotBehaviour.MISS
    );
}
