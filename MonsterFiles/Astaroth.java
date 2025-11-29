package MonsterFiles;

import Models.Player;

public class Astaroth extends Boss{
    int resurrect = 3;
    int max_hp;
    
    public Astaroth(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);
        this.max_hp = maxHp;
    }
    
    @Override
    public void takeTurn(Player target) {
        super.attack(target);
        if(hp <= 0){
            Ressurection();
        }
    }

    @Override
    public boolean isAlive() {
        return checkRessurection();
    }
    
    private void Ressurection(){
        if(resurrect > 0){
            resurrect--;
            hp = max_hp;
            atk += 20;
            def += 5;
            System.out.println("Astaroth has turned back in time and became stronger!");
            System.out.println("Resurrections remaining: " + resurrect);
        }
    }

    private boolean checkRessurection(){
        // Return true if either: still has resurrections OR hp > 0
        return (resurrect > 0) || (hp > 0);
    }
}