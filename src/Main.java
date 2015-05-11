
// "Velocity" describe the volume (gain) of a MIDI note (higher velocity = louder).


import model.Note;
import model.Song;
import model.SongsManager;

public class Main {

    public static void main(String[] args) {

//        TapyGui.show();

        try {
            SongsManager sm = new SongsManager();
            Song s = sm.load("mario_mono.mid");

            System.out.println("\nNOTES:");
            for (Note n : s.getNotes()) {
                System.out.println(n.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }
}
