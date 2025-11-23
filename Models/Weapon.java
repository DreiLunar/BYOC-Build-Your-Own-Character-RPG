package Models;

import java.util.Random;
import java.util.Scanner;

public class Weapon {
    private String weaponName, move1Name, move2Name;
    private int hp, atk, def, sp;

    public Weapon(String name, int hp, int atk, int def, int sp, String m1, String m2) {
        this.weaponName = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.sp = sp;
        this.move1Name = m1;
        this.move2Name = m2;
    }

    public String getWeaponName() {return weaponName;}
    public String getMove1Name() {return move1Name;}
    public String getMove2Name() {return move2Name;}
    public int getHp() {return hp;}
    public int getAtk() {return atk;}
    public int getDef() {return def;}
    public int getSp() {return sp;}

    private static final Weapon[] All_WEAPONS = {
            new Weapon("Longsword", 500, 5, 10, 0, "Slash", "Parry"),
            new Weapon("Dagger", 0, 5, 5, 10, "Stab", "Quicken"),
            new Weapon("Bow", 0, 10, 0, 5, "Arrow Shot", "Lock in"),
            new Weapon("Fire Element", 0, 5, 0, 5, "Ember", "Flame Wall"),
            new Weapon("Water Element", 500, 5, 0, 0, "Water Jet", "Fountain of Life"),
            new Weapon("Earth Element", 250, 0, 10, 0, "Landslide", "Earth Wall"),
            new Weapon("Air Element", 0, 5, 0, 10, "Air Slash", "Wind Chant")
    };

    public static Weapon getRandomWeapon() {
        Random rand = new Random();
        int index = rand.nextInt(All_WEAPONS.length);
        return All_WEAPONS[index];
    }
}
