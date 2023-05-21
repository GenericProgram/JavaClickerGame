package game;

import game.items.Item;

import java.util.Timer;
import java.util.TimerTask;

public class ClickerGame {
    static int clicks = 0;
    static int totalClicksPerSecond = 0;
    static boolean firstTime = true;

    public ClickerGame(){

    }

    public static int getClicks(){
        return clicks;
    }

    public static void addToClicks(int add){
        clicks += add;
    }

    public static void subtractClicks(int amount){
        clicks -= amount;
    }

    public static int getTotalClicksPerSecond() {return totalClicksPerSecond;}

    public static void addToCPS(Item item){
        if(firstTime){
            firstTime = false;
            startCount();
        }
        totalClicksPerSecond += item.getClicksPerSecond();
    }

    private static void startCount(){
        try{
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    clicks += totalClicksPerSecond;
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 0L, 1000L); //call the run() method at 1 second intervals
        }catch (Exception e){
            e.printStackTrace();
            System.exit(0);
        }
    }
}
