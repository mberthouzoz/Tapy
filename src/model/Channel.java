package model;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Channel {
    // lines
    private Line[] lines = new Line[NB_LINES];
    private final long firstTick;
    private final long lastTick;
    public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;
    private static final int NB_LINES = 4;
    // One color by line for notes
    private static final Color[] COLORS = {new Color(255, 0, 0),
                                            new Color(0, 255, 0),
                                            new Color(0, 0, 255),
                                            new Color(255, 255, 0)};

    public Channel(Track[] tracks, int channel) {
        LinkedList<Note> notes = new LinkedList<>();
        HashMap<Integer, Note> keySequ = new HashMap<Integer, Note>();
        // Prototype
        Note note = new Note();
        Line line = new Line();
        int keyMin = 0xFF;
        int keyMax = 0;

        for (Track track : tracks) {
            for (int i = 0; i < track.size(); ++i) {
                MidiEvent event = track.get(i);
                System.out.print("@" + event.getTick() + " ");
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    System.out.print("Channel: " + sm.getChannel() + " ");

                    if (sm.getChannel() == channel) {

                        if (sm.getCommand() == NOTE_ON) {
                            Note n = (Note) note.clone();
                            n.setShortMessage(sm);
                            n.setTick(event.getTick());
                            
                            System.out.println(event.getTick());

                            keySequ.put(n.getKey(), n);
                        } else if (sm.getCommand() == NOTE_OFF) {
                            int k = sm.getData1();
                            Note n = keySequ.get(k);
                            if (n != null) {
                                keySequ.clear();
                                n.setEnd(event.getTick());
                                notes.add(n);

                                int kTmp = n.getKey();
                                if (kTmp < keyMin) {
                                    keyMin = kTmp;
                                } else if (kTmp > keyMax) {
                                    keyMax = kTmp;
                                }
                            }
                        }
                    }

                } else {
                    // Other message
                }
            }
        }

        int interval = keyMax - keyMin;
        for (int i = 0; i < NB_LINES; i++) {
            // clone a line and set some information
            Line l = line.clone();
            l.setNumber(i);
            l.setColor(COLORS[i]);
            lines[i] = l;
        }

        for (Note n : notes) {
            float key = n.getKey();
            int index = (int) Math.round((key - keyMin) / interval * (NB_LINES - 1));

            lines[index].addNote(n);
        }
        firstTick = notes.getFirst().getTick();
        lastTick = notes.getLast().getTick();
    }

    public Line[] getLines() {
        return lines;
    }

    public Line getLine(int i) {
        return lines[i];
    }

    public long getFirstTick() {
        return firstTick;
    }

    public long getLastTick() {
        return lastTick;
    }
}
