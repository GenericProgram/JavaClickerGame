package game.upgrades;

public class Upgrade {
    int level;
    int cost;
    int newCPS;

    public int getCost() {return cost;}
    public int getLevel() {return level;}
    public int getNewCPS() {return newCPS;}
    public void setCost(int cost) {this.cost = cost;}
    public void setNewCPS(int newCPS) {this.newCPS = newCPS;}
}
