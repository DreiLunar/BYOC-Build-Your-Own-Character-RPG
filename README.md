<div align="center">

  <img src="Images/void.gif" alt="Project Banner" width="100%">

  <h1>BYOC</h1>

  <p>
    <b>(BUILD YOU OWN CHARACTER)</b>
  </p>

  <p>
    <img src="https://img.shields.io/badge/version-1.0.0-green" alt="Version">
    <img src="https://img.shields.io/badge/project-OOP-blue" alt="License">
  </p>

</div>

<br />

### **DESCRIPTION/OVERVIEW**
 
This project is a text-based, build-your-own-character RPG game based on TikTok roulette games. 
In this game, you'll create your own hero and embark on a journey through various regions, each with its own distinct set of enemies and a challenging boss at the end. 
As you defeat enemies, you'll level up, increase your stats, and earn points to further customize your character's abilities. 
The game features a variety of enemies, challenging boss battles, and a final, epic confrontation to save the world. 
Can you conquer all the regions and defeat the final boss?

### **OOP CONCEPTS APPLIED**

This project is developed using the four main principles of Object-Oriented Programming:

<details>
  <summary><b>ENCAPSULATION</b></summary>

Encapsulation is demonstrated by restricting direct access to an object's internal state. 
In this project, classes like `Player` and `MonsterClass` have `private` attributes, which can only be accessed and modified through `public` getter and setter methods. 
This prevents unintended external changes and ensures that the object's internal state remains valid. 
For example, the player's `hp` is private and can only be changed via methods like `takeDamage()` or `heal()`, which contain important logic.

```java
// In Models/Player.java
public class Player {
    private int hp;
    private int maxHp;

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = Math.min(hp, this.maxHp); // Logic is encapsulated within the setter
    }

    public void takeDamage(int amount) {
        // ... complex damage calculation
        this.hp -= calculatedDamage;
    }
}
```
</details>

<details>
  <summary><b>INHERITANCE</b></summary>

  Inheritance is used to create a hierarchy of classes that share common characteristics. 
  A `MonsterClass` serves as the superclass, defining base attributes and methods for all enemies. 
  Various subclasses, such as `Boss`, `CommonEnemies`, and `FinalBoss`, inherit from `MonsterClass`. 
  This allows for code reuse while enabling each subclass to have its own specialized features. 
  For instance, `FinalBoss` inherits from `Boss`, which in turn inherits from `MonsterClass`, creating a multi-level inheritance structure.

```java
// In MonsterFiles/MonsterClass.java
public class MonsterClass {
    // Base attributes and methods
}

// In MonsterFiles/Boss.java
public class Boss extends MonsterClass {
    // Inherits from MonsterClass, adds Boss-specific features
}

// In MonsterFiles/CommonEnemies.java
public class CommonEnemies extends MonsterClass {
    // Inherits from MonsterClass, adds CommonEnemies-specific features
}

// In MonsterFiles/FinalBoss.java
public class FinalBoss extends Boss {
    // Inherits from Boss, adding final boss mechanics
}
```
</details>

<details>
  <summary><b>POLYMORPHISM</b></summary>

  Polymorphism allows objects of different classes to be treated as instances of a common superclass. 
  This is achieved through method overriding, where a subclass provides its own implementation of a method from its superclass. 
  The `BattleSystem` interacts with all enemies through a `MonsterClass` reference. 
  However, when the `takeTurn()` method is called, the specific overridden version in the actual subclass (like `FinalBoss` or `Astaroth`) is executed. 
  This allows the battle logic to remain simple and flexible, accommodating any type of monster.

```java
// In Game/BattleSystem.java
public static boolean battle(Player player, MonsterClass enemy) {
    // ...
    enemy.takeTurn(player); // Calls the overridden method of the actual enemy object
    // ...
}

// In MonsterFiles/Astaroth.java
@Override
public void takeTurn(Player target) {
    // Astaroth's unique attack pattern
}

// In MonsterFiles/FinalBoss.java
@Override
public void takeTurn(Player target) {
    // The Final Boss's unique attack pattern
}
```
</details>

<details>
  <summary><b>ABSTRACTION</b></summary>

  Abstraction is the principle of hiding complex implementation details while exposing only essential features. 
  In our project, abstraction is achieved through the `MonsterClass`. 
  This class serves as an abstract representation of an enemy, defining a common interface that all monster types must follow, such as having a `takeTurn()` method.

  The `BattleSystem` interacts with all enemies exclusively through the `MonsterClass` reference. 
  It doesn't need to know the specific logic of any particular monster; it simply calls `enemy.takeTurn(player)`. 
  The complex and unique behavior of each monster, whether it's a simple `BoarKing` attack or the multi-stage logic of the `FinalBoss`, is hidden (abstracted away) from the main battle loop. 
  This makes the code cleaner, more flexible, and easier to maintain.

```java
// In Game/BattleSystem.java
// The BattleSystem only knows about the abstract MonsterClass.
public static boolean battle(Player player, MonsterClass enemy) {
    // ...
    // It calls takeTurn() without needing to know the specific implementation.
    enemy.takeTurn(player);
    // ...
}

// In MonsterFiles/BoarKing.java
// The simple implementation is hidden from the BattleSystem.
@Override
public void takeTurn(Player target) {
    if (isCharging) {
        // ... BoarKing's charge logic
    } else {
        super.attack(target);
    }
}

// In MonsterFiles/FinalBoss.java
// The complex implementation is also hidden from the BattleSystem.
@Override
public void takeTurn(Player target) {
    if (SoulRot) {
        // ... FinalBoss's soul rot logic
    } else {
        // ...
    }
}
```
</details>




