package com.comers.kotlin

import android.app.Application
import android.content.Context

/**
 * Created by Comers on 2017/10/16.
 */

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = this
    }

    companion object {
        var context: Context? = null
            internal set
    }
}
