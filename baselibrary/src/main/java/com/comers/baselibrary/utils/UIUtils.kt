package com.comers.baselibrary.utils

import android.app.Activity
import android.content.Context
import android.text.InputType
import android.text.TextUtils
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import java.util.regex.Pattern

/**
 * Created by human on 2017/5/27.
 */
object UIUtils {

    /**
     * 从EditText获取文本
     */
    fun getText(editText: EditText): String {
        val content = editText.text.toString().trim { it <= ' ' }
        return if (TextUtils.isEmpty(content)) {
            ""
        } else {
            content
        }
    }

    /**
     * 从TexztView获取输入文本
     */
    fun getText(view: TextView): String {
        val content = view.text.toString().trim { it <= ' ' }
        return if (TextUtils.isEmpty(content)) {
            ""
        } else {
            content
        }
    }

    fun getFixUrl(url: String, map: Map<String, Any>): String {
        var url = url
        var json = ""
        for (key in map.keys) {
            if (map[key] != null) {
                if (json == "") {
                    if (null == map[key]) {
                        json = key + "="
                    } else {
                        json = key + "=" + map[key]
                    }
                } else {
                    if (null == map[key]) {
                        json = "$json&$key="
                    } else {
                        json = json + "&" + key + "=" + map[key]
                    }
                }
            }
        }
        if (!TextUtils.isEmpty(json)) {
            url = url + "?" + json
        }
        return url
    }

    /**
     * 关闭软键盘
     */
    fun closeKeybord(mEditText: EditText?, mContext: Context?) {
        if (mEditText == null || mContext == null)
            return
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
        val cls = EditText::class.java
        try {
            val setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", Boolean::class.javaPrimitiveType)
            setShowSoftInputOnFocus.isAccessible = false
            setShowSoftInputOnFocus.invoke(mEditText, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 打卡软键盘
     */
    fun openKeybord(mEditText: EditText, mContext: Context) {
        val imm = mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }


    fun hideKeyboard(activity: Activity?) {
        if (activity == null)
            return
        val inputManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (activity.window.attributes.softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (activity.currentFocus != null)
                inputManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken,
                        InputMethodManager.HIDE_NOT_ALWAYS)
            val cls = EditText::class.java
            try {
                val setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", Boolean::class.javaPrimitiveType)
                setShowSoftInputOnFocus.isAccessible = false
                setShowSoftInputOnFocus.invoke(activity.currentFocus, false)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }


    fun getVersionCode(context: Context): String? {
        try {
            val pkName = context.packageName
            return context
                    .packageManager.getPackageInfo(pkName, 0).versionName
        } catch (e: Exception) {
        }

        return null
    }

    fun isMobile(mobiles: String): Boolean {

        val p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(17[7,0-9])|(18[0-3,5-9]))\\d{8}$")

        val m = p.matcher(mobiles)

        return m.matches()

    }

    fun setText(editText: EditText, text: String) {
        if (!TextUtils.isEmpty(text)) {
            editText.setText(text)
        }
    }

    fun setText(editText: EditText, textShow: String, defaultText: String) {
        if (!TextUtils.isEmpty(textShow)) {
            editText.setText(textShow)
        } else {
            editText.setText(defaultText)
        }
    }

    fun setText(editText: TextView, textShow: String, defaultText: String) {
        if (!TextUtils.isEmpty(textShow)) {
            editText.text = textShow
        } else {
            editText.text = defaultText
        }
    }

    fun setText(editText: TextView, text: String) {
        if (!TextUtils.isEmpty(text)) {
            editText.text = text
        }
    }

    fun setSex(txPatientSex: TextView, patientSex: String) {
        if (TextUtils.isEmpty(patientSex)) {
            return
        }
        if (TextUtils.equals(patientSex, "M")) {
            txPatientSex.text = "男"
        } else if (TextUtils.equals(patientSex, "F")) {
            txPatientSex.text = "女"
        }
    }

    fun hideKeyboard(editText: EditText) {
        val cls = EditText::class.java
        val inType = editText.inputType
        try {
            val setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", Boolean::class.javaPrimitiveType)
            setShowSoftInputOnFocus.isAccessible = false
            setShowSoftInputOnFocus.invoke(editText, false)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        editText.inputType = InputType.TYPE_NULL
        editText.inputType = inType
    }

}
