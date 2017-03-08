/***************************************************************
 * file: PlayActivity.java
 * author: Luis Cortes, Oscar Hernandez, Henry Hu, Y-Uyen La, and An Le
 * class: CS 245 - Programming Graphical User Interfaces
 *
 * assignment: Swing Project v1.0
 * date last modified: 2/5/2017
 *
 * purpose: This class allows to play sounds inside of android apps. It was required because
 * it manages how to stop, play, pause, and release resources on sounds.
 *
 ****************************************************************/

package holay.team.memorycards;

import android.content.Context;
import android.media.MediaPlayer;

public class AudioPlayer {

    private int resource;
    private MediaPlayer mp;

    //method: onCreate
    //purpose: This is the Android convention for handling sound playback.

    public AudioPlayer(int r) {
        resource = r;
    }

    //method: onCreate
    //purpose: This method stops the audio being played and releases the resources
    public void stop() {
        if (mp != null) {
            mp.release();
            mp = null;
        }
    }

    //method: onCreate
    //purpose: This method creates the MediaPlayer and starts the music.
    public void play(Context c) {
        mp = MediaPlayer.create(c, resource);
        mp.start();
    }

    //method: onCreate
    //purpose: This method resumes the music
    public void resume() {
        mp.start();
    }

    //method: onCreate
    //purpose: This method allows you to pause the music
    public void pause() {
        mp.pause();
    }

    //method: onCreate
    //purpose: This method checks to see if the music is playing
    public boolean isPlaying() {
        if (mp != null) {
            return mp.isPlaying();
        } else {
            return false;
        }
    }


    //method: onCreate
    //purpose: This method tells you where it is in the audio at the moment. It's useful to resume where the music left off when you rotate the device.
    public int getPosition() {
        if (mp != null) {
            return mp.getCurrentPosition();
        } else {
            return -1;
        }
    }

    //method: setLoop
    //purpose: This method sets the loop for the audio. It allows the audio to keep playing after it is done playing
    public void setLoop(boolean loop) {
        mp.setLooping(loop);
    }

    //method: onCreate
    //purpose: This method sets the position of the music so it can pick back up from where it left off.
    public void setPosition(int pos) {
        mp.seekTo(pos);
    }
}
