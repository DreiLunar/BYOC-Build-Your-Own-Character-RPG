package MonsterFiles;

import Models.Player;
import Models.TextColor;

public class Astaroth extends Boss{
    int resurrect = 3;


    public Astaroth(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);
    }
    
    @Override
    public void takeTurn(Player target) {
        super.attack(target);

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