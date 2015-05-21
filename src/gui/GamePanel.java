package gui;

import model.Line;
import model.Note;
import model.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

public class GamePanel extends JPanel implements KeyListener {

    private final Song song;
    private long posY = 0;

    public GamePanel(Song s) {
        song = s;
        setPreferredSize(new Dimension(TapyGui.WIDTH, TapyGui.HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponents(g);
    	g.setColor(Color.WHITE);
    	g.fillRect(0, 0, TapyGui.WIDTH, TapyGui.HEIGHT);
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRect(0, TapyGui.HEIGHT-(TapyGui.HEIGHT/5), TapyGui.WIDTH, TapyGui.HEIGHT/10);
    	g.setColor(Color.BLACK);


        LinkedList<Note> notes = song.getNotes();
        int temp = TapyGui.WIDTH/5;
        g.drawLine(temp, 0, temp, TapyGui.HEIGHT);
        g.drawLine(temp * 2, 0, temp * 2, TapyGui.HEIGHT);
        g.drawLine(temp * 3, 0, temp * 3, TapyGui.HEIGHT);
        g.drawLine(temp * 4, 0, temp * 4, TapyGui.HEIGHT);

        for (Line l : song.getLines()) {

            for(Note n : l.getNotes()) {
                g.setColor(Color.RED);
                g.fillOval(temp * (l.getNumber() + 1) - 10, (int) (posY - n.getBeginin() / 5), 20, (int) n.getLength() / 15);
            }
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void startMoving(){
		Timer timer = new Timer(15, e -> {
            posY += 2;
            repaint();
        });
		timer.start();

	}
}
