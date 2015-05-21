package gui;

import javax.swing.*;

public class TapyGui {

    public static int WIDTH = 600;
    public static int HEIGHT = 300;

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Tapy");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel temp = new GamePanel();
        frame.add(temp);
        temp.repaint();
        temp.startMoving();
        // Display the window.
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void show() {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
