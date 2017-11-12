package com.comers.baselibrary.base;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.text.SimpleDateFormat;
import java.util.List;

public class GlobalApplication extends Application {
    public static Context mContext;
    public static Handler mHandler;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
    int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        registerActivityLifecycleCallbacks(new ApplicationListener());

    }
    //解决jar包太多的问题
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }

    private boolean isRuninBackground() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = activityManager.getRunningTasks(100);
        ActivityManager.RunningTaskInfo runningTaskInfo = runningTasks.get(0);
        if (runningTaskInfo.topActivity.getPackageName().equals(getPackageName())) {
            return false;
        } else {
            return true;
        }
    }

    public static Handler getMainHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }
}
