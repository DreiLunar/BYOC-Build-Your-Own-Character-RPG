package MonsterFiles;

import Models.Player;

public class FinalBoss extends Boss{
    int resurrect = 3;
    int max_hp;
    private boolean checkRessurect;
    private boolean SoulRot = false;
    private int timer = 2;
    
    public FinalBoss(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);
        this.max_hp = maxHp; // Initialize max_hp
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
        if(hp <=0){
            Ressurection();
        }
    }
    @Override
    public boolean isAlive(){
        return checkRessurection();
    }

    public void hpBasedDamage(Player target){
        double damage = (0.10 * target.getHp() + atk); // Use getter method
        target.takeDamage((int)damage);
        System.out.println(this.name + " attacks for " + damage + " damage!");
        System.out.println("Player HP is now: " + target.getHp());
    }
    public void SoulRotDamage(Player target){
        int damage = (int)(1.5 * (target.getDef())); // Use getter method
        target.takeDamage(damage);
        System.out.println("Parts of player's soul has rotten... Took "+ damage + " damage!");
    }
    private void Ressurection(){
        if(resurrect > 0){
            resurrect--;
            maxHp = max_hp;
            atk += 20;
            def += 5;
            System.out.println("Astaroth has turned back in time and became stronger!");
            System.out.println("Resurrections remaining: " + resurrect);
        }
    }
    private boolean checkRessurection(){
        if(resurrect == 0){
            checkRessurect = false;
        }
        return checkRessurect;
    }
}
