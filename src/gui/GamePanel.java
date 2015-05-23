package gui;

import model.Channel;
import model.Line;
import model.Note;
import model.Song;
import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JXPanel implements KeyListener {

    private final Song song;
    private final Channel chan;
    private final int zoneY;
    private long posY = TapyGui.HEIGHT / 3;
    private int globalI = 2;

    public GamePanel(Song s, int chanNb) {
        song = s;
        chan = s.getChannel(chanNb);
        setPreferredSize(new Dimension(TapyGui.WIDTH, TapyGui.HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);

        zoneY = TapyGui.HEIGHT - (TapyGui.HEIGHT / 6);

        System.out.println(">>Last " + chan.getLastTick());
        System.out.println(">>>BPM " + song.getBPM());
        System.out.println(">>>TPS " + song.getTicksPerSecond());
    }

    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponents(g);

        g.setFont(new Font(g.getFont().getFontName(), Font.PLAIN, 9));

        g.setColor(new Color(236, 240, 241));
    	g.fillRect(0, 0, TapyGui.WIDTH, TapyGui.HEIGHT);

        // active zone
    	g.setColor(new Color(189, 195, 199, 80));
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

                if(y > zoneY) {
                    g.setColor(new Color(190, 40, 40));
                } else {
                    g.setColor(new Color(231, 76, 60));
                }

                g.fillRoundRect(x - 10, y - len, 20, len, 10, 10);

                g.setColor(new Color(41, 128, 185));
                g.drawString(n.getName(), x - 18, y - len / 2 + 5);
                g.drawString(String.valueOf(n.getTick()), x + 18, y - len / 2 + 5);
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

                g.setColor(new Color(0, 0, 0, 150));
                g.drawLine(x - 3, y, x + 3, y);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());

        switch (e.getKeyCode()) {
            case 89 :
                globalI = - 2;
                break;
            case 88 :
                globalI = - 2;
                break;
            case 67 :
                globalI = - 2;
                break;
            case 86 :
                globalI = - 2;
                break;
            // haut
            case 38 :
                globalI = - 2;
                break;
            // bas
            case 40 :
                globalI = 2;
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
		Timer timer = new Timer(20, e -> {
            posY += globalI;
            repaint();
        });
		timer.start();
	}
}
