package gui;

import model.Channel;
import model.Line;
import model.Note;
import model.Song;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * Game panel
 */
public class GamePanel extends JPanel implements KeyListener {

    private final Song song;
    private final Channel chan;
    private final int zoneY;
    private final int NOTE_WIDTH = TapyGui.WIDTH / 20;
    // Define Color
    private final Color COLOR_IN_ZONE = new Color(190, 40, 40);
    private final Color COLOR_KEY_TO_PRESS = new Color(0, 0, 0, 160);
    private final Color COLOR_ZONE_ACTIVE = new Color(46, 204, 113, 80);
    private final Color COLOR_BACKGROUND = new Color(255, 255, 255);
    // Define Keys
    private final char[] KEYS_TO_PRESS = {'D', 'F', 'J', 'K'};

    private double posY = TapyGui.HEIGHT / 2;
    private double globalI = 2;
    private boolean isPlaying = false;
    private boolean isRunning = true;
    private boolean scorePrinted = false;
    private int score = 0;
    private JLabel scoreLabel;
    private JFrame gameFrame;
    private Timer timer;
    private int iTimer;

    public GamePanel(Song s, int chanNb, JFrame gameFrame) {
        this.gameFrame = gameFrame;
        song = s;
        chan = s.getChannel(chanNb);
        // Full screen
        setPreferredSize(new Dimension(TapyGui.WIDTH, TapyGui.HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);

        scoreLabel = new JLabel(String.valueOf(score));
        scoreLabel.setFont(new Font("Sans", Font.PLAIN, 50));
        add(scoreLabel);

        // Zone active
        zoneY = TapyGui.HEIGHT - (TapyGui.HEIGHT / 6);
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponents(g);

        // Background
        g.setColor(COLOR_BACKGROUND);
        g.fillRect(0, 0, TapyGui.WIDTH, TapyGui.HEIGHT);

        // Active zone
        g.setColor(COLOR_ZONE_ACTIVE);
        g.fillRect(0, zoneY, TapyGui.WIDTH, 20);


        // Line
        g.setColor(new Color(127, 140, 141));
        int temp = TapyGui.WIDTH / 5;
        g.drawRect(temp, -1, 1, TapyGui.HEIGHT);
        g.drawRect(temp * 2, -1, 1, TapyGui.HEIGHT);
        g.drawRect(temp * 3, -1, 1, TapyGui.HEIGHT);
        g.drawRect(temp * 4, -1, 1, TapyGui.HEIGHT);

        int mult = 10;
        int currentLine = 0;

        // For each line display note
        for (Line l : chan.getLines()) {
            int x = temp * (l.getNumber() + 1);

            l.getNotes().forEach(n -> {
                int y = (int) (posY - n.getTick() / mult);
                int len = (int) (n.getLength()) / mult;

                // When note is in active zone
                if (y > zoneY && y + len < zoneY + 40) {
                    g.setColor(COLOR_IN_ZONE);
                    n.setInSection(true);
                } else {
                    g.setColor(l.getColor());
                    n.setInSection(false);
                }

                // Draw note
                g.fillRoundRect(x - NOTE_WIDTH / 2, y - len, NOTE_WIDTH, len, 10, 10);
                g.setColor(Color.BLACK);
                g.drawRoundRect(x - NOTE_WIDTH / 2, y - len, NOTE_WIDTH, len, 10, 10);

            });

            // Displays the key to press
            g.setColor(COLOR_KEY_TO_PRESS);
            g.setFont(new Font("Sans", Font.PLAIN, 70));
            g.drawString(String.valueOf(KEYS_TO_PRESS[currentLine]), x + 20, zoneY + 100);

            currentLine++;
        }

        if (!isPlaying && posY > zoneY) {
            try {
                song.play(); // play the song
            } catch (MidiUnavailableException | InvalidMidiDataException | IOException e) {
                e.printStackTrace();
            }
            System.out.println("Let the song begin !");
            isPlaying = true;
        }

        if (isPlaying && !song.isRunning() && !scorePrinted) {
            timer.stop();
            gameFrame.dispose();
            scorePrinted = true;
            JFrame frame = new JFrame("ScoreBoard");
            frame.setLayout(new FlowLayout());
            frame.add(new ScorePanel(score, frame));

            frame.setSize(400, 200);
            frame.setResizable(false);
            frame.setVisible(true);
            frame.setLocationRelativeTo(null);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // Check if key in tab is pressed and check
        for (int i = 0; i < chan.getLines().length; i++) {
            if (key == KEYS_TO_PRESS[i]) {
                checkLine(i);
                break;
            }
        }
        if (key == 'A') {
            globalI = 0;
        }
        if (key == 'Q') {
            globalI = 1;
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * Main loop
     */
    public void startMoving() {
        float err = Math.abs((float) Math.ceil((1000 / song.getFramesPerSecond() / 2) - (1000 / song.getFramesPerSecond() / 2)));

        int t = (int) ((1000 / song.getFramesPerSecond() / 2)) + 1;

        System.out.println("t: " + t + " a:" + (1000 / song.getFramesPerSecond() / 2) + " E:" + err);

        iTimer = 1;
        timer = new Timer(t, e -> {
            posY += globalI;

            iTimer++;

            if(1 / iTimer <= err) {
                try {
                    Thread.sleep(1);
                    iTimer = 1;
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            if (isRunning) {
                repaint();
            }
        });

        timer.start();
    }

    // check if the note in active zone
    private boolean oneNoteInSection(int line) {
        for (Note n : chan.getLine(line).getNotes()) {
            if (n.isInSection()) {
                return true;
            }
        }

        return false;
    }

    private void checkLine(int line) {
        if (oneNoteInSection(line)) {
            score += 100;
        } else {
            score = score == 0 ? 0 : score - 100;
        }
        scoreLabel.setText(String.valueOf(score));
    }
}
