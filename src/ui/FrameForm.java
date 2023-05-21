package ui;

import game.ClickerGame;
import game.items.AutoClicker;
import game.items.Mercenary;
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
    String[] selectorTabs = {"Items", "Upgrades"};

    AutoClicker ac = new AutoClicker();
    Mercenary merc = new Mercenary();

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
                clicksLabel.setText("Clicks: " + ClickerGame.getClicks());
                clicksLabel.setToolTipText("Total clicks per second: " + ClickerGame.getTotalClicksPerSecond());
                if(ClickerGame.getClicks() == 100){
                    item1.setEnabled(true);
                    item1.setText(ac.getName() + " +" + ac.getClicksPerSecond() + " CPS (" + ac.getCost()+" Clicks)");
                }
                if(ClickerGame.getClicks() >= 750) {
                    item2.setEnabled(true);
                    item2.setText(merc.getName() + " +" + merc.getClicksPerSecond() + " CPS (" + merc.getCost()+" Clicks)");
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, 0L, 1L); //call the run() method at 1 second intervals

        //Main btn
        mainClicker.setText("+1 Clicks");
        mainClicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClickerGame.addToClicks(PlayerClicker.getCurrentClicks());
                clicksLabel.setText("Clicks: " + ClickerGame.getClicks());
            }
        });

        //item stuff
        item1.setToolTipText("Buy an auto clicker to click the button for you!");
        item1.setText("Unlock at 100 clicks");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ClickerGame.getClicks() >= ac.getCost()) {
                    //Remove clicks and raise price
                    ClickerGame.subtractClicks(ac.getCost());
                    double multiplier = ac.getCost() * 0.4;
                    ac.setCost(ac.getCost() + (int)multiplier);

                    //update amount owned in tooltips
                    ac.addAmountOwned(1);
                    item1.setToolTipText("Buy an auto clicker to click the button for you! Owned: " + ac.getAmountOwned());

                    //Set the text of the button and start clicking
                    item1.setText(ac.getName() + " +" + ac.getClicksPerSecond() + " CPS (" + ac.getCost() + " Clicks)");
                    ClickerGame.addToCPS(ac);
                } else{
                    JOptionPane.showMessageDialog(null, "Not enough clicks", "Cannot buy", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        item2.setToolTipText("These top of the line mercs are really good at drag clicking...");
        item2.setText("Unlock at 750 clicks");
        item2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ClickerGame.getClicks() >= merc.getCost()) {
                    //Remove clicks and raise price
                    ClickerGame.subtractClicks(merc.getCost());
                    double multiplier = merc.getCost() * 0.7;
                    merc.setCost(merc.getCost() + (int)multiplier);

                    //update amount owned in tooltips
                    merc.addAmountOwned(1);
                    item2.setToolTipText("These top of the line mercs are really good at drag clicking... Owned: " + merc.getAmountOwned());

                    //Set the text of the button and start clicking
                    item2.setText(merc.getName() + " +" + merc.getClicksPerSecond() + " CPS (" + merc.getCost() + " Clicks)");
                    ClickerGame.addToCPS(merc);
                } else{
                    JOptionPane.showMessageDialog(null, "Not enough clicks", "Cannot buy", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }
}
