package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class ScorePanel extends JPanel{
	JLabel textLabel;
	JLabel scoreLabel;
	JButton okButton;
	
	public ScorePanel(String pathCurrentSong, int score, JFrame frame){
		textLabel = new JLabel("Vous avez obtenu le score de", SwingConstants.CENTER);
		scoreLabel = new JLabel(String.valueOf(score), SwingConstants.CENTER);
		
		okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
			}
		});
		
		this.setLayout(new GridLayout(3,1));
		this.add(textLabel);
		this.add(scoreLabel);
		this.add(okButton);
		
	}
}
