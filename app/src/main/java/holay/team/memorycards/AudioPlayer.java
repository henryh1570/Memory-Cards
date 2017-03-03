package holay.team.memorycards;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {

    private int resource;
    private MediaPlayer mp;

    public AudioPlayer(int r) {
        resource = r;
    }

    public void stop() {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    public void play(Context c) {
        mp = MediaPlayer.create(c, resource);
        mp.start();
    }

    public void resume() {
        mp.start();
    }

    public void pause() {
        mp.pause();
    }

    public boolean isPlaying() {
        if (mp != null) {
            return mp.isPlaying();
        } else {
            return false;
        }
    }

    public int getPosition() {
        if (mp != null) {
            return mp.getCurrentPosition();
        } else {
            return -1;
        }
    }

    public void setPosition(int pos) {
        mp.seekTo(pos);
    }
}
