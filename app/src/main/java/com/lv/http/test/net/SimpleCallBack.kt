package com.lv.http.test.net

/**
 * Date: 2017-06-21
 * Time: 16:29
 * Description:
 */
open class SimpleCallBack<T> {
    private var _onSuccess: ((t: T) -> Unit)? = null

    fun onSuccess(listener: (t: T) -> Unit) {
        _onSuccess = listener
    }

    fun callSuccess(t: T) = _onSuccess?.invoke(t)



    private var _onFailure: ((code: Int) -> Unit)? = null

    fun callFailure(code: Int) = _onFailure?.invoke(code)

    fun onFailure(listener: (code: Int) -> Unit) {
        _onFailure = listener
    }



    private var _onFinish: (() -> Unit)? = null

    fun callFinish() = _onFinish?.invoke()

    fun onFinish(listener: () -> Unit) {
        _onFinish = listener
    }

}