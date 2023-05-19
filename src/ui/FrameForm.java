package ui;

import game.ClickerGame;
import game.items.AutoClicker;
import game.upgrades.PlayerClicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameForm extends JFrame {
    private JTextField clicksTextField;
    private JPanel mainPanel;
    private JButton mainClicker;
    private JButton item1;
    private JButton item5;
    private JButton item4;
    private JButton item3;
    private JButton item2;
    private JPanel shop;
    private JComboBox shopSelector;
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

        mainClicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicksTextField.setText("Clicks: " + ClickerGame.getClicks());
                ClickerGame.addToClicks(PlayerClicker.getCurrentClicks());
            }
        });

        //item stuff
        item1.setEnabled(true);
        item1.setText("Auto Clicker +1 CPS (50 Clicks)");
        item1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                item1.setText(ac.getName() + " +" + ac.getClicksPerSecond() + " CPS (" + ac.getCost()+" Clicks)");
                ClickerGame.addToCPS(ac);
                clicksTextField.setText("Clicks: " + ClickerGame.getClicks());
            }
        });

    }
}
