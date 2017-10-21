package com.comers.kotlin

import android.view.View
import com.comers.baselibrary.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Comers on 2017/10/13.
 */
class KotlinActivity: BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getLayoutId():Int {
        return R.layout.activity_main
    }

    override fun initView() {
        rxExercise.setText("我是Kotlin")
    }

    override fun initData() {
        initListener()
    }

    override fun initListener() {
        val fooClass =  KotlinActivity::class.java

    }
}