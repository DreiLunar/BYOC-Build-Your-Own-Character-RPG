package Models;

public class Race {
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
