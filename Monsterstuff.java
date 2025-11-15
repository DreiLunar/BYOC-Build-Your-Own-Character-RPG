class Player {
    static int hp = 1000; 
}
class MonsterClass {
    String name;
    int atk;
    int hp;
    int def;
    int sp;

    
    MonsterClass(String name, int atk, int hp, int def, int sp) {
        this.name = name;
        this.atk = atk;
        this.hp = hp;
        this.def = def;
        this.sp = sp;
    }

    
    public void attack() {
        System.out.println(this.name + " attacks for " + this.atk + " damage!");
        Player.hp -= this.atk;
        System.out.println("Player HP is now: " + Player.hp);
    }
}


class Boss extends MonsterClass { 
    int phases;

    Boss(String name, int atk, int hp, int def, int sp, int phases) {
        
        super(name, atk, hp, def, sp); 
        this.phases = phases;
    }
    
    public void takeTurn(){
        super.attack();
    }
}


 class BoarKing extends Boss{
    private boolean isCharging = false; 
    private int chargeCount = 0;

    public BoarKing(String name, int atk, int hp, int def, int sp, int phases) {
        super(name, atk, hp, def, sp, phases);
    }

    @Override
    public void takeTurn() {
        if (isCharging) {
            handleChargingLogic();
        } else {

            if (Math.random() < 0.3) {
                startCharging();
            } else {
                super.attack(); 
            }
        }
    }

    private void startCharging() {
        System.out.println(this.name + " scrapes the ground... (Charging Ability)");
        isCharging = true;
        chargeCount = 0;
    }

    private void handleChargingLogic() {
        chargeCount++;
        if (chargeCount >= 2) {
            System.out.println(">>> " + this.name + " UNLEASHES BOAR SMASH! <<<");
            Player.hp -= 250; // Huge damage
            System.out.println("Player took 250 damage!");
            isCharging = false; // Reset state
        } else {
            System.out.println(this.name + " is building up power... (Round " + chargeCount + ")");
        }
    }
}

class Litch extends Boss{
    private int Rounds = 0;
    private boolean Curse = false;

    public Litch(String name, int atk, int hp, int def, int sp, int phases) {
        super(name, atk, hp, def, sp, phases);
    }
    @Override
    public void takeTurn() {
        if (Curse) {
            if(Rounds > 0){
            handleCurse();
            super.attack();
            }
            else{
                System.out.println("Curse has taken effect... Game over...");
                Player.hp -= Player.hp;
            }
        } 
        else {
            startCurse();
            }
        }
    
    private void startCurse() {
        System.out.println(this.name + " cursed player! (15 rounds till death)");
        Curse = true;
        Rounds = 15;
    }

    private void handleCurse(){
        Rounds--;
        System.out.println("You are cursed... (" + Rounds + " Rounds remaining!)");
    }
    }
    

class SkelKing extends Boss{
    private int Armor = 500;
    private int ArmorDurability = 3;
    public SkelKing(String name, int atk, int hp, int def, int sp, int phases) {
        super(name, atk, hp, def, sp, phases);
    }

    private void ArmorCondition(){
        if(ArmorDurability > 0){
            if(Armor == 0){
                Armor = 500;
            }
        }
    }

}

public class Monsterstuff {
    public static void main(String[] args) {
  
        Boss currentBoss = new BoarKing("Litch", 60, 1000, 20, 10, 0);
        MonsterClass slime = new MonsterClass("Slime", 25, 500, 10, 5);
        while (Player.hp > 0 && currentBoss.hp > 0) { 
            currentBoss.takeTurn();
    }
}
}