package MonsterFiles;

import Models.Player;

public class Dantalion extends Boss{
    public Dantalion(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);
    }
    
    // You can add special abilities for Dantalion later
    @Override
    public void takeTurn(Player target) {
        super.attack(target);
        target.healingReversed = true;
    }
}