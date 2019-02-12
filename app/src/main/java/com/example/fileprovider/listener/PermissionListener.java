package com.example.fileprovider.listener;

import android.support.annotation.NonNull;

public interface PermissionListener {
    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
}
