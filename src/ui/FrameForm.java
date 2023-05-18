package ui;

import game.ClickerGame;
import game.upgrades.PlayerClicker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrameForm extends JFrame {
    private JTextField clicksTextField;
    private JPanel mainPanel;
    private JButton mainClicker;
    private JButton upgrade1;
    private JButton upgrade5;
    private JButton upgrade4;
    private JButton upgrade3;
    private JButton upgrade2;
    private JPanel shop;
    private JComboBox shopSelector;


    public FrameForm(){
        setContentPane(mainPanel);
        setTitle("Clicker game");
        setSize(1000,750);
        setDefaultCloseOperation(3);
        setVisible(true);
        setResizable(false);

        mainClicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clicksTextField.setText("Clicks: " + ClickerGame.getClicks());
                ClickerGame.addToClicks(PlayerClicker.getCurrentClicks());
            }
        });
    }

}
