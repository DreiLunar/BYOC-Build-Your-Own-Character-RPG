package Game;

import Models.ConsoleEffect;
import Models.Player;
import MonsterFiles.*;
import java.util.Scanner;

public class MainGame {
    private static final Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Create player
        System.out.print("Enter your name: ");
        String playerName = sc.nextLine();
        Player player = new Player(playerName);
        
        // Game regions in order
        String[] regions = {"Grasslands", "Dungeons", "Barren Lands", "Path to the End", "Beginning of the End"};
        int currentRegion = 0;
        
        // Main game loop
        while (currentRegion < regions.length && player.isAlive()) {
            boolean regionCleared = playRegion(player, regions[currentRegion], currentRegion);
            
            if (regionCleared) {
                System.out.println("\n🎉 " + regions[currentRegion] + " CLEARED! 🎉");
                
                // Give rewards based on region
                giveRegionRewards(player, currentRegion);
                
                currentRegion++;
                
                if (currentRegion < regions.length) {
                    System.out.println("Moving to " + regions[currentRegion] + "...");
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                }
            } else {
                System.out.println("Game Over!");
                break;
            }
        }
        
        if (player.isAlive() && currentRegion >= regions.length) {
            System.out.println("\n🏆 CONGRATULATIONS! YOU SAVED THE WORLD! 🏆");
        }
        
