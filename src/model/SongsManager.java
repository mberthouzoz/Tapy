package model;

import javax.sound.midi.*;
import java.io.File;
import java.io.IOException;

public class SongsManager {

    public Song load(String fileName) throws InvalidMidiDataException, IOException {
        File songf = new File("data/" + fileName);

        Sequence sequence = MidiSystem.getSequence(songf);

        return new Song(sequence);
    }
}
