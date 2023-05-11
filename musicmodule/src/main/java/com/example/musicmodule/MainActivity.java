package com.example.musicmodule;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.musicmodule.Service.MusicService;
import com.example.musicmodule.view.MusicPlayerView;

public class MainActivity extends AppCompatActivity {

    private Button playButton,stopButton,backButton,pauseButton,seekButton;
    private Context context;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicplayerviewlayout);
        context = this;
        initView();

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            String[] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE"};
            requestPermissions(permissions,0);
        }
    }

    private void initView(){
        playButton = findViewById(R.id.start_play);
        stopButton = findViewById(R.id.stop_play);
        backButton = findViewById(R.id.back_button);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent musicServiceIntent = new Intent(context, MusicService.class);
                startService(musicServiceIntent);

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults!=null&&grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    //TODO 申请权限成功

                }else{
                    //TODO 权限未获取

                }
        }
    }
}