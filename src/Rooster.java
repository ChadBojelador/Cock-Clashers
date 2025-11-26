import java.util. ArrayList;
import java.util. List;

public abstract class Rooster {
    private String name;
    private int hp;
    private int maxHp;
    private int attack;
    private int defense;
    private String type;
    private List<Skill> skills;

    public Rooster(String name, int hp, int attack, int defense, String type){
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.type = type;
        this.skills = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public int getHp(){
        return hp;
    }

    public int getMaxHp(){
        return maxHp;
    }

    public String getType(){
        return type;
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public void heal(int amount) {
        hp += amount;
        if (hp > maxHp) hp = maxHp;
    }

    public boolean isFainted() {
        return hp <= 0;
    }

    public void attack(Rooster enemy) {
        int damage = Math.max(GameConstants.MIN_DAMAGE, attack - enemy.defense);
        enemy.takeDamage(damage);
        System. out.println(name + " attacks " + enemy.getName() + " for " + damage + " damage!");
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

    protected int getAttack() {
        return attack;
    }

    // Unified attack method with skill - no longer abstract
    public int attack(Rooster enemy, Skill skill) {
        // Calculate base damage
        int baseDamage = Math.max(GameConstants.MIN_DAMAGE, skill.getDamage() - enemy.getDefense());

        // Apply type effectiveness
        double typeMultiplier = TypeEffectiveness.getMultiplier(skill.getType(), enemy.getType());
        int damage = (int) (baseDamage * typeMultiplier);

        // Check for critical hit
        boolean isCritical = Math.random() < GameConstants.CRIT_CHANCE;
        if (isCritical) {
            damage = (int) (damage * GameConstants.CRIT_MULTIPLIER);
            System.out. println("💥 Critical hit!");
        }

        // Apply damage
        enemy.takeDamage(damage);

        // Display attack message
        String effectiveness = TypeEffectiveness.getEffectivenessMessage(typeMultiplier);
        System.out.println(getName() + " uses " + skill.getName() +
                " on " + enemy.getName() + " for " + damage + " damage!" + effectiveness);

        return damage;
    }

    public static class ManokNaPuti extends Rooster {
        public ManokNaPuti(String name) {
            super(name, GameConstants.MANOK_PUTI_HP, GameConstants. MANOK_PUTI_ATK,
                    GameConstants.MANOK_PUTI_DEF, GameConstants. MANOK_PUTI_TYPE);
            addSkill(new Skill("Flame Peck", 35, "fire"));
            addSkill(new Skill("Burning Crow", 45, "fire"));
            addSkill(new Skill("Scratch", 20, "normal"));
        }
    }

    public static class ManokNaItim extends Rooster {
        public ManokNaItim(String name) {
            super(name, GameConstants. MANOK_ITIM_HP, GameConstants.MANOK_ITIM_ATK,
                    GameConstants. MANOK_ITIM_DEF, GameConstants.MANOK_ITIM_TYPE);
            addSkill(new Skill("Shadow Claw", 50, "dark"));
            addSkill(new Skill("Night Slash", 35, "dark"));
            addSkill(new Skill("Peck", 15, "normal"));
        }
    }

    public static class ManokNaBato extends Rooster {
        public ManokNaBato(String name) {
            super(name, GameConstants.MANOK_BATO_HP, GameConstants.MANOK_BATO_ATK,
                    GameConstants.MANOK_BATO_DEF, GameConstants.MANOK_BATO_TYPE);
            addSkill(new Skill("Stone Beak", 30, "rock"));
            addSkill(new Skill("Rock Smash", 40, "rock"));
            addSkill(new Skill("Hard Scratch", 18, "normal"));
        }
    }
}