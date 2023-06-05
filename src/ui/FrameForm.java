package ui;

import game.ClickerGame;
import game.items.*;
import game.upgrades.AutoClickerUpgrade;
import game.upgrades.MercUpgrade;
import game.upgrades.PlayerClicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;

public class FrameForm extends JFrame {
    private JPanel mainPanel;
    private JButton mainClicker;
    private JButton item1;
    private JButton item5;
    private JButton item4;
    private JButton item3;
    private JButton item2;
    private JPanel shop;
    private JComboBox shopSelector;
    private JLabel clicksLabel;
    private JButton button1;
    String[] selectorTabs = {"Items", "Upgrades"};
    boolean upgradesShown = false;

    AutoClicker ac = new AutoClicker();
    Mercenary merc = new Mercenary();
    ArtificialIntelligence ai = new ArtificialIntelligence();
    ClickFarm cf = new ClickFarm();
    Ken ken = new Ken();

    AutoClickerUpgrade acu = new AutoClickerUpgrade();
    MercUpgrade mc = new MercUpgrade();


    public FrameForm(){
        setContentPane(mainPanel);
        setTitle("Clicker game");
        setSize(1000,750);
        setDefaultCloseOperation(3);
        setVisible(true);
        setResizable(false);
        shopSelector.setModel(new DefaultComboBoxModel(selectorTabs));
        clicksLabel.setText("Clicks: " + ClickerGame.getClicks());
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //Set click label to constantly update
                clicksLabel.setText("Clicks: " + ClickerGame.getClicks());
                clicksLabel.setToolTipText("Total clicks per second: " + ClickerGame.getTotalClicksPerSecond());

                //if item show item stuff and unlocked item stuff
                if(shopSelector.getSelectedItem() == "Items") {
                    upgradesShown = false;
                    mainClicker.setText("+" + PlayerClicker.getCurrentClicks() + " Clicks");
                    if(ac.isUnlocked()){item1.setText(ac.getName() + " +" + ac.getClicksPerSecond() + " CPS (" + ac.getCost()+" Clicks)");} if(merc.isUnlocked()){item2.setText(merc.getName() + " +" + merc.getClicksPerSecond() + " CPS (" + merc.getCost()+" Clicks)");} if(ai.isUnlocked()){item3.setText(ai.getName() + " +" + ai.getClicksPerSecond() + " CPS (" + ai.getCost()+" Clicks)");} if(cf.isUnlocked()){item4.setText(cf.getName() + " +" + cf.getClicksPerSecond() + " CPS (" + cf.getCost()+" Clicks)");} if(ken.isUnlocked()){item5.setText(ken.getName() + " +" + ken.getClicksPerSecond() + " CPS (" + ken.getCost()+" Clicks)");}

                    if(ClickerGame.getClicks() >= 100){
                        item1.setEnabled(true);
                        ac.setUnlocked(true);
                        item1.setText(ac.getName() + " +" + ac.getClicksPerSecond() + " CPS (" + ac.getCost()+" Clicks)");
                    }
                    if(ClickerGame.getClicks() >= 750) {
                        item2.setEnabled(true);
                        merc.setUnlocked(true);
                        item2.setText(merc.getName() + " +" + merc.getClicksPerSecond() + " CPS (" + merc.getCost()+" Clicks)");
                    }
                    if(ClickerGame.getClicks() >= 3000) {
                        item3.setEnabled(true);
                        ai.setUnlocked(true);
                        item3.setText(ai.getName() + " +" + ai.getClicksPerSecond() + " CPS (" + ai.getCost()+" Clicks)");
                    }
                    if(ClickerGame.getClicks() >= 10000){
                        item4.setEnabled(true);
                        cf.setUnlocked(true);
                        item4.setText(cf.getName() + " +" + cf.getClicksPerSecond() + " CPS (" + cf.getCost()+" Clicks)");
                    }
                    if(ClickerGame.getClicks() >= 150000){
                        item5.setEnabled(true);
                        ken.setUnlocked(true);
                        item5.setText(ken.getName() + " +" + ken.getClicksPerSecond() + " CPS (" + ken.getCost()+" Clicks)");
                    }
                } else {
                    upgradesShown = true;
                    mainClicker.setText("Upgrade player clicker? (New CPS = "+ PlayerClicker.getCurrentClicks() * 2 + ", Cost =" + PlayerClicker.getCost() +")");
                    if(ac.isUnlocked()) {
                        item1.setText("Upgrade " + ac.getName() + "? (New CPS = "+ acu.getNewCPS() + ", Cost ="  + acu.getCost() +")");
                    }
                    if(merc.isUnlocked()){
                        item2.setText("Upgrade " + merc.getName() + "? (New CPS = "+ mc.getNewCPS() + ", Cost ="  + mc.getCost() +")");
                    }
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0L, 1L); //call the run() method at 1 second intervals

        //Main btn
        mainClicker.setText("+" + PlayerClicker.getCurrentClicks() + " Clicks");
        mainClicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!upgradesShown) {
                    ClickerGame.addToClicks(PlayerClicker.getCurrentClicks());
                    clicksLabel.setText("Clicks: " + ClickerGame.getClicks());
                } else{
                    if(ClickerGame.getClicks() >= PlayerClicker.getCost()){
                        ClickerGame.subtractClicks(PlayerClicker.getCost());
                        PlayerClicker.setCurrentClicks(PlayerClicker.getCurrentClicks() * 2);
                        PlayerClicker.setCost(PlayerClicker.getCost() *4);
                        mainClicker.setText("Upgrade player clicker? (New CPS = "+ PlayerClicker.getCurrentClicks() * 2 + ", Cost = " + PlayerClicker.getCost() +")");
                    } else{
                        JOptionPane.showMessageDialog(null, "Not enough clicks", "Cannot buy", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        //item stuff
        item1.setToolTipText("Buy an auto clicker to click the button for you!");
        item1.setText("Unlock at 100 clicks");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!upgradesShown) {
                    if (ClickerGame.getClicks() >= ac.getCost()) {
                        //Remove clicks and raise price
                        ClickerGame.subtractClicks(ac.getCost());
                        double multiplier = ac.getCost() * 0.4;
                        ac.setCost(ac.getCost() + (int) multiplier);

                        //update amount owned in tooltips
                        ac.addAmountOwned(1);
                        item1.setToolTipText("Buy an auto clicker to click the button for you! Owned: " + ac.getAmountOwned());

                        //Set the text of the button and start clicking
                        item1.setText(ac.getName() + " +" + ac.getClicksPerSecond() + " CPS (" + ac.getCost() + " Clicks)");
                        ClickerGame.addToCPS(ac);
                    } else {
                        JOptionPane.showMessageDialog(null, "Not enough clicks", "Cannot buy", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    if(ClickerGame.getClicks() >= acu.getCost()){
                        ClickerGame.subtractClicks(acu.getCost());
                        acu.setCost(acu.getCost() * 3);
                        ac.setClicksPerSecond(ac.getClicksPerSecond() * 2);
                        acu.setNewCPS(ac.getClicksPerSecond() * 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Not enough clicks", "Cannot buy", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        item2.setToolTipText("These top of the line mercs are really good at drag clicking...");
        item2.setText("Unlock at 750 clicks");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!upgradesShown) {
                    if (ClickerGame.getClicks() >= merc.getCost()) {
                        //Remove clicks and raise price
                        ClickerGame.subtractClicks(merc.getCost());
                        double multiplier = merc.getCost() * 0.7;
                        merc.setCost(merc.getCost() + (int) multiplier);

                        //update amount owned in tooltips
                        merc.addAmountOwned(1);
                        item2.setToolTipText("These top of the line mercs are really good at drag clicking... Owned: " + merc.getAmountOwned());

                        //Set the text of the button and start clicking
                        item2.setText(merc.getName() + " +" + merc.getClicksPerSecond() + " CPS (" + merc.getCost() + " Clicks)");
                        ClickerGame.addToCPS(merc);
                    } else {
                        JOptionPane.showMessageDialog(null, "Not enough clicks", "Cannot buy", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    if(ClickerGame.getClicks() >= mc.getCost()){
                        ClickerGame.subtractClicks(mc.getCost());
                        mc.setCost(mc.getCost() * 3);
                        merc.setClicksPerSecond(merc.getClicksPerSecond() * 2);
                        mc.setNewCPS(merc.getClicksPerSecond() * 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Not enough clicks", "Cannot buy", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        item3.setToolTipText("Create an AI to generate clicks at a expedient rate!");
        item3.setText("Unlock at 3000 clicks");
        item3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ClickerGame.getClicks() >= ai.getCost()) {
                    //Remove clicks and raise price
                    ClickerGame.subtractClicks(ai.getCost());
                    double multiplier = ai.getCost() * 0.7;
                    ai.setCost(ai.getCost() + (int)multiplier);

                    //update amount owned in tooltips
                    ai.addAmountOwned(1);
                    item3.setToolTipText("Create an AI to generate clicks at a expedient rate! Owned: " + merc.getAmountOwned());

                    //Set the text of the button and start clicking
                    item3.setText(ai.getName() + " +" + ai.getClicksPerSecond() + " CPS (" + ai.getCost() + " Clicks)");
                    ClickerGame.addToCPS(ai);
                } else{
                    JOptionPane.showMessageDialog(null, "Not enough clicks", "Cannot buy", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        item4.setToolTipText("Convert a Bitcoin mining facility to a click farm to generate clicks!");
        item4.setText("Unlock at 10000 clicks");
        item4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ClickerGame.getClicks() >= cf.getCost()) {
                    //Remove clicks and raise price
                    ClickerGame.subtractClicks(cf.getCost());
                    double multiplier = cf.getCost() * 0.7;
                    cf.setCost(cf.getCost() + (int)multiplier);

                    //update amount owned in tooltips
                    cf.addAmountOwned(1);
                    item4.setToolTipText("Convert a Bitcoin mining facility to a click farm to generate clicks! Owned: " + cf.getAmountOwned());

                    //Set the text of the button and start clicking
                    item4.setText(cf.getName() + " +" + cf.getClicksPerSecond() + " CPS (" + cf.getCost() + " Clicks)");
                    ClickerGame.addToCPS(cf);
                } else{
                    JOptionPane.showMessageDialog(null, "Not enough clicks", "Cannot buy", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        item5.setToolTipText("Buy a Ken to use his godly powers to conjure clicks from thin air!");
        item5.setText("Unlock at 150000 clicks");
        item5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ClickerGame.getClicks() >= ken.getCost()) {
                    //Remove clicks and raise price
                    ClickerGame.subtractClicks(ken.getCost());
                    double multiplier = ken.getCost() * 0.7;
                    ken.setCost(ken.getCost() + (int)multiplier);

                    //update amount owned in tooltips
                    ken.addAmountOwned(1);
                    item5.setToolTipText("Buy a Ken to use his godly powers to conjure clicks from thin air! Owned: " + ken.getAmountOwned());

                    //Set the text of the button and start clicking
                    item4.setText(ken.getName() + " +" + ken.getClicksPerSecond() + " CPS (" + ken.getCost() + " Clicks)");
                    ClickerGame.addToCPS(ken);
                } else{
                    JOptionPane.showMessageDialog(null, "Not enough clicks", "Cannot buy", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClickerGame.addToClicks(10000);
            }
        });
    }
}
