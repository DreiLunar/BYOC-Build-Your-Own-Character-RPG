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
            new Race("Human", 1000, 50, 50, 25),
            new Race("Elf", 1000, 65, 30, 35),
            new Race("Dwarf", 1500, 30, 65, 20),
            new Race("Angel", 2000, 35, 80, 25),
            new Race("Demon", 800, 80, 35, 50),
            new Race("God", 2000, 100, 100, 50)
    };

    public static Race getRandomRace() {
        Random rand = new Random();
        int index = rand.nextInt(ALL_RACES.length);
        return ALL_RACES[index];
    }
}