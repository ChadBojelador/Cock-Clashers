public class ManokNaPuti extends Rooster {
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