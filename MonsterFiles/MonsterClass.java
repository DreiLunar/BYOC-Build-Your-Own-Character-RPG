package MonsterFiles;

import Models.Player;

public class MonsterClass {
    public String name;
    public int atk;
    public int maxHp;
    public int def;
    public int sp;
    public int hp;

    public boolean IsAlive = true;

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
        System.out.println(this.name + " attacks!");
        target.takeDamage(this.atk);  // Use takeDamage method instead of direct access
        // System.out.println("Player HP is now: " + target.getHp());

    }

    public void takeTurn(Player target) {
        attack(target);
        
    }
    
    public void takeDamage(int amount) {
        int randomConst = (int)(Math.random() * 16) + 50;
        int actualDamage = (int)Math.max(1, amount/ (1 + (double)this.def/randomConst));
        this.hp -= actualDamage;
        
        
        if(this.hp <= 0){
        this.hp = 0;
        System.out.println(">> " + this.name + " took " + actualDamage + " damage! (HP: " + this.hp + "/" + this.maxHp + ")");
        }
        else{
            System.out.println(">> " + this.name + " took " + actualDamage + " damage! (HP: " + this.hp + "/" + this.maxHp + ")");
        }
    }

    public boolean isAlive(){
        return this.hp > 0;
    }
}
