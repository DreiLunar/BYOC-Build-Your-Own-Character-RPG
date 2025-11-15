import java.util.Scanner;

class Player {
    String name, raceName, weaponName, move1Name, move2Name;
    int hp, maxHp, atk, def, sp, statPointsToSpend;
    private Scanner scanner;

    public Player(String name, Race race, Weapon weapon) {
        this.name = name;
        this.scanner = new Scanner(System.in);
        this.raceName = race.raceName;
        this.weaponName = weapon.weaponName;
        this.move1Name = weapon.move1Name;
        this.move2Name = weapon.move2Name;

        //Combined stats
        this.maxHp = race.hp + weapon.hp;
        this.hp = this.maxHp;
        this.atk = race.atk + weapon.atk;
        this.def = race.def + weapon.def;
        this.sp = race.sp + weapon.sp;
        this.statPointsToSpend = 0;

        System.out.print("\n Welcome, " + this.name + ", the " + this.raceName + "!");

    }
}

class Race {
    String raceName;
    int hp, atk, def, sp;

    public Race(String raceName, int hp, int atk, int def, int sp) {
        this.raceName = raceName;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.sp = sp;
    }
}

class Weapon {
    String weaponName, move1Name, move2Name;
    int hp, atk, def, sp;

    public Weapon(String name, int hp, int atk, int def, int sp, String m1, String m2) {
        this.weaponName = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.sp = sp;
        this.move1Name = m1;
        this.move2Name = m2;

    }
}



class MonsterClass {
    String name;
    int atk;
    int hp;
    int def;
    int sp;

    
    MonsterClass(String name, int atk, int hp, int def, int sp) {
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.def = def;
        this.sp = sp;
    }

    
    public void attack() {
        System.out.println(this.name + " attacks for " + this.atk + " damage!");
        Player.hp -= this.atk;
        System.out.println("Player HP is now: " + Player.hp);
    }
}


class Boss extends MonsterClass { 
    int phases;
    String ability; 
  
    Boss(String name, int atk, int hp, int def, int sp, int phases, String ability) {
        
        super(name, atk, hp, def, sp); 
        this.phases = phases;
        this.ability = ability;
    }

    
    public void abilityUse() {
        System.out.println(this.name + " uses a special ability!");
        System.out.println(this.name + this.ability);
    }
}


public class Main {
    public static void main(String[] args) {
        //Stats data for the RACES
        Race human = new Race("Human", 1000, 50, 50, 25);
        Race elf = new Race("Elf", 1000, 65, 30, 35);
        Race dwarf = new Race("Dwarf", 1500, 30, 65, 20);
        Race angel = new Race("Angel", 2000, 35, 80, 25);
        Race demon = new Race("Demon", 800, 80, 35, 50);
        Race god = new Race("God", 2000, 100, 100, 50);

        //Stats data for the WEAPONS
        Weapon longsword = new Weapon("Longsword", 500, 5, 10, 0, "Slash", "Parr");
        Weapon dagger = new Weapon("Dagger", 0, 5, 5, 10, "Stab", "Quicken");
        Weapon bow = new Weapon("Bow", 0, 10, 0, 5, "Arrow Shot", "Lock in");
        Weapon fire = new Weapon("Fire Element", 0, 5, 0, 5, "Ember", "Flame Wall");
        Weapon water = new Weapon("Water Element", 500, 5, 0, 0, "Water Jet", "Fountain of Life");
        Weapon earth = new Weapon("Earth Element", 250, 0, 10, 0, "Landslide", "Earth Wall");
        Weapon air = new Weapon("Air Element", 0, 5, 0, 10, "Air Slash", "Wind Chant");



        Boss Boarking = new Boss("Boar king", 50, 1000, 20, 10, 0," is now charing a heavy attack for 2 rounds!");
        MonsterClass slime = new MonsterClass("Slime", 25, 500, 10, 5);
        
        Boarking.attack(); //sample boss attack
        Boarking.abilityUse(); //sample ability use, not sure pa pano 'to iimplement

        slime.attack(); //Common enemy attack
    }
}