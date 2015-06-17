/**
 * MCR - Tapy
 * @author: Fabien Salathe, Marc Pellet, Michaël Berthouzoz, David Villa
 * @brief: Game similar to Guitar Hero basic using the Prototype design pattern.
 * @date: 2015-06-17
 */

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
