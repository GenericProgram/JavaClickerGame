package ui;


import javax.swing.*;
import java.awt.*;

public class ButtonPanel extends JPanel {
    public static final ButtonPanel buttonPanel = new ButtonPanel();

    private JButton button = new JButton("+1 Clicks");

    private ButtonPanel(){
        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        button.setPreferredSize(new Dimension(150, 300));
        add(button);
    }
}
