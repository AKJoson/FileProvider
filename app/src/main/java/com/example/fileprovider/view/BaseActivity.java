package com.example.fileprovider.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import com.example.fileprovider.listener.OnResultListener;
import com.example.fileprovider.listener.PermissionListener;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    PermissionListener listener;
    OnResultListener resultListener;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        listener.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        resultListener.onActivityResult(requestCode,resultCode,data);
    }

    public void startActivityForResult(Intent intent,int requestCode,OnResultListener listener) {
        if (listener == null)
            throw new RuntimeException("OnResultListener cann't be null");
        this.resultListener = listener;
       super.startActivityForResult(intent,requestCode);
    }

    public void onRequestPermission(int requestCode, String[] strings, PermissionListener listener) {
        if (listener == null)
            throw new RuntimeException("you must set permission listener");
        this.listener = listener;
        ActivityCompat.requestPermissions(this,strings,requestCode);
    }

    protected abstract void initView();
}
