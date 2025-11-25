package Models;

import java.util.Scanner;

public class Player {
    private String name;
    private String raceName;
    private String weaponName;
    private String move1Name, move2Name;

    private int maxHp;
    private int hp;
    private int atk;
    private int def;
    private int sp;

    public boolean healingReversed = false;

    public Player(String name) {
        this.name = name;
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n==========================================");
        ConsoleEffect.type("        YOU HAVE BEEN REINCARNATED        ", ConsoleEffect.FAST);
        System.out.println("==========================================");

        ConsoleEffect.type("Welcome to the god's realm, " + name + ". Let's determine your fate.", ConsoleEffect.NORMAL);
        waitForInput(scanner);

        ConsoleEffect.type(">> Determining which RACE you are...", ConsoleEffect.FAST);
        ConsoleEffect.pause(1000);

        Race randomRace = Race.getRandomRace();
        this.raceName = randomRace.getRaceName();

        ConsoleEffect.type("   Result: You will be born as [ " + this.raceName.toUpperCase() + " ]!", ConsoleEffect.SLOW);
        ConsoleEffect.pause(1000);
        waitForInput(scanner);

        ConsoleEffect.type(">> Determining your WEAPON compatibility...", ConsoleEffect.FAST);
        ConsoleEffect.pause(1000);

        Weapon randomWeapon = Weapon.getRandomWeapon();
        this.weaponName = randomWeapon.getWeaponName();
        this.move1Name = randomWeapon.getMove1Name();
        this.move2Name = randomWeapon.getMove2Name();

        ConsoleEffect.type("   Result: You wield the [ " + this.weaponName.toUpperCase() + " ]!", ConsoleEffect.SLOW);
        ConsoleEffect.pause(1000);
        waitForInput(scanner);

        this.maxHp = randomRace.getHp() + randomWeapon.getHp();
        this.hp = this.maxHp;
        this.atk = randomRace.getAtk() + randomWeapon.getAtk();
        this.def = randomRace.getDef() + randomWeapon.getDef();
        this.sp = randomRace.getSp() + randomWeapon.getSp();

        System.out.println("------------------------------------------");
        ConsoleEffect.type("          REINCARNATION SUCCESS!          ", ConsoleEffect.FAST);
        System.out.println("------------------------------------------");
        ConsoleEffect.pause(500);
        System.out.println(" Name:   " + this.name);
        ConsoleEffect.pause(300);
        System.out.println(" Race:   " + this.raceName);
        ConsoleEffect.pause(300);
        System.out.println(" Weapon: " + this.weaponName);
        System.out.println("------------------------------------------");
        ConsoleEffect.pause(300);
        System.out.println(" HP:  " + hp + " / " + maxHp);
        ConsoleEffect.pause(300);
        System.out.println(" ATK: " + atk);
        ConsoleEffect.pause(300);
        System.out.println(" DEF: " + def);
        ConsoleEffect.pause(300);
        System.out.println(" SPD: " + sp);
        System.out.println("------------------------------------------");
        ConsoleEffect.pause(300);
        System.out.println("Moves: " + move1Name + ", " + move2Name);
        System.out.println("==========================================\n");
        waitForInput(scanner);

        ConsoleEffect.type("The world will soon be plunged in darkness.", ConsoleEffect.NORMAL);
        ConsoleEffect.pause(1000);

        String mysteryName = "? ? ? ? ?";

        ConsoleEffect.type("You must defeat [ " + mysteryName + " ] to save the world!", 150);
        waitForInput(scanner);
    }

    private void waitForInput(Scanner scanner) {
        System.out.print("\n[Press ENTER to continue]");
        scanner.nextLine();
        System.out.println();
    }

    public void heal(int amount) {
        if (this.healingReversed) {
            System.out.println(">> CURSED! Dantalion's inverse ability turns healing into damage!");
            takeDamage(amount);
        } else {
            this.hp += amount;
            if (this.hp > maxHp) {
                this.hp = maxHp;
            }
            System.out.println(">> " + name + " healed for " + amount + " HP.");
        }
    }

    public void takeDamage(int amount) {
        int randomConst = (int)(Math.random() * 16) + 50;
        int actualDamage = (int)Math.max(1, amount/ (1 + (double)this.def/randomConst));
        this.hp -= actualDamage;
        if (this.hp < 0) this.hp = 0;

        System.out.println(">> " + name + " took " + actualDamage + " damage! (HP: " + hp + "/" + maxHp + ")");
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getMaxHp() { return maxHp; }
    public void setHealingReversed(boolean status) { this.healingReversed = status; }

    public String getMove1Name() {return move1Name;}
    public String getMove2Name() {return move2Name;}

    public void setMaxHp(int maxHp) {this.maxHp = maxHp;}
    public void setHp(int hp) {this.hp = Math.min(hp, this.maxHp);} // Don't exceed max HP
    public void setAtk(int atk) {this.atk = atk;}
    public void setDef(int def) {this.def = def;}
}