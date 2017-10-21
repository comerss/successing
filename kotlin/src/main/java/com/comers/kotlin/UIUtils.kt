package com.comers.kotlin

import android.content.Context

/**
 * Created by Comers on 2017/10/16.
 */

class UIUtils{
    fun getContext():Context?{
        return GlobalApplication.context
    }
    companion object  {
        fun getContext():Context?{
            return GlobalApplication.context
        }
    }
}