        sc.close();
    }
    
    private static boolean playRegion(Player player, String region, int regionIndex) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ENTERING: " + region.toUpperCase());
        System.out.println("=".repeat(50));
        
        int totalBattles = getBattlesForRegion(region);
        
        // Regular enemy battles
        for (int battleNumber = 1; battleNumber <= totalBattles; battleNumber++) {
            System.out.println("\n--- Battle " + battleNumber + "/" + totalBattles + " ---");
            
            MonsterClass enemy = createEnemy(region, battleNumber);
            System.out.println("A " + enemy.name + " appears!");
            ConsoleEffect.pause(1000);
            
            boolean playerWon = BattleSystem.battle(player, enemy);
            
            if (!playerWon) {
                return false; // Player died
            }
            
            // Level up and heal after successful battle
            levelUpPlayer(player, battleNumber, regionIndex);
            
            // Only heal if player took damage and isn't at full health
            if (player.getHp() < player.getMaxHp()) {
                int healAmount = player.getMaxHp() / 30;
                int actualHeal = Math.min(healAmount, player.getMaxHp() - player.getHp());
                player.heal(actualHeal);
                System.out.println("You recover " + actualHeal + " HP after the battle!");
            } else {
                System.out.println("You're already at full health!");
            }
        }
        
        // Heal 25% before boss
        int bossHealAmount = player.getMaxHp() / 8;
        player.heal(bossHealAmount);
        System.out.println("\nYou prepare for the boss and recover " + bossHealAmount + " HP!");
        
        // Boss battle at end of region
        ConsoleEffect.pause(1000);
        System.out.println("\n⚔️  BOSS BATTLE INCOMING! ⚔️");
        Boss boss = createBoss(region, regionIndex);
        ConsoleEffect.pause(1500);
        System.out.println("The " + region + " boss " + boss.name + " appears!");
        ConsoleEffect.pause(2000);
        
        boolean bossDefeated = BattleSystem.battle(player, boss);

        if (bossDefeated) {
            // FULL HEAL after defeating boss
            player.setHp(player.getMaxHp()); // Directly set to max HP
            System.out.println("\n✨ You feel revitalized after defeating " + boss.name + "! ✨");
            System.out.println("Your HP has been fully restored!");
            ConsoleEffect.pause(1000);
        }

        return bossDefeated;
    }
    
    private static void levelUpPlayer(Player player, int battleNumber, int regionIndex) {
        // Calculate stat increases based on region and battle number
        int baseAtkIncrease = 5 + (regionIndex * 2);
        int baseDefIncrease = 1 + (regionIndex * 2);
        int baseHpIncrease = 50 + (regionIndex * 2);
        int pointsToSpend = 2;
        
        System.out.println("\n LEVEL UP! ");
        System.out.println("Battle " + battleNumber + " completed!");
        
        // Store current stats BEFORE any changes
        int oldMaxHp = player.getMaxHp();
        int oldAtk = player.getAtk();
        int oldDef = player.getDef();
        
        // Apply automatic increases FIRST
        player.setMaxHp(oldMaxHp + baseHpIncrease);
        player.setAtk(oldAtk + baseAtkIncrease);
        player.setDef(oldDef + baseDefIncrease);
        
        System.out.println("Automatic stat increases:");
        System.out.println("  +" + baseHpIncrease + " Max HP (" + oldMaxHp + " -> " + player.getMaxHp() + ")");
        System.out.println("  +" + baseAtkIncrease + " ATK (" + oldAtk + " -> " + player.getAtk() + ")");
        System.out.println("  +" + baseDefIncrease + " DEF (" + oldDef + " -> " + player.getDef() + ")");
        
        // Store stats AFTER automatic increases (for bonus points display)
        int afterAutoAtk = player.getAtk();
        int afterAutoDef = player.getDef();
        int afterAutoMaxHp = player.getMaxHp();
        
        // Let player choose where to spend bonus points
        if (pointsToSpend > 0) {
            spendBonusPoints(player, pointsToSpend);
        }
        
        // Show final stats CORRECTLY
        System.out.println("\nYour final stats:");
        System.out.println("  HP:  " + player.getHp() + "/" + player.getMaxHp() + 
                        " (" + afterAutoMaxHp + " -> " + player.getMaxHp() + ")");
        System.out.println("  ATK: " + player.getAtk() + 
                        " (" + afterAutoAtk + " -> " + player.getAtk() + ")");
        System.out.println("  DEF: " + player.getDef() + 
                        " (" + afterAutoDef + " -> " + player.getDef() + ")");
        ConsoleEffect.pause(1000);
    }

    private static void spendBonusPoints(Player player, int points) {
        System.out.println("\nYou have " + points + " bonus points to spend!");
        
        while (points > 0) {
            System.out.println("\nChoose where to spend your points:");
            System.out.println("1. HP (+100 Max HP per point) - Current: " + player.getMaxHp());
            System.out.println("2. ATK (+10 ATK per point) - Current: " + player.getAtk());
            System.out.println("3. DEF (+1 DEF per point) - Current: " + player.getDef());
            System.out.println("4. Skip remaining points");
            System.out.print("Enter your choice (1-4): ");
            
            try {
                int choice = Integer.parseInt(sc.nextLine());
                
                switch (choice) {
                    case 1:
                        int oldHp = player.getMaxHp();
                        player.setMaxHp(oldHp + 100);
                        player.setHp(player.getHp() + 100);
                        points--;
                        System.out.println("+100 Max HP! (" + oldHp + " -> " + player.getMaxHp() + ") - " + points + " points remaining");
                        break;
                        
                    case 2:
                        int oldAtk = player.getAtk();
                        player.setAtk(oldAtk + 10);
                        points--;
                        System.out.println("+10 ATK! (" + oldAtk + " -> " + player.getAtk() + ") - " + points + " points remaining");
                        break;
                        
                    case 3:
                        int oldDef = player.getDef();
                        player.setDef(oldDef + 1);
                        points--;
                        System.out.println("+1 DEF! (" + oldDef + " -> " + player.getDef() + ") - " + points + " points remaining");
                        break;
                        
                    case 4:
                        System.out.println("Skipping remaining " + points + " points.");
                        points = 0;
                        break;
                        
                    default:
                        System.out.println("Invalid choice! Please choose 1-4.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
            }
        }
    }
    
    private static int getBattlesForRegion(String region) {
        return switch (region) {
            case "Grasslands" -> 6;
            case "Dungeons" -> 9;
            case "Barren Lands" -> 12;
            case "Path to the End" -> 0; // 4 generals
            case "Beginning of the End" -> 0; // Final boss
            default -> 3;
        };
    }
    
    private static MonsterClass createEnemy(String region, int battleNumber) {
        if (region.equals("Path to the End")) {
            return createPathBoss(battleNumber);
        }
        else{
        int randomizer = (int)(Math.random() * 5) + 1;
        String name = CommonEnemies.EnemyName(region, randomizer);
        CommonEnemies enemy = new CommonEnemies(name, region, randomizer, battleNumber);
        return enemy;
        }
    }
    
    private static Boss createBoss(String region, int regionIndex) {
        return switch (region) {
            case "Grasslands" -> new BoarKing("Boar King", 200, 3500, 50, 8);
            case "Dungeons" -> new Litch("Litch", 360, 5000, 60, 10);
            case "Barren Lands" -> new SkelKing("Undead King", 420, 6500, 65, 6);
            case "Path to the End" -> createPathBoss(regionIndex);
            case "Beginning of the End" -> new FinalBoss("The Corrupted Being", 580, 9000, 75, 15);
            default -> new BoarKing("Default Boss", 20, 600, 15, 5);
        };
    }

    private static Boss createPathBoss(int regionIndex) {
        return switch (regionIndex - 3) {
            case 0 -> new Forneus("Marshal of the End - Forneus", 420, 6000, 80, 8);
            case 1 -> new Asmodeus("Admiral of the End - Asmodeus", 550, 7000, 65, 12);
            case 2 -> new Dantalion("General of the End - Dantalion", 500, 5500, 100, 8);
            case 3 -> new Astaroth("Grand Duke of the End - Astaroth", 350, 4000, 65, 8);
            default -> new Forneus("Marshal of the End - Forneus", 250, 15000, 120, 12);
        };
    }
    
    private static void giveRegionRewards(Player player, int regionIndex) {
        switch (regionIndex) {
            case 0: // Grasslands
                System.out.println("You gained an ability upgrade from Boar King!");
                // Implement ability upgrade logic

                break;
            case 1: // Dungeons
                System.out.println("You gained an ability upgrade from Litch!");
                break;
            case 2: // Barren Lands
                System.out.println("You gained stats from Undead King!");
                // Implement stat increase
                break;
            case 3: // Path to the End
                System.out.println("You gained weapon/ability upgrades from the generals!");
                break;
        }
    }
}