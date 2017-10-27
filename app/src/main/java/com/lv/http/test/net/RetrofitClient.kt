package com.lv.http.test.net

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Date: 2017-06-21
 * Time: 15:15
 * Description:
 */
class RetrofitClient private constructor() {

    val apiInterface: ApiInterface

    init {
        val builder = OkHttpClient.Builder()
        builder
                .addNetworkInterceptor(TokenInterceptor())
                .addInterceptor(ProtocolInterceptor())
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)

        val retrofit = Retrofit.Builder()
                .baseUrl("http://10.13.3.42:12080/wmapp-server/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder.build())
                .build()

        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    companion object {

        private fun getInstance() = Hoder.instance

        fun getApiInterface() = getInstance().apiInterface
    }

    private object Hoder {
        val instance = RetrofitClient()
    }
}