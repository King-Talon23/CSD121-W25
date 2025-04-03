package lab6.game.Items.Consumable.explosiveItems;

import lab6.Entities.Entity;
import lab6.Utility.GetRandom;
import lab6.game.Items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    }

    private String getSound(){
        return GetRandom.stringListItem(explosiveSounds);
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
