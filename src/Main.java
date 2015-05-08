
// "Velocity" describe the volume (gain) of a MIDI note (higher velocity = louder).


import model.SongsManager;

public class Main {

    public static void main(String[] args) {

//        TapyGui.show();

        try {
            SongsManager sm = new SongsManager();
            sm.load("mario_mono.mid");

        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("end");
    }
}
