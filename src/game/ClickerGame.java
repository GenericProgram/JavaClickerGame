package game;

import game.items.Item;
import game.upgrades.PlayerClicker;

public class ClickerGame {
    static int clicks = PlayerClicker.getCurrentClicks();
    static int totalClicksPerSecond = 0;

    public ClickerGame(){

    }

    public static int getClicks(){
        return clicks;
    }

    public static void addToClicks(int add){
        clicks += add;
    }

    public static int getTotalClicksPerSecond() {return totalClicksPerSecond;}

    public static void addToCPS(Item item){
        try{
            //while(true) {
                totalClicksPerSecond += item.getClicksPerSecond();
                clicks += totalClicksPerSecond;
            //}
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}
