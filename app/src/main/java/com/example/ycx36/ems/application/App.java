package com.example.ycx36.ems.application;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends Application {

    String appID = "aC8DO3anvX0viMvS78PFjqTk-gzGzoHsz";
    String appKey = "w6XADTcQoQJ4Xyb24yBX5vr7";
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,appID,appKey);
    }
}
