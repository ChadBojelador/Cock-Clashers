public class TypeEffectiveness {
    public static double getMultiplier(String attackType, String defenderType) {
        if (attackType. equals("fire") && defenderType.equals("normal")) return 1.5;
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
