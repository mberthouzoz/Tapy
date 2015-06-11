
// "Velocity" describe the volume (gain) of a MIDI note (higher velocity = louder).

import gui.TapyGui;
import model.Song;
import model.SongsManager;

public class Main {

    public static void main(String[] args) {

        try {
            SongsManager sm = new SongsManager();
            Song s = sm.load("elise.mid");
//            Song s = sm.load("Chuck_Berry_-_Johnny_B_Goode.mid");
//            Song s = sm.load("AC-DC_Hells_Bells.mid");

//            TapyGui.show(s, 0); // Show GUI
            TapyGui.show(s, 0); // Show GUI

//            System.out.println("\n## NOTES: ##");
//            for (Line l : s.getChannel(0).getLines()) {
//                System.out.println("Notes for Line[" + l.getNumber() + "]:");
//                for(Note n : l.getNotes()) {
//                    System.out.println(n.toString());
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }
}
