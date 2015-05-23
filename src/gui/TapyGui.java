package gui;

import model.Song;

import javax.swing.*;

public class TapyGui {

    public static int WIDTH = 600;
    public static int HEIGHT = 600;

    private static void createAndShowGUI(Song s, int chanNb) {
        JFrame frame = new JFrame("Tapy");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel temp = new GamePanel(s, chanNb);
        frame.add(temp);
        temp.repaint();
        temp.startMoving();

        // Display the window
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void show(Song s, int chan) {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI(s, chan));
    }
}
