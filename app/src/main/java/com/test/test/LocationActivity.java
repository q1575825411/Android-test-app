package com.test.test;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class LocationActivity extends AppCompatActivity{

    private static final String TAG = "LocationActivity";

    private  Button btn_location ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        bindViews();
        btn_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();
            }
        });
    }

    private void bindViews() {
        btn_location = (Button) findViewById(R.id.btn_location);
        Log.e(TAG, "bindViews: ");
    }


    public Boolean checkPermission() {
        boolean isGranted = true;
        Log.e(TAG, "isGranted: ");
        if (android.os.Build.VERSION.SDK_INT >= 23) {
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            if (this.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=PackageManager.PERMISSION_GRANTED) {
                isGranted = false;
            }
            Log.i("读写权限获取"," ： "+isGranted);
            if (!isGranted) {
                this.requestPermissions(
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission
                                .ACCESS_FINE_LOCATION,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        102);
            }
        }
        return isGranted;
    }

}