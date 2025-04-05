package lab6.Entities.Weapons;

import java.util.Map;

public enum ShotBehaviour {
    MISS, GRAZE, HIT, CRIT;

    public static final Map<ShotBehaviour, ShotBehaviour> shotUpgradeMap = Map.of(
            // upgrade map for crits
            ShotBehaviour.MISS, ShotBehaviour.GRAZE,
            ShotBehaviour.GRAZE, ShotBehaviour.HIT,
            ShotBehaviour.HIT, ShotBehaviour.CRIT
    );

    public static final Map<ShotBehaviour, ShotBehaviour> shotDowngradeMap = Map.of(
            // downgrade map for dodges
            ShotBehaviour.CRIT, ShotBehaviour.HIT,
            ShotBehaviour.HIT, ShotBehaviour.GRAZE,
            ShotBehaviour.GRAZE, ShotBehaviour.MISS
    );
}
