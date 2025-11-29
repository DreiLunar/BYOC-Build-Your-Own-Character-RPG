package MonsterFiles;

import Models.Player;
import Models.TextColor;

public class FinalBoss extends Boss{
    int resurrect = 3;
    private boolean SoulRot = false;
    private int timer = 2;
    
    public FinalBoss(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);

    }
    
    @Override
    public void takeTurn(Player target) {
        if (SoulRot) {
            hpBasedDamage(target);
            if (Math.random() < 0.2) {
                hpBasedDamage(target);
            } 
            timer--;
            if (timer == 0){
                SoulRotDamage(target);
                timer = 4;
            }
        } else {
            System.out.println("Affected by magic, your soul is being rotten away...");
            SoulRot = true;
        }
    }
    
    @Override
    public void takeDamage(int amount) {
        int randomConst = (int)(Math.random() * 16) + 50;
        int actualDamage = (int)Math.max(1, amount/ (1 + (double)this.def/randomConst));
        this.hp -= actualDamage;
        
        
        if (this.hp <= 0) {
            this.hp = 0;
            System.out.println(TextColor.enemyDamage(">> " + this.name + " took " + actualDamage + " damage! (HP: " + this.hp + "/" + this.maxHp + ")"));
            if (resurrect > 0) {
                Ressurection();
            } else {
                this.hp = 0;
                this.IsAlive = false;
                System.out.println("Astaroth has fallen permanently!");
            }
        }
        else{
            System.out.println(TextColor.enemyDamage(">> " + this.name + " took " + actualDamage + " damage! (HP: " + this.hp + "/" + this.maxHp + ")"));
        }
    }

    public void hpBasedDamage(Player target){
        double damage = (0.10 * target.getHp() + atk);
        target.takeDamage((int)damage);
        System.out.println(this.name + " attacks for " + damage + " damage!");
        System.out.println("Player HP is now: " + target.getHp());
    }
    
    public void SoulRotDamage(Player target){
        int damage = (int)(1.5 * (target.getDef()));
        target.takeDamage(damage);
        System.out.println("Parts of player's soul has rotten... Took "+ damage + " damage!");
    }
    
    private void Ressurection(){
            resurrect--;
            System.out.println("\n⏳ *** ASTAROTH REWINDS TIME! *** ⏳");
            this.hp = this.maxHp;
            atk += 20;
            def += 5;
            System.out.println("Astaroth has turned back in time and became stronger!");
            this.IsAlive = true;
            System.out.println("Resurrections remaining: " + resurrect);
    }

}