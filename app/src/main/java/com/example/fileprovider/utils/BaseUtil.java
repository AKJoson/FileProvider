package com.example.fileprovider.utils;

import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

public class BaseUtil {

    public static Uri getFileUri(AppCompatActivity activity, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //you need use FileProvider, otherwise the app will crash.
            return FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", file);
        } else {
            return Uri.fromFile(file);
        }
    }
}
