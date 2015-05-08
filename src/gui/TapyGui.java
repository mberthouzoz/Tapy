package gui;

import javax.swing.*;

public class TapyGui {

    public static int WIDTH = 600;
    public static int HEIGHT = 300;

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Tapy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(new GamePanel());

        // Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void show() {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI());
    }
}
