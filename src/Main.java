
// "Velocity" describe the volume (gain) of a MIDI note (higher velocity = louder).

import gui.ChoicePanel;
import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.add(new ChoicePanel());
        frame.setLayout(new FlowLayout());
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
