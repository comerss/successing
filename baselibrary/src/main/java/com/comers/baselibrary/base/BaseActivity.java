package com.comers.baselibrary.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.comers.baselibrary.R;
import com.comers.baselibrary.utils.ToastUtils;
import com.comers.baselibrary.widget.TitleBar;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseActivity extends AppCompatActivity {
    public Context mContext;
    public TitleBar mTitleBar;
    private FrameLayout mContainer;
    private TextView txTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWondow();
        initBefore();
        //缺点就是多了两层嵌套，优点就是再也不用写标题了，但是需要配合的一点是需要在清单文件中使用
        //使用lable标签来设置页面的标题，同时如何需要右边的按钮，那么们需要自己手动在代码里设置
        setContentView(R.layout.activity_base);
        mTitleBar = findViewById(R.id.mTitleBar);
        if (mTitleBar != null) {
            mTitleBar.setTitle(getTitle().toString());
            mContainer = findViewById(R.id.mContainer);
            txTitle = findViewById(R.id.txTitle);
//        1. 如果root为null，attachToRoot将失去作用，设置任何值都没有意义。
//        2. 如果root不为null，attachToRoot设为true，则会给加载的布局文件的指定一个父布局，即root。
//        3. 如果root不为null，attachToRoot设为false，则会将布局文件最外层的所有layout属性进行设置，当该view被添加到父view当中时，这些layout属性会自动生效。
            LayoutInflater.from(this).inflate(getLayoutId(), mContainer);
            mContext = this;
            initView();
            initData();
            initListener();
            ActivityManager.getDefault().onCreate(this);
        }
    }

    private void initWondow() {
        if (Build.VERSION.SDK_INT >= 21) {
//            StatusBarCompat.setStatusBarColor(this, Color.parseColor("#6A6363"));
        }
 /*        }
        if (StatusBarUtils.isFlyme()) {
            StatusBarUtils.FlymeSetStatusBarLightMode(getWindow(), true);
        } else {
       if (StatusBarUtils.isMIUI()) {
            StatusBarUtils.MIUISetStatusBarLightMode(getWindow(), true);
        } else */
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected abstract void initBefore();

    //用来在做网络请求的时候封装的数据
    public Map<String, Object> mObjectMap = new HashMap<String, Object>();

    //数据解析用的

    //请求网络的时候的进度条样式
    public static Dialog dialog;


    public abstract int getLayoutId();

    public abstract void initView();

    public abstract void initListener();

    public abstract void initData();


    public void initParams() {
        //初始化加载网络的数据
        mObjectMap.clear();
//        mObjectMap.put("token", token);
        mObjectMap.put("app", "android");
//        mObjectMap.put("version", UIUtils.getVersionCode());
    }

    /**
     * 短Toast 弹窗
     *
     * @param
     */
    public void showToast(final String content) {
        ToastUtils.showToast(content);
    }

    public void showToastLong(final String s) {
        ToastUtils.showToastLong(s);
    }

    public void showToastCenter(final String s) {
        ToastUtils.showToastCenter(s);
    }

    public void showTimeToast(long time, final String s) {
        ToastUtils.showTimeToast(time, s);
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivity(Class<E> activity) {
        Intent intent = new Intent(this, activity);
        startActivity(intent);
        //overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivity(Class<E> activity, Bundle extras) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(extras);
        startActivity(intent);
        //overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }

    /**
     * activity跳转(返回结果)
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivityForResult(Class<E> activity, Bundle extras,
                                        int requestCode) {
        Intent intent = new Intent(this, activity);
        intent.putExtras(extras);
        startActivityForResult(intent, requestCode);
        //overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }

    /**
     * activity跳转(返回结果)
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivityForResult(Class<E> activity,
                                        int requestCode) {
        Intent intent = new Intent(this, activity);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void setFinishView(View view) {
        if (view == null)
            return;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 关闭软键盘
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        if (mEditText == null || mContext == null)
            return;
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        Class<EditText> cls = EditText.class;
        try {
            Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
            setShowSoftInputOnFocus.setAccessible(false);
            setShowSoftInputOnFocus.invoke(mEditText, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 打卡软键盘
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public static void hideKeyboard(Activity activity) {
        if (activity == null)
            return;
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (activity.getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (activity.getCurrentFocus() != null)
                inputManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            Class<EditText> cls = EditText.class;
            try {
                Method setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(false);
                setShowSoftInputOnFocus.invoke(activity.getCurrentFocus(), false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //设置标题，计量从清单文件里面进行设置标题！
    public void setTitle(String text) {
        if (txTitle != null)
            txTitle.setText(text);
    }
}
