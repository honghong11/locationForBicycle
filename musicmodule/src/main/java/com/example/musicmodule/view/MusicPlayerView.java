package com.example.musicmodule.view;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.musicmodule.R;
import com.example.musicmodule.Service.MusicService;

import java.io.File;
import java.io.IOException;

/**
 *  音乐播放器页面
 */
public class MusicPlayerView extends LinearLayout {
    private MediaPlayer myMediaPlayer =  new MediaPlayer();;
    private Button backButton,startPlayButton,stopPlayButton;
    private Context myContext;
    private MusicService musicService;

    public MusicPlayerView(Context context) {
        super(context);
        myContext = context;
        initView(context);
    }

    public MusicPlayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        myContext = context;
        initView(context);
    }

    public MusicPlayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MusicPlayerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void initView(Context context){
        LayoutInflater.from(context).inflate(R.layout.musicplayerviewlayout,this,true);
        backButton = (Button)findViewById(R.id.back_button);
        startPlayButton = (Button)findViewById(R.id.start_play);
        stopPlayButton = (Button)findViewById(R.id.stop_play);

        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context,"回退",Toast.LENGTH_SHORT).show();
                Log.d("MusicPlayerView","start success"+myMediaPlayer.getCurrentPosition());
            }
        });

        startPlayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view){
                try {
                    myMediaPlayer.setDataSource("/storage/emulated/0/qqmusic/song/心爱.mp3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myMediaPlayer.start();


            }
        });

        stopPlayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                myMediaPlayer.stop();
            }
        });

    }

//    /**
//     * 不能这样直接写，musicpalyerview是在mainActivity中加载的，是MainActivity对应view的子View，所以点击事件被MainActivity截获
//     * @param view
//     */
//    @Override
//    public void onClick(View view) {
//        int viewId = view.getId();
//        if(viewId!=-1){
//            if(viewId == R.id.back_button){
//                Toast.makeText(myContext,"回退",Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
}
