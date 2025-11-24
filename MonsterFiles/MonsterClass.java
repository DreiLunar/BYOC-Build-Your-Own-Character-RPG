package MonsterFiles;

import Models.Player;

public class MonsterClass {
    public String name;
    public int atk;
    public int maxHp;
    public int def;
    public int sp;
    public int hp;

    
    MonsterClass(String name, int atk, int maxHp, int def, int sp) {
        this.name = name;   
        this.atk = atk;
        this.maxHp = maxHp;
        this.hp = this.maxHp;
        this.def = def;
        this.sp = sp;
    }

    
    // Change the parameter type from Player to Models.Player
    public void attack(Player target) {
        System.out.println(this.name + " attacks for " + this.atk + " damage!");
        target.takeDamage(this.atk);  // Use takeDamage method instead of direct access
        // System.out.println("Player HP is now: " + target.getHp());
    }
    
    public void takeDamage(int amount) {
        int actualDamage = Math.max(1, amount - (this.def / 10));
        this.hp -= actualDamage;
        System.out.println(">> " + this.name + " took " + actualDamage + " damage! (HP: " + this.hp + "/" + this.maxHp + ")");
    }
}
