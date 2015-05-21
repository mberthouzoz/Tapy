package gui;

import javax.swing.*;

import model.Note;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
	
	private int posY = 0;
    public GamePanel() {
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
    	int temp = TapyGui.WIDTH/5;
    	((Graphics2D)g).drawLine(temp, 0, temp, TapyGui.HEIGHT);
    	((Graphics2D)g).drawLine(temp*2, 0, temp*2, TapyGui.HEIGHT);
    	((Graphics2D)g).drawLine(temp*3, 0, temp*3, TapyGui.HEIGHT);
    	((Graphics2D)g).drawLine(temp*4, 0, temp*4, TapyGui.HEIGHT);
    	g.setColor(Color.RED);
    	g.fillOval(temp-10, posY, 20, 20);
    	g.fillOval(temp*2 - 10, posY - 100, 20, 20);
    	
    	
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
		Timer timer = new Timer(20, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				posY++;
				repaint();
				
				
			}
		});
		timer.start();

	}
}
