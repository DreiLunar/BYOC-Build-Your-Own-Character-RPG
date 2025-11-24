package MonsterFiles;

public class Player {
    public int hp = 10000; 
    public int def = 30;   
    public int atk = 200;

    // We now pass the specific monster we want to hit
    public void playerAttack(MonsterClass target){
        target.hp -= this.atk;
                if (target.hp < 0) {
            target.hp = 0;
        }
        System.out.println("Player attacks " + target.name + " for " + this.atk + " damage!");
        System.out.println(target.name + " HP is now: " + target.hp);

    }
}

