package gui;

import model.Channel;
import model.Line;
import model.Note;
import model.Song;
//import org.jdesktop.swingx.JXPanel;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GamePanel extends JPanel implements KeyListener {

	private final Song song;
	private final Channel chan;
	private final int zoneY;
	private final int framePerSec;
	private double posY = TapyGui.HEIGHT / 2;
	private int globalI = 2;
	private boolean isPlaying = false;
	private boolean isRunning = true;
	private int score = 0;
	private JLabel scoreLabel;

	public GamePanel(Song s, int chanNb) {
		song = s;
		chan = s.getChannel(chanNb);
		setPreferredSize(new Dimension(TapyGui.WIDTH, TapyGui.HEIGHT));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		scoreLabel = new JLabel(String.valueOf(score));
		this.add(scoreLabel);
		

		zoneY = TapyGui.HEIGHT - (TapyGui.HEIGHT / 6);

		framePerSec = (int) song.getFramesPerSecond();

		System.out.println("---");
		System.out.println(">>Last " + chan.getLastTick());
		System.out.println(">>>BPM " + song.getBPM());
		System.out.println(">>>TPS " + song.getTicksPerSecond());
		System.out.println(">>>FPS " + song.getFramesPerSecond());
	}


	@Override
	public void paintComponent(Graphics g) {

		super.paintComponents(g);

		g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 9));

		g.setColor(new Color(236, 240, 241));
		g.fillRect(0, 0, TapyGui.WIDTH, TapyGui.HEIGHT);

		// active zone
		g.setColor(new Color(46, 204, 113, 80));
		g.fillRect(0, zoneY, TapyGui.WIDTH, 20);


		g.setColor(Color.BLACK);

		int temp = TapyGui.WIDTH / 5;
		g.drawLine(temp, 0, temp, TapyGui.HEIGHT);
		g.drawLine(temp * 2, 0, temp * 2, TapyGui.HEIGHT);
		g.drawLine(temp * 3, 0, temp * 3, TapyGui.HEIGHT);
		g.drawLine(temp * 4, 0, temp * 4, TapyGui.HEIGHT);

		int mult = 10;

		for (Line l : chan.getLines()) {

			int x = temp * (l.getNumber() + 1);

			for(Note n : l.getNotes()) {
				int y = (int) (posY - n.getTick() / mult);
				int len = (int) (n.getLength()) / mult;

				if(y > zoneY && y < zoneY + 20) {
					g.setColor(new Color(190, 40, 40));
					n.setInSection(true);
				} else {
					g.setColor(new Color(231, 76, 60));
					n.setInSection(false);
				}

				int noteWidth = TapyGui.WIDTH / 20;

				g.fillRoundRect(x - noteWidth/2, y - len, noteWidth, len, 10, 10);

				g.setColor(new Color(41, 128, 185));
				g.drawString(n.getName(), x - 18, y - len / 2 + 5);
				g.drawString(String.valueOf(n.getTick()), x + 18, y - len / 2 + 5);
			}

			// Mesures
			int nMes = 0;
			for (int i = 0; i < l.getLastTick(); i += framePerSec * mult) {
				int y = (int) (posY - i / mult);

				if(nMes % 4 == 0) {
					g.setColor(new Color(80, 80, 80, 120));
					g.drawLine(x - 20, y, x + 20, y);
				}
				nMes++;

				g.setColor(new Color(0, 0, 0, 120));
				g.drawLine(x - 3, y, x + 3, y);
			}
		}

		if(!isPlaying && posY > zoneY) {
			try {
				song.play();
			} catch (MidiUnavailableException | InvalidMidiDataException | IOException e) {
				e.printStackTrace();
			}
			System.out.println("Let the song begin !");
			isPlaying = true;
		}


		//        if(isRunning) {
		//            repaint();
		//        }
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case 'D' : 
			checkLine(0);
			break;
		case 'F' :
			checkLine(1);
			break;

		case 'J' :
			checkLine(2);
			break;

		case 'K':
			checkLine(3);
			break;
		case 89 :
			globalI = - 1;
			break;
		case 88 :
			globalI = 1;
			break;
		case 67 :
			globalI = - 3;
			break;
		case 86 :
			globalI = 3;
			break;
			// haut
		case 38 :
			globalI = - 1;
			break;
			// bas
		case 40 :
			globalI = 1;
			break;
			// q
		case 81 :
			globalI = - 10;
			break;
			// a
		case 65 :
			globalI = 10;
			break;
		default :
			globalI = 0;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public void startMoving(){

		int t = (int)(Math.ceil(1000 / song.getFramesPerSecond() / 2));
		//int t = (int)(song.getFramesPerSecond());

		System.out.println("\n---\n" + t + "\n---\n");

		Timer timer = new Timer(t, e -> {
			posY += globalI;

			if(isRunning) {
				repaint();
			}
		});

		timer.start();
	}
	
	private boolean oneNoteInSection(int line){
		for(Note n : chan.getLine(line).getNotes()){
			if(n.isInSection()){
				return true;
			}
		}
		return false;
	}
	
	private void checkLine(int line){
		if(oneNoteInSection(line)){
			score += 100;
		}else{
			score = score == 0 ? 0 : score - 100;
		}
		scoreLabel.setText(String.valueOf(score));
	}
}
