package com.lv.http.test

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.lv.http.test.net.LoadingSubscriber
import com.lv.http.test.net.RetrofitClient
import com.lv.http.test.net.WidgetInterface
import com.lv.http.test.util.DLog
import com.lv.http.test.util.intoCompositeSubscription
import com.lv.http.test.util.io2main
import kotlinx.android.synthetic.main.activity_main.*
import rx.subscriptions.CompositeSubscription

class MainActivity : AppCompatActivity(), WidgetInterface {

    private var compositeSubscription: CompositeSubscription? = CompositeSubscription()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DLog.init()
        RetrofitClient
                .getApiInterface()
                .getNewest()
                .io2main()
                .subscribe(LoadingSubscriber(this,{
                    onSuccess {
                        DLog.d(it)
                        dd.text=it.toString()
                    }
                    onFinish { 
                        DLog.d(">>>>>")
                    }
                    onFailure { 
                        DLog.d(it)
                    }
                }))
                .intoCompositeSubscription(compositeSubscription)
    }


    override fun showLoadingView() {
    }

    override fun hideLoadingView() {
    }

    override fun toastMessage(message: String?) {
        message?.let {
            Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
        }
    }
}
