package MonsterFiles;
import Models.Player;

public class CommonEnemies extends MonsterClass{
    boolean IsAlive;

    public CommonEnemies(String name, String region, int randomizer, int stage) {
        super(name, 0,0,0,0); 
        this.IsAlive = true;
        
        EnemyStats(region, stage);
    }
    
    public void takeTurn(Player target) {
        super.attack(target);
        if(this.hp <= 0){
            IsAlive = false;
        }
    }

    public static String EnemyName(String region, int randomizer) {
        switch (region) {
            case "Grasslands":
                switch(randomizer) {
                    case 1: return "Slime";
                    case 2: return "Dire Wolf";
                    case 3: return "Wild Boar";
                    case 4: return  "Goblin";
                    default: return "Venom Snake";
                }
            case "Dungeons":
                switch (randomizer) {
                    case 1: return "Skeleton";
                    case 2: return "Armored Knight";
                    case 3: return "Demon Boar";
                    case 4: return  "Golem";
                    default: return "Mimic";
                }
            case "Barren Lands":
                switch (randomizer) {
                    case 1: return "Zombie";
                    case 2: return "Goblin Knight";
                    case 3: return "Venom Slime";
                    case 4: return  "Headless Horseman";
                    default: return "Wraith";
                }
            default:
                break;
        }
        return "Unknown " + region;
    }    
    
    private void EnemyStats(String region, int stage){
        int atk, hp, def, sp;
        
        // VERY EASY STATS
        switch(region){
            case "Grasslands":
                atk = 15;  // Very low attack
                hp = 300;  // Very low HP
                def = 10;  // Low defense
                sp = 5;    
                break;
            case "Dungeons":
                atk = 25; 
                hp = 500;
                def = 15;
                sp = 8;    
                break;
            default: // Barren Lands
                atk = 35; 
                hp = 700;
                def = 20;
                sp = 10;  
                break; 
        }
        stats(atk, hp, def, sp, stage);
    }

    private void stats(int baseAtk, int baseHp, int baseDef, int baseSp, int stage){
        // Minimal scaling - barely increases
        this.atk = baseAtk + (stage * 1);  // +1 ATK per stage
        this.def = baseDef + (stage * 1);  // +1 DEF per stage  
        this.maxHp = baseHp + (stage * 20); // +20 HP per stage
        this.hp = this.maxHp;
        this.sp = baseSp;
    }
}