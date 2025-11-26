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

    public void displayStats() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║     BATTLE STATISTICS            ║");
        System.out.println("╠══════════════════════════════════╣");
        System. out.println("║ Turns Played: " + String.format("%-18d", turnsPlayed) + "║");
        System. out.println("║ Skills Used: " + String.format("%-19d", skillsUsed) + "║");
        System.out.println("║ Damage Dealt: " + String.format("%-18d", totalDamageDealt) + "║");
        System.out.println("║ Damage Taken: " + String.format("%-18d", totalDamageTaken) + "║");
        System.out.println("║ Critical Hits: " + String.format("%-17d", criticalHits) + "║");
        System.out.println("╚══════════════════════════════════╝");
    }
}