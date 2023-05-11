package com.example.androidtest;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener{
    private Button alphaButton,translateButton;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        alphaButton = findViewById(R.id.alpha);
        translateButton =findViewById(R.id.translate);
        imageView = findViewById(R.id.fire);
        alphaButton.setOnClickListener(this);
        translateButton.setOnClickListener(this);
        imageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Animation animation;
        switch (v.getId()){
            case R.id.alpha:
                animation = AnimationUtils.loadAnimation(this,R.anim.alpha_animation);
                imageView.startAnimation(animation);
                break;
            case R.id.translate:
                animation = AnimationUtils.loadAnimation(this,R.anim.translate);
                imageView.startAnimation(animation);
            default:
                break;
        }
    }
}
