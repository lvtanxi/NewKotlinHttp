package com.lv.http.test.util

import com.lv.http.test.BuildConfig
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy

/**
 * Date: 2017-10-27
 * Time: 16:49
 * Description:
 */
object DLog {
    fun init(){
        val formatStrategy = PrettyFormatStrategy
                .newBuilder()
                .tag("lvtanxi")
                .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }

    fun d(any: Any?) {
        if (BuildConfig.DEBUG) {
            val message = any ?: "打印了空消息"
            Logger.d(message.toString())
        }

    }
}