package MonsterFiles;

public class Forneus extends Boss{
    private boolean SoulRot = false;
    private int timer = 2;
    public Forneus(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);
    }
    @Override
    public void takeTurn(Player target) {
        if (SoulRot) {
            super.attack(target);
            timer--;
            if (timer == 0){
                SoulRotDamage(target);
                timer = 2;
            }
            }
     
        else {
            System.out.println("Affected by magic, your soul is being rotten away...");
            SoulRot = true;
            }
             if(hp <=0){
            IsAlive = false;
        }
        }
    
    public void SoulRotDamage(Player target){
        int damage = (2*(target.def));
        target.hp -= damage;
        System.out.println("Parts of player's soul has rotten... Took "+ damage+ " damage!");
    }
}
