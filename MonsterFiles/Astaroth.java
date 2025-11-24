package MonsterFiles;

public class Astaroth extends Boss{
        int resurrect = 3;
        int max_hp = hp;
        public Astaroth(String name, int atk, int maxHp, int def, int sp) {
        super(name, atk, maxHp, def, sp);
    }
    @Override
    public void takeTurn(Player target) {
            super.attack(target);
            if(hp <=0){
            Ressurection();
             if(resurrect ==0){
            IsAlive = false;
        }
            }
    }
    private void Ressurection(){
        if(resurrect > 0){
            hp = max_hp;
            atk += 20;
            def += 20;
            System.out.println("Astaroth has turned back in time and became stronger");
        }
    }
}
