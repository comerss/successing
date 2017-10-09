package com.comers.kotlin

/**
 * Created by Comers on 2017/10/9.
 */
class KolinDemo {

    //参数声明
    // -->可变参数
    val ar: Int = 0;
    var a = 5;
    var kai: Int = 0;
    var si = "jogaogoad";

    constructor()
    constructor(a: Int, kai: Int, si: String) {
        this.a = a
        this.kai = kai
        this.si = si
    }

    fun ad(a: Any): Int? {
        return null;
    }

    fun getStringLength(obj: Any): Int? {
        if (obj !is String) return null
        // `obj` 在这⼀分⽀⾃动转换为 `String`
        return obj.length
    }

    fun traverse() {
        val items = listOf("apple", "banana", "kiwi")
        for (item in items) {
            println(item)
        }
        for (index in items.indices) {
            println("item at $index is ${items[index]}")
        }
        for (inde in items.indices){
            println("$items[$inde]");
        }
        val x = 10
        val y = 9
        if (x in 1..y+1) {
            println("fits in range")
        }
        if(x in 3..y+23){
            println(x);
        }
        for (x in 1..10 step 2) {
            print(x)
        }
        //数列迭代
        for (x in 9 downTo 0 step 3) {
            print(x)
        }
    }
}