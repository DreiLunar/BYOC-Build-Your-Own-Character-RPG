package MonsterFiles;

import Models.Player;

public class Asmodeus extends Boss{
    public Asmodeus(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);
    }
    
    @Override
    public void takeTurn(Player target) {
        hpBasedDamage(target);
        if (Math.random() < 0.3) {
            hpBasedDamage(target);
        } 
        if(hp <=0){
            IsAlive = false;
        }
    }

    public void hpBasedDamage(Player target){
        double damage = (0.25 * target.getHp() + atk); // Use getter method
        target.takeDamage((int)damage);
        System.out.println(this.name + " attacks for " + damage + " damage!");
        System.out.println("Player HP is now: " + target.getHp());
    }
}