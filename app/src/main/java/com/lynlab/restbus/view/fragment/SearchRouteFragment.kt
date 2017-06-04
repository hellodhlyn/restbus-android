package com.lynlab.restbus.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.lynlab.restbus.R
import com.lynlab.restbus.api.RestApi
import com.lynlab.restbus.view.adapter.SearchRouteRecyclerViewAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

/**
 * 노선 검색 프래그먼트
 */
class SearchRouteFragment(private val nextPageSubject: PublishSubject<Any?>) : Fragment() {

    private val restApi = RestApi.instance
    private val recyclerViewAdapter = SearchRouteRecyclerViewAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_search_route, container, false)

        // RecyclerView 설정
        val recyclerView = view.findViewById(R.id.recycler_view_search_route) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = recyclerViewAdapter

        setOnClickAction()

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
     * 아이템을 선택했을 때의 동작을 설정한다.
     */
    fun setOnClickAction() {
        recyclerViewAdapter.getOnItemClickObservable().subscribe({ routeId ->
            nextPageSubject.onNext(routeId)
        })
    }

    /**
     * 검색을 요청, 처리한다.
     */
    fun requestRoutes(query: String) {
        restApi.getRoutes(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { routes ->
                            recyclerViewAdapter.setItems(routes)
                            recyclerViewAdapter.notifyDataSetChanged()
                        },
                        { _ ->
                            Toast.makeText(context, R.string.toast_error_network, Toast.LENGTH_SHORT).show()
                        }
                )
    }

}