### **PROGRAM STRUCTURE**

The project is organized into several key packages and classes that work together to run the game. 
The main components are divided into the `Game`, `Models`, and `MonsterFiles` packages.

#### **Main Classes and Their Roles**

*   **`Game.MainGame`**: This is the central class that contains the `main` method, acting as the entry point of the program. It manages the overall game flow, including player creation, progressing through regions, and initiating battles.

*   **`Game.BattleSystem`**: This class handles the logic for all combat encounters. It takes a `Player` and a `MonsterClass` object and manages the turn-based battle sequence until one is defeated. It is a prime example of abstraction, as it hides the complex details of a fight from the `MainGame`.

*   **`Models.Player`**: This class represents the user's character. It encapsulates all player-related data, such as stats (`hp`, `atk`, `def`), inventory, and abilities. It also includes methods for taking damage, healing, and leveling up.

*   **`MonsterFiles.MonsterClass`**: This is the base superclass for all enemies in the game. It defines the common attributes and methods that every monster shares, such as `name`, `hp`, and a `takeTurn()` method.

*   **`MonsterFiles.Boss` and other subclasses**: These classes inherit from `MonsterClass` to create specific types of enemies. For example, `Boss` is a subclass for major enemies, and classes like `BoarKing` or `FinalBoss` extend it to implement unique behaviors by overriding the `takeTurn()` method.

#### **Class Relationship Diagram**

This diagram illustrates the relationships between the major classes, showing inheritance and composition.

```
[MainGame]
    |
    | uses V
    +------> [BattleSystem]
    |           |
    |           | uses V
    |           +------> [Player]
    |           |
    |           | uses V
    |           +------> [MonsterClass] <---+
    |                                       | (extends)
    +---------------------------------------+
                                            |
           +--------------------------------+
           |                                |
      [Boss]                           [CommonEnemies]
           |                                |
           | (extends)                      | (extends)
           |                                |
      [FinalBoss]                      [BoarKing]
      [Astaroth]                       (and stage other bosses...)
      (and other general bosses...)
```

*   **`MainGame`** creates and uses instances of `Player` and various `MonsterClass` subclasses.
*   **`MainGame`** uses the static `BattleSystem.battle()` method to run combat.
*   **`BattleSystem`** takes `Player` and `MonsterClass` objects to manage the fight.
*   **`Boss`** and **`CommonEnemies`** are subclasses that **extend** `MonsterClass`.
*   Specific bosses like **`FinalBoss`** and **`BoarKing`** **extend** `Boss` or `MonsterClass` and provide their own unique implementations.

**HOW TO RUN THE PROGRAM**

**SAMPLE OUTPUT**

**AUTHOR AND ACKNOWLEDGEMENT**

Project Made By:
<table>
  <tr>
    <td align="center">
      <a href="https://github.com/Invxty">
        <img src="https://github.com/Invxty.png" width="100px;" alt=""/>
      </a>
      <br />
      <sub><b>Abrigo, John Nathaniel F.</b></sub>
      <br />
      <a href="https://github.com/Invxty">
        <img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white" />
      </a>
      <br />
      <a href="https://instagram.com/rvstyyyy">
        <img src="https://img.shields.io/badge/Instagram-E4405F?style=flat&logo=instagram&logoColor=white" />
      </a>
      <br />
      <a href="https://discordapp.com/users/755650211065364520">
        <img src="https://img.shields.io/badge/Discord-5865F2?style=flat&logo=discord&logoColor=white" />
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/ALfish152">
        <img src="https://github.com/ALfish152.png" width="100px;" alt=""/>
      </a>
      <br />
      <sub><b>Almira, Aeron A.</b></sub>
      <br />
      <a href="https://github.com/ALfish152">
        <img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white" />
      </a>
      <br />
      <a href="https://instagram.com/alfish152">
        <img src="https://img.shields.io/badge/Instagram-E4405F?style=flat&logo=instagram&logoColor=white" />
      </a>
      <br />
      <a href="https://discordapp.com/users/935400536352641035">
        <img src="https://img.shields.io/badge/Discord-5865F2?style=flat&logo=discord&logoColor=white" />
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/DreiLunar">
        <img src="https://github.com/DreiLunar.png" width="100px;" alt=""/>
      </a>
      <br />
      <sub><b>Lunar, Von Andrei G.</b></sub>
      <br />
      <a href="https://github.com/DreiLunar">
        <img src="https://img.shields.io/badge/GitHub-181717?style=flat&logo=github&logoColor=white" />
      </a>
      <br />
      <a href="https://instagram.com/drei.lunar">
        <img src="https://img.shields.io/badge/Instagram-E4405F?style=flat&logo=instagram&logoColor=white" />
      </a>
      <br />
      <a href="https://discordapp.com/users/551348088963465236">
        <img src="https://img.shields.io/badge/Discord-5865F2?style=flat&logo=discord&logoColor=white" />
      </a>
    </td>
  </tr>
</table>