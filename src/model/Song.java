package model;

import javax.sound.midi.*;
import java.io.IOException;

public class Song {

    private final Sequence sequence;

    public Song(Sequence sequence) {
        this.sequence = sequence;
    }

    public void play() throws MidiUnavailableException, IOException, InvalidMidiDataException {
        Sequencer sequencer = MidiSystem.getSequencer();
        sequencer.open();
        sequencer.setSequence(sequence);
        sequencer.start();
    }

    public double getBPM() {
        // https://github.com/estine/tmig/blob/master/Code/TMIG.java
        long MicroLen = sequence.getMicrosecondLength();
        long TickLen = sequence.getTickLength();
        int PPQ = sequence.getResolution();

        double ms_per_tick = (double) MicroLen / TickLen;

        return Math.round(1000 * (60000.0 / ms_per_tick / PPQ));
    }

    public float getFramesPerSecond() {
        float divisionType = sequence.getDivisionType();

        return (divisionType == 0.0) ? 24 : divisionType;
    }

    public float getTicksPerSecond() {
        return sequence.getResolution() * getFramesPerSecond();
    }


    public Channel getChannel(int channel) {
        Track[] tracks = sequence.getTracks();

        Channel chan = new Channel(tracks, channel);
        return chan;
    }

}
