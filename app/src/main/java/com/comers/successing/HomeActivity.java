package com.comers.successing;

import android.view.View;

import com.comers.baselibrary.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    @Override
    protected void initBefore() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {
        mTitleBar.setRight("保存", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //执行保存的逻辑，别人的总是最好的！
            }
        });
    }

    @Override
    public void initData() {

    }
}
