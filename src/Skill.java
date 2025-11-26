public final class Skill {
    private final String name;
    private final int damage;
    private final String type;

    public Skill(String name, int damage, String type){
        this.name = name;
        this.damage = damage;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public String getType() {
        return type;
    }
}