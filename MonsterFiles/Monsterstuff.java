package MonsterFiles;
import java.util.Scanner;
public class Monsterstuff {
    public static void main(String[] args) {
        Player user = new Player();
        Scanner sc = new Scanner(System.in);

        String[] mapList = {"Grasslands", "Dungeons", "Barren Lands"};
        int mapIndex = 0;

        int stage = 1;
        

        //Boss currentBoss = new BoarKing("Boar King", 60, 1000, 20, 10);
        
        while(true){
        int battleCount;
        switch(mapIndex){
            case 0:
                battleCount = 6;
                break;
            case 1:
                battleCount = 10;
                break;
            default:
                battleCount = 15;
                break;
        }
        String region = mapList[mapIndex];
        System.out.println("Current Region: " + region);
        System.out.println("Do you want to enter the battle?");
        String ans = sc.nextLine();
        
        if("yes".equals(ans)){
        for (int i = 1; i <= battleCount; i++) {
        int randomizer = (int)(Math.random() * 5) + 1;
        String name = CommonEnemies.EnemyName(region, randomizer);
        CommonEnemies enemy = new CommonEnemies(name, region, randomizer, stage);

        System.out.println("Battle " + i + " in " + region);
        System.out.println("You encountered a " + name); 

        while (user.hp > 0 && enemy.IsAlive) { 
            
            enemy.takeTurn(user);
            if (user.hp <= 0 ){

            break;
            }
                        user.playerAttack(enemy);
            if (!enemy.IsAlive){
                break;
            }

            System.out.println("--- End of Round ---");
            System.out.println("Press Enter to continue...");
            sc.nextLine();
        }
        stage++;

        
        if(!enemy.IsAlive){
            System.out.println("Congrats!");
            }
        if(user.hp <= 0){
            System.out.println("Game Over!");
            }
    }



    System.out.println(">>> " + region + " CLEARED! Moving to next area... <<<");
    mapIndex++;
            }
        }
     }
}