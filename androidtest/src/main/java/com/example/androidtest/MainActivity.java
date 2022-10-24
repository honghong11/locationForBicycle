package com.example.androidtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Context myContext;
    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"调用onCreate()");
        myContext = this;
        setContentView(R.layout.activity_main);
        initView();

        //TODO 实战，activity跳转及数据传递
        //TODO Activity启动模式
        //TODO Fragment的生命周期
        //TODO 实战 仿美团外卖菜单
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"调用onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"调用onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"调用onResume()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"调用onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"调用onDestroy()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,"调用onRestart()");
    }

    public void initView(){
        Button layoutTestButton,secondTestButton,implicitButton3,dataTransButton;
        secondTestButton = findViewById(R.id.button4);
        implicitButton3 = findViewById(R.id.button3);
        dataTransButton = findViewById(R.id.button5);
        layoutTestButton = findViewById(R.id.layout_test);
        layoutTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(myContext,ListViewActivity.class);
                startActivity(intent);
            }
        });

        secondTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startSecondActivity();
            }
        });


        implicitButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startThirdActivity();
            }
        });

        dataTransButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityWithDataTrans();
            }
        });


    }

    //  显式Intent 启动activity
    public void startSecondActivity(){
        Intent intent = new Intent(myContext,SceondActivity.class);
        startActivity(intent);
    }

    //  隐式Intent 启动activity
    public void startThirdActivity(){
        Intent intent = new Intent();
        //action
        intent.setAction("cn.itcast.START_ACTIVITY");
        //data
        intent.setData(Uri.parse("https"));
        //category
        intent.addCategory("android.intent.category.APP_BROWSER");
        startActivity(intent);
    }

    // Activity间的跳转及数据传递
    public void startActivityWithDataTrans(){
        Intent intent = new Intent(myContext,ThirdActivity.class);
        intent.putExtra("main","hellp");
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                Log.d(TAG,getIntent().getStringExtra("third"));
                break;

            default:
        }
    }
}