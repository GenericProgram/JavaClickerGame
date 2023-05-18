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

        //set items
        item1.setText(ac.getName());


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
