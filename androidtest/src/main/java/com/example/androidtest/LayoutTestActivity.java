package com.example.androidtest;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LayoutTestActivity extends AppCompatActivity{
    private Context myContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

        myContext = this;

        initView();

    }

    public void initView(){
        Button submitButton;
        submitButton = findViewById(R.id.submit);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO  本地校验，网络传输，异步接收注册成功是否信息，页面更新

                Toast.makeText(myContext,"提交成功",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
