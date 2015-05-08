package model;

import javax.sound.midi.*;
import java.util.LinkedList;

public class Song {
    public static final int NOTE_ON = 0x90;
    public static final int NOTE_OFF = 0x80;

    private Sequence sequence;

    private LinkedList<Note> notes;

    public Song(Sequence sequence, int channel) {
        this.sequence = sequence;
        notes = new LinkedList<Note>();

        Track[] tracks = sequence.getTracks();

        for (Track track : tracks) {
            for (int i = 0; i < track.size(); ++i) {
                MidiEvent event = track.get(i);
                System.out.print("@" + event.getTick() + " ");
                MidiMessage message = event.getMessage();
                if (message instanceof ShortMessage) {
                    ShortMessage sm = (ShortMessage) message;
                    System.out.print("Channel: " + sm.getChannel() + " ");

                    if (sm.getChannel() == channel) {
                        if(sm.getCommand() == NOTE_ON || sm.getCommand() == NOTE_OFF) {
                            Note n = new Note(sm);
                            System.out.println(n.toString()); // debug
                            notes.add(n);
                        }
                    }

                } else {
                    // Other message
                }
            }

        }
    }

    public Song(Sequence sequence) {
        this(sequence, 0);
    }

    public LinkedList<Note> getNotes() {
        return notes;
    }

    public double getBPM() {
        // https://github.com/estine/tmig/blob/master/Code/TMIG.java
        long MicroLen = sequence.getMicrosecondLength();
        long TickLen = sequence.getTickLength();
        int PPQ = sequence.getResolution();

        double ms_per_tick = (double) MicroLen / TickLen;

        return Math.round(1000 * (60000.0 / ms_per_tick / PPQ));
    }

}
