import java.util.ArrayList;
import java.util.List;

public abstract class Rooster {
    private String name;
    private int hp;
    private int attack;
    private int defense;
    private List<Skill> skills;
    public Rooster(String name, int hp, int attack, int defense){
        this.name = name;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.skills = new ArrayList<>();
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

    public void addSkill(Skill skill) {
        skills.add(skill);
    }

    public List<Skill> getSkills() {
        return skills;
    }
    public int getDefense() {
        return defense;
    }

    public abstract void attack(Rooster enemy, Skill skill);
}


