package TK.Entities.SoldierStuff;


import TK.Entities.Entity;
import TK.Utility.GetRandom;
import TK.Entities.Weapons.Primary.AssaultRifle;
import TK.Entities.Weapons.WeaponTier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static TK.Entities.SoldierStuff.SoliderAttributes.*;

public class Soldier extends Entity {

    String nickname;
    String firstName;
    String lastname;
    Rank rank;
    ClassType classType;
    Integer xp;

    public Soldier() { // always creates a rookie
        super(6, 65, 12, 0, 40, 0, 5, 0, new AssaultRifle(WeaponTier.CONVENTIONAL), null);
        this.rank = Rank.ROOKIE;
        this.classType = getRandomClass();
        this.xp = 0;
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
        System.out.println("│                                       (loser)                                    │");
        System.out.println("│                                (didn't even do good)                             │");
        System.out.println("╰──────────────────────────────────────────────────────────────────────────────────╯");

        System.exit(0);
    }

    public void levelUp() {
        this.hp = setHealth(this.rank, this.classType);
        this.aim = setAim(this.rank, this.classType);
        this.hack = setHack(this.rank, this.classType);
        this.will = Math.min((this.will + GetRandom.IntInRange(4, 12)), 100);

    }

    public String getFirstName() {
        return GetRandom.StringListItem(firstnames);
    }

    public String getLastname() {
        return GetRandom.StringListItem(lastnames);
    }

    private ClassType getRandomClass(){
        return switch (GetRandom.IntInRange(1,4)) {
            case 1 -> ClassType.SPECIALIST;
            case 2 -> ClassType.GRENADIER;
            case 3 -> ClassType.RANGER;
            case 4 -> ClassType.SHARPSHOOTER;
            default -> throw new IllegalArgumentException("Int out of bounds, Class does not exist");
        };
    }

    List<String> firstnames = new ArrayList<>(Arrays.asList(
            "Jacob",
            "Luc",
            "Lauren",
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
            "Bussineau",
            "King",
            "F. Kennedy",
            "Nixon",
            "[REDACTED]",
            "[CLASSIFIED]",
            ""
    ));




}
