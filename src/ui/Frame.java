package ui;

import javax.swing.*;
import java.awt.*;

import static ui.ButtonPanel.buttonPanel;


public class Frame extends JFrame {
    public static final Frame FRAME = new Frame("Clicker Game");

    private Frame(String s){
        super(s);
        setPreferredSize(new Dimension(1000,750));
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(3);

        add(buttonPanel, BorderLayout.WEST);

        pack();
    }
}
