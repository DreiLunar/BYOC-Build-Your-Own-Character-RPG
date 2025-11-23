package Models;

public class Weapon {
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
