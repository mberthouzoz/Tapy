import gui.ChoicePanel;

import javax.swing.*;
import java.awt.*;


public class test {

    public static void main() {
        JFrame frame = new JFrame();
        frame.add(new ChoicePanel());
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
