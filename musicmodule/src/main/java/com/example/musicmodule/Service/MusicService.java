package com.example.musicmodule.Service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    public MusicService() {
        super();
    }

    @Override
    public void onCreate() {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, android.R.raw.);
        mediaPlayer.setOnCompletionListener();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void play(){

    }

    private void seekPlay(int seekPoint){

    }

    private void stop(){

    }

    private void pause(){

    }
}
