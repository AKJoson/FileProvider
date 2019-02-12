package com.example.fileprovider.viewmodel;

import android.databinding.BaseObservable;
import android.support.v7.app.AppCompatActivity;


public class BaseViewModel extends BaseObservable {

    protected AppCompatActivity mActivity;

    BaseViewModel(AppCompatActivity activity) {
        this.mActivity = activity;
    }

}
