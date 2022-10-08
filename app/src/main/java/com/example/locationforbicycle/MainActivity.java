package com.example.locationforbicycle;

import static android.location.LocationManager.GPS_PROVIDER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Timer;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

private Location lastPlaceLocation;
private Context context;
private boolean isStop = false;
private boolean isFirst = true; // 第一次计算，上一次的location和这一次一致
private ArrayList<String> locationInfo = new ArrayList<>(); // 经纬度信息持久化

private float allDistance = 0l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        lastPlaceLocation = new Location(GPS_PROVIDER);
        Button saveFirstInfo,calculate;
        Timer timer;
        TextView firstLocation,distance;
        setContentView(R.layout.activity_main);
        saveFirstInfo = (Button) findViewById(R.id.saveFirstInfo);
        calculate = (Button)findViewById(R.id.calculate);

        calculate.setOnClickListener(this);


        firstLocation = (TextView)findViewById(R.id.firstLocation);
        distance = (TextView)findViewById(R.id.showDistance);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED&&
                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            String [] permissions = {"android.permission.ACCESS_FINE_LOCATION","android.permission.ACCESS_BACKGROUND_LOCATION"};
            requestPermissions(permissions,1);
            return;
        }


        Location location = locationManager.getLastKnownLocation(GPS_PROVIDER);

        Log.d("1111",location.toString());
        saveFirstInfo.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Log.d("1111222",location.toString());
                startRecord(location);
            }
        });





        calculate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                StringBuilder stringBuilder = new StringBuilder();
                for(int i =0;i<locationInfo.size();i++){
                    stringBuilder.append(locationInfo.get(i));
                }
                Toast.makeText(context,stringBuilder.toString(),Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences(context.getPackageName()+System.currentTimeMillis(),Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("locations",stringBuilder.toString());
                editor.apply();

                isStop = true;
                distance.setText(String.valueOf(allDistance));
                Toast.makeText(context,String.valueOf(allDistance),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View var1){

    }

    private void startRecord(Location location){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while(!isStop){

                    locationInfo.add(location.getLongitude()+"-"+location.getLatitude());

                    if(isFirst){
                        lastPlaceLocation = location;
                    }
                    allDistance = location.distanceTo(lastPlaceLocation);
                    allDistance +=allDistance;
                    Log.d(context.getPackageName(),Thread.currentThread().getName());
                    lastPlaceLocation = location;
                    isFirst = false;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t = new Thread(runnable);
        t.start();

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if(grantResults!=null && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // 用户同意权限

                }else{
                    // 用户拒绝权限，弹窗提示
                }
            default:

        }
    }

//    private class MyHandler extends Handler{
//        public MyHandler(){}
//
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            msg.
//        }
//    }
}