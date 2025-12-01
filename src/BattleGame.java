import java.util. Scanner;
import java.util.List;

public class BattleGame {
    private Rooster player;
    private Rooster enemy;
    private Scanner scanner;
    private BattleStats playerStats;
    private BattleStats enemyStats;

    public BattleGame() {
        scanner = new Scanner(System.in);
        playerStats = new BattleStats();
        enemyStats = new BattleStats();
    }

    public void start() {
        BattleDisplay.displayBattleHeader();
        selectRoosters();
        battle();
        endGame();
    }

    private void selectRoosters() {
        BattleDisplay.displayRoosterChoice();
        System.out.print("\nChoose your rooster (1-3): ");
        int playerChoice = InputValidator.getValidChoice(scanner, 1, 3);

        player = RoosterFactory.createRooster(playerChoice, "Red Rooster");
        enemy = RoosterFactory.createRandomRooster("Blue Rooster");

        System.out.println("\n=== BATTLE START ===");
        System.out.println(player.getName() + " (" + player.getType() + ") vs " +
                enemy.getName() + " (" + enemy.getType() + ")");
        System.out.println();
    }

    private void battle() {
        while (!player.isFainted() && !enemy.isFainted()) {
            playerTurn();

            if (enemy.isFainted()) {
                System.out.println("\n🏆 " + enemy.getName() + " has fainted!");
                System.out.println("🎉 " + player.getName() + " wins!");
                break;
            }

            enemyTurn();

            if (player.isFainted()) {
                System.out.println("\n💀 " + player.getName() + " has fainted!");
                System.out.println("😢 " + enemy.getName() + " wins!");
                break;
            }

            playerStats.recordTurn();
            enemyStats. recordTurn();
        }
    }

    private void playerTurn() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out. println("║   " + player.getName() + "'s Turn" + " ". repeat(Math.max(0, 23 - player.getName().length())) + "║");
        System.out.println("╚════════════════════════════════╝");

        BattleDisplay.displayHealthBar(player, player.getMaxHp());
        BattleDisplay.displayHealthBar(enemy, enemy.getMaxHp());

        System.out.println("\nChoose your skill:");
        List<Skill> playerSkills = player.getSkills();
        for (int i = 0; i < playerSkills.size(); i++) {
            Skill skill = playerSkills.get(i);
            System.out.println((i + 1) + ". " + skill.getName() +
                    " (DMG: " + skill.getDamage() +
                    ", Type: " + skill.getType() + ")");
        }

        System.out.print("Your choice: ");
        int choice = InputValidator.getValidChoice(scanner, 1, playerSkills.size()) - 1;

        int damage = player.attack(enemy, playerSkills.get(choice));
        playerStats.recordDamageDealt(damage);
        enemyStats.recordDamageTaken(damage);
        playerStats.recordSkillUsed();
    }

    private void enemyTurn() {
        System.out.println("\n╔════════════════════════════════╗");
        System.out.println("║   " + enemy.getName() + "'s Turn" + " ".repeat(Math.max(0, 24 - enemy.getName().length())) + "║");
        System.out.println("╚════════════════════════════════╝");

        List<Skill> enemySkills = enemy.getSkills();
        int randomSkill = (int) (Math. random() * enemySkills. size());

        int damage = enemy.attack(player, enemySkills.get(randomSkill));
        enemyStats.recordDamageDealt(damage);
        playerStats.recordDamageTaken(damage);
        enemyStats.recordSkillUsed();
    }

    private void endGame() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║         BATTLE END               ║");
        System.out.println("╚══════════════════════════════════╝");

        System.out.println("\n" + player.getName() + "'s Performance:");
        playerStats.displayStats();

        System.out.println("\n" + enemy.getName() + "'s Performance:");
        enemyStats.displayStats();

        scanner.close();
    }
}
