import Models.Race;
import Models.Weapon;

public void takeDamage(int amount) {
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
}




class Boss extends Monsters.MonsterClass {
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
    }
}