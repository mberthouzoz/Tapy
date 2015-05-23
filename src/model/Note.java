package model;

import javax.sound.midi.ShortMessage;

public class Note {

    private int velocity;
    private int key;
    private boolean noteBegin;
    private static final String[] NOTE_NAMES = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B"};
    private static final int NOTE_ON = 0x90;
    private static final int NOTE_OFF = 0x80;
    private long tick;
    private long end;

    public Note(ShortMessage sm) {
        key = sm.getData1();
        velocity = sm.getData2();
        if(sm.getCommand() == NOTE_ON) {
            noteBegin = true;
        } else if (sm.getCommand() == NOTE_OFF) {
            noteBegin = false;
        }
    }

    public boolean isNoteBegin() {
        return noteBegin;
    }

    public int getOctave() {
        return (key / 12)-1;
    }

    public String getName() {
        int note = key % 12;
        return NOTE_NAMES[note];
    }

    public int getVelocity() {
        return velocity;
    }

    public int getKey() {
        return key;
    }

    public void setTick(long tick) {
        this.tick = tick;
    }

    public long getTick() {
        return tick;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getLength() {
        return end - tick;
    }

    @Override
    public String toString() {
        String s = "@" + tick + " [" + getLength()  + "]" + getName() + getOctave() + " key=" + key + " velocity: " + velocity;

        return s;
    }
}
