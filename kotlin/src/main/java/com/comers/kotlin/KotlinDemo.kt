package com.comers.kotlin

/**
 * Created by Comers on 2017/10/16.
 */
data class JavaBean(var name: String)

data class KotlinTest(var name: String)
data class KotlinDemo(var sex: String, var age: Int) {
    var info: String = sex + age
}
