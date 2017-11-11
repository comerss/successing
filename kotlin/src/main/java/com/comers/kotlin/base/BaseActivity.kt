package com.comers.kotlin.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by Comers on 2017/11/10.
 * ${DESCRIPTION}
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)}

    abstract fun getLayoutId(): Int
}