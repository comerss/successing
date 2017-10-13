package com.comers.baselibrary.base

import android.app.Application
import android.content.Context

/**
 * Created by Comers on 2017/10/13.
 */

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
    companion object {
        var mContext : Context=GlobalApplication()
    }
}
