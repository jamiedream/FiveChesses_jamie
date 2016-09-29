package chunying.fivechesses_v2;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by Juyn-Ying on 27/9/2016.
 */
public class Music {
    private static MediaPlayer mp = null;

    /** Stop old song and start new one */
    public static void  play(Context context, int resource) {

        stop(context);
        mp = MediaPlayer.create(context, resource);
        mp.setLooping(true);
        mp.start();
        if(MainActivity.isPlay){mp.stop();}

    }

    /** Stop the music */
    public static void stop(Context context) {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }




}
