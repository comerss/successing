package com.comers.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       var kotlinDemo: KolinDemo= KolinDemo();
        kotlinDemo.traverse()
    }
}
