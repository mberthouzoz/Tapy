
// "Velocity" describe the volume (gain) of a MIDI note (higher velocity = louder).


import gui.TapyGui;
import model.Line;
import model.Note;
import model.Song;
import model.SongsManager;

public class Main {

    public static void main(String[] args) {

        try {
            SongsManager sm = new SongsManager();
            Song s = sm.load("mario_mono.mid");

            TapyGui.show(s); // Show GUI

            System.out.println("\n## NOTES: ##");
            for (Line l : s.getLines()) {
                System.out.println("Notes for Line[" + l.getNumber() + "]:");
                for(Note n : l.getNotes()) {
                    System.out.println(n.toString());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }
}
