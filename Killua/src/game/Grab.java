package game;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * Collision listener that allows Killua to collect things.
 */
public class Grab implements CollisionListener {
    private Killua killua;

    public Grab(Killua killua) {
        this.killua = killua;
    }

    @Override
    public void collide(CollisionEvent e) {
        // if the user collides with the coin then the coin will be destroyed and the money count will be increased
        if (e.getOtherBody() == killua) {
            killua.incrementMoney();
            e.getReportingBody().destroy();
            SoundClip coinCollect;
            // Plays a sound when the user collects the coins
//            try {
//                coinCollect = new SoundClip("data/CoinCollect.wav");   // Open an audio input stream
//                coinCollect.setVolume(2);
//            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException w) {
//                System.out.println(w);
//            }
        }
    }

}
