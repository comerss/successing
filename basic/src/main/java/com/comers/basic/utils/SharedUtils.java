package com.comers.basic.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.liangyibang.market.R;


/**
 * Created by lq on 2016/12/7.
 * 作者：栗启
 * 作用：Perferences工具类
 */
public class SharedUtils {
    public static SharedPreferences preferences;

    //初始化
    public static void init() {
        if (preferences == null) {
            preferences = UIUtils.getContext().getSharedPreferences(UIUtils.getContext().getString(R.string.app_name), Context.MODE_PRIVATE);
        }

    }

    //添加属性
    public static <T> void put( String key, T values) {
        init();
        SharedPreferences.Editor edit = preferences.edit();
        if (values instanceof String) {
            edit.putString(key, values.toString());
        } else if (values instanceof Integer) {
            edit.putInt(key, (Integer) values);
        } else if (values instanceof Long) {
            edit.putLong(key, (Long) values);
        } else if (values instanceof Boolean) {
            edit.putBoolean(key, (Boolean) values);
        } else if (values instanceof Float) {
            edit.putFloat(key, (Float) values);
        }
        edit.commit();
    }

    //查询属性
    public static <T> T get(Context context, String key, T values) {
        init();
        Object o = null;
        if (values instanceof String) {
            o = preferences.getString(key, values.toString());
        } else if (values instanceof Integer) {
            o = preferences.getInt(key, (Integer) values);
        } else if (values instanceof Long) {
            o = preferences.getLong(key, (Long) values);
        } else if (values instanceof Boolean) {
            o = preferences.getBoolean(key, (Boolean) values);
        } else if (values instanceof Float) {
            o = preferences.getFloat(key, (Float) values);
        }
        T t = (T) o;
        return t;
    }
    //查询属性
    public static <T> T get(String key, T values) {
        init();
        Object o = null;
        if (values instanceof String) {
            o = preferences.getString(key, values.toString());
        } else if (values instanceof Integer) {
            o = preferences.getInt(key, (Integer) values);
        } else if (values instanceof Long) {
            o = preferences.getLong(key, (Long) values);
        } else if (values instanceof Boolean) {
            o = preferences.getBoolean(key, (Boolean) values);
        } else if (values instanceof Float) {
            o = preferences.getFloat(key, (Float) values);
        }
        T t = (T) o;
        return t;
    }


}
