package com.lv.http.test.net

import rx.Subscriber
import java.lang.ref.WeakReference
import java.net.ConnectException
import java.net.SocketTimeoutException

/**
 * Date: 2017-06-21
 * Time: 15:50
 * Description:
 */
open class LoadingSubscriber<T>(
        widgetInterface: WidgetInterface,
        listener: SimpleCallBack<T>.() -> Unit,
        private val showLoadingView: Boolean = true,
        private val toastMessgae: Boolean = true) : Subscriber<T>() {

    private val weakReference: WeakReference<WidgetInterface> = WeakReference(widgetInterface)
    private val callBack: SimpleCallBack<T> = bindSimpleCallBack(listener)


    override fun onStart() {
        if (showLoadingView)
            weakReference.get()?.showLoadingView()
    }

    override fun onNext(t: T) {
        callBack.callSuccess(t)
    }

    override fun onError(e: Throwable?) {
        e?.printStackTrace()
        var code = 0
        if (toastMessgae) {
            e?.let {
                val error: String?
                when (e) {
                    is ConnectException -> {
                        error = "服务器连接错误，请稍后重试。"
                        code = 1
                    }
                    is SocketTimeoutException -> {
                        error = "服务器连接超时，请稍后重试。"
                        code = 2
                    }
                    is HttpException -> {
                        code = e.code
                        error = e.message
                    }
                    else -> error = e.message
                }
                e.printStackTrace()
                weakReference.get()?.toastMessage(error)
            }
        }
        if (showLoadingView)
            weakReference.get()?.hideLoadingView()
        callBack.callFailure(code)
        callBack.callFinish()
    }

    override fun onCompleted() {
        if (showLoadingView)
            weakReference.get()?.hideLoadingView()
        callBack.callFinish()
    }

    private fun bindSimpleCallBack(listener: SimpleCallBack<T>.() -> Unit): SimpleCallBack<T> {
        val ca = SimpleCallBack<T>()
        ca.listener()
        return ca
    }
}
