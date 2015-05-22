package gui;

import model.Line;
import model.Note;
import model.Song;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {

    private final Song song;
    private long posY = TapyGui.HEIGHT / 3;
    private int globalI = 2;

    public GamePanel(Song s) {
        song = s;
        setPreferredSize(new Dimension(TapyGui.WIDTH, TapyGui.HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);

        System.out.println(">>>>>> " + song.getLastTick());
        System.out.println(">>>BPM " + song.getBPM());
        System.out.println(">>>TPS " + song.getTicksPerSecond());
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponents(g);

    	g.setColor(Color.WHITE);
    	g.fillRect(0, 0, TapyGui.WIDTH, TapyGui.HEIGHT);
    	g.setColor(Color.LIGHT_GRAY);
    	g.fillRect(0, TapyGui.HEIGHT-(TapyGui.HEIGHT/5), TapyGui.WIDTH, TapyGui.HEIGHT/10);
    	g.setColor(Color.BLACK);

        int temp = TapyGui.WIDTH/5;
        g.drawLine(temp, 0, temp, TapyGui.HEIGHT);
        g.drawLine(temp * 2, 0, temp * 2, TapyGui.HEIGHT);
        g.drawLine(temp * 3, 0, temp * 3, TapyGui.HEIGHT);
        g.drawLine(temp * 4, 0, temp * 4, TapyGui.HEIGHT);

        int mult = 10;

        for (Line l : song.getLines()) {

            int x = temp * (l.getNumber() + 1);

            for(Note n : l.getNotes()) {
                int y = (int) (posY - n.getTick() / mult);
                int len = (int) (n.getLength()) / mult;

                g.setColor(Color.RED);
                g.fillRoundRect(x - 10, y - len, 20, len, 10, 10);

                g.setColor(Color.BLUE);
                g.drawString(n.getName(), x - 18, y + 10);
                g.drawString(String.valueOf(n.getTick()), x + 18, y + 10);
            }

            // Mesures
            int nMes = 0;
            for (int i = 0; i < l.getLastTick(); i += song.getFramesPerSecond() * 10) {
                int y = (int) (posY - i / mult);

                if(nMes % 8 == 0) {
                    g.setColor(Color.GRAY);
                    g.drawLine(x - 20, y, x + 20, y);
                }
                nMes++;

                g.setColor(Color.BLACK);
                g.drawLine(x - 3, y, x + 3, y);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        System.out.println(e.getKeyCode());

        int code = e.getKeyCode();
        // haut
        if(code == 38) {
            globalI = -2;
        }
        // bas
        else if(code == 40) {
            globalI = 2;
        }
        // q
        else if(code == 81) {
            globalI = -8;
        }
        // a
        else if(code == 65) {
            globalI = 8;
        }
        // space
        else if(code == 32) {
            globalI = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    public void startMoving(){
		Timer timer = new Timer(20, e -> {
            posY += globalI;
            repaint();
        });
		timer.start();
	}
}
