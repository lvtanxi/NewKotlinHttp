package com.lv.http.test.net

import com.lv.http.test.model.UpdateBean
import retrofit2.http.POST
import rx.Observable

/**
 * Date: 2017-06-21
 * Time: 15:18
 * Description:
 */
interface ApiInterface {
    @POST("app/getNewest")
    fun getNewest():Observable<UpdateBean>
}