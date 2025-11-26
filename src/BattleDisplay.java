public class BattleDisplay {
    public static void displayHealthBar(Rooster rooster, int maxHp) {
        int barLength = 20;
        int filled = (int) ((rooster.getHp() / (double) maxHp) * barLength);

        System.out.print(rooster.getName() + " [");
        for (int i = 0; i < barLength; i++) {
            System.out.print(i < filled ? "█" : "░");
        }
        System.out.println("] " + rooster.getHp() + "/" + maxHp);
    }

    public static void displayBattleHeader() {
        System.out. println("\n╔══════════════════════════════════╗");
        System.out. println("║      ROOSTER BATTLE ARENA        ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    public static void displayRoosterChoice() {
        System.out.println("\n=== PICK YOUR ROOSTER ===");
        System.out.println("1. Manok na Puti (Balanced) - Type: Fire");
        System.out.println("   HP: 120 | ATK: 30 | DEF: 10");
        System.out.println("2.  Manok na Itim (High Attack) - Type: Dark");
        System.out.println("   HP: 100 | ATK: 40 | DEF: 8");
        System.out. println("3. Manok na Bato (High Defense) - Type: Rock");
        System. out.println("   HP: 150 | ATK: 20 | DEF: 20");
    }
}