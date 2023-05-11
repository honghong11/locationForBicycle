package com.example.androidtest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        initView();

    }

    public void initView(){
        Button backButton = findViewById(R.id.back);
        Button writeButton = findViewById(R.id.writ);
        Button readButton = findViewById(R.id.read);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("third","nihao");
                setResult(1);
                finish();
            }
        });

        writeButton.setOnClickListener(new View.OnClickListener() {
            FileOutputStream fileOutputStream;
            @Override
            public void onClick(View v) {
                try {
                    fileOutputStream = ThirdActivity.this.openFileOutput("private.txt",MODE_PRIVATE);
                    fileOutputStream.write("hello word".getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }finally {
                    if(fileOutputStream!=null){
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        readButton.setOnClickListener(new View.OnClickListener() {
            String content = "1";
            FileInputStream fileInputStream = null;
            @Override
            public void onClick(View v) {
                try {
                    fileInputStream = openFileInput("private.txt");
                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    content = new String(buffer);
                    Toast.makeText(ThirdActivity.this,content,Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(fileInputStream!=null){
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

}
