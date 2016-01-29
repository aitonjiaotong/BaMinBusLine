package com.example.administrator.busline_aition_myapp;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Administrator on 2015-12-14.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        SDKInitializer.initialize(this);
        super.onCreate();
    }
}
