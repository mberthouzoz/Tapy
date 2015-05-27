package model;

import javax.sound.midi.*;
import java.io.*;

public class SongsManager {

    private Sequence sequence;
    private File songFile;

    public Song load(String fileName) throws InvalidMidiDataException, IOException {
        songFile = new File("data/" + fileName);

        sequence = MidiSystem.getSequence(songFile);

        return new Song(sequence);
    }
}
