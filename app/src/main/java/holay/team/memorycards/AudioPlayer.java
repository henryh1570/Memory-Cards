package holay.team.memorycards;

import android.media.MediaPlayer;

// A class only for making sure mediaplayer is prepared
public class AudioPlayer {
    private MediaPlayer mp;
    private boolean isPrepared = false;

    public AudioPlayer(MediaPlayer mp) {
        this.mp = mp;
    }

    public MediaPlayer getMediaPlayer() {
        return mp;
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared() {
        isPrepared = true;
    }

    public boolean isPlaying () {
        return mp.isPlaying();
    }

    public void stop() {
        mp.stop();
    }
}
