package MonsterFiles;

import Models.Player;  // ADD THIS IMPORT

public class Boss extends MonsterClass {

    public Boss(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp); 

    }
    
    // Change parameter type
    @Override
    public void takeTurn(Player target) {
        super.attack(target);
        
    }
}