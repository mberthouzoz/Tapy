package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Score panel, at the end of the song
 */
public class ScorePanel extends JPanel {

    public ScorePanel(int score, JFrame frame) {
        JLabel textLabel = new JLabel("Vous avez obtenu le score de", SwingConstants.CENTER);
        textLabel.setFont(new Font("Sans", Font.PLAIN, 16));
        JLabel scoreLabel = new JLabel(String.valueOf(score), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Sans", Font.BOLD, 28));

        JButton okButton = new JButton("OK");
        okButton.setFont(new Font("Sans", Font.PLAIN, 18));
        okButton.setSize(250, 60);
        okButton.addActionListener(e -> frame.dispose());

        setLayout(new GridLayout(3, 1));
        add(textLabel);
        add(scoreLabel);
        add(okButton);
    }
}
