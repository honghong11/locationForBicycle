package com.example.androidtest;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.androidtest.Fragment.GroundPalaceFragment;
import com.example.androidtest.Fragment.MyFragment;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class SceondActivity extends AppCompatActivity {
    private TextView ground,mine,broadcaster,shop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        initView();


    }

    public void initView(){
        ground = findViewById(R.id.ground_palace);
        mine = findViewById(R.id.mine);
        broadcaster = findViewById(R.id.broadcast);
        shop = findViewById(R.id.shop);

        ground.setBackgroundColor(0x1FCCCCCC);
        GroundPalaceFragment myFragment = new GroundPalaceFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fmTrans = fm.beginTransaction();
        fmTrans.replace(R.id.fragment,myFragment);
        fmTrans.commit();

        //展示"广场"fragment
        ground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ground.setBackgroundColor(0x1FCCCCCC);
                mine.setBackgroundColor(0x000000);
                GroundPalaceFragment myFragment = new GroundPalaceFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fmTrans = fm.beginTransaction();
                fmTrans.replace(R.id.fragment,myFragment);
                fmTrans.commit();
            }
        });

        //展示"我的"fragment
        mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mine.setBackgroundColor(0x1FCCCCCC);
                ground.setBackgroundColor(0x000000);
                MyFragment myFragment = new MyFragment();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction fmTrans = fm.beginTransaction();

                fmTrans.replace(R.id.fragment,myFragment);
                fmTrans.commit();
            }
        });

        //展示"播客"fragment
        broadcaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //展示"商城"fragment
        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
