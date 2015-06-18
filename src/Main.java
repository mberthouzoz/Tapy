/**
 * MCR - Tapy
 * @author: Fabien Salathe, Marc Pellet, Michael Berthouzoz, David Villa
 * @brief: Game similar to Guitar Hero basic using the Prototype design pattern.
 * @date: 2015-06-17
 */

import gui.ChoicePanel;
import javax.swing.*;
import java.awt.*;

public class Main {

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {

        // Use default OS UI
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Create the choice panel window
        JFrame frame = new JFrame();
        frame.add(new ChoicePanel());
        frame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
