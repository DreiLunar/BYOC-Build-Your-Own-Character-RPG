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
    public boolean isParrying = false;
    public boolean isDefending = false;
    public int wallHp = 0;
    public int chantDuration = 0;
    public double strengthMult = 1.0;
    public double magicMult = 1.0;

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
        int rawDamage = (int)Math.max(1, amount/ (1 + (double)this.def/randomConst));
        int actualDamage;
        if(this.isParrying){
            System.out.println(">>> Clang! You have parried the attack! <<<");
            actualDamage = (int)(rawDamage *0.70);
            this.isParrying = false;
        }
        else if(this.isDefending){
            System.out.println(">>> Hiss! Some of the attack has scorched in flames! <<<");
            actualDamage = (int)(rawDamage *0.50);
            this.isDefending = false;
        }
        else if(this.wallHp > 0){
            System.out.println(">>> Stand ground for as this wall will take the damage! <<<");
            System.out.println("Wall Hp: " + wallHp);
            if(wallHp > rawDamage){
            wallHp -= rawDamage;
            actualDamage = 0;
            System.out.println("The Earth wall has absorbed the hit!");
            System.out.println("Wall Hp: " + wallHp);
            }
            else{
                System.out.println("THE EARTH WALL SHATTERED!");
                int overDamage = rawDamage - this.wallHp;
                this.wallHp = 0;
                actualDamage = overDamage;
            }
        }
        else{
            actualDamage = rawDamage;
        }
        if (this.hp < 0) this.hp = 0;
        this.hp -= actualDamage;
        System.out.println(">> " + name + " took " + actualDamage + " damage! (HP: " + hp + "/" + maxHp + ")");
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

    public void castWindChant(){
        this.chantDuration = 3;
        System.out.println("You chant with the wind! (attack increased for 3 rounds)");
    }
    
    public void buffTimer(){
        if(this.chantDuration > 0){
            this.chantDuration--;
            System.out.println("Blessed by the wind: " + this.chantDuration + " rounds remaining!");
            
            if(this.chantDuration == 0){
                System.out.println("The autumn breeze subsides. Your attack returns to normal.");
            }
        }
    }

    public void resetBuff(){
        this.chantDuration = 0;
    }

    public void upgradeAbility(int choice) {
        switch(choice) {
            case 0:
                this.strengthMult += 0.3; 
                break;

            case 1: 
                this.magicMult += 0.3; 
                break;

            case 2: 
                this.chantDuration += 1; 
                break;
        }
    }

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getAtk() { int currentAtk = this.atk;
        if(this.chantDuration >0){
            currentAtk = (int)(currentAtk * 1.3);
        }
        return currentAtk; }
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