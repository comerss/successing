package com.comers.baselibrary.base;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by Comers on 2017/9/26.
 * 解决SDK》=24时显示的位置不正确的问题，目前只做了显示在 某个控件的下方的 适配
 * 如果是比较的小的弹框，显示在某个精确的位置还是需要自己写的
 * 主要 showAsDropDown（）
 */

public abstract class BasePopup extends PopupWindow {
    public Context mContext;
    private  View mRootView;

    public BasePopup(Context context) {
        super(context);
        init(context);
    }
    public BasePopup(Activity context){
        super(context);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        mRootView = getInflateView();
        setContentView(mRootView);
        initView(mRootView);
        setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        setFocusable(true);//设置获取焦点
        setTouchable(true);//设置可以触摸
        setOutsideTouchable(true);//设置外边可以点击
        setBackgroundDrawable(new BitmapDrawable());
        //设置点击事件的监听
        setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
                    //不在该界面的点击事件
                    dismiss();
                    return true;
                }
                return false;
            }
        });
    }

    public abstract View getInflateView();
    public abstract void initView(View rootView);
    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int h = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom;
            setHeight(h);
        }
        super.showAsDropDown(anchor, xoff, yoff);
    }
}
