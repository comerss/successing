package com.comers.kotlin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var kotlinDemo: KolinDemo? = KolinDemo(5)
        val traverse = kotlinDemo?.traverse()
        kotlinDemo?.kai?.equals(kotlinDemo.kai)
        rxExercise.setOnClickListener {
            var intent = Intent(this, JavaActivity::class.java)
            startActivity(intent)
        }
        kotlinDemo.let { print("$it,我真的是it") }
        kotlinDemo.apply {
            kotlinDemo?.a = 89
            print(kotlinDemo?.a)
        }
        Log.i("Kotlin", "初始化Kotlin-----------")
        setData(null)
        KotlinDemo.context
        UIUtils.getContext()
        var  javabean=JavaBean("df")
        var kotlinTest=KotlinTest("hfaid")
    }

    fun setData(kotlindemo: KolinDemo?) {
        kotlindemo?.strs="ajoajosdgjoajd"
    }
}
