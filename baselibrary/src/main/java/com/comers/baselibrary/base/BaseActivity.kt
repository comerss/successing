package com.comers.baselibrary.base

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.comers.baselibrary.utils.ToastUtils

/**
 * Created by Comers on 2017/10/9.
 */

abstract open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initView()
        initData()
        initListener()
    }

    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()
    abstract fun initListener()
    fun showToast(string: String) {
        ToastUtils.showToast(string)
    }

    fun <T> toActivity(clazz: Class<T>) {
        var intent = Intent(this, clazz)
        startActivity(intent)
    }
}
