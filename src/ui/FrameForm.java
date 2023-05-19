package ui;

import game.ClickerGame;
import game.items.AutoClicker;
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


    public FrameForm(){
        setContentPane(mainPanel);
        setTitle("Clicker game");
        setSize(1000,750);
        setDefaultCloseOperation(3);
        setVisible(true);
        setResizable(false);
        shopSelector.setModel(new DefaultComboBoxModel(selectorTabs));
        clicksLabel.setText("Clicks: " + ClickerGame.getClicks());


        mainClicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClickerGame.addToClicks(PlayerClicker.getCurrentClicks());
                clicksLabel.setText("Clicks: " + ClickerGame.getClicks());
                if(ClickerGame.getClicks() == 100){
                    item1.setEnabled(true);
                    item1.setText(ac.getName() + " +" + ac.getClicksPerSecond() + " CPS (" + ac.getCost()+" Clicks)");
                }
            }
        });

        //item stuff
        item1.setToolTipText("Buy an auto clicker to click the button for you!");
        item1.setText("Unlock at 100 clicks");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(ClickerGame.getClicks() >= 50) {
                    ClickerGame.subtractClicks(ac.getCost());
                    ac.setCost(ac.getCost());
                    item1.setText(ac.getName() + " +" + ac.getClicksPerSecond() + " CPS (" + ac.getCost() + " Clicks)");
                    ClickerGame.addToCPS(ac);
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            clicksLabel.setText("Clicks: " + ClickerGame.getClicks());
                        }

                    };

                    Timer timer = new Timer();
                    timer.schedule(task, 0L, 500L); //call the run() method at 1 second intervals
                } else{
                    JOptionPane.showMessageDialog(null, "Not enough clicks", "Cannot buy", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        item2.setEnabled(true);
        item2.setText("Hire Mercenary");
        item2.setToolTipText("These top of the line mercs are really good at drag clicking...");

    }
}
