package game.items;

public class Item {
    String name;
    int clicksPerSecond=0;
    int cost=0;
    int amountOwned=0;

    public int getClicksPerSecond() {return clicksPerSecond;}
    public String getName() {return name;}
    public int getAmountOwned() {return amountOwned;}
    public int getCost() {return cost;}
    public void setCost(int cost) {this.cost = cost;}
    public void setClicksPerSecond(int clicksPerSecond) {this.clicksPerSecond = clicksPerSecond;}
    public void addAmountOwned(int add){amountOwned+=add;}
}
