package MonsterFiles;

import Models.Player;

public class FinalBoss extends Boss{
    int resurrect = 3;
    int max_hp;
    private boolean checkRessurect = true;
    private boolean SoulRot = false;
    private int timer = 2;
    
    public FinalBoss(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);
        this.max_hp = maxHp;
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
        if(hp <= 0){
            Ressurection();
        }
    }
    
    @Override
    public boolean isAlive(){
        return checkRessurection();
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
        if(resurrect > 0){
            resurrect--;
            hp = max_hp; // Fixed: should be hp, not maxHp
            atk += 20;
            def += 5;
            System.out.println("The Corrupted Being has turned back in time and became stronger!"); // Fixed name
            System.out.println("Resurrections remaining: " + resurrect);
        } else {
            checkRessurect = false; // Set to false when no resurrections left
        }
    }
    
    private boolean checkRessurection(){
        // Return true if either: still has resurrections OR hp > 0
        return (resurrect > 0) || (hp > 0);
    }
}