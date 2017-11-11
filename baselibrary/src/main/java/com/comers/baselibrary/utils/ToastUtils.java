package com.comers.baselibrary.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

import com.comers.baselibrary.base.GlobalApplication;

//真正开始自己的命运反抗之旅
public class ToastUtils {
    public static void toast(Context context, String str) {
        Toast toast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        String versionCode = UIUtils.INSTANCE.getVersionCode(context);
    }

    private static Toast mToast;

    public static void showToast(String content) {
        if (mToast == null) {
            mToast = Toast.makeText(GlobalApplication.Companion.getMContext(), content, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            mToast.setText(content);
        }
        if (!TextUtils.isEmpty(content)) {
            mToast.show();
        } else {
            mToast.cancel();
        }
    }

    public static void showToastLong(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(GlobalApplication.Companion.getMContext(), text, Toast.LENGTH_LONG);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_LONG);
        }
        if (!TextUtils.isEmpty(text)) {
            mToast.show();
        }
    }


    public static void showToastCenter(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(GlobalApplication.Companion.getMContext(), text, Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            mToast.setText(text);
        }
        if (!TextUtils.isEmpty(text)) {
            mToast.show();
        }
    }

    public static void cancelToast() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public static void showTimeToast(final long cnt, String text) {
        mToast = Toast.makeText(GlobalApplication.Companion.getMContext(), text, Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
    }

    /**
     * 如何按返回键想让Toast立即不显示，执行该方法
     */
    public static void onBackPressed() {
        cancelToast();
    }
}
