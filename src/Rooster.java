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

    public static class ManokNaPuti extends Rooster {
        public ManokNaPuti(String name) {
            super(name, 120, 30, 10);
            addSkill(new Skill("Flame Peck", 35, "fire"));
            addSkill(new Skill("Burning Crow", 45, "fire"));
            addSkill(new Skill("Scratch", 20, "normal"));
        }

        @Override
        public void attack(Rooster enemy, Skill skill) {
            int damage = Math.max(1, skill.getDamage() - enemy.getDefense());
            enemy.takeDamage(damage);
            System.out.println(getName() + " uses " + skill.getName() +
                    " on " + enemy.getName() + " for " + damage + " damage!");
        }
    }
    
    public static class ManokNaBato extends Rooster {
        public ManokNaBato(String name) {
            super(name, 150, 20, 20);
            addSkill(new Skill("Stone Beak", 30, "rock"));
            addSkill(new Skill("Rock Smash", 40, "rock"));
            addSkill(new Skill("Hard Scratch", 18, "normal"));
        }

        @Override
        public void attack(Rooster enemy, Skill skill) {
            int damage = Math.max(1, skill.getDamage() - enemy.getDefense());
            enemy.takeDamage(damage);
            System.out.println(getName() + " uses " + skill.getName() +
                    " on " + enemy.getName() + " for " + damage + " damage!");
        }
    }
}



