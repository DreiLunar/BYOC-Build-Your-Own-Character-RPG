package MonsterFiles;

public class MonsterClass {
    public String name;
    public int atk;
    public int maxHp;
    public int def;
    public int sp;
    public int hp;

    
    MonsterClass(String name, int atk, int maxHp, int def, int sp) {
        this.name = name;   
        this.atk = atk;
        this.maxHp = maxHp;
        this.hp = this.maxHp;
        this.def = def;
        this.sp = sp;
    }

    
    public void attack(Player target) {
        System.out.println(this.name + " attacks for " + this.atk + " damage!");
        target.hp -= this.atk;
        System.out.println("Player HP is now: " + target.hp);
    }
    public void takeDamage(int amount) {
        int actualDamage = Math.max(1, amount - (this.def / 10));
        this.hp -= actualDamage;
        System.out.println(">> " + this.name + " took " + actualDamage + " damage! (HP: " + this.hp + "/" + this.maxHp + ")");
    }
}
