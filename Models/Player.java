package Models;

import java.util.Scanner;

public class Player {
    private final Main main;
    String name, raceName, weaponName, move1Name, move2Name;
    int hp, maxHp, atk, def, sp, statPointsToSpend;

    boolean healingReversed = false;


    private Scanner scanner;

    public Player(Main main, String name, Race race, Weapon weapon) {
        this.main = main;
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

    public void heal(int amount) {
        if (this.healingReversed) {
            System.out.println("Dantalion's inverse ability is active!");
            System.out.println("Your healing turns into damage!");
            main.takeDamage(amount);
        } else {
            this.hp += amount;
            if (this.hp > maxHp) {
                this.hp = maxHp;
            }
        }
    }
}
