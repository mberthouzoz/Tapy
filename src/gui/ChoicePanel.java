package gui;

import model.Song;
import model.SongsManager;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Choice Panel
 * Panel to chose a midi file and start the game
 */
public class ChoicePanel extends JPanel {
    JButton chooseFileButton, startButton;
    JFileChooser fileChooser;
    JTextField path;
    JLabel text, title;
    JPanel researchPanel;

    public ChoicePanel() {
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("MIDI File", "mid"));

        chooseFileButton = new JButton("Parcourir");
        chooseFileButton.addActionListener(arg0 -> {
            // Open choice panel in the current java folder
            File workingDirectory = new File(System.getProperty("user.dir") + "/data");
            fileChooser.setCurrentDirectory(workingDirectory);
            int dialog = fileChooser.showOpenDialog(null);

            // If a we click on "open" and the file exists
            if (dialog == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile().exists()) {
                path.setText(fileChooser.getSelectedFile().getPath());
            }
        });

        // When we start a game
        startButton = new JButton("Lancer Partie");
        startButton.addActionListener(arg0 -> {
            if (!path.getText().isEmpty()) {
                String temp = path.getText();
                File test = new File(temp);
                if (!test.exists()) {
                    System.out.println("Erreur : Fichier introuvable");
                } else {
                    try {
                        SongsManager sm = new SongsManager();
                        Song s = sm.load(temp);
                        TapyGui.show(s, 0);
                    } catch (InvalidMidiDataException | IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.println("Erreur : sélectionner un fichier pour commencer");
            }
        });

        title = new JLabel("TAPY");
        title.setFont(new Font("Sans", Font.BOLD, 48));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(230, 230, 230));

        text = new JLabel("Sélectionner un fichier midi");
        path = new JTextField();
        path.setSize(new Dimension(100, 100));

        researchPanel = new JPanel();
        researchPanel.setLayout(new GridLayout(1, 2));
        researchPanel.add(path);
        researchPanel.add(chooseFileButton);

        setLayout(new GridLayout(4, 1));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setBackground(new Color(100, 100, 100));
        add(title);
        add(text);
        add(researchPanel);
        add(startButton);
    }
}
