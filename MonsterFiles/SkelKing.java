package MonsterFiles;

import Models.Player;

public class SkelKing extends Boss{
    private int Armor = 1500;
    private int ArmorDurability = 3;
    
    public SkelKing(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);
    }
        
    @Override
    public void takeTurn(Player target) {
        super.attack(target);
    }
   @Override
    public void takeDamage(int amount) {
        if (this.Armor > 0) {
            System.out.println("The attack hits the Skeleton King's Armor!");
            
            if (amount <= this.Armor) {
                this.Armor -= amount;
                amount = 0; 
                System.out.println("The Armor absorbed all damage! Armor remaining: " + this.Armor);
            } 
            else {
                int damageToHp = amount - this.Armor;
                this.Armor = 0;
                amount = damageToHp;
                System.out.println(">>> THE ARMOR SHATTERED! <<<");
            }
        }

        if (this.Armor <= 0 && this.ArmorDurability > 0) {
            System.out.println("...The bones reassemble... Armor Regenerated!");
            this.Armor = 2000;
            this.ArmorDurability--;
            System.out.println("(Armor Repairs Left: " + this.ArmorDurability + ")");
        }

        

        super.takeDamage(amount); 
    }
}
