# 🎓 OOP CONCEPTS IN COCK-CLASHERS
## Object-Oriented Programming Analysis Report

**Project:** Cock Clashers: Object-Oriented Coliseum  
**Author:** Chad Laurence Bojelador  
**Date:** December 1, 2025  
**Repository:** https://github.com/ChadBojelador/Cock-Clashers

---

## 📋 Table of Contents
1. [Encapsulation](#-1-encapsulation)
2. [Inheritance](#-2-inheritance)
3. [Polymorphism](#-3-polymorphism)
4. [Abstraction](#-4-abstraction)
5. [Interface](#-5-interface)
6. [Summary Table](#-complete-summary-table)

---

## 🔒 1. ENCAPSULATION

**Definition:** Encapsulation is the bundling of data (fields) and methods that operate on that data within a single class, while restricting direct access to some of the object's components. This is achieved using access modifiers (`private`, `protected`, `public`).

---

### 📁 `Skill.java` — Full Encapsulation Example
```java
public final class Skill {
    // PRIVATE FIELDS - Cannot be accessed directly from outside
    private final String name;
    private final int damage;
    private final String type;
    private final String soundEffect;

    // CONSTRUCTOR - Only way to set values
    public Skill(String name, int damage, String type, String soundEffect){
        this.name = name;
        this.damage = damage;
        this.type = type;
        this.soundEffect = soundEffect;
    }

    // PUBLIC GETTERS - Controlled access to private data
    public String getName() { return name; }
    public int getDamage() { return damage; }
    public String getType() { return type; }
    public String getSoundEffect() { return soundEffect; }
}
```
**Explanation:** 
- All fields are `private final` — they cannot be changed or accessed directly
- No setters exist — this makes `Skill` an **immutable object**
- Data can only be read through getters, protecting data integrity

---

### 📁 `Rooster.java` — Encapsulation with Protected Access
```java
public abstract class Rooster {
    // PRIVATE FIELDS - Hidden from all other classes
    private final String name;
    private int hp;                    // Only this can change (not final)
    private final int maxHp;
    private final int attack;
    private final int defense;
    private final String type;
    private final List<Skill> skills;
    private BufferedImage frontSprite;
    private BufferedImage backSprite;

    // PUBLIC GETTERS - Safe access
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public String getType() { return type; }
    public int getDefense() { return defense; }
    
    // PROTECTED GETTER - Only subclasses can access attack value
    protected int getAttack() { return attack; }
    
    // CONTROLLED MODIFICATION - hp can only be changed through these methods
    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;  // Validation: HP cannot go negative
    }
    
    public void healFull() { 
        hp = maxHp;  // Controlled way to restore HP
    }
}
```
**Explanation:**
- `private` fields hide internal data from external classes
- `protected getAttack()` allows only subclasses to access attack value
- `takeDamage()` includes validation (HP can't go below 0)
- No direct way to set HP to any value — must use `takeDamage()` or `healFull()`

---

### 📁 `BattleStats.java` — Encapsulation with Behavior Methods
```java
public class BattleStats {
    // PRIVATE FIELDS - All statistics are hidden
    private int totalDamageDealt;
    private int totalDamageTaken;
    private int skillsUsed;
    private int turnsPlayed;
    private int criticalHits;

    // PUBLIC METHODS - Only way to modify private data
    public void recordDamageDealt(int damage) {
        totalDamageDealt += damage;  // Controlled increment
    }

    public void recordDamageTaken(int damage) {
        totalDamageTaken += damage;
    }

    public void recordSkillUsed() {
        skillsUsed++;
    }

    public void recordTurn() {
        turnsPlayed++;
    }

    public void recordCriticalHit() {
        criticalHits++;
    }

    public void displayStats() { ... }  // Formatted output
}
```
**Explanation:**
- No getters for individual fields — data can only be recorded
- Each method serves a specific purpose (single responsibility)
- `displayStats()` provides formatted output without exposing raw data

---

### 📁 `MusicManager.java` — Encapsulation with State Management
```java
public class MusicManager {
    // PRIVATE STATE - Internal audio management
    private Clip musicClip;
    private FloatControl musicVolumeControl;
    private boolean isMuted = false;
    private float currentVolume = 0.6f;
    private String currentTrack = "";
    
    // PUBLIC INTERFACE - Clean methods to control audio
    public void playMusic(String filepath, boolean loop) { ... }
    public void playSound(String filepath) { ... }
    public void stopMusic() { ... }
    public void setVolume(float volume) { ... }
    public void toggleMute() { ... }
    public boolean isPlaying() { ... }
}
```
**Explanation:**
- Complex audio clip management is hidden inside the class
- Users just call simple methods like `playMusic()` or `toggleMute()`
- Internal state (`currentTrack`, `isMuted`) is managed automatically

---

### 📁 `Player.java` — Encapsulation with Mixed Access
```java
public class Player {
    // PUBLIC FIELDS - Accessible for game engine (position tracking)
    public int x, y;
    public int speed;
    public int direction;
    public boolean isMoving;

    // PRIVATE FIELDS - Animation internals hidden
    private BufferedImage spriteSheet;
    private int spriteCounter = 0;
    private int spriteNum = 0;
    private final int[] walkSequence = {0, 1, 2, 3};
    private int sequenceIndex = 0;

    // PRIVATE METHODS - Internal logic hidden
    private void loadImages() { ... }
    private void updateAnimation() { ... }
    private boolean checkCollision(...) { ... }

    // PUBLIC METHODS - External interface
    public void update(...) { ... }
    public void draw(...) { ... }
}
```
**Explanation:**
- Position (`x`, `y`) is public for direct game engine access (design choice)
- Animation details are private — external code doesn't need to know how animation works
- `checkCollision()` is private — collision logic is internal implementation detail

---

## 🧬 2. INHERITANCE

**Definition:** Inheritance allows a class (child/subclass) to inherit properties and methods from another class (parent/superclass). It promotes code reuse and establishes an "is-a" relationship.

---

### 📁 `Rooster.java` — Parent Class and Child Subclasses
```java
// PARENT CLASS (ABSTRACT - Cannot be instantiated directly)
public abstract class Rooster {
    // Common properties for ALL roosters
    private final String name;
    private int hp;
    private final int maxHp;
    private final int attack;
    private final int defense;
    private final String type;
    private final List<Skill> skills;
    
    // Common constructor
    public Rooster(String name, int hp, int attack, int defense, 
                   String type, String frontPath, String backPath) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.defense = defense;
        this.type = type;
        this.skills = new ArrayList<>();
        loadSprites(frontPath, backPath);
    }
    
    // Common methods inherited by all subclasses
    public int attack(Rooster enemy, Skill skill) { ... }
    public void takeDamage(int damage) { ... }
    public boolean isFainted() { ... }
    public final void addSkill(Skill skill) { ... }
    
    // --- CHILD CLASSES (Inherit everything from Rooster) ---
    
    // CHILD 1: ManokNaPula "is-a" Rooster
    public static class ManokNaPula extends Rooster {
        public ManokNaPula(String name) {
            // Call parent constructor with Fire-type stats
            super(name, 
                  GameConstants.MANOK_PULA_HP,      // 120 HP
                  GameConstants.MANOK_PULA_ATK,     // 30 ATK
                  GameConstants.MANOK_PULA_DEF,     // 10 DEF
                  GameConstants.MANOK_PULA_TYPE,    // "fire"
                  GameConstants.PATH_PULA_FRONT, 
                  GameConstants.PATH_PULA_BACK);
            // Add Fire-type specific skills
            addSkill(new Skill("Flame Peck", 35, "fire", "..."));
            addSkill(new Skill("Burning Crow", 45, "fire", "..."));
            addSkill(new Skill("Scratch", 20, "normal", "..."));
        }
    }

    // CHILD 2: ManokNaItim "is-a" Rooster
    public static class ManokNaItim extends Rooster {
        public ManokNaItim(String name) {
            super(name, 
                  GameConstants.MANOK_ITIM_HP,      // 100 HP
                  GameConstants.MANOK_ITIM_ATK,     // 40 ATK
                  GameConstants.MANOK_ITIM_DEF,     // 8 DEF
                  GameConstants.MANOK_ITIM_TYPE,    // "dark"
                  GameConstants.PATH_ITIM_FRONT, 
                  GameConstants.PATH_ITIM_BACK);
            // Add Dark-type specific skills
            addSkill(new Skill("Shadow Claw", 50, "dark", "..."));
            addSkill(new Skill("Night Slash", 35, "dark", "..."));
            addSkill(new Skill("Peck", 15, "normal", "..."));
        }
    }

    // CHILD 3: ManokNaBato "is-a" Rooster
    public static class ManokNaBato extends Rooster {
        public ManokNaBato(String name) {
            super(name, 
                  GameConstants.MANOK_BATO_HP,      // 150 HP
                  GameConstants.MANOK_BATO_ATK,     // 20 ATK
                  GameConstants.MANOK_BATO_DEF,     // 20 DEF
                  GameConstants.MANOK_BATO_TYPE,    // "rock"
                  GameConstants.PATH_BATO_FRONT, 
                  GameConstants.PATH_BATO_BACK);
            // Add Rock-type specific skills
            addSkill(new Skill("Stone Beak", 30, "rock", "..."));
            addSkill(new Skill("Rock Smash", 40, "rock", "..."));
            addSkill(new Skill("Hard Scratch", 18, "normal", "..."));
        }
    }
}
```
**Explanation:**
- `extends` keyword establishes inheritance relationship
- `super()` calls the parent constructor to initialize inherited fields
- All subclasses inherit: `attack()`, `takeDamage()`, `isFainted()`, `getHp()`, etc.
- Each subclass has different stats and skills but same structure
- `abstract` means `Rooster` cannot be created directly — must use subclass

---

### 📁 `GameEngine.java` — Inherits from JPanel
```java
public class GameEngine extends JPanel implements Runnable, KeyListener {
    // Inherits ALL methods from JPanel:
    // - setPreferredSize()
    // - setBackground()
    // - setDoubleBuffered()
    // - repaint()
    // - etc.
    
    public GameEngine() {
        // Using inherited methods from JPanel
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
}
```
**Explanation:**
- `GameEngine` inherits all GUI functionality from `JPanel`
- No need to rewrite drawing, sizing, or buffering code
- Can focus on game-specific logic while using inherited GUI features

---

## 🔄 3. POLYMORPHISM

**Definition:** Polymorphism means "many forms." It allows objects of different classes to be treated as objects of a common parent class. The two types are:
- **Compile-time (Overloading):** Same method name, different parameters
- **Runtime (Overriding):** Subclass provides specific implementation of parent method

---

### 📁 `RoosterFactory.java` — Polymorphism in Action
```java
public class RoosterFactory {
    // Returns DIFFERENT rooster types as the SAME parent type (Rooster)
    public static Rooster createRooster(int choice, String customName) {
        switch (choice) {
            case 1: return new Rooster.ManokNaPula(customName);  // Returns as Rooster
            case 2: return new Rooster.ManokNaItim(customName);  // Returns as Rooster
            case 3: return new Rooster.ManokNaBato(customName);  // Returns as Rooster
            default: return new Rooster.ManokNaPula(customName);
        }
    }

    public static Rooster createRandomRooster(String name) {
        int choice = 1 + (int) (Math.random() * 3);
        return createRooster(choice, name);  // Polymorphic return
    }
}
```
**Explanation:**
- Return type is `Rooster` (parent class)
- Actual object can be `ManokNaPula`, `ManokNaItim`, or `ManokNaBato`
- Calling code doesn't need to know which specific type was created
- Each rooster will behave according to its own skills and stats

**Usage in GameEngine:**
```java
// Polymorphic assignment - don't know which type until runtime
Rooster playerCock = RoosterFactory.createRooster(starterIndex + 1, "My Manok");
Rooster enemyCock = RoosterFactory.createRandomRooster("Wild Chicken");

// Both work the same way even though they might be different types
playerCock.attack(enemyCock, skill);  // Could be any rooster type
enemyCock.takeDamage(damage);         // Works for any rooster type
```

---

### 📁 `GameEngine.java` — Method Overriding (Runtime Polymorphism)
```java
public class GameEngine extends JPanel implements Runnable, KeyListener {
    
    // OVERRIDING Runnable.run()
    @Override
    public void run() {
        // Custom game loop implementation
        while (gameThread != null) {
            update();
            repaint();
        }
    }
    
    // OVERRIDING JPanel.paintComponent()
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Call parent method first
        Graphics2D g2 = (Graphics2D) g.create();
        // Custom drawing code...
    }
    
    // OVERRIDING KeyListener methods
    @Override
    public void keyPressed(KeyEvent e) {
        // Custom key handling for game
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        activeKeys.remove(e.getKeyCode());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used but required by interface
    }
}
```
**Explanation:**
- `@Override` annotation indicates method overriding
- Parent class (`JPanel`) has default `paintComponent()` — we override with custom drawing
- `Runnable` interface requires `run()` — we provide game loop implementation
- `KeyListener` interface requires 3 methods — we implement all of them

---

### 📁 `MusicManager.java` — Method Overloading (Compile-time Polymorphism)
```java
public class MusicManager {
    // OVERLOADED METHOD - Same name, different parameters
    
    // Version 1: Default volume
    public void playSound(String filepath) {
        playSound(filepath, 0.7f);  // Calls version 2 with default volume
    }
    
    // Version 2: Custom volume
    public void playSound(String filepath, float volume) {
        // Actual implementation with volume control
        if (isMuted) return;
        // ... play sound at specified volume
    }
}
```
**Explanation:**
- Two `playSound()` methods with different signatures
- Compiler chooses which version based on arguments provided
- Provides flexibility without duplicate code

---

### 📁 `Player.java` — Switch Expression Polymorphism
```java
public void draw(Graphics2D g2, int tileSize) {
    // Different behavior based on direction value
    int renderRow = switch (direction) {
        case 0 -> 0; // Down - row 0 of sprite sheet
        case 1 -> 3; // Up - row 3 of sprite sheet
        case 2 -> 1; // Left - row 1 of sprite sheet
        case 3 -> 2; // Right - row 2 of sprite sheet
        default -> 0;
    };
    // Same draw code, different sprite row based on direction
}
```
**Explanation:**
- Single method produces different visual output based on state
- Direction value determines which sprite row to use
- Behavior changes at runtime based on player input

---

## 🎭 4. ABSTRACTION

**Definition:** Abstraction is the concept of hiding complex implementation details and showing only the necessary features. It can be achieved through abstract classes and interfaces.

---

### 📁 `Rooster.java` — Abstract Class
```java
// ABSTRACT CLASS - Cannot be instantiated, serves as template
public abstract class Rooster {
    
    // Complex attack calculation HIDDEN inside this method
    public int attack(Rooster enemy, Skill skill) {
        // User doesn't need to know these details:
        int baseDamage = Math.max(GameConstants.MIN_DAMAGE, 
                                  skill.getDamage() - enemy.getDefense());
        double typeMultiplier = TypeEffectiveness.getMultiplier(
                                  skill.getType(), enemy.getType());
        int damage = (int) (baseDamage * typeMultiplier);
        
        boolean isCritical = Math.random() < GameConstants.CRIT_CHANCE;
        if (isCritical) {
            damage = (int) (damage * GameConstants.CRIT_MULTIPLIER);
        }
        
        enemy.takeDamage(damage);
        return damage;
    }
    
    // Private method - implementation detail hidden
    private void loadSprites(String fPath, String bPath) {
        try {
            if(fPath != null) 
                this.frontSprite = ImageIO.read(getClass().getResource(fPath));
            if(bPath != null) 
                this.backSprite = ImageIO.read(getClass().getResource(bPath));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Error loading rooster sprites: " + e.getMessage());
        }
    }
}
```
**Explanation:**
- `abstract` keyword means this class is a template, not a complete class
- Cannot write `new Rooster()` — must use subclass like `new ManokNaPula()`
- Complex damage formula is abstracted into simple `attack()` method
- Users just call `rooster.attack(enemy, skill)` without knowing the math
- Sprite loading complexity is hidden in private method

---

### 📁 `TypeEffectiveness.java` — Abstraction of Game Logic
```java
public class TypeEffectiveness {
    // SIMPLE INTERFACE hides complex type matchup logic
    public static double getMultiplier(String attackType, String defenderType) {
        // Super effective matchups
        if (attackType.equals("fire") && defenderType.equals("normal")) return 1.5;
        if (attackType.equals("dark") && defenderType.equals("fire")) return 1.5;
        if (attackType.equals("rock") && defenderType.equals("dark")) return 1.5;

        // Not very effective matchups
        if (attackType.equals("normal") && defenderType.equals("rock")) return 0.5;
        if (attackType.equals("fire") && defenderType.equals("rock")) return 0.5;
        if (attackType.equals("dark") && defenderType.equals("rock")) return 0.75;

        // Neutral
        return 1.0;
    }

    public static String getEffectivenessMessage(double multiplier) {
        if (multiplier >= 1.5) return " It's super effective!";
        if (multiplier <= 0.5) return " It's not very effective...";
        if (multiplier < 1.0) return " Not very effective. ";
        return "";
    }
}
```
**Explanation:**
- Complex type chart logic is hidden behind simple `getMultiplier()` call
- User doesn't need to memorize "fire beats normal" rules
- Just call `getMultiplier("fire", "rock")` and get the answer
- Abstraction makes the battle system easy to use

---

### 📁 `MusicManager.java` — Abstraction of Audio System
```java
public class MusicManager {
    // Complex audio internals HIDDEN
    private Clip musicClip;
    private FloatControl musicVolumeControl;
    private AudioInputStream audioStream;
    
    // SIMPLE PUBLIC INTERFACE
    public void playMusic(String filepath, boolean loop) {
        // All the complex audio setup is hidden:
        // - AudioSystem.getAudioInputStream()
        // - AudioSystem.getClip()
        // - clip.open(), clip.loop()
        // - FloatControl for volume
        // User just calls: playMusic("/Audio/music.wav", true)
    }
    
    public void toggleMute() {
        // Complex volume control abstracted to simple toggle
        isMuted = !isMuted;
        // ... internal volume adjustments hidden
    }
}
```
**Explanation:**
- Java's audio API is complex (`AudioInputStream`, `Clip`, `FloatControl`, etc.)
- `MusicManager` abstracts this into simple methods: `playMusic()`, `stopMusic()`, `toggleMute()`
- GameEngine just calls `musicManager.playMusic(path, true)` without audio knowledge

---

## 🔌 5. INTERFACE

**Definition:** An interface is a contract that defines what methods a class must implement, without specifying how. It allows unrelated classes to share common behavior.

---

### 📁 `GameEngine.java` — Implementing Multiple Interfaces
```java
// Implements TWO interfaces: Runnable and KeyListener
public class GameEngine extends JPanel implements Runnable, KeyListener {
    
    // ========== RUNNABLE INTERFACE ==========
    // Contract: Must provide run() method for threading
    
    @Override
    public void run() {
        // This is called when gameThread.start() is executed
        double drawInterval = 1_000_000_000.0 / 60.0;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();   // Update game logic
                repaint();  // Redraw screen
                delta--;
            }
        }
    }
    
    // ========== KEYLISTENER INTERFACE ==========
    // Contract: Must provide keyPressed(), keyReleased(), keyTyped()
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        switch (gameState) {
            case STATE_TITLE -> {
                if (code == KeyEvent.VK_ENTER) {
                    gameState = STATE_STARTER_SELECT;
                }
            }
            case STATE_ROAMING -> {
                activeKeys.add(code);  // Track pressed keys
            }
            case STATE_BATTLE -> {
                if (code == KeyEvent.VK_1) executeTurn(0);
                // ... handle battle input
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        activeKeys.remove(e.getKeyCode());  // Track released keys
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Required by interface but not used
        // Empty implementation is valid
    }
}
```
**Explanation:**
- `implements Runnable` — Promise to provide `run()` method for threading
- `implements KeyListener` — Promise to provide keyboard handling methods
- Must implement ALL methods defined in the interface
- `keyTyped()` is required but can be empty if not needed
- Interfaces allow `GameEngine` to work with Java's threading and event systems

**How interfaces are used:**
```java
// In Main.java
GameEngine gamePanel = new GameEngine();
gamePanel.addKeyListener(gamePanel);  // Works because GameEngine implements KeyListener

// Threading
gameThread = new Thread(gamePanel);  // Works because GameEngine implements Runnable
gameThread.start();  // Calls gamePanel.run()
```

---

## 📊 COMPLETE SUMMARY TABLE

| OOP Concept | Files | Key Code | Explanation |
|-------------|-------|----------|-------------|
| **ENCAPSULATION** | `Skill.java` | `private final String name;` + `public String getName()` | Private fields with public getters protect data |
| | `Rooster.java` | `private int hp;` + `public void takeDamage()` | HP can only be modified through controlled methods |
| | `BattleStats.java` | `private int totalDamageDealt;` + `recordDamageDealt()` | Statistics can only be incremented, not set directly |
| | `MusicManager.java` | `private Clip musicClip;` + `public void playMusic()` | Complex audio management hidden behind simple API |
| | `Player.java` | `private BufferedImage spriteSheet;` | Animation internals are hidden from external code |
| **INHERITANCE** | `Rooster.java` | `class ManokNaPula extends Rooster` | Child classes inherit all parent properties and methods |
| | | `super(name, hp, atk, def, type, ...)` | Subclass calls parent constructor to initialize |
| | `GameEngine.java` | `class GameEngine extends JPanel` | Game panel inherits all Swing GUI functionality |
| **POLYMORPHISM** | `RoosterFactory.java` | `public static Rooster createRooster()` | Returns different types through parent reference |
| | `GameEngine.java` | `@Override public void run()` | Overrides Runnable interface method |
| | | `@Override public void paintComponent()` | Overrides JPanel drawing method |
| | | `@Override public void keyPressed()` | Implements KeyListener interface |
| | `MusicManager.java` | `playSound(String)` + `playSound(String, float)` | Method overloading - same name, different parameters |
| | `Player.java` | `switch (direction) { case 0 -> 0; ... }` | Different behavior based on runtime value |
| **ABSTRACTION** | `Rooster.java` | `public abstract class Rooster` | Cannot instantiate directly, serves as template |
| | | `public int attack(enemy, skill)` | Complex damage formula hidden in simple method |
| | `TypeEffectiveness.java` | `getMultiplier(attackType, defenderType)` | Type matchup complexity hidden behind simple call |
| | `MusicManager.java` | `playMusic(path, loop)` | Audio API complexity abstracted to simple methods |
| **INTERFACE** | `GameEngine.java` | `implements Runnable` | Contract to provide `run()` for threading |
| | | `implements KeyListener` | Contract to provide keyboard event handlers |

---

## ✅ CONCLUSION

The Cock-Clashers project successfully demonstrates all five core Object-Oriented Programming principles:

1. **Encapsulation** is used extensively to protect data integrity across all entity classes
2. **Inheritance** creates a clean hierarchy with `Rooster` as the parent and three specialized child classes
3. **Polymorphism** enables flexible rooster creation and method overriding for game functionality
4. **Abstraction** hides complex implementations behind simple, intuitive interfaces
5. **Interfaces** allow the game engine to integrate with Java's threading and event systems

These OOP principles make the codebase maintainable, extensible, and easy to understand.

---

*Report generated on December 1, 2025*  
*Project: Cock Clashers - Object-Oriented Coliseum*
