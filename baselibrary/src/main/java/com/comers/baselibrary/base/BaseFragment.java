package com.comers.baselibrary.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.comers.basic.utils.ConstantsPool;
import com.comers.basic.utils.SharedUtils;
import com.comers.basic.utils.ToastUtils;
import com.comers.basic.utils.UIUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by lq on 2017/4/22.
 * 作者：栗启
 * 作用：Fragment的基类
 */

public abstract class BaseFragment extends Fragment {

    public View rootView;
    public String token  = "";
    public Map<String, Object> mObjectMap = new HashMap<String, Object>();
    Context mContext;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initBefore();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutID=getLayoutId();
        rootView = inflater.inflate(layoutID,null);
        token  = SharedUtils.get(getActivity(), ConstantsPool.TOKEN, "");
        initView();
        initData();
        initListener();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mContext=getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    public void initParams() {
        //初始化加载网络的数据
        mObjectMap.clear();
        mObjectMap.put("token", token);
        mObjectMap.put("app","android");
        mObjectMap.put("version", UIUtils.getVersionCode());
    }
    protected abstract void initBefore();
    //获取数据
    protected abstract int getLayoutId();

    public abstract void initView();

    public abstract void initData();
    //初始化头部
    protected abstract void initListener();


    public void showTimeToast(long time, final String s) {
        ToastUtils.showTimeToast(time,s);
    }
    /**
     * 短Toast 弹窗
     *
     * @param s
     */
    public void showToast(final String s) {
        ToastUtils.showToast(s);
//        Toast.makeText(OldBaseActivity.this, s, Toast.LENGTH_SHORT).show();
    }
    public void showToastCenter(final String s) {
        ToastUtils.showToastCenter(s);
//        Toast.makeText(OldBaseActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivity(Class<E> activity) {
        Intent intent = new Intent(getContext(), activity);
        startActivity(intent);
//        getActivity().overridePendingTransition(R.anim.anim_enter,R.anim.anim_exit);
    }

    /**
     * activity跳转
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivity(Class<E> activity, Bundle extras) {
        Intent intent = new Intent(getContext(), activity);
        intent.putExtras(extras);
        startActivity(intent);
//        getActivity().overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
    }

    /**
     * activity跳转(返回结果)
     *
     * @param activity
     * @param <E>
     */
    public <E> void toActivityForResult(Class<E> activity, Bundle extras,
                                        int requestCode) {
        Intent intent = new Intent(getContext(), activity);
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
        Intent intent = new Intent(getContext(), activity);
        startActivityForResult(intent, requestCode);
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
}
