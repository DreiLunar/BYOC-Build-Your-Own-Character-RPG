package MonsterFiles;

import Models.Player;

public class SkelKing extends Boss{
    private int Armor = 500;
    private int ArmorDurability = 3;
    
    public SkelKing(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);
    }
        
    @Override
    public void takeTurn(Player target) {
        super.attack(target);
        if(hp <=0){
            IsAlive = false;
        }
    }

    private void ArmorCondition(){
        if(ArmorDurability > 0){
            if(Armor == 0){
                Armor = 500;
            }
        }
    }
}