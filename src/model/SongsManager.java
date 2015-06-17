package model;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import java.io.File;
import java.io.IOException;

/**
 * Songs manager
 * Useful to load songs
 */
public class SongsManager {

    private Sequence sequence;
    private File songFile;

    /**
     * Load a midi song
     * @param fileName
     * @return Song The loaded song
     * @throws InvalidMidiDataException
     * @throws IOException
     */
    public Song load(String fileName) throws InvalidMidiDataException, IOException {
        songFile = new File(fileName);

        sequence = MidiSystem.getSequence(songFile);

        return new Song(sequence);
    }
}
