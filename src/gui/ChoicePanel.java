package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Song;
import model.SongsManager;

public class ChoicePanel extends JPanel {
	JButton chooseFileButton, startButton;
	JFileChooser fileChooser;
	JTextField path;
	JLabel text;
	JPanel researchPanel;
	
	public ChoicePanel() {
		chooseFileButton = new JButton("Parcourir");
		
		chooseFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				fileChooser.showOpenDialog(null);
				
				path.setText(fileChooser.getSelectedFile().getPath());
				
				
			}
		});
		
		startButton = new JButton("Lancer Partie");
		fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileNameExtensionFilter("MIDI File", "mid"));
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!path.getText().isEmpty()){
					String temp = path.getText();
					File test = new File(temp);
					if(!test.exists()){
						System.out.println("Erreur : Fichier introuvable");
					}else{
						
			            
						try {
							SongsManager sm = new SongsManager();
							Song s = sm.load(temp);
							TapyGui.show(s, 0); 
						} catch (InvalidMidiDataException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            
					}
				}else{
					System.out.println("Erreur : séléctionnez un fichier pour commencer");
				}
				
			}
		});
		
		
		text = new JLabel("Séléctionez un fichier midi");
		path = new JTextField();
		path.setSize(new Dimension(100, 100));
		researchPanel = new JPanel();
		researchPanel.setLayout(new GridLayout(1,2));
		
		researchPanel.add(path);
		researchPanel.add(chooseFileButton);
		
		this.setLayout(new GridLayout(3,1));
		this.add(text);
		this.add(researchPanel);
		this.add(startButton);
		
	}
}
