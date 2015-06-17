package model;

import javax.sound.midi.*;

import java.io.IOException;

public class Song {

    private final Sequence sequence;
    private Sequencer sequencer;

    public Song(Sequence sequence) {
        this.sequence = sequence;
        try {
			sequencer = MidiSystem.getSequencer();
		} catch (MidiUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Play the song in background
     * @throws MidiUnavailableException
     * @throws IOException
     * @throws InvalidMidiDataException
     */
    public void play() throws MidiUnavailableException, IOException, InvalidMidiDataException {
        sequencer.open();
        sequencer.setSequence(sequence);
        sequencer.start();
    }

    /**
     * Get the BPM of the song
     * @return double The BPM
     */
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

    /**
     * Get a specific channel
     * @param channel
     * @return
     */
    public Channel getChannel(int channel) {
        Track[] tracks = sequence.getTracks();

        Channel chan = new Channel(tracks, channel);
        return chan;
    }
    
    public boolean isRunning(){
    	return sequencer.isRunning();
    }

}
