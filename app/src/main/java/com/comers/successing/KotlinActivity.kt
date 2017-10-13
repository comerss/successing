package com.comers.successing

import android.widget.TextView
import com.comers.baselibrary.base.BaseActivity

/**
 * Created by Comers on 2017/10/13.
 */
class KotlinActivity: BaseActivity() {
    override fun getLayoutId():Int {
        return R.layout.activity_home
    }

    override fun initView() {
        findViewById<TextView>(R.id.rxExercise)
    }

    override fun initData() {
        initListener()
    }

    override fun initListener() {
        initView()
    }
}