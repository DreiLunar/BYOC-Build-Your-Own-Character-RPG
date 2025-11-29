package MonsterFiles;

import Models.Player;
import Models.TextColor;

public class MonsterClass {
    public String name;
    public int atk;
    public int maxHp;
    public int def;
    public int sp;
    public int hp;

    public boolean IsAlive = true;
    //burn
    private boolean isBurned = false;
    private int DotDuration = 0;
    //poison
    private boolean isPoisoned = false;
    //stun
    public boolean isStunned = false;

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
        System.out.println(TextColor.enemyDamage(">> " + this.name + " took " + actualDamage + " damage! (HP: " + this.hp + "/" + this.maxHp + ")"));
        }
        else{
            System.out.println(TextColor.enemyDamage(">> " + this.name + " took " + actualDamage + " damage! (HP: " + this.hp + "/" + this.maxHp + ")"));
        }
    }

    public boolean isAlive(){
        return this.hp > 0;
    }

    public void applyPoison(int rounds) {
        this.isPoisoned = true;
        this.DotDuration = rounds;
        System.out.println(">>> " + this.name + " got poisoned! (Will take damage for " + rounds + " rounds)");
        handleStatusEffects();
    }

    public void applyBurn(int rounds) {
        if(this.isBurned){
            this.DotDuration += rounds;
            System.out.println("The flames intensify! Burn extended by " + rounds + " rounds.");
            System.out.println("(Total Burn Duration: " + this.DotDuration + " rounds)");
        }
        else{
        this.isBurned = true;
        this.DotDuration = rounds;
        System.out.println(">>> " + this.name + " caught on FIRE! (Will take damage for " + rounds + " rounds)");
        
        }
        handleStatusEffects();
    }

    public void handleStatusEffects() {
        if (this.isBurned) {
            if (this.DotDuration > 0) {
                
                int damage = (int)(this.maxHp * 0.10); 
                if (damage < 1) damage = 1;

                System.out.println("🔥 " + this.name + " takes " + damage + " burn damage!");
                
                this.hp -= damage;
                if (this.hp < 0) this.hp = 0;
                
                this.DotDuration--;
                System.out.println(this.name + " HP: " + this.hp + " (Burn turns left: " + this.DotDuration + ")");
            } 
            else {
                this.isBurned = false;
                System.out.println("The fire on " + this.name + " has died out.");
            }
        }

        if (this.isPoisoned) {
            if (this.DotDuration > 0) {
 
                int damage = (int)(this.maxHp * 0.15); 
                if (damage < 1) damage = 1;

                System.out.println("🫧 " + this.name + " takes " + damage + " poison damage!");
                
                this.hp -= damage;
                if (this.hp < 0) this.hp = 0;
                
                this.DotDuration--;
                System.out.println(this.name + " HP: " + this.hp + " (Poison turns left: " + this.DotDuration+ ")");
            } 
            else {
                this.isPoisoned = false;
                System.out.println(this.name + " found the antidote.");
            }
        }
    }


    public int getHp() { return hp; }
    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getMaxHp() { return maxHp; }
}
