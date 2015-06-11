package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!path.getText().isEmpty()){
					String temp = path.getText();
					File test = new File(temp);
					if(!test.exists()){
						System.out.println("Erreur : Fichier introuvable");
					}else{
						System.out.println(temp);
					}
				}else{
					System.out.println("Erreur : séléctionnez un fichier pour commencer");
				}
				
			}
		});
		
		fileChooser = new JFileChooser();
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
