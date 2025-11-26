public class RoosterFactory {

    public static Rooster createRooster(int choice, String customName) {
        switch (choice) {
            case 1:
                return new Rooster.ManokNaPuti(customName);
            case 2:
                return new Rooster.ManokNaItim(customName);
            case 3:
                return new Rooster.ManokNaBato(customName);
            default:
                System.out.println("Invalid choice.  Defaulting to Manok na Puti.");
                return new Rooster.ManokNaPuti(customName);
        }
    }

    public static Rooster createRandomRooster(String name) {
        int choice = 1 + (int) (Math.random() * 3);
        return createRooster(choice, name);
    }
}