## **BYOC (BUILD YOUR OWN CHARACTER)**

### **DESCRIPTION/OVERVIEW**
 
This project is a text-based, build-your-own-character RPG game based on TikTok roulette games. 
In this game, you'll create your own hero and embark on a journey through various regions, each with its own distinct set of enemies and a challenging boss at the end. 
As you defeat enemies, you'll level up, increase your stats, and earn points to further customize your character's abilities. 
The game features a variety of enemies, challenging boss battles, and a final, epic confrontation to save the world. 
Can you conquer all the regions and defeat the final boss?

### **OOP CONCEPTS APPLIED**

This project is developed using the four main principles of Object-Oriented Programming: Encapsulation, Inheritance, Polymorphism, and Abstraction.

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




**PROGRAM STRUCTURE**

**HOW TO RUN THE PROGRAM**

**SAMPLE OUTPUT**

**AUTHOR AND ACKNOWLEDGEMENT**

