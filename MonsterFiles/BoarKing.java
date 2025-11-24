package MonsterFiles;

public class BoarKing extends Boss{
    private boolean isCharging = false; 
    private int chargeCount = 0;

    public BoarKing(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);
    }

    @Override
    public void takeTurn(Player target) {
        if (isCharging) {
            handleChargingLogic(target);
        } else {

            if (Math.random() < 0.3) {
                startCharging();
            } else {
                super.attack(target); 
            }
        if(hp <=0){
            IsAlive = false;
        }
        }
    }

    private void startCharging() {
        System.out.println(this.name + " scrapes the ground... (Charging Ability)");
        isCharging = true;
        chargeCount = 0;
    }

    private void handleChargingLogic(Player target) {
        chargeCount++;
        if (chargeCount >= 2) {
            System.out.println(">>> " + this.name + " UNLEASHES BOAR SMASH! <<<");
            target.hp -= 250; // Huge damage
            System.out.println("Player took 250 damage!");
            isCharging = false; // Reset state
        } else {
            System.out.println(this.name + " is building up power... (Round " + chargeCount + ")");
        }
    }
}

