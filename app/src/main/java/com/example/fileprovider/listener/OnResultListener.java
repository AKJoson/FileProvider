package com.example.fileprovider.listener;

import android.content.Intent;

public interface OnResultListener {
    void onActivityResult(int requestCode, int resultCode, Intent data);
}
