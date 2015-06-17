package gui;

import model.Song;

import javax.swing.*;
import java.awt.*;

/**
 * Game window
 */
public class TapyGui {

    public static int WIDTH = 600;
    public static int HEIGHT = 600;

    private static void createAndShowGUI(Song s, int chanNb) {
        JFrame frame = new JFrame("Tapy");
        //frame.setSize(WIDTH, HEIGHT);
        Toolkit tk = Toolkit.getDefaultToolkit();
        WIDTH = ((int) tk.getScreenSize().getWidth());
        HEIGHT = ((int) tk.getScreenSize().getHeight()) - 60;
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GamePanel gamePane = new GamePanel(s, chanNb, frame);
        frame.add(gamePane);
        gamePane.repaint();
        gamePane.startMovingOLD();

        frame.setIgnoreRepaint(true); // Make active rendering possible

        // Display the window
        frame.setResizable(false);
        frame.pack();
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public static void show(Song s, int chan) {
        javax.swing.SwingUtilities.invokeLater(() -> createAndShowGUI(s, chan));
    }
}
