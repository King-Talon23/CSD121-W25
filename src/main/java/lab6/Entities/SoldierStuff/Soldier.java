package lab6.Entities.SoldierStuff;


import lab6.Entities.Entity;
import lab6.Entities.Weapons.Weapon;
import lab6.Utility.GetRandom;
import lab6.Entities.Weapons.Primary.AssaultRifle;
import lab6.Entities.Weapons.WeaponTier;
import lab6.Entities.SoldierStuff.SoliderAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lab6.Entities.SoldierStuff.SoliderAttributes.*;

public class Soldier extends Entity {

    String nickname;
    String firstName;
    String lastname;
    Rank rank;
    ClassType classType;
    Integer xp;

    public Soldier(ClassType SoliderClass) { // always creates a rookie
        super(null);
        this.rank = Rank.ROOKIE;
        this.classType = SoliderClass;
    }

    public String printSummery() {

        return """
                """;
    }

    @Override
    public Integer getDodge() {
        return 0;
    }

    @Override
    public Integer getAim() {
        return setAim(this.rank, this.classType);
    }

    @Override
    public Integer getMobility() {
        return 6;
    }

    @Override
    public Integer getWill() {
        return 45;
    }

    @Override
    public Integer getHack() {
        return setHack(this.rank, this.classType);
    }

    @Override
    public Integer getHP() {
        return setHealth(this.rank, this.classType);
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
    public void handleDeath() {

        System.out.println("\n                                    _ ._  _ , ._ _");
        System.out.println("                                  (_  ' ( `  )_ .__)");
        System.out.println("                                ( (  (    )   `)  ) _)");
        System.out.println("                               (__ (_   (_ . _) _) ,__)");
        System.out.println("                                   `--`\\  ' . /`--`");
        System.out.println("                                   ,::: ;    ; :::,");
        System.out.println("                                   '::::::::::::::'");
        System.out.println("╭──────────────────────────────────────/ ─ ── \\────────────────────────────────────╮");
        System.out.println("│                                     lol you died                                 │");
        System.out.println("│                                                                                  │");
        System.out.println("│                                                                                  │");
        System.out.println("╰──────────────────────────────────────────────────────────────────────────────────╯");

        System.exit(0);
    }

    public void levelUp() {
        this.rank = rankMap.get(this.rank);

        this.hp = setHealth(this.rank, this.classType);
        this.aim = setAim(this.rank, this.classType);
        this.hack = setHack(this.rank, this.classType);
        this.will = Math.min((this.will + GetRandom.IntInRange(4, 12)), 100);

        if (this.rank == Rank.SQUADDIE) {
            // assign class weapons upon reaching second rank
            this.weapon = classWeapon.get(this.classType);
        }
    }

    public String getFirstName() {
        return GetRandom.StringListItem(firstnames);
    }

    public String getLastname() {
        return GetRandom.StringListItem(lastnames);
    }

    List<String> firstnames = new ArrayList<>(Arrays.asList(
            "Jacob",
            "Luc",
            "Talon",
            "John",
            "Randy",
            "Robbert",
            "Nomad",
            "[REDACTED]"
    ));
    List<String> lastnames = new ArrayList<>(Arrays.asList(
            "Kluding",
            "Brochu",
            "King",
            "F. Kennedy",
            "Nixon",
            "[REDACTED]",
            "[CLASSIFIED]",
            ""
    ));




}
