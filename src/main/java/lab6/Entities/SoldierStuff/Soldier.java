package lab6.Entities.SoldierStuff;


import lab6.Entities.Entity;
import lab6.Entities.Weapons.Weapon;
import lab6.Utility.GetRandom;
import lab6.Entities.Weapons.Primary.AssaultRifle;
import lab6.Entities.Weapons.WeaponTier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static lab6.Entities.SoldierStuff.SoliderAttributes.*;
import static lab6.Entities.SoldierStuff.SoliderAttributes.Rank.*;

public class Soldier extends Entity {

    String firstname;
    String lastname;
    Rank rank;
    ClassType classType;
    public Soldier(ClassType SoliderClass) {
        super();
        this.classType = SoliderClass;
        this.rank = ROOKIE;
        this.firstname = getFirstName();
        this.lastname = getLastname();

    }

    public String printSoldierSummery() {
        return String.format("[%s] %s %s\nAim: %s   Health: %s   Hack: %s\nWeapon: [%s] %s",
                this.rank.toString(), this.firstname, this.lastname, this.aim, this.hp, this.hack,
                this.weapon.weaponTier.toString(), this.weapon.name);
    }

    @Override
    public Integer getDodge() {
        return 0;
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
    public Integer getAim() {
        if (this.rank != null) {
            return setAim(this.rank.ordinal(), this.classType.ordinal());
        }
        return 1;
    }


    @Override
    public Integer getMaxHack() {
        if (this.rank != null) {
            return setHack(this.rank.ordinal(), this.classType.ordinal());
        }
        return 1;
    }

    @NotNull
    @Override
    public Integer getMaxHP() {
        if (this.rank != null) {
            return setHealth(this.rank.ordinal(), this.classType.ordinal());
        }
        return 1;
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
        int rankOrdinal = this.rank.ordinal();
        int classOrdinal = this.classType.ordinal();

        this.hp = setHealth(rankOrdinal, classOrdinal);
        this.aim = setAim(rankOrdinal, classOrdinal);
        this.hack = setHack(rankOrdinal, classOrdinal);
        this.will = Math.min((this.will + GetRandom.intInRange(4, 12)), 100);

        if (this.rank == Rank.SQUADDIE) {
            // assign class weapons after reaching second rank
            this.weapon = classWeapon.get(this.classType);
        }
    }

    public String getFirstName() {
        return GetRandom.stringListItem(firstnames);
    }

    public String getLastname() {
        return GetRandom.stringListItem(lastnames);
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
            ""
    ));
}
