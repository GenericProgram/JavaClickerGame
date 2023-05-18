package game;

import game.upgrades.PlayerClicker;

public class ClickerGame {
    static int clicks = PlayerClicker.getCurrentClicks();
    public ClickerGame(){

    }

    public static int getClicks(){
        return clicks;
    }

    public static void addToClicks(int add){
        clicks += add;
    }
}
