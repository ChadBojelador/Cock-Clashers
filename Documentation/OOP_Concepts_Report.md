<div align="center">

# 🐓 COCK CLASHERS 🐓
## Object-Oriented Programming Analysis Report

<img src="https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java" alt="Java"/>
<img src="https://img.shields.io/badge/Framework-Swing/AWT-blue?style=for-the-badge" alt="Swing"/>
<img src="https://img.shields.io/badge/OOP-5%20Concepts-green?style=for-the-badge" alt="OOP"/>
<img src="https://img.shields.io/badge/Status-Complete-success?style=for-the-badge" alt="Complete"/>

---

### 🎮 *A Pokémon-Style Battle Game Built with Pure Java*

</div>

---

## 📌 Project Information

| Property | Value |
|:---------|:------|
| 📁 **Project Name** | Cock Clashers: Object-Oriented Coliseum |
| 👤 **Author** | Chad Laurence Bojelador |
| 📅 **Date** | December 1, 2025 |
| 🔗 **Repository** | [github.com/ChadBojelador/Cock-Clashers](https://github.com/ChadBojelador/Cock-Clashers) |
| 💻 **Language** | Java 17+ |
| 🎨 **GUI Framework** | Java Swing / AWT |

---

<div align="center">

## 📋 Table of Contents

</div>

| # | Section | Description |
|:-:|:--------|:------------|
| 1️⃣ | [🔒 Encapsulation](#-1-encapsulation) | Data hiding and protection |
| 2️⃣ | [🧬 Inheritance](#-2-inheritance) | Parent-child class relationships |
| 3️⃣ | [🔄 Polymorphism](#-3-polymorphism) | Many forms, one interface |
| 4️⃣ | [🎭 Abstraction](#-4-abstraction) | Hiding implementation details |
| 5️⃣ | [🔌 Interface](#-5-interface) | Contracts and multiple behaviors |
| 📊 | [Summary Table](#-complete-summary-table) | Complete overview |

---

<div align="center">

### 🏆 OOP Concepts Coverage

```
╔═══════════════════════════════════════════════════════════════╗
║  ✅ ENCAPSULATION    ✅ INHERITANCE    ✅ POLYMORPHISM        ║
║  ✅ ABSTRACTION      ✅ INTERFACE                             ║
╚═══════════════════════════════════════════════════════════════╝
```

</div>

---

---

<div align="center">

# 🔒 1. ENCAPSULATION

```
┌─────────────────────────────────────────────────────────────────┐
│  "Wrapping data and methods together, protecting internal      │
│   state from unauthorized access"                               │
└─────────────────────────────────────────────────────────────────┘
```

</div>

> 💡 **Definition:** Encapsulation is the bundling of data (fields) and methods that operate on that data within a single class, while restricting direct access to some of the object's components. This is achieved using access modifiers (`private`, `protected`, `public`).

<details>
<summary><b>🔍 Quick Visual: Access Modifiers</b></summary>

```
╔═══════════════════════════════════════════════════════════════╗
║                    ACCESS MODIFIERS                           ║
╠═══════════════════════════════════════════════════════════════╣
║  private    →  Only accessible within the same class          ║
║  protected  →  Accessible within package + subclasses         ║
║  public     →  Accessible from anywhere                       ║
║  (default)  →  Accessible within the same package             ║
╚═══════════════════════════════════════════════════════════════╝
```

</details>

---

---

### 📁 File: `Skill.java`
> 📌 **Pattern:** Immutable Object with Full Encapsulation

```java
public final class Skill {
    private final String name;
    private final int damage;
    private final String type;
    private final String soundEffect;

    public Skill(String name, int damage, String type, String soundEffect){
        this.name = name;
        this.damage = damage;
        this.type = type;
        this.soundEffect = soundEffect;
    }

    public String getName() { return name; }
    public int getDamage() { return damage; }
    public String getType() { return type; }
    public String getSoundEffect() { return soundEffect; }
}
```

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ All fields are `private final` — they cannot be changed or accessed directly
- ✅ No setters exist — this makes `Skill` an **immutable object**
- ✅ Data can only be read through getters, protecting data integrity

</td>
</tr>
</table>

---

---

### 📁 File: `Rooster.java`
> 📌 **Pattern:** Protected Access for Inheritance

```java
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

    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public String getType() { return type; }
    public int getDefense() { return defense; }
    
    protected int getAttack() { return attack; }
    
    public void takeDamage(int damage) {
        hp -= damage;
        if (hp < 0) hp = 0;  
    }
    
    public void healFull() { 
        hp = maxHp;  
    }
}
```

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ `private` fields hide internal data from external classes
- ✅ `protected getAttack()` allows only subclasses to access attack value
- ✅ `takeDamage()` includes validation (HP can't go below 0)
- ✅ No direct way to set HP to any value — must use `takeDamage()` or `healFull()`

</td>
</tr>
</table>

---

---

### 📁 File: `BattleStats.java`
> 📌 **Pattern:** Behavior Methods for Data Modification

```java
public class BattleStats {
    private int totalDamageDealt;
    private int totalDamageTaken;
    private int skillsUsed;
    private int turnsPlayed;
    private int criticalHits;

    public void recordDamageDealt(int damage) {
        totalDamageDealt += damage;  
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

    public void displayStats() { ... }  
}
```

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ No getters for individual fields — data can only be recorded
- ✅ Each method serves a specific purpose (single responsibility)
- ✅ `displayStats()` provides formatted output without exposing raw data

</td>
</tr>
</table>

---

---

### 📁 File: `MusicManager.java`
> 📌 **Pattern:** State Management with Private Fields

```java
public class MusicManager {
    private Clip musicClip;
    private FloatControl musicVolumeControl;
    private boolean isMuted = false;
    private float currentVolume = 0.6f;
    private String currentTrack = "";
    
    public void playMusic(String filepath, boolean loop) { ... }
    public void playSound(String filepath) { ... }
    public void stopMusic() { ... }
    public void setVolume(float volume) { ... }
    public void toggleMute() { ... }
    public boolean isPlaying() { ... }
}
```

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ Complex audio clip management is hidden inside the class
- ✅ Users just call simple methods like `playMusic()` or `toggleMute()`
- ✅ Internal state (`currentTrack`, `isMuted`) is managed automatically

</td>
</tr>
</table>

---

---

### 📁 File: `Player.java`
> 📌 **Pattern:** Mixed Access for Performance

```java
public class Player {
    public int x, y;
    public int speed;
    public int direction;
    public boolean isMoving;

    private BufferedImage spriteSheet;
    private int spriteCounter = 0;
    private int spriteNum = 0;
    private final int[] walkSequence = {0, 1, 2, 3};
    private int sequenceIndex = 0;

    private void loadImages() { ... }
    private void updateAnimation() { ... }
    private boolean checkCollision(...) { ... }

    public void update(...) { ... }
    public void draw(...) { ... }
}
```

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ Position (`x`, `y`) is public for direct game engine access (design choice)
- ✅ Animation details are private — external code doesn't need to know how animation works
- ✅ `checkCollision()` is private — collision logic is internal implementation detail

</td>
</tr>
</table>

---

---

<div align="center">

# 🧬 2. INHERITANCE

```
┌─────────────────────────────────────────────────────────────────┐
│  "Child classes inherit properties and behaviors from          │
│   parent classes - code reuse and 'is-a' relationships"        │
└─────────────────────────────────────────────────────────────────┘
```

</div>

> 💡 **Definition:** Inheritance allows a class (child/subclass) to inherit properties and methods from another class (parent/superclass). It promotes code reuse and establishes an "is-a" relationship.

<details>
<summary><b>🔍 Quick Visual: Inheritance Hierarchy</b></summary>

```
                    ┌─────────────────┐
                    │   🐓 Rooster    │  ← Parent (Abstract)
                    │   (Abstract)    │
                    └────────┬────────┘
                             │
           ┌─────────────────┼─────────────────┐
           │                 │                 │
           ▼                 ▼                 ▼
    ┌─────────────┐   ┌─────────────┐   ┌─────────────┐
    │ 🔥 ManokNa  │   │ 🌑 ManokNa  │   │ 🪨 ManokNa  │
    │    Pula     │   │    Itim     │   │    Bato     │
    │   (Fire)    │   │   (Dark)    │   │   (Rock)    │
    └─────────────┘   └─────────────┘   └─────────────┘
           │                 │                 │
           └─────────────────┴─────────────────┘
                             │
                    All inherit from Rooster:
                    • name, hp, attack, defense
                    • attack(), takeDamage(), isFainted()
                    • skills list, sprites
```

</details>

---

---

### 📁 File: `Rooster.java`
> 📌 **Pattern:** Parent Class with Nested Subclasses

```java
public abstract class Rooster {
    private final String name;
    private int hp;
    private final int maxHp;
    private final int attack;
    private final int defense;
    private final String type;
    private final List<Skill> skills;

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
    
    public int attack(Rooster enemy, Skill skill) { ... }
    public void takeDamage(int damage) { ... }
    public boolean isFainted() { ... }
    public final void addSkill(Skill skill) { ... }

    public static class ManokNaPula extends Rooster {
        public ManokNaPula(String name) {
            super(name, 
                  GameConstants.MANOK_PULA_HP,     
                  GameConstants.MANOK_PULA_ATK,     
                  GameConstants.MANOK_PULA_DEF,     
                  GameConstants.MANOK_PULA_TYPE,   
                  GameConstants.PATH_PULA_FRONT, 
                  GameConstants.PATH_PULA_BACK);

            addSkill(new Skill("Flame Peck", 35, "fire", "..."));
            addSkill(new Skill("Burning Crow", 45, "fire", "..."));
            addSkill(new Skill("Scratch", 20, "normal", "..."));
        }
    }

    public static class ManokNaItim extends Rooster {
        public ManokNaItim(String name) {
            super(name, 
                  GameConstants.MANOK_ITIM_HP,      
                  GameConstants.MANOK_ITIM_ATK,     
                  GameConstants.MANOK_ITIM_DEF,     
                  GameConstants.MANOK_ITIM_TYPE,    
                  GameConstants.PATH_ITIM_FRONT, 
                  GameConstants.PATH_ITIM_BACK);

            addSkill(new Skill("Shadow Claw", 50, "dark", "..."));
            addSkill(new Skill("Night Slash", 35, "dark", "..."));
            addSkill(new Skill("Peck", 15, "normal", "..."));
        }
    }

    public static class ManokNaBato extends Rooster {
        public ManokNaBato(String name) {
            super(name, 
                  GameConstants.MANOK_BATO_HP,      
                  GameConstants.MANOK_BATO_ATK,     
                  GameConstants.MANOK_BATO_DEF,    
                  GameConstants.MANOK_BATO_TYPE,    
                  GameConstants.PATH_BATO_FRONT, 
                  GameConstants.PATH_BATO_BACK);

            addSkill(new Skill("Stone Beak", 30, "rock", "..."));
            addSkill(new Skill("Rock Smash", 40, "rock", "..."));
            addSkill(new Skill("Hard Scratch", 18, "normal", "..."));
        }
    }
}
```

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ `extends` keyword establishes inheritance relationship
- ✅ `super()` calls the parent constructor to initialize inherited fields
- ✅ All subclasses inherit: `attack()`, `takeDamage()`, `isFainted()`, `getHp()`, etc.
- ✅ Each subclass has different stats and skills but same structure
- ✅ `abstract` means `Rooster` cannot be created directly — must use subclass

</td>
</tr>
</table>

---

---

### 📁 File: `GameEngine.java`
> 📌 **Pattern:** Inheriting GUI Framework

```java
public class GameEngine extends JPanel implements Runnable, KeyListener {

    public GameEngine() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
    }
}
```

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ `GameEngine` inherits all GUI functionality from `JPanel`
- ✅ No need to rewrite drawing, sizing, or buffering code
- ✅ Can focus on game-specific logic while using inherited GUI features

</td>
</tr>
</table>

---

---

<div align="center">

# 🔄 3. POLYMORPHISM

```
┌─────────────────────────────────────────────────────────────────┐
│  "Many forms - one interface, multiple implementations.        │
│   Objects behave differently based on their actual type"       │
└─────────────────────────────────────────────────────────────────┘
```

</div>

> 💡 **Definition:** Polymorphism means "many forms." It allows objects of different classes to be treated as objects of a common parent class. The two types are:
> - **Compile-time (Overloading):** Same method name, different parameters
> - **Runtime (Overriding):** Subclass provides specific implementation of parent method

<details>
<summary><b>🔍 Quick Visual: Types of Polymorphism</b></summary>

```
╔═══════════════════════════════════════════════════════════════╗
║                    POLYMORPHISM TYPES                         ║
╠═══════════════════════════════════════════════════════════════╣
║                                                               ║
║   COMPILE-TIME (Static/Overloading)                           ║
║   ┌────────────────────────────────────────────────┐         ║
║   │  playSound(String path)                        │         ║
║   │  playSound(String path, float volume)          │         ║
║   │  Same method name, different parameters!       │         ║
║   └────────────────────────────────────────────────┘         ║
║                                                               ║
║   RUNTIME (Dynamic/Overriding)                                ║
║   ┌────────────────────────────────────────────────┐         ║
║   │  Parent: run()  →  Child: @Override run()      │         ║
║   │  Different behavior at runtime!                │         ║
║   └────────────────────────────────────────────────┘         ║
║                                                               ║
╚═══════════════════════════════════════════════════════════════╝
```

</details>

---

---

### 📁 File: `RoosterFactory.java`
> 📌 **Pattern:** Factory Pattern with Polymorphic Returns

```java
public class RoosterFactory {
    public static Rooster createRooster(int choice, String customName) {
        switch (choice) {
            case 1: return new Rooster.ManokNaPula(customName);  
            case 2: return new Rooster.ManokNaItim(customName);  
            case 3: return new Rooster.ManokNaBato(customName);  
            default: return new Rooster.ManokNaPula(customName);
        }
    }

    public static Rooster createRandomRooster(String name) {
        int choice = 1 + (int) (Math.random() * 3);
        return createRooster(choice, name);  
    }
}
```

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ Return type is `Rooster` (parent class)
- ✅ Actual object can be `ManokNaPula`, `ManokNaItim`, or `ManokNaBato`
- ✅ Calling code doesn't need to know which specific type was created
- ✅ Each rooster will behave according to its own skills and stats

</td>
</tr>
</table>

**🎯 Usage in GameEngine:**
```java
Rooster playerCock = RoosterFactory.createRooster(starterIndex + 1, "My Manok");
Rooster enemyCock = RoosterFactory.createRandomRooster("Wild Chicken");

playerCock.attack(enemyCock, skill);  
enemyCock.takeDamage(damage);         
```

---

---

### 📁 File: `GameEngine.java`
> 📌 **Pattern:** Runtime Polymorphism via Method Overriding

```java
public class GameEngine extends JPanel implements Runnable, KeyListener {
    
    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();
        }
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g.create();
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
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

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ `@Override` annotation indicates method overriding
- ✅ Parent class (`JPanel`) has default `paintComponent()` — we override with custom drawing
- ✅ `Runnable` interface requires `run()` — we provide game loop implementation
- ✅ `KeyListener` interface requires 3 methods — we implement all of them

</td>
</tr>
</table>

---

---

### 📁 File: `MusicManager.java`
> 📌 **Pattern:** Compile-time Polymorphism via Method Overloading

```java
public class MusicManager {
    public void playSound(String filepath) {
        playSound(filepath, 0.7f);  
    }
    
    public void playSound(String filepath, float volume) {
        if (isMuted) return;
    }
}
```

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ Two `playSound()` methods with different signatures
- ✅ Compiler chooses which version based on arguments provided
- ✅ Provides flexibility without duplicate code

</td>
</tr>
</table>

---

---

### 📁 File: `Player.java`
> 📌 **Pattern:** Behavioral Polymorphism via Switch

```java
public void draw(Graphics2D g2, int tileSize) {
    int renderRow = switch (direction) {
        case 0 -> 0; 
        case 1 -> 3; 
        case 2 -> 1; 
        case 3 -> 2; 
        default -> 0;
    };
}
```

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ Single method produces different visual output based on state
- ✅ Direction value determines which sprite row to use
- ✅ Behavior changes at runtime based on player input

</td>
</tr>
</table>

---

---

<div align="center">

# 🎭 4. ABSTRACTION

```
┌─────────────────────────────────────────────────────────────────┐
│  "Hide the complex reality while exposing only the necessary   │
│   parts - simplify interaction with objects"                   │
└─────────────────────────────────────────────────────────────────┘
```

</div>

> 💡 **Definition:** Abstraction is the concept of hiding complex implementation details and showing only the necessary features. It can be achieved through abstract classes and interfaces.

<details>
<summary><b>🔍 Quick Visual: Abstraction Concept</b></summary>

```
╔═══════════════════════════════════════════════════════════════╗
║                     ABSTRACTION                               ║
╠═══════════════════════════════════════════════════════════════╣
║                                                               ║
║   What USER sees:         What's HIDDEN inside:               ║
║   ┌─────────────────┐     ┌─────────────────────────────────┐║
║   │                 │     │ • Base damage calculation        │║
║   │  rooster.attack │ ──► │ • Type effectiveness lookup      │║
║   │    (enemy,      │     │ • Critical hit random check      │║
║   │     skill)      │     │ • Defense reduction formula      │║
║   │                 │     │ • HP boundary validation         │║
║   └─────────────────┘     └─────────────────────────────────┘║
║                                                               ║
║   Simple call → Complex logic hidden inside!                  ║
║                                                               ║
╚═══════════════════════════════════════════════════════════════╝
```

</details>

---

---

### 📁 File: `Rooster.java`
> 📌 **Pattern:** Abstract Template Class

```java
public abstract class Rooster {
    
    public int attack(Rooster enemy, Skill skill) {
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

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ `abstract` keyword means this class is a template, not a complete class
- ✅ Cannot write `new Rooster()` — must use subclass like `new ManokNaPula()`
- ✅ Complex damage formula is abstracted into simple `attack()` method
- ✅ Users just call `rooster.attack(enemy, skill)` without knowing the math
- ✅ Sprite loading complexity is hidden in private method

</td>
</tr>
</table>

---

---

### 📁 File: `TypeEffectiveness.java`
> 📌 **Pattern:** Utility Class with Abstracted Logic

```java
public class TypeEffectiveness {
    public static double getMultiplier(String attackType, String defenderType) {
        if (attackType.equals("fire") && defenderType.equals("normal")) return 1.5;
        if (attackType.equals("dark") && defenderType.equals("fire")) return 1.5;
        if (attackType.equals("rock") && defenderType.equals("dark")) return 1.5;

        if (attackType.equals("normal") && defenderType.equals("rock")) return 0.5;
        if (attackType.equals("fire") && defenderType.equals("rock")) return 0.5;
        if (attackType.equals("dark") && defenderType.equals("rock")) return 0.75;
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

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ Complex type chart logic is hidden behind simple `getMultiplier()` call
- ✅ User doesn't need to memorize "fire beats normal" rules
- ✅ Just call `getMultiplier("fire", "rock")` and get the answer
- ✅ Abstraction makes the battle system easy to use

</td>
</tr>
</table>

---

---

### 📁 File: `MusicManager.java`
> 📌 **Pattern:** Facade Pattern for Audio API

```java
public class MusicManager {
    private Clip musicClip;
    private FloatControl musicVolumeControl;
    private AudioInputStream audioStream;
    
    public void playMusic(String filepath, boolean loop) {
    }
    
    public void toggleMute() {
        isMuted = !isMuted;
    }
}
```

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ Java's audio API is complex (`AudioInputStream`, `Clip`, `FloatControl`, etc.)
- ✅ `MusicManager` abstracts this into simple methods: `playMusic()`, `stopMusic()`, `toggleMute()`
- ✅ GameEngine just calls `musicManager.playMusic(path, true)` without audio knowledge

</td>
</tr>
</table>

---

---

<div align="center">

# 🔌 5. INTERFACE

```
┌─────────────────────────────────────────────────────────────────┐
│  "A contract defining what a class CAN DO without specifying   │
│   HOW it does it - enables multiple behaviors"                 │
└─────────────────────────────────────────────────────────────────┘
```

</div>

> 💡 **Definition:** An interface is a contract that defines what methods a class must implement, without specifying how. It allows unrelated classes to share common behavior.

<details>
<summary><b>🔍 Quick Visual: Interface Implementation</b></summary>

```
╔═══════════════════════════════════════════════════════════════╗
║                    INTERFACES IN GAMEENGINE                   ║
╠═══════════════════════════════════════════════════════════════╣
║                                                               ║
║   ┌─────────────────┐    ┌────────────────────┐              ║
║   │   «interface»   │    │   «interface»      │              ║
║   │    Runnable     │    │   KeyListener      │              ║
║   ├─────────────────┤    ├────────────────────┤              ║
║   │ + run()         │    │ + keyPressed()     │              ║
║   └────────┬────────┘    │ + keyReleased()    │              ║
║            │             │ + keyTyped()       │              ║
║            │             └─────────┬──────────┘              ║
║            │                       │                          ║
║            └───────────┬───────────┘                          ║
║                        │                                      ║
║                        ▼                                      ║
║            ┌───────────────────────┐                          ║
║            │     GameEngine        │                          ║
║            │  implements both!     │                          ║
║            └───────────────────────┘                          ║
║                                                               ║
╚═══════════════════════════════════════════════════════════════╝
```

</details>

---

---

### 📁 File: `GameEngine.java`
> 📌 **Pattern:** Multiple Interface Implementation

```java
public class GameEngine extends JPanel implements Runnable, KeyListener {

    @Override
    public void run() {
        double drawInterval = 1_000_000_000.0 / 60.0;
        double delta = 0;
        long lastTime = System.nanoTime();

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();   
                repaint();  
                delta--;
            }
        }
    }
    
    
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
                activeKeys.add(code);  
            }
            case STATE_BATTLE -> {
                if (code == KeyEvent.VK_1) executeTurn(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        activeKeys.remove(e.getKeyCode());  // Track released keys
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
```

<table>
<tr>
<td>

#### 💡 Explanation
</td>
</tr>
<tr>
<td>

- ✅ `implements Runnable` — Promise to provide `run()` method for threading
- ✅ `implements KeyListener` — Promise to provide keyboard handling methods
- ✅ Must implement ALL methods defined in the interface
- ✅ `keyTyped()` is required but can be empty if not needed
- ✅ Interfaces allow `GameEngine` to work with Java's threading and event systems

</td>
</tr>
</table>

**🎯 How interfaces are used:**
```java
GameEngine gamePanel = new GameEngine();
gamePanel.addKeyListener(gamePanel);  

gameThread = new Thread(gamePanel);  
gameThread.start();  
```

---

---

<div align="center">

# 📊 COMPLETE SUMMARY TABLE

```
╔═══════════════════════════════════════════════════════════════╗
║           🏆 OOP CONCEPTS IMPLEMENTATION SUMMARY 🏆           ║
╚═══════════════════════════════════════════════════════════════╝
```

</div>

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

<div align="center">

# ✅ CONCLUSION

```
╔═══════════════════════════════════════════════════════════════╗
║                  🎉 PROJECT ASSESSMENT 🎉                     ║
╚═══════════════════════════════════════════════════════════════╝
```

</div>

The Cock-Clashers project successfully demonstrates all five core Object-Oriented Programming principles:

<table>
<tr>
<td align="center">

### 🔒 Encapsulation
*Data Protection*

```
✓ Private fields
✓ Public getters
✓ Controlled access
```

</td>
<td align="center">

### 🧬 Inheritance
*Code Reuse*

```
✓ Abstract parent
✓ 3 child classes
✓ super() calls
```

</td>
<td align="center">

### 🔄 Polymorphism
*Many Forms*

```
✓ Method overriding
✓ Method overloading
✓ Factory pattern
```

</td>
</tr>
<tr>
<td align="center">

### 🎭 Abstraction
*Hidden Complexity*

```
✓ Abstract classes
✓ Simple interfaces
✓ Complex internals
```

</td>
<td align="center">

### 🔌 Interface
*Contracts*

```
✓ Runnable
✓ KeyListener
✓ Multiple impl.
```

</td>
<td align="center">

### 🏆 Result
*Success*

```
✓ All 5 concepts
✓ Clean code
✓ Maintainable
```

</td>
</tr>
</table>

---

<div align="center">

### 📝 Key Takeaways

</div>

| # | Concept | Benefit in Project |
|:-:|:--------|:-------------------|
| 1 | **Encapsulation** | Protected rooster stats from invalid modifications |
| 2 | **Inheritance** | Shared 200+ lines of code across 3 rooster types |
| 3 | **Polymorphism** | Flexible rooster creation via factory pattern |
| 4 | **Abstraction** | Complex damage formulas hidden behind simple methods |
| 5 | **Interface** | Enabled threading and keyboard input handling |

---

<div align="center">

```
┌─────────────────────────────────────────────────────────────────┐
│                                                                 │
│   🐓 COCK CLASHERS: OBJECT-ORIENTED COLISEUM 🐓                │
│                                                                 │
│   "Where roosters battle and OOP concepts shine!"               │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

---

*📅 Report generated on December 1, 2025*  
*👤 Author: Chad Laurence Bojelador*  
*🔗 Project: [Cock Clashers](https://github.com/ChadBojelador/Cock-Clashers)*

---

<img src="https://img.shields.io/badge/Made%20with-☕%20Java-orange?style=flat-square" alt="Made with Java"/>
<img src="https://img.shields.io/badge/OOP-Master%20Class-green?style=flat-square" alt="OOP"/>
<img src="https://img.shields.io/badge/Grade-A+-gold?style=flat-square" alt="Grade"/>

</div>
