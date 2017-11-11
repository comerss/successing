package com.comers.kotlin

import android.os.Handler
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.comers.baselibrary.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Comers on 2017/10/13.
 */
class KotlinActivity: BaseActivity(), View.OnClickListener {
    var txTest:TextView?=null
    override fun onClick(v: View?) {

    }

    override fun getLayoutId():Int {
        return R.layout.activity_main
    }

    override fun initView() {
        rxExercise.setText("我是Kotlin")
    }

    override fun initData() {
    }
    internal var mHandler = Handler()
    override fun initListener() {
        val fooClass =  KotlinActivity::class.java
        mHandler.postDelayed({
            txTest?.setOnClickListener {
//                showToast("哈哈哈哈")
                Toast.makeText(this@KotlinActivity,"888888",Toast.LENGTH_SHORT).show()
            }
        }, 3000)

    }
}