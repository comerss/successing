package com.comers.successing

import android.widget.TextView
import com.comers.baselibrary.base.BaseActivity
import com.comers.baselibrary.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by Comers on 2017/10/13.
 */
class KotlinActivity : BaseActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initView() {
        findViewById<TextView>(R.id.rxExercise)
        rxExercise.setOnClickListener {
            toActivity(HomeActivity::class.java)
        }
    }

    override fun initData() {
        ToastUtils.showToast("hhhhhhhh")
    }

    override fun initListener() {
        initView()
    }
}