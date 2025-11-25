package MonsterFiles;

import Models.Player;

public class Litch extends Boss{
    private int Rounds = 0;
    private boolean Curse = false;

    // CORRECTED CONSTRUCTOR - Only 5 parameters
    public Litch(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);
    }
    
    @Override
    public void takeTurn(Player target) {
        if (Curse) {
            if(Rounds > 0){
            handleCurse(target);
            super.attack(target);
            }
            else{
                System.out.println("Curse has taken effect... Game over...");
                target.takeDamage(target.getHp()); // Deal damage equal to current HP
            }
        } 
        else {
            startCurse(target);
            }
    }
    
    private void startCurse(Player target) {
        System.out.println(this.name + " cursed player! (15 rounds till death)");
        Curse = true;
        Rounds = 15;
    }

    private void handleCurse(Player target){
        Rounds--;
        System.out.println("You are cursed... (" + Rounds + " Rounds remaining!)");
    }
}