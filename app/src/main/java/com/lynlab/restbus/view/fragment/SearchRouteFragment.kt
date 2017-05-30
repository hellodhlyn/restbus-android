package com.lynlab.restbus.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.lynlab.restbus.R
import com.lynlab.restbus.api.RestApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * 노선 검색 프래그먼트
 */
class SearchRouteFragment : Fragment() {

    private var restApi = RestApi()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_route, container, false)

        // 검색 Observable 추가
        val searchEditText = view.findViewById(R.id.edittext_search_route) as EditText
        val observable: Observable<String> = Observable.create({ emitter ->
            emitter.onNext(searchEditText.text.toString())
        })

        // 검색 Subscriber 설정
        val searchButton = view.findViewById(R.id.button_search_route) as Button
        searchButton.setOnClickListener({ observable.subscribe({ query -> requestRoutes(query) }) })

        return view
    }

    /**
     * 검색을 요청, 처리한다.
     */
    fun requestRoutes(query: String) {
        restApi.getRoutes(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { routes -> Toast.makeText(context, "${routes.size} result(s)", Toast.LENGTH_SHORT).show() },
                        { _ -> Toast.makeText(context, R.string.toast_error_network, Toast.LENGTH_SHORT).show() }
                )
    }

}