package Game;

import Models.ConsoleEffect;
import Models.Player;
import MonsterFiles.MonsterClass;
import java.util.Scanner;

public class BattleSystem {
    private static final Scanner sc = new Scanner(System.in);
    
    public static boolean battle(Player player, MonsterClass enemy) {
        System.out.println("\n=== BATTLE START ===");
        ConsoleEffect.pause(1000);
        System.out.println(player.getName() + " vs " + enemy.name);
        
        while (player.isAlive() && enemy.isAlive()) {
            // Player turn
            System.out.println("\n--- Your Turn ---");
            boolean playerActionTaken = playerTurn(player, enemy);
            if (!playerActionTaken) {
                continue;
            }
            
            if (enemy.hp <= 0) {
                System.out.println(">>> " + enemy.name + " defeated! <<<");
                return true;
            }
            player.buffTimer();
            // Enemy turn
            System.out.println("\n--- " + enemy.name + "'s Turn ---");
            enemyTurn(player, enemy);
            if (!player.isAlive()) {
                System.out.println(">>> " + player.getName() + " was defeated! <<<");
                return false;
            }
            
            ConsoleEffect.pause(1000);
            System.out.println("\n--- Status ---");
            System.out.println(player.getName() + " HP: " + player.getHp() + "/" + player.getMaxHp());
            System.out.println(enemy.name + " HP: " + enemy.hp + "/" + enemy.maxHp);
            // System.out.println("Press Enter to continue...");
            // sc.nextLine();
            ConsoleEffect.pause(1000);
        }
        
        return player.isAlive();
    }
    
    private static boolean playerTurn(Player player, MonsterClass enemy) {
        System.out.println("Choose your action:");
        System.out.println("1. " + player.getMove1Name());
        System.out.println("2. " + player.getMove2Name());
        System.out.print("Enter your choice (1-2): ");
        ConsoleEffect.pause(1000);
        
        try {
            int choice = Integer.parseInt(sc.nextLine());
            
            switch (choice) {
                case 1:
                    useWeaponMove1(player, enemy);
                    return true;
                    
                case 2:
                    useWeaponMove2(player, enemy);
                    return true;
                    
                default:
                    System.out.println("Invalid choice! Please choose 1 or 2.");
                    return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number!");
            return false;
        }
    }

    // need ayusin tong mga moves na to bang bang bang bang =================================================================
    private static void useWeaponMove1(Player player, MonsterClass enemy) {
    String moveName = player.getMove1Name();
    System.out.println("\nYou use " + moveName + "!");
    
    switch (moveName.toLowerCase()) {
        case "slash":
            int slashDamage = (int)(player.getAtk() * (player.strengthMult + 0.6));
            enemy.takeDamage(slashDamage);  
            // Remove duplicate message - takeDamage() already prints damage
            break;
            
        case "stab":
            int stabDamage = (int)(player.getAtk() * (player.strengthMult + 0.4));
            enemy.takeDamage(stabDamage);
            if(Math.random()< 0.30){
                
                enemy.applyPoison(3);
            }
            break;
            
        case "arrow shot":
            int arrowDamage = (int)(player.getAtk() * (player.strengthMult + 0.8));
            enemy.takeDamage(arrowDamage);
            
            break;
            
        case "ember":
            int emberDamage = (int)(player.getAtk() * (player.magicMult + 0.4));
            enemy.takeDamage(emberDamage);
            if(Math.random()< 0.30){
                
                enemy.applyBurn(4);
            }
            
            break;
            
        case "water jet":
            int waterDamage = (int)(player.getAtk() * (player.magicMult + 0.2) + enemy.getMaxHp() * 0.08);
            enemy.takeDamage(waterDamage);
            break;
            
        case "landslide":
            int landslideDamage = (int)(player.getAtk() * (player.magicMult + 0.5));
            enemy.takeDamage(landslideDamage);
                if(Math.random()< 0.20){
                
                enemy.isStunned = true;
                System.out.println(">>> " + enemy.name + " was STUNNED! <<<");
            }
            break;
            
        case "air slash":
            int airDamage = (int)(player.getAtk() * (player.magicMult - 0.1));
            int airslash = (int)(Math.random() * 5) + 1;
            switch(airslash){
                case 1:
                    enemy.takeDamage(airDamage);
                    break;
                case 2:
                    enemy.takeDamage(airDamage);
                    enemy.takeDamage(airDamage);
                    break;
                case 3:
                    enemy.takeDamage(airDamage);
                    enemy.takeDamage(airDamage);
                    enemy.takeDamage(airDamage);
                    break;
                default:
                    enemy.takeDamage(airDamage);
                    enemy.takeDamage(airDamage);
                    enemy.takeDamage(airDamage);
                    enemy.takeDamage(airDamage);
                    break;
            };
            
            break;
            
        default:
            int defaultDamage = (int)(player.getAtk() * 1.5);
            enemy.takeDamage(defaultDamage);
            break;
    }
    ConsoleEffect.pause(1000);
    }
    
    private static void useWeaponMove2(Player player, MonsterClass enemy) {
    String moveName = player.getMove2Name();
    System.out.println("\nYou use " + moveName + "!");
    
    switch (moveName.toLowerCase()) {
        case "parry":
            System.out.println("You enter a defensive stance! (Next damage reduced by 30%)");
            player.isParrying = true;
            int parryDamage = (int)(player.getAtk() * (player.strengthMult - 0.2));
            enemy.takeDamage(parryDamage);
            break;
            
        case "quicken":
            int quickenRandomizer = (int)((Math.random()*3)+5);
            int quickDamage1 = player.getAtk();
            System.out.println("You move with blinding speed and attacks the opponent " + quickenRandomizer +" times!");
            for (int i =1; i <= quickenRandomizer; i++) {
                enemy.takeDamage((int)(quickDamage1 * ((player.strengthMult)/i+0.3)));   
            }
            break;
            
        case "lock in":
            System.out.println("You focus for a precise shot!");
            int critRandomizer = (int)((Math.random() * 3)+4); 
            int focusDamage = (int)(player.getAtk() * ((player.strengthMult + 1.0) + critRandomizer));
            enemy.takeDamage(focusDamage);
            break;
            
        case "flame wall":
            System.out.println("You create a protective wall of flames!");
            enemy.applyBurn(1);
            player.isDefending = true;
            break;
            
        case "fountain of life":
            int healAmount = (int)(player.getMaxHp() * 0.15);
            player.heal(healAmount);
            System.out.println("Fountain of Life restores " + healAmount + " HP!");
            break;
            
        case "earth wall":
            int earthWallHp = (int)(player.getMaxHp() *0.5);
            System.out.println("You raise a sturdy earth barrier! (Defense increased)");
            player.wallHp = earthWallHp;
            break;
            
        case "wind chant":
            
            player.castWindChant();

            break;
            
        default:
            int defaultDamage = (int)(player.getAtk() * 1.3);
            enemy.takeDamage(defaultDamage);
            break;
    }
    ConsoleEffect.pause(1000);
}
    
    private static void enemyTurn(Player player, MonsterClass enemy) {
        if(!enemy.isStunned){
        enemy.takeTurn(player);
        }
        else {
        System.out.println("🚫 " + enemy.name + " is stunned and cannot move!");
        enemy.isStunned = false;
    }
    }
}