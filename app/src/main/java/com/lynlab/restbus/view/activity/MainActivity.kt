package com.lynlab.restbus.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import android.widget.Toast
import com.lynlab.restbus.R
import com.lynlab.restbus.api.RestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    private val restApi: RestApi = RestApi()

    private var helloTextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setText()
    }

    /**
     * 서버에서 헬로 월드 메시지를 불러와 화면상에 띄운다.
     */
    private fun setText() {
        helloTextView = findViewById(R.id.textview_main_hello) as TextView
        restApi.getHello()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { response -> helloTextView!!.text = response },
                        { _ -> Toast.makeText(this, R.string.toast_error_network, Toast.LENGTH_SHORT).show() }
                )
    }

}
