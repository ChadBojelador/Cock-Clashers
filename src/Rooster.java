public class Rooster {
    private String name;
    private int hp;
    private int attack;
    private int defense;

    public Rooster(String name, int hp, int attack, int defense){
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
    }

    public String getName(){
        return name;
    }
    public int getHp(){
        return hp;
    }


    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public boolean isFainted() {
        return hp <= 0;
    }

    public void attack(Rooster enemy) {
        int damage = Math.max(1, attack - enemy.defense);
        enemy.takeDamage(damage);
        System.out.println(name + " attacks " + enemy.getName() + " for " + damage + " damage!");
    }
}

