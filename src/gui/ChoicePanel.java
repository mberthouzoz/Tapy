package gui;

import model.Song;
import model.SongsManager;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ChoicePanel extends JPanel {
    JButton chooseFileButton, startButton;
    JFileChooser fileChooser;
    JTextField path;
    JLabel text, title;
    JPanel researchPanel;

    public ChoicePanel() {
        chooseFileButton = new JButton("Parcourir");

        chooseFileButton.addActionListener(arg0 -> {

            File workingDirectory = new File(System.getProperty("user.dir") + "/data");
            fileChooser.setCurrentDirectory(workingDirectory);
            int dialog = fileChooser.showOpenDialog(null);

            if (dialog == JFileChooser.APPROVE_OPTION && fileChooser.getSelectedFile().exists()) {
                path.setText(fileChooser.getSelectedFile().getPath());
            }
        });

        startButton = new JButton("Lancer Partie");
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("MIDI File", "mid"));
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

        text = new JLabel("Sélectionner un fichier midi");
        path = new JTextField();
        path.setSize(new Dimension(100, 100));
        researchPanel = new JPanel();
        researchPanel.setLayout(new GridLayout(1, 2));

        researchPanel.add(path);
        researchPanel.add(chooseFileButton);

        this.setLayout(new GridLayout(4, 1));
        this.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        this.add(title);
        this.add(text);
        this.add(researchPanel);
        this.add(startButton);
    }
}
