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

        if (amount >= this.Armor) {
            //Armor Breaks, but absorbs everything
            System.out.println(">>> THE ARMOR SHATTERED! But it stopped the blow completely. <<<");
            this.Armor = 0; 
        } 
        else {
            //Armor takes damage
            this.Armor -= amount;
            System.out.println("The Armor absorbed the hit! Armor remaining: " + this.Armor);
        }

        //Armor takes the direct hit, thus negating the damage
        amount = 0; 
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
