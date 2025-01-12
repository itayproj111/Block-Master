package com.example.proj;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class PlayService extends Service implements MediaPlayer.OnCompletionListener {
    private MediaPlayer mediaPlayer;

    public PlayService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this,R.raw.aa);
        mediaPlayer.setOnCompletionListener(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.setLooping(false); // not restart song again
        mediaPlayer.setVolume(100,100);
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if(mediaPlayer.isPlaying())
        {
            mediaPlayer.stop();
        }

        mediaPlayer = MediaPlayer.create(this,R.raw.abba_sos);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

    }
}