package TK.game.Items.Consumable.explosiveItems;

import TK.Entities.Entity;
import TK.game.Items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static TK.Entities.SoldierStuff.Cover.FLANKED;
import static TK.Entities.SoldierStuff.Cover.explosionCoverMap;
import static TK.game.game.*;

public abstract class ExplosiveItem extends Item {
    Integer lowEndDamage;
    Integer highEndDamage;
    String sound;
    public ExplosiveItem(String name, Integer apCost, String key, Integer lowEndDamage, Integer highEndDamage) {
        super(name, apCost, key);
        this.lowEndDamage = lowEndDamage;
        this.highEndDamage = highEndDamage;
        this.sound = getSound();
    }

    @Override
    public void useItem(Entity user){
        println(this.sound);
        int damage = getRandomIntInRange(lowEndDamage, highEndDamage);
        Entity[] affected = room[room_number];
        for (int i = 0; i < affected.size(); i++) {
            Entity enemy = affected.get(i);
            try {
                enemy.hp -= damage;
                if (enemy.cover != FLANKED) { // destroy enemy cover
                    enemy.cover = explosionCoverMap.get(enemy.cover);
                }
                int[] loot = getLoot(enemy);
                gunFragments += loot[0];
                elerium += loot[1];
                meld += loot[2];
                alloy += loot[3];
                enemy.isDead();
            } catch (IndexOutOfBoundsException e) {
                break;  // Exit the loop if index is out of range
            }
        }
    }

    private String getSound(){
        return getRandomListItem(explosiveSounds);
    }

    private static final List<String> explosiveSounds = new ArrayList<>(Arrays.asList(
            "*BOOM!*",
            "*KABOOM!*",
            "*BANG!*",
            "*BLAM!*",
            "*CRASH!*",
            "*EXPLOSION!*",
            "*POW!*",
            "*BLAST!*",
            "*KABLOOEY*",
            "boom -_-"
    ));

}
