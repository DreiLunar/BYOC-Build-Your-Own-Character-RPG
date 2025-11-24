package MonsterFiles;

public class CommonEnemies extends MonsterClass{
    boolean IsAlive;
    private static final int base_hp = 2000;
    private static final int base_atk = 50;
    private static final int base_def = 15;
    private static final int base_sp = 2;

    public CommonEnemies(String name, String region, int randomizer, int stage) {
        super(name, 0,0,0,0); 
        this.IsAlive = true;
        
        EnemyStats(region, stage);
    }
    
        
    public void takeTurn(Player target){
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

        int atk, hp, def,sp;
            switch(region){
                case "Grasslands":
                    atk = 150; 
                    hp = 1000;
                    def = 20;
                    sp = 10;    
                    break;
                case "Dungeons":
                    atk = 250; 
                    hp = 2000;
                    def = 40;
                    sp = 15;    
                    break;
                default:
                    atk = 400; 
                    hp = 5000;
                    def = 660;
                    sp = 20;  
                    break; 
            }
            stats(atk, hp, def, sp, stage);
            }

    private void stats(int baseAtk, int baseHp, int baseDef, int baseSp, int stage){
        this.atk = (int)(baseAtk * 1.3) + (stage * 25);
        this.def = (int)(baseDef * 1.3) + (stage * 5);
        this.maxHp = (int)(baseHp * 1.5) + (stage * 500);
        this.hp = this.maxHp;
        this.sp = (int)(baseSp * 1.1);
    }

}
