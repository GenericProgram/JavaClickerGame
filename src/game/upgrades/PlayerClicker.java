package game.upgrades;

public class PlayerClicker {
    static int currentClicks = 1;

    public static int getCurrentClicks() {
        return currentClicks;
    }

    public static void setCurrentClicks(int currentClicks1) {
        currentClicks = currentClicks1;
    }
}
