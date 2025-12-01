import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

public abstract class Rooster {

    private final String name;
    private int hp; 
    private final int maxHp;
    private final int attack;
    private final int defense;
    private final String type;
    private final List<Skill> skills;
    
   
    private BufferedImage frontSprite;
    private BufferedImage backSprite;

    public Rooster(String name, int hp, int attack, int defense, String type, String frontPath, String backPath){
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.type = type;
        this.skills = new ArrayList<>();
        loadSprites(frontPath, backPath);
    }
    
    private void loadSprites(String fPath, String bPath) {
        try {
            if(fPath != null) this.frontSprite = ImageIO.read(getClass().getResource(fPath));
            if(bPath != null) this.backSprite = ImageIO.read(getClass().getResource(bPath));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error loading rooster sprites: " + e.getMessage());
        }
    }

    public BufferedImage getFrontSprite() { return frontSprite; }
    public BufferedImage getBackSprite() { return backSprite; }
    
    public void healFull() { 
        hp = maxHp;
    }

    public String getName(){ return name; }
    public int getHp(){ return hp; }
    public int getMaxHp(){ return maxHp; }
    public String getType(){ return type; }
    public int getDefense() { return defense; }
    protected int getAttack() { return attack; }
    
    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;
    }

    public boolean isFainted() {
        return hp <= 0;
    }

    public final void addSkill(Skill skill) {
        skills.add(skill);
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public int attack(Rooster enemy, Skill skill) {
        int baseDamage = Math.max(GameConstants.MIN_DAMAGE, skill.getDamage() - enemy.getDefense());
        double typeMultiplier = TypeEffectiveness.getMultiplier(skill.getType(), enemy.getType());
        int damage = (int) (baseDamage * typeMultiplier);

        boolean isCritical = Math.random() < GameConstants.CRIT_CHANCE;
        if (isCritical) {
            damage = (int) (damage * GameConstants.CRIT_MULTIPLIER);
        }
        enemy.takeDamage(damage);
        return damage;
    }

    // --- SUBCLASSES ---

    public static class ManokNaPula extends Rooster {
        public ManokNaPula(String name) {
            super(name, GameConstants.MANOK_PULA_HP, GameConstants.MANOK_PULA_ATK,
                    GameConstants.MANOK_PULA_DEF, GameConstants.MANOK_PULA_TYPE,
                    GameConstants.PATH_PULA_FRONT, GameConstants.PATH_PULA_BACK);
            addSkill(new Skill("Flame Peck", 35, "fire", "/Audio/Sounds/Elemental/Fire.wav"));
            addSkill(new Skill("Burning Crow", 45, "fire", "/Audio/Sounds/Elemental/Fireball.wav"));
            addSkill(new Skill("Scratch", 20, "normal", "/Audio/Sounds/Whoosh & Slash/Slash.wav"));
        }
    }

    public static class ManokNaItim extends Rooster {
        public ManokNaItim(String name) {
            super(name, GameConstants.MANOK_ITIM_HP, GameConstants.MANOK_ITIM_ATK,
                    GameConstants.MANOK_ITIM_DEF, GameConstants.MANOK_ITIM_TYPE,
                    GameConstants.PATH_ITIM_FRONT, GameConstants.PATH_ITIM_BACK);
            addSkill(new Skill("Shadow Claw", 50, "dark", "/Audio/Sounds/Magic & Skill/Magic5.wav"));
            addSkill(new Skill("Night Slash", 35, "dark", "/Audio/Sounds/Whoosh & Slash/Slash3.wav"));
            addSkill(new Skill("Peck", 15, "normal", "/Audio/Sounds/Whoosh & Slash/Whoosh.wav"));
        }
    }

    public static class ManokNaBato extends Rooster {
        public ManokNaBato(String name) {
            super(name, GameConstants.MANOK_BATO_HP, GameConstants.MANOK_BATO_ATK,
                    GameConstants.MANOK_BATO_DEF, GameConstants.MANOK_BATO_TYPE,
                    GameConstants.PATH_BATO_FRONT, GameConstants.PATH_BATO_BACK);
            addSkill(new Skill("Stone Beak", 30, "rock", "/Audio/Sounds/Hit & Impact/Impact3.wav"));
            addSkill(new Skill("Rock Smash", 40, "rock", "/Audio/Sounds/Hit & Impact/Impact5.wav"));
            addSkill(new Skill("Hard Scratch", 18, "normal", "/Audio/Sounds/Whoosh & Slash/Slash2.wav"));
        }
    }
}