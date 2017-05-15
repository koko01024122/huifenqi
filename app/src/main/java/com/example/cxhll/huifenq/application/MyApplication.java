package com.example.cxhll.huifenq.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import com.tencent.bugly.Bugly;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by CXHLL on 2017/1/22.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {

       // CrashReport.initCrashReport(getApplicationContext(),"",false);
        Bugly.init(getApplicationContext(), "f7a56fe063", false);

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        Set<String> set=new HashSet<>();
        set.add("testone");
        set.add("testtwo");
        JPushInterface.setTags(this,set,null);
        ZXingLibrary.initDisplayOpinion(this);
      //配置图片选择器

        Fresco.initialize(getApplicationContext());


        super.onCreate();
    }
}
