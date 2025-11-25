package MonsterFiles;

import Models.Player;

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
            target.takeDamage(1000); // Use takeDamage method instead of direct access
            isCharging = false; // Reset state
        } else {
            System.out.println(this.name + " is building up power... (Round " + chargeCount + ")");
        }
    }
}