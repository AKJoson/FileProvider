package com.example.fileprovider.utils;

import android.os.Environment;

import java.io.File;

public class FileUtil {

    public static File getCacheFile(){
        File filesDir = new File(Environment.getExternalStorageDirectory() +"/imags/");
        if (!filesDir.exists()){
            filesDir.mkdirs();
        }
        return filesDir;
    }

    public static File getFile(){
       return new File(getCacheFile(),System.currentTimeMillis()+".jpg");
    }
}
