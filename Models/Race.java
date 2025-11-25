package Models;

import java.util.Random;

public class Race {
    private String raceName;
    private int hp, atk, def, sp;

    public Race(String raceName, int hp, int atk, int def, int sp) {
        this.raceName = raceName;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.sp = sp;
    }

    public String getRaceName() {return raceName;}
    public int getHp() {return hp;}
    public int getAtk() {return atk;}
    public int getDef() {return def;}
    public int getSp() {return sp;}

    //Races stats
    private static final Race[] ALL_RACES = new Race[] {
            new Race("Human", 2000, 200, 10,  30),
            new Race("Elf", 1900, 260, 5,  60),
            new Race("Dwarf", 3500, 150, 20,  10),
            new Race("Angel", 2500, 220, 15,  30),
            new Race("Demon", 1500, 300, 5,  50),
            new Race("God", 4000, 350, 40, 50)
    };

    public static Race getRandomRace() {
        Random rand = new Random();
        int index = rand.nextInt(ALL_RACES.length);
        return ALL_RACES[index];
    }
}