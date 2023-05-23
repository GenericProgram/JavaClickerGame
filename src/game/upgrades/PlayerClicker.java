package game.upgrades;

public class PlayerClicker {
    static int currentClicks = 1;
    static int cost = 100;

    public static int getCurrentClicks() {
        return currentClicks;
    }
    public static void setCurrentClicks(int currentClicks1) {
        currentClicks = currentClicks1;
    }
    public static int getCost() {return cost;}
    public static void setCost(int cost) {PlayerClicker.cost = cost;}
}
