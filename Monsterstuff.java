class Player {
    static int hp = 1000; 
    static int def = 30;
    int atk = 200;

    public void playerAttack(){
        Boss.hp -= atk;
        System.out.println("Player attacks for " + this.atk + " damage!");
        System.out.println("Boss HP is now: " + Boss.hp);

    }
}
class MonsterClass {
    String name;
    int atk;
    static int hp;
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

class Forneus extends Boss{
    private boolean SoulRot = false;
    private int timer = 2;
    public Forneus(String name, int atk, int hp, int def, int sp, int phases) {
        super(name, atk, hp, def, sp, phases);
    }
    @Override
    public void takeTurn() {
        if (SoulRot) {
            super.attack();
            timer--;
            if (timer == 0){
                SoulRotDamage();
                timer = 2;
            }
            }
     
        else {
            System.out.println("Affected by magic, your soul is being rotten away...");
            SoulRot = true;
            }
        }
    
    public void SoulRotDamage(){
        int damage = (2*(Player.def));
        Player.hp -= damage;
        System.out.println("Parts of player's soul has rotten... Took "+ damage+ " damage!");
    }
    } 

class Asmodeus extends Boss{
    public Asmodeus(String name, int atk, int hp, int def, int sp, int phases) {
        super(name, atk, hp, def, sp, phases);
    }
    @Override
    public void takeTurn() {
            hpBasedDamage();
            if (Math.random() < 0.3) {
                hpBasedDamage();
            } 
    }

    public void hpBasedDamage(){
        double damage = (0.25*Player.hp + atk);
        Player.hp -= (int)damage;
        System.out.println(this.name + " attacks for " + damage + " damage!");
        System.out.println("Player HP is now: " + Player.hp);
    }
}

class Dantalion extends Boss{
    public Dantalion(String name, int atk, int hp, int def, int sp, int phases) {
        super(name, atk, hp, def, sp, phases);
    }
}

class Astaroth extends Boss{
    int resurrect = 3;
    int max_hp = hp;
        public Astaroth(String name, int atk, int hp, int def, int sp, int phases) {
        super(name, atk, hp, def, sp, phases);
    }
    @Override
    public void takeTurn() {
            super.attack();
            if(hp <=0){
            Ressurection();
            }
    }
    private void Ressurection(){
        if(resurrect > 0){
            hp = max_hp;
            atk += 20;
            def += 20;
            System.out.println("Astaroth has turned back in time and became stronger");
        }
    }
}


public class Monsterstuff {
    public static void main(String[] args) {
        Player user = new Player();
        Boss currentBoss = new Astaroth("Forneus", 60, 1000, 20, 10, 0);
        MonsterClass slime = new MonsterClass("Slime", 25, 500, 10, 5);
        while (Player.hp > 0 && currentBoss.hp > 0) { 
            currentBoss.takeTurn();
            user.playerAttack();
    }
}
}