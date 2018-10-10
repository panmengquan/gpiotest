package com.project.my.gpiotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.FileOutputStream;

import static android.system.Os.open;
import static android.system.OsConstants.O_RDWR;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnLight;
    private Button btnPutout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        btnLight = findViewById(R.id.button1);
        btnPutout = findViewById(R.id.button2);
        btnLight.setOnClickListener(this);
        btnPutout.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button1:
                try{
                    Log.i("Log","开始");
                    FileOutputStream fops =new FileOutputStream("/sys/class/leds/NET_MODE/brightness");
                    fops.write("1".getBytes());
                    Log.i("Log","结束");
                    Log.i("Log","结束");
                    Log.i("Log","结束");
                    fops.flush();
                    fops.close();
                }catch (Exception e){
                    e.printStackTrace();
                }

                break;
            case R.id.button2:
                try{
                    FileOutputStream fops =new FileOutputStream("/sys/class/leds/NET_MODE/brightness");
                    fops.write("0".getBytes());

                    fops.flush();
                    fops.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }
}
