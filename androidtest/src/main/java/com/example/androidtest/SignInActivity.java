package com.example.androidtest;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidtest.util.MyDBHelper;
import com.example.androidtest.util.PermissionUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    private Context myContext;
    private SharedPreferences sharedPreferences = null;
    private SharedPreferences.Editor editor = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
        myContext = this;
        sharedPreferences = getSharedPreferences("userInfo",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        initView();


    }

    public void initView(){
        Button signButton,read,writeSDCardButton,readSDCardButton,writeSP,readSP,opeartionDB;

        signButton = findViewById(R.id.sign_button);
        read = findViewById(R.id.read);
        writeSDCardButton = findViewById(R.id.write_sdcard);
        readSDCardButton = findViewById(R.id.read_sdcard);
        writeSP = findViewById(R.id.write_sp);
        readSP = findViewById(R.id.read_sp);
        opeartionDB = findViewById(R.id.sql_lite_operation);

        signButton.setOnClickListener(new View.OnClickListener() {
            FileOutputStream fileOutputStream = null;
            @Override
            public void onClick(View view) {
                String content =  "hhhhhhh";
                try {
                    fileOutputStream = openFileOutput("data.txt",MODE_PRIVATE);
                    fileOutputStream.write(content.getBytes());
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    try{
                        if(fileOutputStream!=null){
                            fileOutputStream.close();
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            FileInputStream fileInputStream= null;
            @Override
            public void onClick(View view) {
                try {
                    fileInputStream = openFileInput("data.txt");
                    byte[] buffer = new byte[fileInputStream.available()];
                    fileInputStream.read(buffer);
                    String contentString = new String(buffer);
                    Toast.makeText(SignInActivity.this,contentString,Toast.LENGTH_SHORT).show();
                }catch (Exception e){
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

        writeSDCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 写入SD卡，首先申请权限，Android 6.0后动态申请权限
                if(Build.VERSION.SDK_INT>=23){
                    String [] permissions = {"android.permission.WRITE_EXTERNAL_STORAGE"};
                    requestPermissions(permissions,1);
                }

            }
        });

        readSDCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 读外部存储文件，Android 6.0 后需要申请动态权限
                if(Build.VERSION.SDK_INT>=23){
                    requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"},2);
                }
            }
        });

        writeSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //使用SharedPreference写入数据
                editor.putString("pass","你好");
                editor.commit();
            }
        });

        readSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 使用SharedPreference读数据
                String data = sharedPreferences.getString("pass","");
                Toast.makeText(SignInActivity.this,data,Toast.LENGTH_SHORT).show();

            }
        });

        opeartionDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDBHelper myDBHelper = new MyDBHelper(SignInActivity.this,"DBdemo",null,1);
                SQLiteDatabase db = myDBHelper.getWritableDatabase();

                //-----不使用sql语句进行操作
                //插入
                ContentValues values = new ContentValues();
                values.put("name","xh");
                values.put("passwd","123");
                long id = db.insert("userInfo",null,values);
                db.close();

                //查询
                Cursor cursor = db.query("userInfo",null,"_id=?",new String[]{id+""},null,null,null);
                if(cursor.getCount()!=0){
                    while (cursor.moveToNext()){
                        @SuppressLint("Range")
                        String _id = cursor.getString(cursor.getColumnIndex("_id"));
                        @SuppressLint("Range")
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String passwd = cursor.getString(cursor.getColumnIndex("passwd"));
                        Toast.makeText(SignInActivity.this,name+"--"+passwd+"--"+_id,Toast.LENGTH_SHORT).show();
                    }
                }
                cursor.close();
                db.close();


                //----使用sql语句进行操作
                //插入
//                db.execSQL("insert into userInfo (name,passwd) values(?,?)",new Object[]{"xx",321});
                //查询
//                Cursor cursor = db.rawQuery("select * from userInfo where name=?",new String[]{"xx"});
//                if(cursor.getCount()!=0){
//                    while (cursor.moveToNext()){
//                        @SuppressLint("Range")
//                        String _id = cursor.getString(cursor.getColumnIndex("_id"));
//                        @SuppressLint("Range")
//                        String name = cursor.getString(cursor.getColumnIndex("name"));
//                        @SuppressLint("Range") String passwd = cursor.getString(cursor.getColumnIndex("passwd"));
//                        Toast.makeText(SignInActivity.this,name+"--"+passwd+"--"+_id,Toast.LENGTH_SHORT).show();
//                    }
//                }
//                cursor.close();
//                db.close();

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == 1){ //处理写文件
            for(int i = 0;i<permissions.length;i++){
                if(permissions[i].equals("android.permission.WRITE_EXTERNAL_STORAGE")&&grantResults[i]== PackageManager.PERMISSION_GRANTED){
                    // 写外部文件权限获取，开始写文件
                    String state = Environment.getExternalStorageState();
                    if(state.equals(Environment.MEDIA_MOUNTED)){
                        File SDPath = Environment.getExternalStorageDirectory();
                        File file = new File(SDPath,"data.txt");
                        FileOutputStream fileOutputStream = null;
                        try {
                            fileOutputStream = new FileOutputStream(file);
                            fileOutputStream.write("这里是密码".getBytes());
                        } catch (Exception e) {
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
                }
            }
        }else if(requestCode == 2){ //处理读文件
            for(int i = 0;i<permissions.length;i++){
                if(permissions[i].equals("android.permission.READ_EXTERNAL_STORAGE")&&grantResults[i]==PackageManager.PERMISSION_GRANTED){
                    String state = Environment.getExternalStorageState();
                    if(state.equals(Environment.MEDIA_MOUNTED)){
                        File SDPath = Environment.getExternalStorageDirectory();
                        File file = new File(SDPath,"data.txt");
                        FileInputStream fileInputStream = null;
                        BufferedReader bufferedReader = null;
                        try {
                            fileInputStream = new FileInputStream(file);
                            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                            String data = bufferedReader.readLine();
                            Toast.makeText(SignInActivity.this,data,Toast.LENGTH_SHORT).show();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }catch (IOException e){
                            e.printStackTrace();
                        }finally {
                            if(bufferedReader!=null){
                                try {
                                    bufferedReader.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            if(fileInputStream!=null){
                                try {
                                    fileInputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}
