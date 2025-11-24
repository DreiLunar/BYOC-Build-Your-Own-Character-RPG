package MonsterFiles;

public class Boss extends MonsterClass{
    boolean IsAlive;

    public Boss(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp); 
        this.IsAlive = true;
    }
    
    public void takeTurn(Player target){
        super.attack(target);
    }
}

