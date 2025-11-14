import java.util.Random;
import java.util.Scanner;

class Character {
    String name;
    int hp;
    int attack;

    Character(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }
}

public class Main {

    // Generate a random character
    public static Character generateRandomCharacter(String label) {
        Random rand = new Random();
        String[] names = {"Astra", "Rogar", "Mira", "Drakon", "Zephyr", "Kael", "Nyra"};

        String name = names[rand.nextInt(names.length)];
        int hp = rand.nextInt(41) + 60;     // HP between 60–100
        int attack = rand.nextInt(6) + 10;  // Attack between 10–15

        return new Character(label + " " + name, hp, attack);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Generate your character & enemy
        Character player = generateRandomCharacter("Player");
        Character enemy = generateRandomCharacter("Enemy");

        System.out.println("=== RANDOM CHARACTER GENERATOR ===");
        System.out.println("You got: " + player.name + " | HP: " + player.hp + " | ATK: " + player.attack);
        System.out.println("Enemy:  " + enemy.name + " | HP: " + enemy.hp + " | ATK: " + enemy.attack);

        System.out.println("\n=== TURN-BASED BATTLE STARTS ===");

        // Game loop
        while (player.hp > 0 && enemy.hp > 0) {
            System.out.println("\nYour turn! (press ENTER to attack)");
            scanner.nextLine();

            // Player attacks
            enemy.hp -= player.attack;
            System.out.println(">>> You dealt " + player.attack + " damage!");
            if (enemy.hp <= 0) break;

            // Enemy attacks back
            System.out.println(enemy.name + " attacks!");
            player.hp -= enemy.attack;
            System.out.println(">>> Enemy dealt " + enemy.attack + " damage!");

            // Display HP
            System.out.println("\nYour HP: " + player.hp);
            System.out.println("Enemy HP: " + enemy.hp);
        }

        System.out.println("\n=== BATTLE RESULT ===");
        if (player.hp > 0) {
            System.out.println("🎉 You WIN!");
        } else {
            System.out.println("💀 You LOST!");
        }
    }
}
