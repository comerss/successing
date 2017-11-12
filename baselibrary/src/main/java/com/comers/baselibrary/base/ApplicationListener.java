package com.comers.baselibrary.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.comers.basic.http.HttpHelper;


/**
 * Created by Comers on 2017/9/20.
 * 前台后台的监听
 * 当应用回到后台如果需要冷启动则需要杀死进程！
 */

public class ApplicationListener implements Application.ActivityLifecycleCallbacks {

    private int foregroundCount = 0; // 位于前台的 Activity 的数目

    @Override
    public void onActivityStarted(final Activity activity) {
        if (foregroundCount <= 0) {
            // TODO 这里处理从后台恢复到前台的逻辑
        }
        foregroundCount++;
    }

    @Override
    public void onActivityStopped(Activity activity) {
        foregroundCount--;
        if (foregroundCount <= 0) {

        }
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        HttpHelper.mContext = activity;
        ActivityManager.getDefault().onCreate(activity);
    }

    @Override
    public void onActivityResumed(Activity activity) {
        ActivityManager.getDefault().onResume(activity);
        HttpHelper.mContext = activity;
    }

    @Override
    public void onActivityPaused(Activity activity) {
        ActivityManager.getDefault().onPause(activity);
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        ActivityManager.getDefault().onDestroy(activity);
    }
}
