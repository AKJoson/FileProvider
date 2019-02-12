package com.example.fileprovider.viewmodel;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.Bindable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.fileprovider.BR;
import com.example.fileprovider.listener.OnResultListener;
import com.example.fileprovider.listener.PermissionListener;
import com.example.fileprovider.utils.BaseUtil;
import com.example.fileprovider.utils.FileUtil;
import com.example.fileprovider.view.BaseActivity;

import java.io.File;

public class ViewModel extends BaseViewModel implements PermissionListener, OnResultListener {

    private final int take_photo = 0x0000;
    private final int chioce_pic = 0x0001;
    private String absolutePath;
    private String imgUrl;

    public ViewModel(AppCompatActivity activity) {
        super(activity);
    }

    public void onClick(int type) {
        switch (type) {
            case 0:
                //Request Permission
                if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(mActivity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    takePhoto();
                else
                    ((BaseActivity) mActivity).onRequestPermission(take_photo, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, this);
                break;
            case 1:
                if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                    chiocePic();
                else
                    ((BaseActivity) mActivity).onRequestPermission(chioce_pic, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, this);
                break;
        }
    }

    @Bindable
    public String getImgUrl() {
        return imgUrl;
    }

    public void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = FileUtil.getFile();
        absolutePath = file.getAbsolutePath();
        intent.putExtra(MediaStore.EXTRA_OUTPUT, BaseUtil.getFileUri(mActivity, file));
        ((BaseActivity) mActivity).startActivityForResult(intent, take_photo, this);
    }

    private void chiocePic() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ((BaseActivity) mActivity).startActivityForResult(intent, chioce_pic, this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (take_photo == requestCode) {
            boolean isOk = true;
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                    isOk = false;
            }
            if (isOk)
                takePhoto();
        } else if (chioce_pic == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                chiocePic();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == take_photo) {
                imgUrl = absolutePath;
                notifyPropertyChanged(BR.imgUrl);
            } else if (requestCode == chioce_pic) {
                Uri uri = data.getData();
                if (uri != null) {
                    String[] project = {MediaStore.Images.Media.DATA};
                    Cursor cursor = mActivity.getContentResolver().query(uri, project, null, null, null);
                    if (cursor != null) {
                        String path = "";
                        if (cursor.moveToFirst()) {
                            path = cursor.getString(cursor.getColumnIndex(project[0]));
                        }
                        cursor.close();
                        if (!TextUtils.isEmpty(path)) {
                            imgUrl = path;
                            notifyPropertyChanged(BR.imgUrl);
                        }
                    }
                }
            }
        }
    }
}
