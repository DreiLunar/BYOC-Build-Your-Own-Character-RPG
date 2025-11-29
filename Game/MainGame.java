package Game;

import Models.ConsoleEffect;
import Models.Player;
import Models.TextColor;
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
                System.out.println("\n" + TextColor.success("🎉 " + regions[currentRegion] + " CLEARED! 🎉"));
                
                // Give rewards based on region
                giveRegionRewards(player, currentRegion);
                
                currentRegion++;
                
                if (currentRegion < regions.length) {
                    System.out.println(TextColor.dialogue("Moving to " + regions[currentRegion] + "..."));
                    System.out.println("Press Enter to continue...");
                    sc.nextLine();
                }
            } else {
                System.out.println(TextColor.warning("Game Over!"));
                break;
            }
        }

        // Final message if player completed all regions
        if (currentRegion == regions.length && player.isAlive()) {
            System.out.println("\n" + TextColor.success("=".repeat(60)));
            System.out.println(TextColor.success("🏆 CONGRATULATIONS! YOU HAVE COMPLETED THE GAME! 🏆"));
            System.out.println(TextColor.success("=".repeat(60)));
        }

        sc.close();
    }
    
    private static boolean playRegion(Player player, String region, int regionIndex) {
        System.out.println("\n" + TextColor.important("=".repeat(50)));
        System.out.println(TextColor.important("ENTERING: " + region.toUpperCase()));
        System.out.println(TextColor.important("=".repeat(50)));

        // Special handling for unique regions
        if (region.equals("Path to the End")) {
            return playPathToTheEndRegion(player, regionIndex);
        }
        if (region.equals("Beginning of the End")) {
            return playFinalBossRegion(player, region, regionIndex);
        }
        
        int totalBattles = getBattlesForRegion(region);
        
        // Regular enemy battles
        for (int battleNumber = 1; battleNumber <= totalBattles; battleNumber++) {
            System.out.println("\n" + TextColor.status("--- Battle " + battleNumber + "/" + totalBattles + " ---"));
            
            MonsterClass enemy = createEnemy(region, battleNumber);
            System.out.println(TextColor.enemy("A " + enemy.name + " appears!"));
            ConsoleEffect.pause(1000);
            
            boolean playerWon = BattleSystem.battle(player, enemy);
            
            if (!playerWon) {
                return false; // Player died
            }

            player.resetBuff();
            
            // Level up and heal after successful battle
            levelUpPlayer(player, battleNumber, regionIndex);
            
            // Only heal if player took damage and isn't at full health
            if (player.getHp() < player.getMaxHp()) {
                int healAmount = player.getMaxHp() / 30;
                int actualHeal = Math.min(healAmount, player.getMaxHp() - player.getHp());
                player.heal(actualHeal);
                System.out.println(TextColor.heal("You recover " + actualHeal + " HP after the battle!"));
            } else {
                System.out.println(TextColor.status("You're already at full health!"));
            }
            
            ConsoleEffect.pause(500);
        }
        
        // Heal before boss
        int bossHealAmount = player.getMaxHp() / 8;
        player.heal(bossHealAmount);
        System.out.println("\n" + TextColor.heal("You prepare for the boss and recover " + bossHealAmount + " HP!"));
        
        // Boss battle at end of region
        ConsoleEffect.pause(1000);
        System.out.println("\n" + TextColor.warning("⚔️  BOSS BATTLE INCOMING! ⚔️"));
        Boss boss = createBoss(region, regionIndex);
        ConsoleEffect.pause(1500);
        System.out.println(TextColor.enemy("The " + region + " boss " + boss.name + " appears!"));
        ConsoleEffect.pause(2000);
        
        boolean bossDefeated = BattleSystem.battle(player, boss);

        if (bossDefeated) {
            // FULL HEAL after defeating boss
            player.setHp(player.getMaxHp());
            System.out.println("\n" + TextColor.success("You feel revitalized after defeating " + boss.name + "!"));
            System.out.println(TextColor.heal("Your HP has been fully restored!"));
            ConsoleEffect.pause(1000);
        }

        return bossDefeated;
    }

    // Path to the End Region
    private static boolean playPathToTheEndRegion(Player player, int regionIndex) {
        System.out.println(TextColor.dialogue("You must defeat the four generals of the end to proceed!"));
        ConsoleEffect.pause(1500);

        for (int i = 0; i < 4; i++) {
            int preBossHeal = player.getMaxHp() / 3;
            player.heal(preBossHeal);
            System.out.println("\n" + TextColor.heal("You steel yourself for the next challenge and recover " + preBossHeal + " HP!"));
            ConsoleEffect.pause(1000);

            System.out.println("\n" + TextColor.warning("⚔️  BOSS BATTLE INCOMING! (" + (i + 1) + "/4) ⚔️"));
            ConsoleEffect.pause(1000);
            Boss general = createPathBoss(i + 1);
            ConsoleEffect.pause(1500);
            System.out.println(TextColor.enemy("The " + general.name + " blocks your path!"));
            ConsoleEffect.pause(2000);

            boolean generalDefeated = BattleSystem.battle(player, general);
            if (!generalDefeated) {
                return false; // Player died
            }
            System.out.println("\n" + TextColor.success("✨ " + general.name + " has been vanquished! ✨"));
            givePathRewards(player, i+1);
            levelUpPlayer(player, i+1, 3); // Use region index 3 for Path to the End
            ConsoleEffect.pause(1000);
        }
        return true; // Player won the region
    }

    private static boolean playFinalBossRegion(Player player, String region, int regionIndex) {
        // Heal before the final battle
        int bossHealAmount = player.getMaxHp() / 2;
        player.heal(bossHealAmount);
        System.out.println("\n" + TextColor.heal("You gather all your strength for the final fight and recover " + bossHealAmount + " HP!"));

        ConsoleEffect.pause(1000);
        System.out.println("\n" + TextColor.warning("⚔️  THE FINAL BATTLE IS AT HAND! ⚔️"));
        Boss boss = createBoss(region, regionIndex);
        ConsoleEffect.pause(1500);
        System.out.println(TextColor.enemy(boss.name + " looks down on you from his throne"));
        ConsoleEffect.pause(2000);

        boolean bossDefeated = BattleSystem.battle(player, boss);

        if (bossDefeated) {
            System.out.println("\n" + TextColor.success("🏆 CONGRATULATIONS! YOU SAVED THE WORLD! 🏆"));
        }
        return bossDefeated;
    }

    private static void levelUpPlayer(Player player, int battleNumber, int regionIndex) {
        // Calculate stat increases based on region and battle number
        int baseAtkIncrease = 5 + (regionIndex * 2);
        int baseDefIncrease = 1 + (regionIndex * 2);
        int baseHpIncrease = 50 + (regionIndex * 2);
        int pointsToSpend = 2;

        System.out.println("\n" + TextColor.success(" LEVEL UP! "));
        System.out.println(TextColor.status("Battle " + battleNumber + " completed!"));

        // Store current stats BEFORE any changes
        int oldMaxHp = player.getMaxHp();
        int oldAtk = player.getAtk();
        int oldDef = player.getDef();

        // Apply automatic increases FIRST
        player.setMaxHp(oldMaxHp + baseHpIncrease);
        player.setAtk(oldAtk + baseAtkIncrease);
        player.setDef(oldDef + baseDefIncrease);

        System.out.println(TextColor.status("Automatic stat increases:"));
        System.out.println(TextColor.heal("  +" + baseHpIncrease + " Max HP (" + oldMaxHp + " -> " + player.getMaxHp() + ")"));
        System.out.println(TextColor.damage("  +" + baseAtkIncrease + " ATK (" + oldAtk + " -> " + player.getAtk() + ")"));
        System.out.println(TextColor.blue("  +" + baseDefIncrease + " DEF (" + oldDef + " -> " + player.getDef() + ")"));

        // Store stats AFTER automatic increases (for bonus points display)
        int afterAutoAtk = player.getAtk();
        int afterAutoDef = player.getDef();
        int afterAutoMaxHp = player.getMaxHp();

        // Let player choose where to spend bonus points
        if (pointsToSpend > 0) {
            spendBonusPoints(player, pointsToSpend);
        }

        // Show final stats CORRECTLY
        System.out.println("\n" + TextColor.status("Your final stats:"));
        System.out.println(TextColor.heal("  HP:  " + player.getHp() + "/" + player.getMaxHp() +
                        " (" + afterAutoMaxHp + " -> " + player.getMaxHp() + ")"));
        System.out.println(TextColor.damage("  ATK: " + player.getAtk() +
                        " (" + afterAutoAtk + " -> " + player.getAtk() + ")"));
        System.out.println(TextColor.blue("  DEF: " + player.getDef() +
                        " (" + afterAutoDef + " -> " + player.getDef() + ")"));
        ConsoleEffect.pause(1000);
    }

    private static void spendBonusPoints(Player player, int points) {
        System.out.println("\n" + TextColor.status("You have " + points + " bonus points to spend!"));

        while (points > 0) {
            System.out.println("\n" + TextColor.status("Choose where to spend your points:"));
            System.out.println(TextColor.heal("1. HP (+100 Max HP per point) - Current: " + player.getMaxHp()));
            System.out.println(TextColor.damage("2. ATK (+10 ATK per point) - Current: " + player.getAtk()));
            System.out.println(TextColor.blue("3. DEF (+1 DEF per point) - Current: " + player.getDef()));
            System.out.println(TextColor.status("4. Skip remaining points"));
            System.out.print("Enter your choice (1-4): ");

            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        int oldHp = player.getMaxHp();
                        player.setMaxHp(oldHp + 100);
                        player.setHp(player.getHp() + 100);
                        points--;
                        System.out.println(TextColor.heal("+100 Max HP! (" + oldHp + " -> " + player.getMaxHp() + ") - " + points + " points remaining"));
                        break;

                    case 2:
                        int oldAtk = player.getAtk();
                        player.setAtk(oldAtk + 10);
                        points--;
                        System.out.println(TextColor.damage("+10 ATK! (" + oldAtk + " -> " + player.getAtk() + ") - " + points + " points remaining"));
                        break;

                    case 3:
                        int oldDef = player.getDef();
                        player.setDef(oldDef + 1);
                        points--;
                        System.out.println(TextColor.blue("+1 DEF! (" + oldDef + " -> " + player.getDef() + ") - " + points + " points remaining"));
                        break;

                    case 4:
                        System.out.println(TextColor.status("Skipping remaining " + points + " points."));
                        points = 0;
                        break;

                    default:
                        System.out.println(TextColor.warning("Invalid choice! Please choose 1-4."));
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println(TextColor.warning("Please enter a valid number!"));
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
        else {
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
            case "Beginning of the End" -> new FinalBoss("The Corrupted Being", 580, 9000, 75, 15);
            default -> new BoarKing("Default Boss", 20, 600, 15, 5);
        };
    }

    private static Boss createPathBoss(int generalNumber) {
        return switch (generalNumber) {
            case 1 -> new Forneus("Marshal of the End - Forneus", 420, 6000, 80, 8);
            case 2 -> new Asmodeus("Admiral of the End - Asmodeus", 550, 7000, 65, 12);
            case 3 -> new Dantalion("General of the End - Dantalion", 500, 5500, 100, 8);
            case 4 -> new Astaroth("Grand Duke of the End - Astaroth", 350, 4000, 65, 8);
            default -> new Forneus("Marshal of the End - Forneus", 250, 15000, 120, 12);
        };
    }
    
    private static void giveRegionRewards(Player player, int regionIndex) {
        switch (regionIndex) {
            case 0: // Grasslands
                player.upgradeAbility(0);
                System.out.println(TextColor.success("The Boar king dropped a strengthening gauntlet! Your physical strikes now carry devastating force."));
                break;
            case 1: // Dungeons
                System.out.println(TextColor.success("You picked up a mysterious artifact from the Litch's remains! Your elemental spells have evolved into tools of destruction."));
                player.upgradeAbility(1);
                break;
            case 2: // Barren Lands
                System.out.println(TextColor.success("As the Undead King withers, his remains contain an ancient totem that improves your focus! Buffs (Wind Chant) last 1 turn longer."));
                player.upgradeAbility(2);
                break;
        }
    }
    
    private static void givePathRewards(Player player, int generalNumber) {
        switch (generalNumber) {
            case 1: // Forneus
                player.upgradeAbility(1);
                System.out.println(TextColor.dialogue("He kneels in despair, screeching with an otherworldly agony."));
                System.out.println(TextColor.success("Defeating Forneus granted you the Marshal's Glyph! A surge of raw mana emboldens your body."));
                break;

            case 2: // Asmodeus
                System.out.println(TextColor.dialogue("Asmodeus, amazed by your performance, he granted you the Admiral's Edge! Your weapon hums with newfound power."));
                player.upgradeAbility(0);
                break;
                
            case 3: // Dantalion
                System.out.println(TextColor.dialogue("Dantalion bows. The General whose sword once caused the apocalypse recognizes your strength."));
                System.out.println(TextColor.success("He grants you the General's Resolve! Your spirit is unyielding; your battle enchantments now last longer"));
                player.upgradeAbility(2);
                break;

            case 4: // Astaroth
                System.out.println(TextColor.dialogue("Almighty as one can be, everyone must fall. The once Great Duke now yields to the inevitable flow of time."));
                System.out.println(TextColor.success("Astaroth grants you the **Authority of the End**!"));
                System.out.println(TextColor.important(">> Your destructive potential has transcended limits, while your battle enchantments now defy the very flow of time. <<"));
                player.upgradeAbility(2);
                player.upgradeAbility(1);
                player.upgradeAbility(0);
                break;
        }
    }

    
}